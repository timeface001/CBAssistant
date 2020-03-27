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
                int i = 0;
                Map<String, List<ProductItemVar>> pVars = new HashMap<>();
                UploadRequest request = new UploadRequest();
                UploadItem item = new UploadItem();

                List<UploadItem> items = new ArrayList<>();
                System.out.println("publish product start.....");

                for (Map.Entry<String, UploadServiceRequest.SplitRequest> entry : sr.getLanguageList().entrySet()) {

                    Map<String, ProductAmzUpload> skuMap = new HashMap<>();
                    UploadItem languageItem = new UploadItem();
                    int index = 0;
                    for (ProductAmzUpload product : entry.getValue().getList()) {

                        List<ProductItemVar> vars = productSkuTypeService.selectListByProductId(product.getProductAmzId());
                        for (ProductItemVar vat : vars) {
                            if (vat.getPrice() == null) {
                                System.out.println("product:" + product.getId() + " sku price is null");
                                if (index == 0) {
                                    languageItem = null;
                                }
                                break;
                            }
                        }
                        index++;
                        pVars.put(product.getId(), vars);
                        UploadItem mid = null;


                        if (vars.size() == 1) {
                            mid = uploadSingleProductStr(product, vars);
                            skuMap.put(product.getItemSku() + "-" + vars.get(0).getSku(), product);
                        } else if(vars.size()>1){
                            //翻译变体
                            //translate(vars, entry.getKey());
                            mid = uploadMutiProductStr(product, vars);
                            for (ProductItemVar va : vars) {
                                skuMap.put(product.getItemSku() + "-" + va.getSku(), product);
                            }
                        }

                        if (mid == null) {
                            return;
                        }

                        item.setInventoryStr(mid.getInventoryStr());
                        item.setImageStr(mid.getImageStr());

                        languageItem.setShop(entry.getValue().getShopReq());
                        languageItem.setProductStr(mid.getProductStr());
                        languageItem.setRelationsStr(mid.getRelationsStr());

                        languageItem.setPriceStr(AmzXmlTemplate.getUploadPriceStr(product,entry.getValue().getShopReq(), vars));

                        //request.setSkuMap(skuMap);
                        //变更状态为发布中
                        ProductAmzUpload update = new ProductAmzUpload();
                        update.setPublishStatus(PublishStatusEnum.PROCESS.getVal());
                        update.setId(product.getId());
                        productAmzUploadDao.updateByPrimaryKeySelective(update);
                        productManagerService.updateClaimProduct(PublishStatusEnum.PROCESS, product.getProductAmzId());
                    }
                    languageItem.setProductMap(skuMap);
                    languageItem.setProducts(entry.getValue().getList());
                    if (languageItem != null) {
                        items.add(languageItem);
                    }
                }


                request.setProducts(sr.getProducts());

                item.setShop(sr.getShop());
                try {

                    System.out.println("上传文本如下");

                    request.setShop(sr.getShop());


                    List<String> submitIds = new ArrayList<>();
                    System.out.println("请求上传商品主体....");
                    Map<String, Map<String, ProductAmzUpload>> submitMap = new HashMap<>();
                    Map<String, List<ProductAmzUpload>> submitList = new HashMap<>();
                    for (UploadItem item1 : items) {
                        System.out.println();
                        System.out.println(item1.getProductStrHead());

                        FileInputStream productIs = new FileInputStream(FileUtils.byte2File(item1.getProductStrHead().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "product_fee.txt"));
                        ResponseDto<String> feeDto = getUploadResult(getService(sr.getShop()), getSubmitFeedRequest(productIs, sr, item1.getShop().getMarketIds(), AmzFeeType.PRODUCT_FEED));
                        i++;
                        if (!feeDto.isSuccess()) {
                            for (ProductAmzUpload upload : item1.getProducts()) {
                                ProductAmzUpload update = new ProductAmzUpload();
                                update.setPublishStatus(PublishStatusEnum.FAILED.getVal());
                                update.setUploadDesc(feeDto.getMsg());
                                update.setId(upload.getId());
                                productAmzUploadDao.updateByPrimaryKeySelective(update);
                                productManagerService.updateClaimProduct(PublishStatusEnum.FAILED, upload.getProductAmzId());
                                sr.getUpdateIds().add(upload.getId());
                            }
                            continue;
                        }


                        submitIds.add(feeDto.getData());
                        submitMap.put(feeDto.getData(), item1.getProductMap());
                        submitList.put(feeDto.getData(), item1.getProducts());

                    }

                    System.out.println("sids:"+ JSON.toJSONString(submitIds));


                    request.setSkuMap(submitMap);
                    request.setSubmitProducts(submitList);
                    request.setUpdateIds(sr.getUpdateIds());
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //if (mid.isSuccess()) {
                    System.out.println("商品主题上传结束。。。");

                    System.out.println("上传价格信息:");
                  /*  for (Map.Entry<String, UploadServiceRequest.SplitRequest> entry : sr.getExrateList().entrySet()) {
                        String priceStr = "";
                        for (ProductAmzUpload pp : entry.getValue().getList()) {
                            priceStr = priceStr + AmzXmlTemplate.getUploadPriceStr(pp, entry.getValue().getShopReq(), pVars.get(pp.getId()));
                        }
                        priceStr = AmzXmlTemplate.addUploadPriceStrHead(priceStr, sr.getShop());
                        System.out.println("上传价格xml:" + priceStr);
                        FileInputStream priceIs = new FileInputStream(FileUtils.byte2File(priceStr.getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "price_fee.txt"));
                        new UploadTask(getService(sr.getShop()), getSubmitFeedRequest(priceIs, sr, entry.getValue().getShopReq().getMarketIds(), AmzFeeType.PRICING_FEED), AmzFeeType.PRICING_FEED).run();
                        i++;
                    }*/

                    for (UploadItem item1 : items) {
                        if (StringUtils.isNotBlank(item1.getPriceStr())) {
                            //System.out.println(item1.getPriceStr());
                            i++;
                            FileInputStream priceIs = new FileInputStream(FileUtils.byte2File(item1.getPriceStr().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "product_price.txt"));
                            new UploadTask(getService(sr.getShop()), getSubmitFeedRequest(priceIs, sr, item1.getShop().getMarketIds(), AmzFeeType.PRICING_FEED), AmzFeeType.PRICING_FEED).run();
                        }
                    }


                    System.out.println("开始上传库存xml:");
                    //System.out.println("开始上传库存xml:" + item.getInventoryStrHead());
                    FileInputStream inventoryIs = new FileInputStream(FileUtils.byte2File(item.getInventoryStrHead().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "inventory_fee.txt"));
                    new UploadTask(getService(sr.getShop()), getSubmitFeedRequest(inventoryIs, sr, AmzFeeType.INVENTORY_FEED), AmzFeeType.INVENTORY_FEED).run();
                    i++;

                    System.out.println("开始上传关系信息:");
                    for (UploadItem item1 : items) {
                        ResponseDto<String> relationDto;
                        if (!item1.isRelationEmpty()) {
                            //System.out.println(item1.getRelationsStrHead());
                            i++;
                            FileInputStream relationIs = new FileInputStream(FileUtils.byte2File(item1.getRelationsStrHead().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "product_relationship.txt"));
                            new UploadTask(getService(sr.getShop()), getSubmitFeedRequest(relationIs, sr, item1.getShop().getMarketIds(), AmzFeeType.RELATIONSHIPS_FEED), AmzFeeType.RELATIONSHIPS_FEED).run();
                        }
                    }

                    System.out.println("开始上传图片信息:");
                    //System.out.println("图片xml:" + item.getImageStrHead());
                    FileInputStream imageIs = new FileInputStream(FileUtils.byte2File(item.getImageStrHead().getBytes(), commonSet.getAmzUploadProductPath(), UUID.randomUUID() + "image_fee.txt"));
                    new UploadTask(getService(sr.getShop()), getSubmitFeedRequest(imageIs, sr, AmzFeeType.PRODUCT_IMAGES_FEED), AmzFeeType.PRODUCT_IMAGES_FEED).run();
                    i++;

                    request.setSubmitIds(submitIds);
                    getFeedSubResult(request);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("publish product end.....times:"+i);

            }

            /*private void translate(List<ProductItemVar> vars, String key) {
                if (vars.size() > 1) {
                    CountryCodeEnum cc = CountryCodeEnum.sign(key);
                    for (ProductItemVar var : vars) {
                        String color = GeneralUtils.translate(var.getColorName(), "uk", cc);
                        String size = GeneralUtils.translate(var.getSizeName(), "uk", cc);
                        var.setMaterialType(GeneralUtils.translate(var.getMaterialType(), "uk", cc));
                        var.setColorName(color);
                        var.setColorMap(color);
                        var.setSizeName(size);
                        var.setSizeMap(size);
                    }
                }
            }*/
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
        request.setMWSAuthToken(shop.getAuthToken());
        request.setFeedType(feeType.getVal());
        return request;
    }

    public void getFeedSubResult(UploadRequest req) {

        System.out.println("等待160秒获取上传结果。。。。。。");


        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ResponseDto<String> dto = new ResponseDto();
        long wait = 30000;
        try {
            List<String> submitIds = req.getSubmitIds();
            GetFeedSubmissionListRequest request = new GetFeedSubmissionListRequest();
            request.setMerchant(req.getShop().getMerchantId());
            request.setFeedSubmissionIdList(new IdList(submitIds));
            request.setMWSAuthToken(req.getShop().getAuthToken());
            int i = 0;
            boolean isDone;
            do {
                GetFeedSubmissionListResponse response = getService(req.getShop()).getFeedSubmissionList(request);
                isDone = getFeedResult(response);
                i++;
                try {
                    Thread.sleep(wait);
                    System.out.println("GetFeedSubmissionListRequest:第" + i + "个30秒");
                    wait = wait + 30000;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("is Done:" + isDone);
            } while (!isDone && i < 20);

            String errorMsg = "";
            boolean isSuccess = true;
            if (isDone) {

                for (String submitId : submitIds) {
                    List<String> errorList = new ArrayList<>();
                    Map<String, ProductAmzUpload> errorMap = new HashMap<>();
                    GetFeedSubmissionResultRequest resultRequest = new GetFeedSubmissionResultRequest();
                    resultRequest.setMerchant(req.getShop().getMerchantId());
                    resultRequest.setMWSAuthToken(req.getShop().getAuthToken());
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
                        ProductAmzUpload result = getErrorMsg(res, req.getSkuMap().get(submitId));
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


                    boolean isAllError = StringUtils.isNotBlank(dto.getMsg());
                    System.out.println("异常结果:"+JSON.toJSONString(errorMap,true));
                    for (Map.Entry<String, ProductAmzUpload> entry : errorMap.entrySet()) {
                        //变更状态为发布失败
                        if (entry.getKey() != null) {
                            ProductAmzUpload update = new ProductAmzUpload();
                            update.setPublishStatus(PublishStatusEnum.FAILED.getVal());
                            update.setUploadDesc(entry.getValue().getUploadDesc());
                            update.setId(entry.getKey());
                            productAmzUploadDao.updateByPrimaryKeySelective(update);
                            productManagerService.updateClaimProduct(PublishStatusEnum.FAILED, entry.getValue().getProductAmzId());

                            errorList.add(entry.getKey());
                            //isAllError = false;
                        }
                    }

                    for (ProductAmzUpload id : req.getSubmitProducts().get(submitId)) {
                        if (GeneralUtils.isNotNullOrEmpty(req.getUpdateIds()) && req.getUpdateIds().contains(id.getId())) {
                            continue;
                        }

                        //变更状态为发布成功
                        if (isAllError) {
                            ProductAmzUpload update = new ProductAmzUpload();
                            if (GeneralUtils.isNotNullOrEmpty(errorList) && !errorList.contains(id.getId())) {
                                update.setUploadDesc("由于其他商品数据错误引起批量发布失败");
                            } else {
                                update.setUploadDesc(dto.getMsg());
                            }

                            update.setPublishStatus(PublishStatusEnum.FAILED.getVal());
                            update.setId(id.getId());
                            productAmzUploadDao.updateByPrimaryKeySelective(update);
                            productManagerService.updateClaimProduct(PublishStatusEnum.FAILED, id.getProductAmzId());
                        } else {

                            ProductAmzUpload update = new ProductAmzUpload();
                            update.setPublishStatus(PublishStatusEnum.SUCCESS.getVal());
                            update.setId(id.getId());
                            productAmzUploadDao.updateByPrimaryKeySelective(update);
                            productManagerService.updateClaimProduct(PublishStatusEnum.SUCCESS, id.getProductAmzId());
                        }
                    }
                }


            } else {
                for (String submitId : submitIds) {
                    for (ProductAmzUpload id : req.getSubmitProducts().get(submitId)) {
                        ProductAmzUpload update = new ProductAmzUpload();
                        update.setPublishStatus(PublishStatusEnum.FAILED.getVal());
                        update.setUploadDesc("超长时间请求亚马逊返回结果失败");
                        update.setId(id.getId());
                        productAmzUploadDao.updateByPrimaryKeySelective(update);
                        productManagerService.updateClaimProduct(PublishStatusEnum.FAILED, id.getProductAmzId());
                    }
                }
            }


            dto.setSuccess(isSuccess);
            dto.setMsg(errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public ProductAmzUpload getErrorMsg(JSONObject json, Map<String,ProductAmzUpload> skuMap) {
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
                            sku=sku.substring(1, sku.length() - 1);

                            upload = skuMap.containsKey(sku) ? skuMap.get(sku) : new ProductAmzUpload();
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

            ex.printStackTrace();
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

    class UploadTask implements Runnable {

        private MarketplaceWebService service;

        private SubmitFeedRequest request;

        private AmzFeeType feeType;

        @Override
        public void run() {
            String key = feeType.getDesc();
            System.out.println(key + "上传接口");
            ResponseDto dto = getUploadResult(service, request);

            System.out.println(key + "submitId:" + dto.getData());
        }

        public UploadTask(MarketplaceWebService service, SubmitFeedRequest request, AmzFeeType feeType) {
            this.service = service;
            this.request = request;
            this.feeType = feeType;
        }
    }
}
