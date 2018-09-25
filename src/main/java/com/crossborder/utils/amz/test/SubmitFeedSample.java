/******************************************************************************* 
 *  Copyright 2009 Amazon Services.
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  
 *  You may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 *  CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 *  specific language governing permissions and limitations under the License.
 * ***************************************************************************** 
 *
 *  Marketplace Web Service Java Library
 *  API Version: 2009-01-01
 *  Generated: Wed Feb 18 13:28:48 PST 2009 
 * 
 */

package com.crossborder.utils.amz.test;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.mws.*;
import com.amazonaws.mws.model.*;
import com.crossborder.utils.FileUtils;
import com.crossborder.utils.amz.upload.AmzFeeType;

/**
 * 
 * Submit Feed Samples
 * 
 * 
 */
public class SubmitFeedSample {

    /**
     * Just add a few required parameters, and try the service Submit Feed
     * functionality
     * 
     * @param args
     *            unused
     */
    /**
     * @param args
     */
    public static void main(String... args) {

        /************************************************************************
         * Access Key ID and Secret Access Key ID, obtained from:
         * http://aws.amazon.com
         ***********************************************************************/
        final String accessKeyId = "AKIAIRTMKJ3L3DNCORYQ";
        final String secretAccessKey = "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";

        final String appName = "";
        final String appVersion = "";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();


        /************************************************************************
         * Uncomment to set the appropriate MWS endpoint.
         ************************************************************************/
        // US
         //config.setServiceURL("https://mws-eu.amazonservices.com");
        // UK
        // config.setServicDEFAULTL("https://mws.amazonservices.co.uk/");
        // Germany
         //config.setServiceURL("https://mws.amazonservices.de/");
        // France
        // config.setServicDEFAULTL("https://mws.amazonservices.fr/");
        // Italy
         config.setServiceURL("https://mws.amazonservices.it/");
        // Japan
        // config.setServicDEFAULTL("https://mws.amazonservices.jp/");
        // China
        // config.setServicDEFAULTL("https://mws.amazonservices.com.cn/");
        // Canada
        // config.setServicDEFAULTL("https://mws.amazonservices.ca/");
        // India
        // config.setServicDEFAULTL("https://mws.amazonservices.in/");

        /************************************************************************
         * You can also try advanced configuration options. Available options are:
         *
         *  - Signature Version
         *  - Proxy Host and Proxy Port
         *  - User Agent String to be sent to Marketplace Web Service
         *
         ***********************************************************************/

        /************************************************************************
         * Instantiate Http Client Implementation of Marketplace Web Service        
         ***********************************************************************/

        MarketplaceWebService service = new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);


        /************************************************************************
         * Setup request parameters and uncomment invoke to try out sample for
         * Submit Feed
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all
         * Marketplace Web Service calls.
         ***********************************************************************/
        final String merchantId = "A21WK1ZAUBHF10";
        final String sellerDevAuthToken = "<Merchant Developer MWS Auth Token>";
        // marketplaces to which this feed will be submitted; look at the
        // API reference document on the MWS website to see which marketplaces are
        // included if you do not specify the list yourself
        List<String> ids=new ArrayList<>();
        //ids.add("A13V1IB3VIYZZH");
        //ids.add("A1PA6795UKMFR9");
        ids.add("APJ6JRA9NG5V4");
        final IdList marketplaces = new IdList(ids);

        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(merchantId);
        //request.setMWSAuthToken(sellerDevAuthToken);
        request.setMarketplaceIdList(marketplaces);

        request.setFeedType(AmzFeeType.PRODUCT_IMAGES_FEED.getVal());

