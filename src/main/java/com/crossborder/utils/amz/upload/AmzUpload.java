package com.crossborder.utils.amz.upload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.*;
import com.crossborder.dao.ProductIdGenDao;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.entity.ProductIdGen;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.util.*;

@Component
public class AmzUpload {

    @Autowired
    private CommonSet commonSet;
    @Autowired
    private ProductIdGenDao productIdGenDao;

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

    public ResponseDto uploadProduct(ProductAmzUpload product, Map<String, Object> shop, List<ProductItemVar> vars) {

        if (vars.size() == 1) {
            return uploadSingleProduct(product, shop, vars);
        } else {


            ResponseDto result = uploadMutiProduct(product, shop, vars);

            return result;
        }
    }

    public ResponseDto uploadSingleProduct(final ProductAmzUpload product, final Map<String, Object> shop, final List<ProductItemVar> vars) {


        ResponseDto result = new ResponseDto();
        ProductIdGen gen = productIdGenDao.selectProductIdForUseOne(null, GeneralUtils.getUserId());
        if (gen == null) {
            System.out.println("UPC库中没有可用ID...");
            result.setMsg("UPC库中没有可用ID");
            result.setCode("001");
            result.setSuccess(false);
            return result;
        }
        final ResponseDto<String> dto;
            product.setExternalProductId(gen.getProductId());
            product.setExternalProductIdType(gen.getType());
        FileInputStream productIs = AmzXmlTemplate.uploadProduct(product, shop, commonSet.getAmzUploadProductPath(), vars.get(0), true);
        dto = getUploadResult(getService(shop), getSubmitFeedRequest(productIs, shop, AmzFeeType.PRODUCT_FEED));

            productIdGenDao.updateUsed(gen.getType(), product.getId(), gen.getProductId());

        new Thread(new Runnable() {
            @Override
            public void run() {
                FileInputStream inventoryIs = AmzXmlTemplate.uploadInventory(product, shop, commonSet.getAmzUploadProductPath(), vars);
                FileInputStream priceIs = AmzXmlTemplate.uploadPrice(product, shop, commonSet.getAmzUploadProductPath(), vars);
                FileInputStream imIs = AmzXmlTemplate.uploadImage(product, shop, commonSet.getAmzUploadProductPath(), vars, commonSet.getProductImagePath());

                System.out.println("开始请求。。。");


                System.out.println("等待20秒");
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ResponseDto dto1 = null;
                int i = 0;
                do {
                    dto1 = getFeedSubResult(shop, dto.getData());
                    i++;
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第" + i + "次请求。。。。");
                } while (!dto1.isSuccess() && i < 3);
                System.out.println(JSON.toJSONString(dto1));
                System.out.println("上传其他信息");
                getUploadResult(getService(shop), getSubmitFeedRequest(inventoryIs, shop, AmzFeeType.INVENTORY_FEED));
                getUploadResult(getService(shop), getSubmitFeedRequest(priceIs, shop, AmzFeeType.PRICING_FEED));
                getUploadResult(getService(shop), getSubmitFeedRequest(imIs, shop, AmzFeeType.PRODUCT_IMAGES_FEED));
            }
        }).start();

        result.setData(dto.getData());
        result.setSuccess(true);
        return result;
    }

