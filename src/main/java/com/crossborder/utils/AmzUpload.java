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

import java.io.*;
import java.util.Arrays;
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

        config.setServiceURL(shop.get("END_POINT").toString());

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

        ResponseDto result=getUploadResult(getService(shop),request);


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

    public static void main(String[] args) throws IOException, JDOMException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("/Users/fengsong/Downloads/rule_chain.txt"));
        SAXBuilder sb = new SAXBuilder();
        org.jdom2.Document doc = sb.build(isr);



        Element root = doc.getRootElement();
        List<Element> list= root.getChildren("Node");
        int i=0;
        for(Element e:list){
            i++;
            System.out.println(e.getChild("browseNodeName").getText());
        }
        System.out.println(i);
    }
}
