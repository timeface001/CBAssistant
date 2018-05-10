package com.crossborder.utils;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.*;
import com.crossborder.entity.ProductAmzUpload;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
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

        String s = "/Users/fengsong/Downloads/rule_chain.txt";
        FileInputStream fis = AmzXmlTemplate.uploadProduct(product, shop);
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