    public ResponseDto uploadMutiProduct(ProductAmzUpload product, Map<String, Object> shop, List<ProductItemVar> vars) {

        ResponseDto result = new ResponseDto();
        FileInputStream inventoryIs = AmzXmlTemplate.uploadInventory(product, shop, commonSet.getAmzUploadProductPath(), vars);
        FileInputStream priceIs = AmzXmlTemplate.uploadPrice(product, shop, commonSet.getAmzUploadProductPath(), vars);
        FileInputStream imIs = AmzXmlTemplate.uploadImage(product, shop, commonSet.getAmzUploadProductPath(), vars,commonSet.getProductImagePath());
        FileInputStream relations = AmzXmlTemplate.uploadRelationShop(product, shop, commonSet.getAmzUploadProductPath(), vars);

        List<ResponseDto> resList = new ArrayList<>();
        for (ProductItemVar var : vars) {
            ProductIdGen gen = productIdGenDao.selectProductIdForUseOne(null, GeneralUtils.getUserId());
            if (gen != null) {
                product.setExternalProductId(gen.getProductId());
                product.setExternalProductIdType(gen.getType());
                FileInputStream productIs = AmzXmlTemplate.uploadProduct(product, shop, commonSet.getAmzUploadProductPath(), var, false);
                resList.add(getUploadResult(getService(shop), getSubmitFeedRequest(productIs, shop, AmzFeeType.PRODUCT_FEED)));
                productIdGenDao.updateUsed(gen.getType(), product.getId(), gen.getProductId());
            } else {
                result.setMsg("UPC库中没有可用ID");
                result.setCode("001");
                result.setSuccess(false);
                return result;
            }
        }


        resList.add(getUploadResult(getService(shop), getSubmitFeedRequest(inventoryIs, shop, AmzFeeType.INVENTORY_FEED)));
        resList.add(getUploadResult(getService(shop), getSubmitFeedRequest(priceIs, shop, AmzFeeType.PRICING_FEED)));
        resList.add(getUploadResult(getService(shop), getSubmitFeedRequest(imIs, shop, AmzFeeType.PRODUCT_IMAGES_FEED)));
        resList.add(getUploadResult(getService(shop), getSubmitFeedRequest(relations, shop, AmzFeeType.RELATIONSHIPS_FEED)));

        String str = "";
        for (ResponseDto dto : resList) {
            str += dto.getData() + ",";
        }
        result.setData(str);
        result.setSuccess(true);
        return result;
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

    public ResponseDto uploadProductRelations(ProductAmzUpload product, Map<String, Object> shop, List<ProductItemVar> vars) {

        FileInputStream fis = AmzXmlTemplate.uploadRelationShop(product, shop, commonSet.getAmzUploadProductPath(), vars);
        SubmitFeedRequest request = getRequest(shop, AmzFeeType.RELATIONSHIPS_FEED);
        try {
            request.setFeedContent(fis);
            request.setContentMD5(MD5.computeContentMD5HeaderValue(fis));

        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseDto result = getUploadResult(getService(shop), request);


        return result;
    }

    private SubmitFeedRequest getRequest(Map<String, Object> shop, AmzFeeType feeType) {
        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(shop.get("MERCHANT_ID").toString());
        request.setMarketplaceIdList(new IdList(Arrays.asList(
                shop.get("MARKETPLACEID").toString())));

        request.setFeedType(feeType.getVal());
        return request;
    }

    public ResponseDto<String> getFeedSubResult(Map<String, Object> shop, String submitId) {
        GetFeedSubmissionListRequest request = new GetFeedSubmissionListRequest();
        request.setMerchant(shop.get("MERCHANT_ID").toString());
        request.setFeedSubmissionIdList(new IdList(Arrays.asList(submitId)));
        ResponseDto<String> dto = new ResponseDto();
        int i = 0;
        try {
            GetFeedSubmissionListResponse response = getService(shop).getFeedSubmissionList(request);
            boolean isDone;
            do {
                isDone = getFeedResult(response);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!isDone && i < 3);

            if (isDone) {

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
                        dto.setSuccess(false);
                    } else {
                        dto.setSuccess(true);
                    }
                } catch (JDOMException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


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
                if (reports.getJSONObject(k).getString("StatusCode").equals("[Complete]")) {
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
        XMLGregorianCalendar startDate = df
                .newXMLGregorianCalendar(new GregorianCalendar(2018, 1, 1));
        request.setStartDate(startDate);

        System.out.println("***************");

        System.out.println("shopId:"+shop.get("SHOP_ID"));
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

            System.out.println("REQUEST_ID:"+reportRequestId);
            if (reportRequestId != null) {
                String reportId = null;
                int i = 0;
                do {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reportId = getCategoryReportId(shop, merchantId, reportRequestId);
                    i++;
                    System.out.println("第" + i + "次:  获取reportId-- " + reportId);
                } while (reportId == null && i < 6);

                System.out.println("REPORTID:"+reportId);
                //reportId="9497489579017662";
                if (reportId != null) {
                    GetReportRequest requestpp = new GetReportRequest();
                    requestpp.setMerchant(merchantId);

                    requestpp.setReportId(reportId);
                    try {
                        requestpp.setReportOutputStream(new FileOutputStream(path));

                        GetReportResponse respon = service.getReport(requestpp);
                        System.out.println(respon.isSetGetReportResult());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }


            }


        } catch (MarketplaceWebServiceException e) {
            e.printStackTrace();
        }
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
}
