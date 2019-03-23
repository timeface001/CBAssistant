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
        final String accessKeyId = "AKIAJIWEGLTH7K56E4SA";
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
         config.setServiceURL("https://mws.amazonservices.co.uk/");
        // Germany
         //config.setServiceURL("https://mws.amazonservices.de/");
        // France
        // config.setServicDEFAULTL("https://mws.amazonservices.fr/");
        // Italy
         //config.setServiceURL("https://mws.amazonservices.it/");
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
        final String sellerDevAuthToken = "amzn.mws.a8b309d8-2b57-8547-fac8-ae4d0bf06e70";
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
        request.setMWSAuthToken(sellerDevAuthToken);
        request.setMarketplaceIdList(marketplaces);

        request.setFeedType(AmzFeeType.PRODUCT_IMAGES_FEED.getVal());

        String text="<?xml version=\"1.0\" ?><AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header><DocumentVersion>1.01</DocumentVersion><MerchantIdentifier>A2RD88T826IBCC</MerchantIdentifier>\n" +
                "</Header><MessageType>Product</MessageType>\n" +
                "<PurgeAndReplace>false</PurgeAndReplace>" +
                "<Message><MessageID>6111120155301</MessageID>" +
                "<OperationType>Update</OperationType><Product><SKU>GZ-GG4W7JCT</SKU><ItemPackageQuantity>10</ItemPackageQuantity><DescriptionData>" +
                "<Title>Walking Stick Sticks Folding Elderly Health Women Personal Care Poles Telescopic014</Title><Brand>SY</Brand> <Description>&lt;p&gt;PRODUCT NAME: ELDERLY CRUTCHES&lt;br&gt;WEIGHT: 2KG&lt;br&gt;HEIGHT: ADJUSTABLE, 60CM-98CM&lt;br&gt;BODY MATERIAL: CARBON ROD, LIGHT AND BEAUTIFUL&lt;br&gt;THICKNESS: 1.2MM&lt;br&gt;SUITABLE FOR THE CROWD: THE ELDERLY, ADULTS AND MEN CAN USE&lt;br&gt;&lt;br&gt;The lightweight aluminium telescopic walking sticks receive a hard anodised treatment that prevents damage to the attractive surface making them durable&lt;br&gt;The stylish handle is shaped to fit comfortably in either hand with a handy wrist strap allowing you to use your hand freely&lt;br&gt;The base gives you points of contact for superior balance and stability, just like the human foot,strong moisture-wicking ability,non-slip,and a warm satisfaction in winter.&lt;br&gt;Foldable,Ultra-light,and Portable: folds up in seconds,light weight,put it in the coming carrying bag&lt;br&gt;&lt;/p&gt;</Description><BulletPoint>★ Easy to fold into four sections and can easily be stored for room for manoeuvre and comfort. The pipe folds into four sections, which means that you do it in your bag, when you do not use or when you travel.</BulletPoint><BulletPoint>★ With Easy &amp; convenient for everyday use for your safety and endurance with excellent lightweight. The ergonomically fitted handle gives the user to maximise safety and comfort with high quality and sustainability.</BulletPoint><BulletPoint>★ Non-slip non marking rubber tip prevents shocks and keep them safe too. while providing the flexibility to walking, jogging and light. The stable Bracelet can be hidden and battery case to prevent accidents in use, and provide comfort, when folded and storage.</BulletPoint><BulletPoint>★ Ideal for mobile use on the go. Creases easy for storage or travel and has a comfortable arm band. Unisex element for the older men or women and fits neatly in the luggage, backpacks, posts, comfortable foam grip handle for those who suffer from arthritis. Caution to use this walking stick on wet floors, tiles and other slippery surfaces.</BulletPoint><BulletPoint>★ Folding design makes it portable tube on the market.</BulletPoint><Manufacturer>SY</Manufacturer><SearchTerms>walking stick sticks for folding women men ferrules foldable collapsible telescopic rubber ladies hurrycane crutches tips adjustable prime switch pockets unknown hurricane trekking canes</SearchTerms><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable><RecommendedBrowseNode>2826411031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>parent</Parentage><VariationTheme>Color</VariationTheme></VariationData></Sports></ProductData></Product></Message>" +
                "" +
                "<Message><MessageID>6111120155300</MessageID><OperationType>Update</OperationType><Product><SKU>GZ-GG4W7JCT-black</SKU>" +
                "<StandardProductID><Type>EAN</Type><Value>8156239941781</Value></StandardProductID><ItemPackageQuantity>10</ItemPackageQuantity><DescriptionData><Title>Walking Stick Sticks Folding Elderly Health Women Personal Care Poles Telescopic014,black</Title><Brand>SY</Brand> <Description>&lt;p&gt;PRODUCT NAME: ELDERLY CRUTCHES&lt;br&gt;WEIGHT: 2KG&lt;br&gt;HEIGHT: ADJUSTABLE, 60CM-98CM&lt;br&gt;BODY MATERIAL: CARBON ROD, LIGHT AND BEAUTIFUL&lt;br&gt;THICKNESS: 1.2MM&lt;br&gt;SUITABLE FOR THE CROWD: THE ELDERLY, ADULTS AND MEN CAN USE&lt;br&gt;&lt;br&gt;The lightweight aluminium telescopic walking sticks receive a hard anodised treatment that prevents damage to the attractive surface making them durable&lt;br&gt;The stylish handle is shaped to fit comfortably in either hand with a handy wrist strap allowing you to use your hand freely&lt;br&gt;The base gives you points of contact for superior balance and stability, just like the human foot,strong moisture-wicking ability,non-slip,and a warm satisfaction in winter.&lt;br&gt;Foldable,Ultra-light,and Portable: folds up in seconds,light weight,put it in the coming carrying bag&lt;br&gt;&lt;/p&gt;</Description><BulletPoint>★ Easy to fold into four sections and can easily be stored for room for manoeuvre and comfort. The pipe folds into four sections, which means that you do it in your bag, when you do not use or when you travel.</BulletPoint><BulletPoint>★ With Easy &amp; convenient for everyday use for your safety and endurance with excellent lightweight. The ergonomically fitted handle gives the user to maximise safety and comfort with high quality and sustainability.</BulletPoint><BulletPoint>★ Non-slip non marking rubber tip prevents shocks and keep them safe too. while providing the flexibility to walking, jogging and light. The stable Bracelet can be hidden and battery case to prevent accidents in use, and provide comfort, when folded and storage.</BulletPoint><BulletPoint>★ Ideal for mobile use on the go. Creases easy for storage or travel and has a comfortable arm band. Unisex element for the older men or women and fits neatly in the luggage, backpacks, posts, comfortable foam grip handle for those who suffer from arthritis. Caution to use this walking stick on wet floors, tiles and other slippery surfaces.</BulletPoint><BulletPoint>★ Folding design makes it portable tube on the market.</BulletPoint><Manufacturer>SY</Manufacturer><SearchTerms>walking stick sticks for folding women men ferrules foldable collapsible telescopic rubber ladies hurrycane crutches tips adjustable prime switch pockets unknown hurricane trekking canes</SearchTerms><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable><RecommendedBrowseNode>2826411031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>child</Parentage><VariationTheme>Color</VariationTheme><Color>black</Color></VariationData></Sports></ProductData></Product></Message></AmazonEnvelope>";
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
