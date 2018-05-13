package com.crossborder.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.*;
import com.crossborder.entity.ProductAmzUpload;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@Component
public class AmzUpload {

    @Autowired
    private CommonSet commonSet;

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

    public ResponseDto uploadProduct(ProductAmzUpload product, Map<String, Object> shop) {

        FileInputStream fis = AmzXmlTemplate.uploadProduct(product, shop, commonSet.getAmzUploadProductPath());
        SubmitFeedRequest request = getRequest(shop, AmzFeeType.PRODUCT_FEED);
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
                String path = commonSet.getAmzUploadProductPath() + shop.get("SHOP_ID") + "feedSubmissionResult.xml";
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
                    dto.setSuccess(false);
                    dto.setMsg(getErrorMsg(res));
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


    public ResponseDto<String> getUploadResult(MarketplaceWebService service, SubmitFeedRequest request) {

        ResponseDto<String> dto = new ResponseDto();
        SubmitFeedResponse response = null;
        try {
            response = service.submitFeed(request);

            if (response.isSetSubmitFeedResult()) {
                System.out.print("        SubmitFeedResult");
                System.out.println();
                SubmitFeedResult submitFeedResult = response
                        .getSubmitFeedResult();
                if (submitFeedResult.isSetFeedSubmissionInfo()) {
                    System.out.print("            FeedSubmissionInfo");
                    System.out.println();
                    FeedSubmissionInfo feedSubmissionInfo = submitFeedResult
                            .getFeedSubmissionInfo();
                    if (feedSubmissionInfo.isSetFeedSubmissionId()) {
                        dto.setSuccess(true);
                        dto.setData(feedSubmissionInfo.getFeedSubmissionId());
                    }

                    if (feedSubmissionInfo.isSetFeedProcessingStatus()) {
                        System.out
                                .print("                FeedProcessingStatus");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedProcessingStatus());
                        System.out.println();
                    }

                }
            } else {
                dto.setSuccess(false);
                dto.setMsg("发布失败，系统原因");
            }

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
            RequestReportResponse response = service.requestReport(request);
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
                GetReportListRequest requestqq = new GetReportListRequest();
                requestqq.setMerchant(merchantId);
                TypeList typeList = new TypeList();
                typeList.setType(Arrays.asList("_GET_XML_BROWSE_TREE_DATA_"));
                requestqq.setReportTypeList(typeList);

                requestqq.setReportRequestIdList(new IdList(Arrays.asList(reportRequestId)));

                GetReportListResponse listReponse = getService(shop).getReportList(requestqq);

                if (listReponse.isSetGetReportListResult()) {

                    GetReportListResult getReportListResult = listReponse.getGetReportListResult();

                    java.util.List<ReportInfo> reportInfoListList = getReportListResult.getReportInfoList();
                    for (ReportInfo reportInfoList : reportInfoListList) {

                        if (reportInfoList.isSetReportId()) {
                            reportId = reportInfoList.getReportId();
                        }
                    }
                }

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
}