        String text="<?xml version=\"1.0\" encoding=\"utf-8\" ?><AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>\n" +
                "<DocumentVersion>1.01</DocumentVersion><MerchantIdentifier>A21WK1ZAUBHF10</MerchantIdentifier>\n" +
                "</Header>\n" +
                "<MessageType>ProductImage</MessageType><Message><MessageID>161800001</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370</SKU><ImageType>Main</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/156bdcf4-0ce9-49dd-a810-7f009e240d67.jpg</ImageLocation></ProductImage></Message><Message><MessageID>2</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370</SKU><ImageType>PT1</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/bf9c9265-7cad-42bd-930d-a2c919af33da.jpg</ImageLocation></ProductImage></Message><Message><MessageID>161800003</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-black</SKU><ImageType>Main</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/0d9673eb-3359-4762-a4a4-a334044d472d.jpg</ImageLocation></ProductImage></Message><Message><MessageID>4</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-black</SKU><ImageType>PT1</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/ab54c174-6c69-4360-a9b7-a33568a62825.jpg</ImageLocation></ProductImage></Message><Message><MessageID>5</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-black</SKU><ImageType>PT2</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/66c415a4-fd3c-4d6f-bfc5-22e8c9683da8.jpg</ImageLocation></ProductImage></Message><Message><MessageID>6</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-black</SKU><ImageType>PT3</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/bc5c2815-3357-4d10-9d8e-e919764b5e1e.jpg</ImageLocation></ProductImage></Message><Message><MessageID>7</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-black</SKU><ImageType>PT4</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/eb94f8d1-4e19-4bf0-8c31-762612db43ca.jpg</ImageLocation></ProductImage></Message><Message><MessageID>161800008</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-red</SKU><ImageType>Main</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/f54939b5-923a-4182-ad15-9bf1e1b2a0cf.jpg</ImageLocation></ProductImage></Message><Message><MessageID>9</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-red</SKU><ImageType>PT1</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/0db71da4-5260-45f7-a954-3f1aad176732.jpg</ImageLocation></ProductImage></Message><Message><MessageID>10</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-red</SKU><ImageType>PT2</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/e117949f-05e5-4c7c-bfce-44f5565fc75b.jpg</ImageLocation></ProductImage></Message><Message><MessageID>11</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-red</SKU><ImageType>PT3</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/4c051153-c9c3-4884-b83b-aa7d53deb959.jpg</ImageLocation></ProductImage></Message><Message><MessageID>12</MessageID><OperationType>Update</OperationType><ProductImage><SKU>SUO-3FOSD370-red</SKU><ImageType>PT4</ImageType><ImageLocation>http://cbassistant.oss-cn-beijing.aliyuncs.com/product/b0400b63-b2d9-4c7a-9433-f2d7b4b55547.jpg</ImageLocation></ProductImage></Message></AmazonEnvelope>";
        FileUtils.byte2File(text.getBytes(),"/Users/fengsong/Downloads/amz/","rule_chain.txt");

        String s="/Users/fengsong/Downloads/amz/rule_chain.txt";
        File f=new File(s);
        try {
            request.setFeedContent(new FileInputStream(f));
            request.setContentMD5(computeContentMD5HeaderValue(new FileInputStream(f)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //request.setContentType();

        invokeSubmitFeed(service, request);

    }

    public static String computeContentMD5HeaderValue( FileInputStream fis )
            throws IOException, NoSuchAlgorithmException {

        DigestInputStream dis = new DigestInputStream( fis,
                MessageDigest.getInstance( "MD5" ));

        byte[] buffer = new byte[8192];
        while( dis.read( buffer ) > 0 );

        String md5Content = new String(
                org.apache.commons.codec.binary.Base64.encodeBase64(
                        dis.getMessageDigest().digest()) );

        // Effectively resets the stream to be beginning of the file
        // via a FileChannel.
        fis.getChannel().position( 0 );

        return md5Content;
    }

    /**
     * Submit Feed request sample Uploads a file for processing together with
     * the necessary metadata to process the file, such as which type of feed it
     * is. PurgeAndReplace if true means that your existing e.g. inventory is
     * wiped out and replace with the contents of this feed - use with caution
     * (the DEFAULT is false).
     * 
     * @param service
     *            instance of MarketplaceWebService service
     * @param request
     *            Action to invoke
     */
    public static void invokeSubmitFeed(MarketplaceWebService service,
            SubmitFeedRequest request) {
        try {

            SubmitFeedResponse response = service.submitFeed(request);

            System.out.println("SubmitFeed Action Response");
            System.out
            .println("=============================================================================");
            System.out.println();

            System.out.print("    SubmitFeedResponse");
            System.out.println();
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
                        System.out.print("                FeedSubmissionId");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedSubmissionId());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedType()) {
                        System.out.print("                FeedType");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedType());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetSubmittedDate()) {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedProcessingStatus()) {
                        System.out
                        .print("                FeedProcessingStatus");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedProcessingStatus());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetStartedProcessingDate()) {
                        System.out
                        .print("                StartedProcessingDate");
                        System.out.println();
                        System.out
                        .print("                    "
                                + feedSubmissionInfo
                                .getStartedProcessingDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetCompletedProcessingDate()) {
                        System.out
                        .print("                CompletedProcessingDate");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo
                                .getCompletedProcessingDate());
                        System.out.println();
                    }
                }
            }
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata responseMetadata = response
                .getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                "
                            + responseMetadata.getRequestId());
                    System.out.println();
                }
            }
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();
            System.out.println();

        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
    }

}
