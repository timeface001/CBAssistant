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
import org.springframework.transaction.annotation.Transactional;

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

    private MarketplaceWebService getService(UploadServiceRequest.ShopReq shop) {
        final String accessKeyId = shop.getAccessKey();
        final String secretAccessKey = shop.getSecretKey();

        final String appName = "";
        final String appVersion = "";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        config.setServiceURL(shop.getServiceUrl());
        return new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);
    }


    @Transactional
    public void bathUploadProduct(final UploadServiceRequest sr) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, List<ProductItemVar>> pVars = new HashMap<>();
                UploadRequest request = new UploadRequest();
                UploadItem item = new UploadItem();

                List<UploadItem> items = new ArrayList<>();
                System.out.println("publish product start.....");
                Map<String, ProductAmzUpload> skuMap = new HashMap<>();

                for (Map.Entry<String, UploadServiceRequest.SplitRequest> entry : sr.getLanguageList().entrySet()) {

                    UploadItem languageItem = new UploadItem();
                    for (ProductAmzUpload product : entry.getValue().getList()) {
                        List<ProductItemVar> vars = productSkuTypeService.selectListByProductId(product.getProductAmzId());
                        pVars.put(product.getId(), vars);
                        UploadItem mid = new UploadItem();
                        if (vars.size() == 1) {
                            mid = uploadSingleProductStr(product, vars);
                            skuMap.put(product.getItemSku() + "-" + vars.get(0).getSku(), product);
                        } else {
                            mid = uploadMutiProductStr(product, vars);
                            for (ProductItemVar va : vars) {
                                skuMap.put(product.getItemSku() + "-" + va.getSku(), product);
                            }
                        }

                        if (mid == null) {
                            return;
                        }

                        languageItem.setShop(entry.getValue().getShopReq());
                        languageItem.setProductStr(mid.getProductStr());

                        items.add(languageItem);

                        request.setSkuMap(skuMap);
                        //变更状态为发布中
                        ProductAmzUpload update = new ProductAmzUpload();
                        update.setPublishStatus(PublishStatusEnum.PROCESS.getVal());
                        update.setId(product.getId());
                        productAmzUploadDao.updateByPrimaryKeySelective(update);
                        productManagerService.updateClaimProduct(PublishStatusEnum.PROCESS, product.getProductAmzId());
                    }
                }


                request.setProducts(sr.getProducts());

                item.setShop(sr.getShop());
                try {

                    System.out.println("上传文本如下");

                    System.out.println();
                    System.out.println(item.getImageStrHead());
                    //System.out.println(item.getPriceStr());
                    System.out.println();
                    System.out.println(item.getInventoryStrHead());
                    System.out.println();
                    System.out.println(item.getRelationsStrHead());


                    FileInputStream imageIs = new FileInputStream(FileUtils.byte2File(item.getImageStrHead().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "image_fee.txt"));
                    FileInputStream inventoryIs = new FileInputStream(FileUtils.byte2File(item.getInventoryStrHead().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "inventory_fee.txt"));


                    request.setShop(sr.getShop());
                    FileInputStream relationIs = null;
                    if (!item.isRelationEmpty()) {
                        relationIs = new FileInputStream(FileUtils.byte2File(item.getRelationsStrHead().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "relations_fee.txt"));
                    }

                    List<String> submitIds = new ArrayList<>();
                    System.out.println("请求上传商品主体....");
                    for (UploadItem item1 : items) {
                        System.out.println();
                        System.out.println(item1.getProductStrHead());

                        FileInputStream productIs = new FileInputStream(FileUtils.byte2File(item1.getProductStrHead().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "product_fee.txt"));
                        ResponseDto<String> feeDto = getUploadResult(getService(sr.getShop()), getSubmitFeedRequest(productIs, sr, item1.getShop().getMarketIds(), AmzFeeType.PRODUCT_FEED));

                        if (!feeDto.isSuccess()) {
                            for (ProductAmzUpload upload : sr.getProducts()) {
                                ProductAmzUpload update = new ProductAmzUpload();
                                update.setPublishStatus(PublishStatusEnum.FAILED.getVal());
                                update.setUploadDesc(feeDto.getMsg());
                                update.setId(upload.getId());
                                productAmzUploadDao.updateByPrimaryKeySelective(update);
                                productManagerService.updateClaimProduct(PublishStatusEnum.FAILED, upload.getProductAmzId());
                            }
                            continue;
                        }

                        submitIds.add(feeDto.getData());
                    }


                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //if (mid.isSuccess()) {
                    System.out.println("商品主题上传成功。。。");


                    ResponseDto<String> imageDto = getUploadResult(getService(sr.getShop()), getSubmitFeedRequest(imageIs, sr, AmzFeeType.PRODUCT_IMAGES_FEED));
                    if(imageDto.getData()!=null){
                        //submitIds.add(imageDto.getData());
                    }
                    System.out.println("上传图片信息:" + imageDto.getData());

                    ResponseDto<String> inventoryDto = getUploadResult(getService(sr.getShop()), getSubmitFeedRequest(inventoryIs, sr, AmzFeeType.INVENTORY_FEED));
                    //submitIds.add(inventoryDto.getData());
                    System.out.println("上传库存信息:" + inventoryDto.getData());

                    for (Map.Entry<String, UploadServiceRequest.SplitRequest> entry : sr.getExrateList().entrySet()) {
                        String priceStr = "";
                        for (ProductAmzUpload pp : entry.getValue().getList()) {
                            priceStr = priceStr + AmzXmlTemplate.getUploadPriceStr(pp, entry.getValue().getShopReq(), pVars.get(pp.getId()));
                        }
                        priceStr = AmzXmlTemplate.addUploadPriceStrHead(priceStr, sr.getShop());
                        System.out.println("价格xml:" + priceStr);
                        FileInputStream priceIs = new FileInputStream(FileUtils.byte2File(priceStr.getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "price_fee.txt"));
                        ResponseDto<String> priceDto = getUploadResult(getService(sr.getShop()), getSubmitFeedRequest(priceIs, sr, entry.getValue().getShopReq().getMarketIds(), AmzFeeType.PRICING_FEED));
                        //submitIds.add(priceDto.getData());
                        System.out.println("上传价格信息:" + priceDto.getData());
                    }

                    ResponseDto<String> relationDto;
                    if (relationIs != null) {
                        relationDto = getUploadResult(getService(sr.getShop()), getSubmitFeedRequest(relationIs, sr, AmzFeeType.RELATIONSHIPS_FEED));
                        System.out.println("上传关系信息:" + relationDto.getData());
                    }

                    request.setSubmitIds(submitIds);
                    getFeedSubResult(request);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("publish product end.....");

            }
        }).start();

    }


    private SubmitFeedRequest getSubmitFeedRequest(FileInputStream fis, UploadServiceRequest sr, AmzFeeType type) {

        SubmitFeedRequest request = getRequest(sr.getShop(), type);
        try {
            request.setFeedContent(fis);
            request.setContentMD5(MD5.computeContentMD5HeaderValue(fis));
            request.setMarketplaceIdList(new IdList(sr.getMarketIds()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    private SubmitFeedRequest getSubmitFeedRequest(FileInputStream fis, UploadServiceRequest sr, Set<String> marketIds, AmzFeeType type) {

        SubmitFeedRequest request = getRequest(sr.getShop(), type);
        try {
            request.setFeedContent(fis);
            request.setContentMD5(MD5.computeContentMD5HeaderValue(fis));
            request.setMarketplaceIdList(new IdList(new ArrayList<String>(marketIds)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }


    private SubmitFeedRequest getRequest(UploadServiceRequest.ShopReq shop, AmzFeeType feeType) {
        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(shop.getMerchantId());

        request.setFeedType(feeType.getVal());
        return request;
    }

    public void getFeedSubResult(UploadRequest req) {

        System.out.println("等待160秒获取上传结果。。。。。。");


        try {
            Thread.sleep(160000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ResponseDto<String> dto = new ResponseDto();
        try {
            List<String> submitIds = req.getSubmitIds();
            GetFeedSubmissionListRequest request = new GetFeedSubmissionListRequest();
            request.setMerchant(req.getShop().getMerchantId());
            request.setFeedSubmissionIdList(new IdList(submitIds));
            int i = 0;
            boolean isDone;
            do {
                GetFeedSubmissionListResponse response = getService(req.getShop()).getFeedSubmissionList(request);
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

                Map<String, ProductAmzUpload> errorMap = new HashMap<>();
                List<String> errorList = new ArrayList<>();
                for (String submitId : submitIds) {
                    GetFeedSubmissionResultRequest resultRequest = new GetFeedSubmissionResultRequest();
                    resultRequest.setMerchant(req.getShop().getMerchantId());

                    resultRequest.setFeedSubmissionId(submitId);

                    OutputStream processingResult = null;
                    String path = commonSet.getAmzUploadProductPath() + submitId + "feedSubmissionResult.xml";
                    try {
                        processingResult = new FileOutputStream(path);
                        resultRequest.setFeedSubmissionResultOutputStream(processingResult);
                        getService(req.getShop()).getFeedSubmissionResult(resultRequest);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject res = XmlUtil.xml2JSON(FileUtils.File2byte(new File(path)));
                        System.out.println(JSON.toJSONString(res));
                        ProductAmzUpload result = getErrorMsg(res, req);
                        dto.setMsg(result.getUploadDesc());
                        System.out.println("submitId:" + submitId + ",result:" + JSON.toJSONString(result, true));
                        if (StringUtils.isNotBlank(dto.getMsg())) {
                            if (errorMap.containsKey(result.getId())) {
                                errorMap.get(result.getId()).setUploadDesc(errorMap.get(result.getId()).getUploadDesc() + "," + result.getUploadDesc());
                            } else {
                                errorMap.put(result.getId(), result);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        dto.setMsg(e.getMessage());
                        isSuccess = false;
                    }
                }

                System.out.println("异常结果:"+JSON.toJSONString(errorMap,true));
                for (Map.Entry<String, ProductAmzUpload> entry : errorMap.entrySet()) {
                    //变更状态为发布失败
                    ProductAmzUpload update = new ProductAmzUpload();
                    update.setPublishStatus(PublishStatusEnum.FAILED.getVal());
                    update.setUploadDesc(entry.getValue().getUploadDesc());
                    update.setId(entry.getKey());
                    productAmzUploadDao.updateByPrimaryKeySelective(update);
                    productManagerService.updateClaimProduct(PublishStatusEnum.FAILED, entry.getValue().getProductAmzId());

                    errorList.add(entry.getKey());
                }

                for (ProductAmzUpload id : req.getProducts()) {
                    //变更状态为发布成功
                    if (!errorList.contains(id.getId())) {
                        ProductAmzUpload update = new ProductAmzUpload();
                        update.setPublishStatus(PublishStatusEnum.SUCCESS.getVal());
                        update.setId(id.getId());
                        productAmzUploadDao.updateByPrimaryKeySelective(update);
                        productManagerService.updateClaimProduct(PublishStatusEnum.SUCCESS, id.getProductAmzId());
                    }
                }

            }


            dto.setSuccess(isSuccess);
            dto.setMsg(errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public ProductAmzUpload getErrorMsg(JSONObject json, UploadRequest req) {
        String str = "";
        JSONArray array = json.getJSONObject("AmazonEnvelope").getJSONArray("Message");
        ProductAmzUpload upload = new ProductAmzUpload();
        for (int i = 0; i < array.size(); i++) {
            JSONArray reports = array.getJSONObject(i).getJSONArray("ProcessingReport");
            for (int k = 0; k < reports.size(); k++) {
                JSONObject report = reports.getJSONObject(k);
                if (report.getString("StatusCode").equals("[Complete]") && report.getJSONArray("ProcessingSummary").getJSONObject(0).getString("MessagesWithError").equals("[0]")) {
                    return new ProductAmzUpload();
                }

                JSONArray list = reports.getJSONObject(k).getJSONArray("Result");
                //暂时只获取错误信息第一条作为提示
                boolean isSkuInfo = false;
                String commonMsg = "";
                for (int j = 0; j < list.size(); j++) {
                    JSONObject object = list.getJSONObject(j);
                    String sku = "";
                    if (object.containsKey("AdditionalInfo")) {
                        sku = object.getJSONArray("AdditionalInfo").getJSONObject(0).getString("SKU");
                    }

                    if (object.getString("ResultCode").equals("[Error]")) {
                        if (StringUtils.isNotBlank(sku)) {
                            upload = req.getUploadResponse(sku.substring(1, sku.length() - 1));
                            upload.setUploadDesc(object.getString("ResultDescription") + ",");
                            break;
                        }
                        upload.setUploadDesc(object.getString("ResultDescription") + ",");
                    }
                }
            }
        }
        return upload;
    }

    private boolean getFeedResult(GetFeedSubmissionListResponse response) {
        boolean isDone = true;
        if (response.isSetGetFeedSubmissionListResult()) {
            GetFeedSubmissionListResult getFeedSubmissionListResult = response.getGetFeedSubmissionListResult();
            List<FeedSubmissionInfo> feedSubmissionInfoList = getFeedSubmissionListResult.getFeedSubmissionInfoList();
            System.out.println(JSON.toJSONString(feedSubmissionInfoList));
            for (FeedSubmissionInfo feedSubmissionInfo : feedSubmissionInfoList) {

                isDone = feedSubmissionInfo.getFeedProcessingStatus().equals("_DONE_") && isDone;
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
       /* MarketplaceWebService service = getService(shop);

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
        *//*XMLGregorianCalendar startDate = df
                .newXMLGregorianCalendar(new GregorianCalendar(2018, 1, 1));
        request.setStartDate(startDate);*//*

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
        }*/
    }


    public UploadItem uploadSingleProductStr(final ProductAmzUpload product, final List<ProductItemVar> vars) {


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
                return null;
            }

            product.setExternalProductId(gen.getProductId());
            product.setExternalProductIdType(gen.getType());
        }

        final ResponseDto<String> dto;

        String productStr = AmzXmlTemplate.getUploadProductStr(product, vars.get(0), true);
        String inventoryStr = AmzXmlTemplate.getUploadInventoryStr(product, vars);
        //String priceStr = AmzXmlTemplate.getUploadPriceStr(product, shop, vars);
        String imageStr = AmzXmlTemplate.getUploadImageStr(product, commonSet.getProductImagePath(), vars);
        //dto = getUploadResult(getService(shop), getSubmitFeedRequest(productIs, shop, AmzFeeType.PRODUCT_FEED));

        UploadItem item = new UploadItem();
        item.setImageStr(imageStr);
        item.setInventoryStr(inventoryStr);
        item.setProductStr(productStr);
        item.setRelationsStr("");

        productIdGenDao.updateUsed(product.getExternalProductIdType(), product.getId(), product.getExternalProductId(), product.getItemSku() + "-" + product.getAmzSku());


        return item;
    }

    public UploadItem uploadMutiProductStr(ProductAmzUpload product, List<ProductItemVar> vars) {

        UploadItem uploadItem = new UploadItem();
        String inventoryIs = AmzXmlTemplate.getUploadInventoryStr(product, vars);
        //String priceIs = AmzXmlTemplate.getUploadPriceStr(product, shop, vars);
        String imIs = AmzXmlTemplate.getUploadImageStr(product, commonSet.getProductImagePath(), vars);
        String relations = AmzXmlTemplate.getUploadRelationsStr(product, vars);

        uploadItem.setInventoryStr(inventoryIs);
        //uploadItem.setPriceStr(priceIs);
        uploadItem.setImageStr(imIs);
        uploadItem.setRelationsStr(relations);

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
                            return null;
                        }
                        product.setExternalProductId(gen.getProductId());
                        product.setExternalProductIdType(gen.getType());
                    }

                    productIdGenDao.updateUsed(product.getExternalProductIdType(), product.getId(), product.getExternalProductId(), product.getItemSku() + "-" + var.getSku());
                }


            } else {
                product.setItemName(name);
            }

            String productIs = AmzXmlTemplate.getUploadProductStr(product, var, false);
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

        return uploadItem;
    }




    private String getCategoryReportId(Map<String, Object> shop, String merchantId, String reportRequestId) throws MarketplaceWebServiceException {

        /*GetReportListRequest requestqq = new GetReportListRequest();
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
        }*/
        return null;
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
