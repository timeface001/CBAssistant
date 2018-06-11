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
import java.util.Arrays;

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
        final String accessKeyId = "AKIAIIPFMNZKSXY6SUWQ";
        final String secretAccessKey = "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";

        final String appName = "";
        final String appVersion = "";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();


        /************************************************************************
         * Uncomment to set the appropriate MWS endpoint.
         ************************************************************************/
        // US
         config.setServiceURL("https://mws.amazonservices.com/");
        // UK
        // config.setServiceURL("https://mws.amazonservices.co.uk/");
        // Germany
        // config.setServiceURL("https://mws.amazonservices.de/");
        // France
        // config.setServiceURL("https://mws.amazonservices.fr/");
        // Italy
        // config.setServiceURL("https://mws.amazonservices.it/");
        // Japan
        // config.setServiceURL("https://mws.amazonservices.jp/");
        // China
        // config.setServiceURL("https://mws.amazonservices.com.cn/");
        // Canada
        // config.setServiceURL("https://mws.amazonservices.ca/");
        // India
        // config.setServiceURL("https://mws.amazonservices.in/");

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
        final String merchantId = "AX2JQLLAWG3JN";
        final String sellerDevAuthToken = "<Merchant Developer MWS Auth Token>";
        // marketplaces to which this feed will be submitted; look at the
        // API reference document on the MWS website to see which marketplaces are
        // included if you do not specify the list yourself
        final IdList marketplaces = new IdList(Arrays.asList(
        		"ATVPDKIKX0DER"));

        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(merchantId);
        //request.setMWSAuthToken(sellerDevAuthToken);
        request.setMarketplaceIdList(marketplaces);

        request.setFeedType(AmzFeeType.PRODUCT_FEED.getVal());

        String text="<?xml version=\"1.0\" ?><AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header><DocumentVersion>1.01</DocumentVersion><MerchantIdentifier>AX2JQLLAWG3JN</MerchantIdentifier>\n" +
                "</Header><MessageType>Product</MessageType>\n" +
                "<PurgeAndReplace>false</PurgeAndReplace><Message><MessageID>1</MessageID><OperationType>Update</OperationType><Product><SKU>MS-Y-WNNP89U7-purple</SKU><StandardProductID><Type>EAN</Type><Value>6493494030914</Value></StandardProductID><ItemPackageQuantity>30</ItemPackageQuantity><DescriptionData><Title>Female toys Wand Massage Handheld Personal Foot Massage for Women men Pleasure,purple</Title><Brand>Relifat</Brand> <Description>It is designed to stimulate the sensitive parts of body, excellent ergonomic design to bring users a wonderful sensory stimulation. Can relax your body easily after a busy day. &lt;br&gt;&lt;br&gt;&lt;b&gt;Features:&lt;/b&gt;&lt;br&gt;&amp;nbsp;1. Highest quality materials that are safe and skin-friendly.&lt;br&gt;&amp;nbsp;2. Very high quality currently at a very good price.&lt;br&gt;&amp;nbsp;3. 100% SATISFACTION GUARANTEE&lt;br&gt;&lt;b&gt;&lt;br&gt;Product Details&lt;/b&gt;&lt;br&gt;Product Dimensions: 16.53” x 2.67” &lt;br&gt;Material: Medical grade silicone+ABS&lt;br&gt;Waterproof: Yes&lt;br&gt;Hardness: Perfect balance between hardness and softness&lt;br&gt;&lt;br&gt;&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>SIZE:Max length: 16.53 inch,Max diameter: 2.67 inch ,stimulate the sensitive parts of body, excellent ergonomic design to bring users a wonderful sensory stimulation. Can relax your body easily after a busy day</BulletPoint><BulletPoint>HIGH-QUALITY &amp; SAFTY: High-quality and medical standard PVC material. Non-toxic, harmless, safe, healthy and skin-friendly to use</BulletPoint><BulletPoint>SOFT &amp; EASY TO CLEAN: Flexible,soft and smooth with durable shape, long enough to Bring a very comfortable feeling. Portable and waterproof, easy to clean before and after usage</BulletPoint><BulletPoint>APPLICABLE FOR: It can be played in bedroom,washroom, allowing for greater variations in position, location and flexibility</BulletPoint><BulletPoint>DISCREET PACKING: All products come in discreet standard packing, keeping your privacy in strictest confidential</BulletPoint><Manufacturer>Ligai</Manufacturer><SearchTerms>Handheld Personal Body Therapeutic</SearchTerms><SearchTerms>Rechargeable Portable Personal Vibrating</SearchTerms><SearchTerms>Powerful Body Electric Magic Wand</SearchTerms><SearchTerms>Cordless Curved Recovery</SearchTerms><SearchTerms>For Muscle Aches Sports</SearchTerms><ItemType>12598690031</ItemType><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable>\n" +
                "<RecommendedBrowseNode>12598690031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>child</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme><Color>purple</Color></VariationData></Sports></ProductData></Product></Message><Message><MessageID>1</MessageID><OperationType>Update</OperationType><Product><SKU>MS-Y-WNNP89U7-black</SKU><StandardProductID><Type>EAN</Type><Value>6493494030921</Value></StandardProductID><ItemPackageQuantity>30</ItemPackageQuantity><DescriptionData><Title>Female toys Wand Massage Handheld Personal Foot Massage for Women men Pleasure,black</Title><Brand>Relifat</Brand> <Description>It is designed to stimulate the sensitive parts of body, excellent ergonomic design to bring users a wonderful sensory stimulation. Can relax your body easily after a busy day. &lt;br&gt;&lt;br&gt;&lt;b&gt;Features:&lt;/b&gt;&lt;br&gt;&amp;nbsp;1. Highest quality materials that are safe and skin-friendly.&lt;br&gt;&amp;nbsp;2. Very high quality currently at a very good price.&lt;br&gt;&amp;nbsp;3. 100% SATISFACTION GUARANTEE&lt;br&gt;&lt;b&gt;&lt;br&gt;Product Details&lt;/b&gt;&lt;br&gt;Product Dimensions: 16.53” x 2.67” &lt;br&gt;Material: Medical grade silicone+ABS&lt;br&gt;Waterproof: Yes&lt;br&gt;Hardness: Perfect balance between hardness and softness&lt;br&gt;&lt;br&gt;&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>SIZE:Max length: 16.53 inch,Max diameter: 2.67 inch ,stimulate the sensitive parts of body, excellent ergonomic design to bring users a wonderful sensory stimulation. Can relax your body easily after a busy day</BulletPoint><BulletPoint>HIGH-QUALITY &amp; SAFTY: High-quality and medical standard PVC material. Non-toxic, harmless, safe, healthy and skin-friendly to use</BulletPoint><BulletPoint>SOFT &amp; EASY TO CLEAN: Flexible,soft and smooth with durable shape, long enough to Bring a very comfortable feeling. Portable and waterproof, easy to clean before and after usage</BulletPoint><BulletPoint>APPLICABLE FOR: It can be played in bedroom,washroom, allowing for greater variations in position, location and flexibility</BulletPoint><BulletPoint>DISCREET PACKING: All products come in discreet standard packing, keeping your privacy in strictest confidential</BulletPoint><Manufacturer>Ligai</Manufacturer><SearchTerms>Handheld Personal Body Therapeutic</SearchTerms><SearchTerms>Rechargeable Portable Personal Vibrating</SearchTerms><SearchTerms>Powerful Body Electric Magic Wand</SearchTerms><SearchTerms>Cordless Curved Recovery</SearchTerms><SearchTerms>For Muscle Aches Sports</SearchTerms><ItemType>12598690031</ItemType><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable>\n" +
                "<RecommendedBrowseNode>12598690031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>child</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme><Color>black</Color></VariationData></Sports></ProductData></Product></Message><Message><MessageID>1</MessageID><OperationType>Update</OperationType><Product><SKU>MS-Y-WNNP89U7-Flesh</SKU><StandardProductID><Type>EAN</Type><Value>6493494030938</Value></StandardProductID><ItemPackageQuantity>30</ItemPackageQuantity><DescriptionData><Title>Female toys Wand Massage Handheld Personal Foot Massage for Women men Pleasure,Flesh</Title><Brand>Relifat</Brand> <Description>It is designed to stimulate the sensitive parts of body, excellent ergonomic design to bring users a wonderful sensory stimulation. Can relax your body easily after a busy day. &lt;br&gt;&lt;br&gt;&lt;b&gt;Features:&lt;/b&gt;&lt;br&gt;&amp;nbsp;1. Highest quality materials that are safe and skin-friendly.&lt;br&gt;&amp;nbsp;2. Very high quality currently at a very good price.&lt;br&gt;&amp;nbsp;3. 100% SATISFACTION GUARANTEE&lt;br&gt;&lt;b&gt;&lt;br&gt;Product Details&lt;/b&gt;&lt;br&gt;Product Dimensions: 16.53” x 2.67” &lt;br&gt;Material: Medical grade silicone+ABS&lt;br&gt;Waterproof: Yes&lt;br&gt;Hardness: Perfect balance between hardness and softness&lt;br&gt;&lt;br&gt;&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>SIZE:Max length: 16.53 inch,Max diameter: 2.67 inch ,stimulate the sensitive parts of body, excellent ergonomic design to bring users a wonderful sensory stimulation. Can relax your body easily after a busy day</BulletPoint><BulletPoint>HIGH-QUALITY &amp; SAFTY: High-quality and medical standard PVC material. Non-toxic, harmless, safe, healthy and skin-friendly to use</BulletPoint><BulletPoint>SOFT &amp; EASY TO CLEAN: Flexible,soft and smooth with durable shape, long enough to Bring a very comfortable feeling. Portable and waterproof, easy to clean before and after usage</BulletPoint><BulletPoint>APPLICABLE FOR: It can be played in bedroom,washroom, allowing for greater variations in position, location and flexibility</BulletPoint><BulletPoint>DISCREET PACKING: All products come in discreet standard packing, keeping your privacy in strictest confidential</BulletPoint><Manufacturer>Ligai</Manufacturer><SearchTerms>Handheld Personal Body Therapeutic</SearchTerms><SearchTerms>Rechargeable Portable Personal Vibrating</SearchTerms><SearchTerms>Powerful Body Electric Magic Wand</SearchTerms><SearchTerms>Cordless Curved Recovery</SearchTerms><SearchTerms>For Muscle Aches Sports</SearchTerms><ItemType>12598690031</ItemType><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable>\n" +
                "<RecommendedBrowseNode>12598690031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>child</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme><Color>Flesh</Color></VariationData></Sports></ProductData></Product></Message><Message><MessageID>1</MessageID><OperationType>Update</OperationType><Product><SKU>MS-Y-WNNP89U7</SKU><ItemPackageQuantity>90</ItemPackageQuantity><DescriptionData><Title>Female toys Wand Massage Handheld Personal Foot Massage for Women men Pleasure</Title><Brand>Relifat</Brand> <Description>It is designed to stimulate the sensitive parts of body, excellent ergonomic design to bring users a wonderful sensory stimulation. Can relax your body easily after a busy day. &lt;br&gt;&lt;br&gt;&lt;b&gt;Features:&lt;/b&gt;&lt;br&gt;&amp;nbsp;1. Highest quality materials that are safe and skin-friendly.&lt;br&gt;&amp;nbsp;2. Very high quality currently at a very good price.&lt;br&gt;&amp;nbsp;3. 100% SATISFACTION GUARANTEE&lt;br&gt;&lt;b&gt;&lt;br&gt;Product Details&lt;/b&gt;&lt;br&gt;Product Dimensions: 16.53” x 2.67” &lt;br&gt;Material: Medical grade silicone+ABS&lt;br&gt;Waterproof: Yes&lt;br&gt;Hardness: Perfect balance between hardness and softness&lt;br&gt;&lt;br&gt;&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>SIZE:Max length: 16.53 inch,Max diameter: 2.67 inch ,stimulate the sensitive parts of body, excellent ergonomic design to bring users a wonderful sensory stimulation. Can relax your body easily after a busy day</BulletPoint><BulletPoint>HIGH-QUALITY &amp; SAFTY: High-quality and medical standard PVC material. Non-toxic, harmless, safe, healthy and skin-friendly to use</BulletPoint><BulletPoint>SOFT &amp; EASY TO CLEAN: Flexible,soft and smooth with durable shape, long enough to Bring a very comfortable feeling. Portable and waterproof, easy to clean before and after usage</BulletPoint><BulletPoint>APPLICABLE FOR: It can be played in bedroom,washroom, allowing for greater variations in position, location and flexibility</BulletPoint><BulletPoint>DISCREET PACKING: All products come in discreet standard packing, keeping your privacy in strictest confidential</BulletPoint><Manufacturer>Ligai</Manufacturer><SearchTerms>Handheld Personal Body Therapeutic</SearchTerms><SearchTerms>Rechargeable Portable Personal Vibrating</SearchTerms><SearchTerms>Powerful Body Electric Magic Wand</SearchTerms><SearchTerms>Cordless Curved Recovery</SearchTerms><SearchTerms>For Muscle Aches Sports</SearchTerms><ItemType>12598690031</ItemType><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable>\n" +
                "<RecommendedBrowseNode>12598690031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>parent</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme></VariationData></Sports></ProductData></Product></Message></AmazonEnvelope>";
        FileUtils.byte2File(text.getBytes(),"/Users/fengsong/Downloads/","rule_chain.txt");

        String s="/Users/fengsong/Downloads/rule_chain.txt";
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
     * (the default is false).
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
