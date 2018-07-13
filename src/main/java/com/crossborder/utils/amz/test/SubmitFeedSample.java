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
        final String accessKeyId = "AKIAI4GHLAOXP37NQMMQ";
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
         config.setServiceURL("https://mws.amazonservices.de/");
        // France
        // config.setServicDEFAULTL("https://mws.amazonservices.fr/");
        // Italy
        // config.setServicDEFAULTL("https://mws.amazonservices.it/");
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
        final String merchantId = "A2RD88T826IBCC";
        final String sellerDevAuthToken = "<Merchant Developer MWS Auth Token>";
        // marketplaces to which this feed will be submitted; look at the
        // API reference document on the MWS website to see which marketplaces are
        // included if you do not specify the list yourself
        List<String> ids=new ArrayList<>();
        //ids.add("A13V1IB3VIYZZH");
        //ids.add("A1PA6795UKMFR9");
        ids.add("A1PA6795UKMFR9");
        final IdList marketplaces = new IdList(ids);

        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(merchantId);
        //request.setMWSAuthToken(sellerDevAuthToken);
        request.setMarketplaceIdList(marketplaces);

        request.setFeedType(AmzFeeType.PRODUCT_FEED.getVal());

        String text="<?xml version=\"1.0\" ?><AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header><DocumentVersion>1.01</DocumentVersion><MerchantIdentifier>A2RD88T826IBCC</MerchantIdentifier>\n" +
                "</Header><MessageType>Product</MessageType>\n" +
                "<PurgeAndReplace>false</PurgeAndReplace><Message><MessageID>20673015033</MessageID><OperationType>Update</OperationType><Product><SKU>ZDY-KPA281PI</SKU><ItemPackageQuantity>120</ItemPackageQuantity><DescriptionData><Title>Camping Klappstuhl Outdoor Ultra Light Portable Aluminium Angeln Stuhl</Title><Brand>BNN</Brand> <Description>PRODUKTBESCHREIBUNG: &lt;br&gt; NAME: Outdoor Moon Klappstuhl &lt;br&gt; MATERIAL: 600d Juice Cloth + Aluminiumlegierung &lt;br&gt; FARBE: Rot Weiß Netz, Rot Schwarz Netz, Dunkel Grau Netz, Dunkel Grau Schwarz Netz &lt;br&gt; PRODUKTGRÖSSE: Erweitern: 53 * 35 * 67cm Lagerung: 34 * 11 * 10cm (Handmessung hat einige Fehler) &lt;br&gt; PRODUKTGEWICHT: 900g &lt;br&gt; LAGERKAPAZITÄT: 150kg &lt;br&gt; Ein Mehrzweckstuhl ist für eine Vielzahl von Anlässen im Freien geeignet, eine kluge Wahl für Reisen, sitzende Fischerei, Barbecue-Treffen und ruhelosigkeit Sitzplätze. &lt;br&gt;&lt;br&gt;\n" +
                "&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>Hochwertige Aluminiumlegierung, korrosionsbeständig, kein Rost, leichte Tragkraft.</BulletPoint><BulletPoint>Strenge Anoden-Prozess umweltfreundliche Farbe, ungiftig und harmlos, langfristige Verwendung verblasst nicht und fällt nicht ab.</BulletPoint><BulletPoint>Hochwertiger Kunststoffbügel, starke Schraubenbefestigung, höherer Sicherheitsindex, nicht leicht zu knacken und fest.</BulletPoint><BulletPoint>Pflaume-Fuß-Anti-Beleg-Plastikabdeckung, verletzt nicht den Fußboden, Anti-Beleg und starke Reibung.</BulletPoint><BulletPoint>Aluminiumlegierungs-Schnittstelle, Rohr-Entwurf, einfach zu entfernen, einfach und fest.</BulletPoint><Manufacturer>BNN</Manufacturer><SearchTerms>Angeln Stuhl, Klappstuhl, Portable Outdoor-Stuhl mit Armlehnen, Angeln Stuhl mit Armen, Klappstuhl Camping, Strandkorb, Strandkorb mit Tragegriff, Angeln Stühle, Campingstühle</SearchTerms><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable><RecommendedBrowseNode>2864848031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>parent</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme></VariationData></Sports></ProductData></Product></Message><Message><MessageID>20673015032</MessageID><OperationType>Update</OperationType><Product><SKU>ZDY-KPA281PI-Dark-gray-black-net</SKU><StandardProductID><Type>EAN</Type><Value>6213570659811</Value></StandardProductID><ItemPackageQuantity>30</ItemPackageQuantity><DescriptionData><Title>Camping Klappstuhl Outdoor Ultra Light Portable Aluminium Angeln Stuhl,Dark-gray-black-net</Title><Brand>BNN</Brand> <Description>PRODUKTBESCHREIBUNG: &lt;br&gt; NAME: Outdoor Moon Klappstuhl &lt;br&gt; MATERIAL: 600d Juice Cloth + Aluminiumlegierung &lt;br&gt; FARBE: Rot Weiß Netz, Rot Schwarz Netz, Dunkel Grau Netz, Dunkel Grau Schwarz Netz &lt;br&gt; PRODUKTGRÖSSE: Erweitern: 53 * 35 * 67cm Lagerung: 34 * 11 * 10cm (Handmessung hat einige Fehler) &lt;br&gt; PRODUKTGEWICHT: 900g &lt;br&gt; LAGERKAPAZITÄT: 150kg &lt;br&gt; Ein Mehrzweckstuhl ist für eine Vielzahl von Anlässen im Freien geeignet, eine kluge Wahl für Reisen, sitzende Fischerei, Barbecue-Treffen und ruhelosigkeit Sitzplätze. &lt;br&gt;&lt;br&gt;\n" +
                "&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>Hochwertige Aluminiumlegierung, korrosionsbeständig, kein Rost, leichte Tragkraft.</BulletPoint><BulletPoint>Strenge Anoden-Prozess umweltfreundliche Farbe, ungiftig und harmlos, langfristige Verwendung verblasst nicht und fällt nicht ab.</BulletPoint><BulletPoint>Hochwertiger Kunststoffbügel, starke Schraubenbefestigung, höherer Sicherheitsindex, nicht leicht zu knacken und fest.</BulletPoint><BulletPoint>Pflaume-Fuß-Anti-Beleg-Plastikabdeckung, verletzt nicht den Fußboden, Anti-Beleg und starke Reibung.</BulletPoint><BulletPoint>Aluminiumlegierungs-Schnittstelle, Rohr-Entwurf, einfach zu entfernen, einfach und fest.</BulletPoint><Manufacturer>BNN</Manufacturer><SearchTerms>Angeln Stuhl, Klappstuhl, Portable Outdoor-Stuhl mit Armlehnen, Angeln Stuhl mit Armen, Klappstuhl Camping, Strandkorb, Strandkorb mit Tragegriff, Angeln Stühle, Campingstühle</SearchTerms><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable><RecommendedBrowseNode>2864848031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>child</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme><Color>Dark-gray-black-net</Color></VariationData></Sports></ProductData></Product></Message><Message><MessageID>20673015031</MessageID><OperationType>Update</OperationType><Product><SKU>ZDY-KPA281PI-Dark-gray-white-net</SKU><StandardProductID><Type>EAN</Type><Value>6213570659828</Value></StandardProductID><ItemPackageQuantity>30</ItemPackageQuantity><DescriptionData><Title>Camping Klappstuhl Outdoor Ultra Light Portable Aluminium Angeln Stuhl,Dark-gray-white-net</Title><Brand>BNN</Brand> <Description>PRODUKTBESCHREIBUNG: &lt;br&gt; NAME: Outdoor Moon Klappstuhl &lt;br&gt; MATERIAL: 600d Juice Cloth + Aluminiumlegierung &lt;br&gt; FARBE: Rot Weiß Netz, Rot Schwarz Netz, Dunkel Grau Netz, Dunkel Grau Schwarz Netz &lt;br&gt; PRODUKTGRÖSSE: Erweitern: 53 * 35 * 67cm Lagerung: 34 * 11 * 10cm (Handmessung hat einige Fehler) &lt;br&gt; PRODUKTGEWICHT: 900g &lt;br&gt; LAGERKAPAZITÄT: 150kg &lt;br&gt; Ein Mehrzweckstuhl ist für eine Vielzahl von Anlässen im Freien geeignet, eine kluge Wahl für Reisen, sitzende Fischerei, Barbecue-Treffen und ruhelosigkeit Sitzplätze. &lt;br&gt;&lt;br&gt;\n" +
                "&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>Hochwertige Aluminiumlegierung, korrosionsbeständig, kein Rost, leichte Tragkraft.</BulletPoint><BulletPoint>Strenge Anoden-Prozess umweltfreundliche Farbe, ungiftig und harmlos, langfristige Verwendung verblasst nicht und fällt nicht ab.</BulletPoint><BulletPoint>Hochwertiger Kunststoffbügel, starke Schraubenbefestigung, höherer Sicherheitsindex, nicht leicht zu knacken und fest.</BulletPoint><BulletPoint>Pflaume-Fuß-Anti-Beleg-Plastikabdeckung, verletzt nicht den Fußboden, Anti-Beleg und starke Reibung.</BulletPoint><BulletPoint>Aluminiumlegierungs-Schnittstelle, Rohr-Entwurf, einfach zu entfernen, einfach und fest.</BulletPoint><Manufacturer>BNN</Manufacturer><SearchTerms>Angeln Stuhl, Klappstuhl, Portable Outdoor-Stuhl mit Armlehnen, Angeln Stuhl mit Armen, Klappstuhl Camping, Strandkorb, Strandkorb mit Tragegriff, Angeln Stühle, Campingstühle</SearchTerms><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable><RecommendedBrowseNode>2864848031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>child</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme><Color>Dark-gray-white-net</Color></VariationData></Sports></ProductData></Product></Message><Message><MessageID>20673015030</MessageID><OperationType>Update</OperationType><Product><SKU>ZDY-KPA281PI-Red-black-net</SKU><StandardProductID><Type>EAN</Type><Value>6213570659835</Value></StandardProductID><ItemPackageQuantity>30</ItemPackageQuantity><DescriptionData><Title>Camping Klappstuhl Outdoor Ultra Light Portable Aluminium Angeln Stuhl,Red-black-net</Title><Brand>BNN</Brand> <Description>PRODUKTBESCHREIBUNG: &lt;br&gt; NAME: Outdoor Moon Klappstuhl &lt;br&gt; MATERIAL: 600d Juice Cloth + Aluminiumlegierung &lt;br&gt; FARBE: Rot Weiß Netz, Rot Schwarz Netz, Dunkel Grau Netz, Dunkel Grau Schwarz Netz &lt;br&gt; PRODUKTGRÖSSE: Erweitern: 53 * 35 * 67cm Lagerung: 34 * 11 * 10cm (Handmessung hat einige Fehler) &lt;br&gt; PRODUKTGEWICHT: 900g &lt;br&gt; LAGERKAPAZITÄT: 150kg &lt;br&gt; Ein Mehrzweckstuhl ist für eine Vielzahl von Anlässen im Freien geeignet, eine kluge Wahl für Reisen, sitzende Fischerei, Barbecue-Treffen und ruhelosigkeit Sitzplätze. &lt;br&gt;&lt;br&gt;\n" +
                "&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>Hochwertige Aluminiumlegierung, korrosionsbeständig, kein Rost, leichte Tragkraft.</BulletPoint><BulletPoint>Strenge Anoden-Prozess umweltfreundliche Farbe, ungiftig und harmlos, langfristige Verwendung verblasst nicht und fällt nicht ab.</BulletPoint><BulletPoint>Hochwertiger Kunststoffbügel, starke Schraubenbefestigung, höherer Sicherheitsindex, nicht leicht zu knacken und fest.</BulletPoint><BulletPoint>Pflaume-Fuß-Anti-Beleg-Plastikabdeckung, verletzt nicht den Fußboden, Anti-Beleg und starke Reibung.</BulletPoint><BulletPoint>Aluminiumlegierungs-Schnittstelle, Rohr-Entwurf, einfach zu entfernen, einfach und fest.</BulletPoint><Manufacturer>BNN</Manufacturer><SearchTerms>Angeln Stuhl, Klappstuhl, Portable Outdoor-Stuhl mit Armlehnen, Angeln Stuhl mit Armen, Klappstuhl Camping, Strandkorb, Strandkorb mit Tragegriff, Angeln Stühle, Campingstühle</SearchTerms><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable><RecommendedBrowseNode>2864848031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>child</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme><Color>Red-black-net</Color></VariationData></Sports></ProductData></Product></Message><Message><MessageID>20673015029</MessageID><OperationType>Update</OperationType><Product><SKU>ZDY-KPA281PI-Red-white-net</SKU><StandardProductID><Type>EAN</Type><Value>6213570659842</Value></StandardProductID><ItemPackageQuantity>30</ItemPackageQuantity><DescriptionData><Title>Camping Klappstuhl Outdoor Ultra Light Portable Aluminium Angeln Stuhl,Red-white-net</Title><Brand>BNN</Brand> <Description>PRODUKTBESCHREIBUNG: &lt;br&gt; NAME: Outdoor Moon Klappstuhl &lt;br&gt; MATERIAL: 600d Juice Cloth + Aluminiumlegierung &lt;br&gt; FARBE: Rot Weiß Netz, Rot Schwarz Netz, Dunkel Grau Netz, Dunkel Grau Schwarz Netz &lt;br&gt; PRODUKTGRÖSSE: Erweitern: 53 * 35 * 67cm Lagerung: 34 * 11 * 10cm (Handmessung hat einige Fehler) &lt;br&gt; PRODUKTGEWICHT: 900g &lt;br&gt; LAGERKAPAZITÄT: 150kg &lt;br&gt; Ein Mehrzweckstuhl ist für eine Vielzahl von Anlässen im Freien geeignet, eine kluge Wahl für Reisen, sitzende Fischerei, Barbecue-Treffen und ruhelosigkeit Sitzplätze. &lt;br&gt;&lt;br&gt;\n" +
                "&lt;p&gt;&lt;/p&gt;</Description><BulletPoint>Hochwertige Aluminiumlegierung, korrosionsbeständig, kein Rost, leichte Tragkraft.</BulletPoint><BulletPoint>Strenge Anoden-Prozess umweltfreundliche Farbe, ungiftig und harmlos, langfristige Verwendung verblasst nicht und fällt nicht ab.</BulletPoint><BulletPoint>Hochwertiger Kunststoffbügel, starke Schraubenbefestigung, höherer Sicherheitsindex, nicht leicht zu knacken und fest.</BulletPoint><BulletPoint>Pflaume-Fuß-Anti-Beleg-Plastikabdeckung, verletzt nicht den Fußboden, Anti-Beleg und starke Reibung.</BulletPoint><BulletPoint>Aluminiumlegierungs-Schnittstelle, Rohr-Entwurf, einfach zu entfernen, einfach und fest.</BulletPoint><Manufacturer>BNN</Manufacturer><SearchTerms>Angeln Stuhl, Klappstuhl, Portable Outdoor-Stuhl mit Armlehnen, Angeln Stuhl mit Armen, Klappstuhl Camping, Strandkorb, Strandkorb mit Tragegriff, Angeln Stühle, Campingstühle</SearchTerms><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable><RecommendedBrowseNode>2864848031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType><VariationData><Parentage>child</Parentage>\n" +
                "<VariationTheme>Color</VariationTheme><Color>Red-white-net</Color></VariationData></Sports></ProductData></Product></Message></AmazonEnvelope>";
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
