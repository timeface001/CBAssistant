package com.crossborder.utils.amz.upload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.*;
import com.crossborder.dao.ProductAmzUploadDao;
import com.crossborder.dao.ProductIdGenDao;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.entity.ProductIdGen;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ProductSkuTypeService;
import com.crossborder.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.*;
import java.util.*;

@Component
public class AmzUpload {

    @Autowired
    private CommonSet commonSet;
    @Autowired
    private ProductIdGenDao productIdGenDao;
    @Autowired
    private ProductAmzUploadDao productAmzUploadDao;
    @Autowired
    private ProductManagerService productManagerService;
    @Autowired
    private ProductSkuTypeService productSkuTypeService;

    private MarketplaceWebService getService(Map<String, Object> shop) {
        final String accessKeyId = shop.get("ACCESSKEY_ID").toString();
        final String secretAccessKey = shop.get("SECRET_KEY").toString();

        final String appName = "";
        final String appVersion = "";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        config.setServiceURL(shop.get("ENDPOINT").toString());
        return new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);
    }


    public void bathUploadProduct(final List<ProductAmzUpload> products, final Map<String, Object> shop) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                UploadItem item = new UploadItem();
                System.out.println("publish product start.....");
                for (ProductAmzUpload product : products) {
                    List<ProductItemVar> vars = productSkuTypeService.selectListByProductId(product.getProductAmzId());
                    if (vars.size() == 1) {
                        uploadSingleProductStr(product, shop, vars, item);
                    } else {
                        uploadMutiProductStr(product, shop, vars, item);
                    }
                    //变更状态为发布中
                    ProductAmzUpload update = new ProductAmzUpload();
                    update.setPublishStatus(PublishStatusEnum.PROCESS.getVal());
                    update.setId(product.getId());
                    productAmzUploadDao.updateByPrimaryKeySelective(update);
                    productManagerService.updateClaimProduct(PublishStatusEnum.PROCESS, product.getProductAmzId());
                }

                item.setShop(shop);
                try {

                    System.out.println("上传文本如下");
                    System.out.println(item.getProductStr());
                    System.out.println();
                    System.out.println(item.getImageStr());
                    System.out.println();
                    System.out.println(item.getPriceStr());
                    System.out.println();
                    System.out.println(item.getInventoryStr());
                    System.out.println();
                    System.out.println(item.getRelationsStr());

                    FileInputStream productIs = new FileInputStream(FileUtils.byte2File(item.getProductStr().getBytes(), commonSet.getAmzUploadProductPath(), GeneralUtils.cuurentDateStr() + "product_fee.txt"));
                    FileInputStream imageIs = new FileInputStream(FileUtils.byte2File(item.getImageStr().getBytes(), commonSet.getAmzUploadProductPath(), GeneralUtils.cuurentDateStr() + "image_fee.txt"));
                    FileInputStream inventoryIs = new FileInputStream(FileUtils.byte2File(item.getInventoryStr().getBytes(), commonSet.getAmzUploadProductPath(), GeneralUtils.cuurentDateStr() + "inventory_fee.txt"));
                    FileInputStream priceIs = new FileInputStream(FileUtils.byte2File(item.getPriceStr().getBytes(), commonSet.getAmzUploadProductPath(), GeneralUtils.cuurentDateStr() + "price_fee.txt"));


                    FileInputStream relationIs = null;
                    if (!item.isRelationEmpty()) {
                        relationIs = new FileInputStream(FileUtils.byte2File(item.getRelationsStr().getBytes(), commonSet.getAmzUploadProductPath(), GeneralUtils.cuurentDateStr() + "relations_fee.txt"));
                    }

                    ResponseDto<String> feeDto = getUploadResult(getService(shop), getSubmitFeedRequest(productIs, shop, AmzFeeType.PRODUCT_FEED));

                    List<String> submitIds = new ArrayList<>();

                    System.out.println("请求fee....");
                    System.out.println(JSON.toJSONString(feeDto));
                    //if (feeDto.isSuccess()) {
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //ResponseDto mid = uploadResult(shop, feeDto.getData(), "商品主体");
                    submitIds.add(feeDto.getData());

                    //if (mid.isSuccess()) {
                    System.out.println("商品主题上传成功。。。");


                    System.out.println("上传图片信息");
                    ResponseDto<String> imageDto = getUploadResult(getService(shop), getSubmitFeedRequest(imageIs, shop, AmzFeeType.PRODUCT_IMAGES_FEED));
                    submitIds.add(imageDto.getData());
                    System.out.println("上传库存信息");
                    ResponseDto<String> inventoryDto = getUploadResult(getService(shop), getSubmitFeedRequest(inventoryIs, shop, AmzFeeType.INVENTORY_FEED));
                    submitIds.add(inventoryDto.getData());

                    System.out.println("上传价格信息");
                    ResponseDto<String> priceDto = getUploadResult(getService(shop), getSubmitFeedRequest(priceIs, shop, AmzFeeType.PRICING_FEED));
                    submitIds.add(priceDto.getData());

                    ResponseDto<String> relationDto;
                    if (relationIs != null) {
                        System.out.println("上传关系信息");
                        relationDto = getUploadResult(getService(shop), getSubmitFeedRequest(relationIs, shop, AmzFeeType.RELATIONSHIPS_FEED));
                        submitIds.add(relationDto.getData());
                    }

                    ResponseDto responseDto = uploadResult(shop, submitIds, "商品结果");
                    if (responseDto.isSuccess()) {
                        for (ProductAmzUpload product : products) {
                            //变更状态为发布成功
                            ProductAmzUpload update = new ProductAmzUpload();
                            update.setPublishStatus(PublishStatusEnum.SUCCESS.getVal());
                            update.setId(product.getId());
                            productAmzUploadDao.updateByPrimaryKeySelective(update);
                            productManagerService.updateClaimProduct(PublishStatusEnum.SUCCESS, product.getProductAmzId());
                        }
                    } else {
                        System.out.println("上传失败");

                        for (ProductAmzUpload product : products) {
                            //变更状态为发布失败
                            ProductAmzUpload update = new ProductAmzUpload();
                            update.setPublishStatus(PublishStatusEnum.FAILED.getVal());
                            update.setId(product.getId());
                            productAmzUploadDao.updateByPrimaryKeySelective(update);
                            productManagerService.updateClaimProduct(PublishStatusEnum.SUCCESS, product.getProductAmzId());
                        }
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("publish product end.....");

            }
        }).start();

    }

    private ResponseDto uploadResult(Map<String, Object> shop, List<String> submitIds, String desc) {
        ResponseDto mid = null;
        int i = 0;
        System.out.println("等待10秒获取" + desc + "结果。。。。。。");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        do {

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mid = getFeedSubResult(shop, submitIds);
            i++;

            System.out.println("第" + i + "次请求。。。。");
        } while (!mid.isSuccess() && i < 10 && StringUtils.isBlank(mid.getMsg()));

        System.out.println("最后对象:" + JSON.toJSONString(mid));
        return mid;
    }

    private SubmitFeedRequest getSubmitFeedRequest(FileInputStream fis, Map<String, Object> shop, AmzFeeType type) {

        SubmitFeedRequest request = getRequest(shop, type);
        try {
            request.setFeedContent(fis);
            request.setContentMD5(MD5.computeContentMD5HeaderValue(fis));
            request.setMarketplaceIdList(new IdList(Arrays.asList(shop.get("MARKETPLACEID").toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }


    private SubmitFeedRequest getRequest(Map<String, Object> shop, AmzFeeType feeType) {
        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(shop.get("MERCHANT_ID").toString());
        request.setMarketplaceIdList(new IdList(Arrays.asList(
                shop.get("MARKETPLACEID").toString())));

        request.setFeedType(feeType.getVal());
        return request;
    }

    public ResponseDto<String> getFeedSubResult(Map<String, Object> shop, List<String> submitIds) {
        GetFeedSubmissionListRequest request = new GetFeedSubmissionListRequest();
        request.setMerchant(shop.get("MERCHANT_ID").toString());
        request.setFeedSubmissionIdList(new IdList(submitIds));
        ResponseDto<String> dto = new ResponseDto();
        int i = 0;
        try {
            boolean isDone;
            do {
                GetFeedSubmissionListResponse response = getService(shop).getFeedSubmissionList(request);
                isDone = getFeedResult(response);
                i++;
                try {
                    Thread.sleep(30000);
                    System.out.println("GetFeedSubmissionListRequest:第" + i + "个30秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("is Done:" + isDone);
            } while (!isDone && i < 20);

            String errorMsg = "";
            boolean isSuccess = true;
            if (isDone) {

                for (String submitId : submitIds) {
                    GetFeedSubmissionResultRequest resultRequest = new GetFeedSubmissionResultRequest();
                    resultRequest.setMerchant(shop.get("MERCHANT_ID").toString());

                    resultRequest.setFeedSubmissionId(submitId);

                    OutputStream processingResult = null;
                    String path = commonSet.getAmzUploadProductPath() + submitId + "feedSubmissionResult.xml";
                    try {
                        processingResult = new FileOutputStream(path);
                        resultRequest.setFeedSubmissionResultOutputStream(processingResult);
                        getService(shop).getFeedSubmissionResult(resultRequest);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject res = XmlUtil.xml2JSON(FileUtils.File2byte(new File(path)));
                        System.out.println(JSON.toJSONString(res));
                        dto.setMsg(getErrorMsg(res));
                        if (StringUtils.isNotBlank(dto.getMsg())) {
                            //isSuccess=isSuccess;
                        } else {
                            dto.setSuccess(true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        dto.setMsg(e.getMessage());
                        isSuccess = false;
                    }
                }

            }


            dto.setSuccess(isSuccess);
            dto.setMsg(errorMsg);
        } catch (MarketplaceWebServiceException e) {
            e.printStackTrace();
        }


        return dto;

    }

    public String getErrorMsg(JSONObject json) {
        String str = "";
        JSONArray array = json.getJSONObject("AmazonEnvelope").getJSONArray("Message");
        for (int i = 0; i < array.size(); i++) {
            JSONArray reports = array.getJSONObject(i).getJSONArray("ProcessingReport");
            for (int k = 0; k < reports.size(); k++) {
                JSONObject report = reports.getJSONObject(k);
                if (report.getString("StatusCode").equals("[Complete]") && report.getJSONArray("ProcessingSummary").getJSONObject(0).getString("MessagesWithError").equals("[0]")) {
                    return "";
                }
                JSONArray list = reports.getJSONObject(k).getJSONArray("Result");
                for (int j = 0; j < list.size(); j++) {
                    JSONObject object = list.getJSONObject(j);
                    if (object.getString("ResultCode").equals("[Error]")) {
                        str += object.getString("ResultDescription") + ",";
                    }
                }
            }
        }
        return StringUtils.isBlank(str) ? "" : str.substring(0, str.length() - 1);
    }

    private boolean getFeedResult(GetFeedSubmissionListResponse response) {
        boolean isDone = false;
        if (response.isSetGetFeedSubmissionListResult()) {
            GetFeedSubmissionListResult getFeedSubmissionListResult = response.getGetFeedSubmissionListResult();
            List<FeedSubmissionInfo> feedSubmissionInfoList = getFeedSubmissionListResult.getFeedSubmissionInfoList();
            System.out.println(JSON.toJSONString(feedSubmissionInfoList));
            for (FeedSubmissionInfo feedSubmissionInfo : feedSubmissionInfoList) {

                isDone = feedSubmissionInfo.getFeedProcessingStatus().equals("_DONE_");
            }
        }

        return isDone;
    }

    Logger logger = Logger.getLogger(AmzUpload.class);

    public ResponseDto<String> getUploadResult(MarketplaceWebService service, SubmitFeedRequest request) {

        ResponseDto<String> dto = new ResponseDto();
        SubmitFeedResponse response = null;
        try {
            response = service.submitFeed(request);

            logger.debug("submit fee......");
            if (response.isSetSubmitFeedResult()) {
                SubmitFeedResult submitFeedResult = response
                        .getSubmitFeedResult();
                logger.debug("submit result:" + JSON.toJSONString(submitFeedResult, true));
                if (submitFeedResult.isSetFeedSubmissionInfo()) {
                    FeedSubmissionInfo feedSubmissionInfo = submitFeedResult
                            .getFeedSubmissionInfo();
                    if (feedSubmissionInfo.isSetFeedSubmissionId()) {
                        dto.setSuccess(true);
                        dto.setData(feedSubmissionInfo.getFeedSubmissionId());
                    }

                }
            } else {
                dto.setSuccess(false);
                dto.setMsg("发布失败，系统原因");
            }

            logger.debug("submit feed end....");

        } catch (MarketplaceWebServiceException ex) {

            dto.setMsg(ex.getMessage());
            dto.setCode(ex.getErrorCode());
            dto.setSuccess(false);
        }

        return dto;

    }

    public void write(String path, Map<String, Object> shop) {
        MarketplaceWebService service = getService(shop);

        String merchantId = shop.get("MERCHANT_ID").toString();

        RequestReportRequest request = new RequestReportRequest()
                .withMerchant(shop.get("MERCHANT_ID").toString())
                .withMarketplaceIdList(new IdList(Arrays.asList(
                        shop.get("MARKETPLACEID").toString())))
                .withReportType("_GET_XML_BROWSE_TREE_DATA_")
                .withReportOptions("ShowSalesChannel=true");

        DatatypeFactory df = null;
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        /*XMLGregorianCalendar startDate = df
                .newXMLGregorianCalendar(new GregorianCalendar(2018, 1, 1));
        request.setStartDate(startDate);*/

        System.out.println("***************");

        System.out.println("shopId:" + shop.get("SHOP_ID"));
        try {
            RequestReportResponse response = getService(shop).requestReport(request);
            String reportRequestId = null;
            if (response.isSetRequestReportResult()) {
                RequestReportResult requestReportResult = response.getRequestReportResult();
                if (requestReportResult.isSetReportRequestInfo()) {
                    ReportRequestInfo reportRequestInfo = requestReportResult.getReportRequestInfo();
                    if (reportRequestInfo.isSetReportRequestId()) {
                        reportRequestId = reportRequestInfo.getReportRequestId();
                    }
                }
            }

            System.out.println("REQUEST_ID:" + reportRequestId);
            if (reportRequestId != null) {
                String reportId = null;
                int i = 0;
                do {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reportId = getCategoryReportId(shop, merchantId, reportRequestId);
                    i++;
                    System.out.println("第" + i + "次:  获取reportId-- " + reportId);
                } while (reportId == null && i < 6);

                System.out.println("REPORTID:" + reportId);
                //reportId="9497489579017662";
                long l1 = System.currentTimeMillis();
                if (reportId != null) {
                    GetReportRequest requestpp = new GetReportRequest();
                    requestpp.setMerchant(merchantId);

                    requestpp.setReportId(reportId);
                    System.out.println("请求开始");
                    try {
                        requestpp.setReportOutputStream(new FileOutputStream(path));

                        GetReportResponse respon = service.getReport(requestpp);
                        System.out.println(respon.isSetGetReportResult());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("请求结束，耗时" + (System.currentTimeMillis() - l1));
                }


            }


        } catch (MarketplaceWebServiceException e) {
            e.printStackTrace();
        }
    }


    public void uploadSingleProductStr(final ProductAmzUpload product, final Map<String, Object> shop, final List<ProductItemVar> vars, UploadItem item) {


        ProductIdGen gen = productIdGenDao.selectProductIdByAmzSku(product.getItemSku() + "-" + product.getAmzSku());
        if (gen != null) {
            product.setExternalProductId(gen.getProductId());
            product.setExternalProductIdType(gen.getType());
        }
        if (StringUtils.isBlank(product.getExternalProductId())) {
            gen = productIdGenDao.selectProductIdForUseOne(null, product.getUserId());
            if (gen == null) {
                System.out.println("UPC库中没有可用ID...");
                breakWhileUPCReason(product);
                return;
            }

            product.setExternalProductId(gen.getProductId());
            product.setExternalProductIdType(gen.getType());
        }

        final ResponseDto<String> dto;

        String productStr = AmzXmlTemplate.getUploadProductStr(product, shop, vars.get(0), true);
        String inventoryStr = AmzXmlTemplate.getUploadInventoryStr(product, vars);
        String priceStr = AmzXmlTemplate.getUploadPriceStr(product, shop, vars);
        String imageStr = AmzXmlTemplate.getUploadImageStr(product, shop, commonSet.getProductImagePath(), vars);
        //dto = getUploadResult(getService(shop), getSubmitFeedRequest(productIs, shop, AmzFeeType.PRODUCT_FEED));

        item.setImageStr(imageStr);
        item.setPriceStr(priceStr);
        item.setInventoryStr(inventoryStr);
        item.setProductStr(productStr);

        productIdGenDao.updateUsed(product.getExternalProductIdType(), product.getId(), product.getExternalProductId(), product.getItemSku() + "-" + product.getAmzSku());


    }

    public void uploadMutiProductStr(ProductAmzUpload product, Map<String, Object> shop, List<ProductItemVar> vars, UploadItem uploadItem) {

        String inventoryIs = AmzXmlTemplate.getUploadInventoryStr(product, vars);
        String priceIs = AmzXmlTemplate.getUploadPriceStr(product, shop, vars);
        String imIs = AmzXmlTemplate.getUploadImageStr(product, shop, commonSet.getProductImagePath(), vars);
        String relations = AmzXmlTemplate.getUploadRelationsStr(product, vars);

        uploadItem.setInventoryStr(inventoryIs);
        uploadItem.setPriceStr(priceIs);
        uploadItem.setImageStr(imIs);
        uploadItem.setRelationsStr(relations);

        List<ResponseDto> resList = new ArrayList<>();


        System.out.println("start to upload muti product......");
        String name = product.getItemName();
        for (ProductItemVar var : vars) {
            //如果是变体 标题是标题加上变体名称逗号拼接
            if (StringUtils.isNotBlank(var.getVariationType())) {
                product.setItemName(name + GeneralUtils.setProductTitle(var));

                ProductIdGen gen = null;
                //查询之前可用产品ID

                if (StringUtils.isBlank(product.getExternalProductId())) {
                    gen = productIdGenDao.selectProductIdByAmzSku(product.getItemSku() + "-" + var.getSku());
                    if (gen != null) {
                        product.setExternalProductId(gen.getProductId());
                        product.setExternalProductIdType(gen.getType());
                    }

                    if (product.getExternalProductId() == null) {
                        gen = productIdGenDao.selectProductIdForUseOne(null, product.getUserId());
                        if (gen == null) {
                            breakWhileUPCReason(product);
                            return;
                        }
                        product.setExternalProductId(gen.getProductId());
                        product.setExternalProductIdType(gen.getType());
                    }

                    productIdGenDao.updateUsed(product.getExternalProductIdType(), product.getId(), product.getExternalProductId(), product.getItemSku() + "-" + var.getSku());
                }


            } else {
                product.setItemName(name);
            }

            String productIs = AmzXmlTemplate.getUploadProductStr(product, shop, var, false);
            //ResponseDto<String> dto = getUploadResult(getService(shop), getSubmitFeedRequest(productIs, shop, AmzFeeType.PRODUCT_FEED));
            uploadItem.setProductStr(productIs);
            if (StringUtils.isNotBlank(var.getVariationType())) {
                productAmzUploadDao.updateBySku(product.getProductAmzId(), product.getAmzSku() + "-" + GeneralUtils.getVarValue(var), product.getExternalProductIdType(), product.getExternalProductId());
                product.setExternalProductIdType(null);
                product.setExternalProductId(null);
            }

            product.setItemName("");

        }

        product.setItemName(name);

    }




    private String getCategoryReportId(Map<String, Object> shop, String merchantId, String reportRequestId) throws MarketplaceWebServiceException {

        GetReportListRequest requestqq = new GetReportListRequest();
        requestqq.setMerchant(merchantId);
        TypeList typeList = new TypeList();
        typeList.setType(Arrays.asList("_GET_XML_BROWSE_TREE_DATA_"));
        requestqq.setReportTypeList(typeList);

        requestqq.setReportRequestIdList(new IdList(Arrays.asList(reportRequestId)));

        GetReportListResponse listReponse = getService(shop).getReportList(requestqq);

        if (listReponse.isSetGetReportListResult()) {

            GetReportListResult getReportListResult = listReponse.getGetReportListResult();

            List<ReportInfo> reportInfoListList = getReportListResult.getReportInfoList();
            for (ReportInfo reportInfoList : reportInfoListList) {

                if (reportInfoList.isSetReportId()) {
                    return reportInfoList.getReportId();
                }
            }
        }
        return null;
    }

    class UploadItem {
        private String productStr;
        private String imageStr;
        private String priceStr;
        private String inventoryStr;
        private String relationsStr;
        private Map<String, Object> shop;

        public Map<String, Object> getShop() {
            return shop;
        }

        public void setShop(Map<String, Object> shop) {
            this.shop = shop;
        }

        public String getProductStr() {
            return AmzXmlTemplate.addUploadProductStrHead(productStr, getShop());
            //return productStr;
        }

        public void setProductStr(String productStr) {
            this.productStr = this.productStr + productStr;
        }

        public String getImageStr() {
            return AmzXmlTemplate.addUploadImageStrHead(imageStr, getShop());
        }

        public void setImageStr(String imageStr) {
            this.imageStr = this.imageStr + imageStr;
        }

        public String getPriceStr() {
            return AmzXmlTemplate.addUploadPriceStrHead(priceStr, getShop());
        }

        public void setPriceStr(String priceStr) {
            this.priceStr = this.priceStr + priceStr;
        }

        public String getInventoryStr() {
            return AmzXmlTemplate.addUploadInventoryStrHead(inventoryStr, getShop());
        }

        public void setInventoryStr(String inventoryStr) {
            this.inventoryStr = this.inventoryStr + inventoryStr;
        }

        public boolean isRelationEmpty() {
            return StringUtils.isEmpty(this.relationsStr);
        }

        public String getRelationsStr() {
            return AmzXmlTemplate.addUploadRelationsStrHead(relationsStr, getShop());
        }

        public void setRelationsStr(String relationsStr) {
            this.relationsStr = this.relationsStr + relationsStr;
        }

        public UploadItem() {
            this.imageStr = "";
            this.productStr = "";
            this.inventoryStr = "";
            this.priceStr = "";
            this.relationsStr = "";
        }
    }

    private void breakWhileUPCReason(ProductAmzUpload product) {
        ProductAmzUpload updateUpload = new ProductAmzUpload();
        updateUpload.setPublishStatus(PublishStatusEnum.FAILED.getVal());
        updateUpload.setUpdateDelete("update");
        updateUpload.setPublishTime(new Date());
        updateUpload.setUploadDesc("UPC库中没有可用ID...");
        updateUpload.setId(product.getId());

        productManagerService.updateClaimProduct(PublishStatusEnum.FAILED, product.getProductAmzId());
        productAmzUploadDao.updateByPrimaryKeySelective(updateUpload);
    }
}
