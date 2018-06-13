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
         config.setServiceURL("https://mws-eu.amazonservices.com");
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
        final String merchantId = "A21WK1ZAUBHF10";
        final String sellerDevAuthToken = "<Merchant Developer MWS Auth Token>";
        // marketplaces to which this feed will be submitted; look at the
        // API reference document on the MWS website to see which marketplaces are
        // included if you do not specify the list yourself
        List<String> ids=new ArrayList<>();
        ids.add("A13V1IB3VIYZZH");
        ids.add("A1RKKUPIHCS9HS");
        final IdList marketplaces = new IdList(ids);

        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(merchantId);
        //request.setMWSAuthToken(sellerDevAuthToken);
        request.setMarketplaceIdList(marketplaces);

        request.setFeedType(AmzFeeType.PRODUCT_FEED.getVal());

        String text="<?xml version=\"1.0\" ?><AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header><DocumentVersion>1.01</DocumentVersion><MerchantIdentifier>A21WK1ZAUBHF10</MerchantIdentifier>\n" +
                "</Header><MessageType>Product</MessageType>\n" +
                "<PurgeAndReplace>false</PurgeAndReplace><Message><MessageID>1</MessageID><OperationType>Update</OperationType><Product><SKU>MIX-WZQQDZ-YIJIA-4</SKU><StandardProductID><Type>EAN</Type><Value>6132962188350</Value></StandardProductID><ItemPackageQuantity>30</ItemPackageQuantity><DescriptionData><Title>Batidora Portátil Blender Mini Eléctrica Para Batidos con Interruptor de un Toque y Cuchillas de Acero Inoxidable</Title><Brand>LifeEasyTool</Brand> <Description>&lt;p&gt;La licuadora está diseñada para aquellos que quieran disfrutar de batidos rápidos, fáciles y batidos, batidos y zumos sin complicaciones simplemente mezclar y listo.&lt;br&gt;El productor de jugo con dos botellas portátiles y cuchillas seguras desmontables.&lt;br&gt;También es fácil de llevar y limpiar&lt;br&gt;&lt;br&gt;Características del producto&lt;br&gt;1 El productor de jugo tiene una botella portátil, por lo que puede quitar la botella de&lt;br&gt;   el mezclador, cubra y apriete la tapa para facilitar su transporte&lt;br&gt;2 La licuadora con cuchillas desmontables, solo para echar agua sobre &lt;br&gt;   ella para limpiarla&lt;br&gt;3 Las dos botellas están hechas de vidrio y plástico de grado alimenticio, &lt;br&gt;   respectivamente&lt;br&gt;&lt;br&gt;&lt;br&gt;Cómo utilizar&lt;br&gt;1 Primero, corte las frutas y verduras en pedazos(Cuchillas convenientes para cortar) &lt;br&gt;   y ponerlos en la botella. Luego añade la cantidad apropiada de agua o leche &lt;br&gt;   (según su gusto).Pero recuerde que el jugo no debe exceder dos tercios de la &lt;br&gt;   botella,y molienda seca no más de la mitad&lt;br&gt;2 Luego, cubra la botella con tapa que con las cuchillas y apriétela&lt;br&gt;3 Por último, coloque la botella en la máquina y gírela en el sentido de las agujas del &lt;br&gt;   reloj para asegurarse la botella y el cuerpo están firmemente atascados, luego &lt;br&gt;   conecte el poder y enciende el interruptor&lt;br&gt;4 No instalado correctamente, el exprimidor no comenzará a funcionar&lt;br&gt;&lt;br&gt;Atención&lt;br&gt;Mantenga presionado el interruptor y presiónelo durante 5 segundos para detenerlo. En general,presione 3-5 veces y termine. Recuerde no presionar más de 10 segundos,de lo contrario, afectará el uso de la licuadora debido a la alta temperatura&lt;br&gt;&lt;/p&gt;</Description><BulletPoint>MEZCLAR Y IR: Blender es bueno para batidos, cubitos de hielo, jugos, batidos de frutas y suplementos alimenticios para niños</BulletPoint><BulletPoint>FUNCIONAMIENTO DE UN SOLO TOQUE: con la operación de un toque, haga que la mezcla sea rápida y simple. Pero no opere el interruptor continuamente por más de 20 segundos a la vez.</BulletPoint><BulletPoint>304 HOJA DE ACERO INOXIDABLE: Las hojas de la mezcladora están hechas de acero inoxidable 304, que es resistente a la corrosión y no es fácil de oxidar</BulletPoint><BulletPoint>FÁCIL DE LIMPIAR: Después del uso, simplemente necesita enjuagar bajo agua del grifo</BulletPoint><BulletPoint>PORTABILIDAD: El exprimidor es pequeño y fácil de transportar, no ocupa espacio. Ideal para el gimnasio, el trabajo, la escuela y el día</BulletPoint><Manufacturer>Shanney</Manufacturer><SearchTerms>mini batidora portatil pequeña</SearchTerms><SearchTerms>picadora  manual multifuncion de mano</SearchTerms><SearchTerms>trituradora electrica hielo sencilla</SearchTerms><SearchTerms>juicer sin cable</SearchTerms><SearchTerms>blender</SearchTerms><ItemType>2165624031</ItemType><IsGiftWrapAvailable>false</IsGiftWrapAvailable><IsGiftMessageAvailable>false</IsGiftMessageAvailable><RecommendedBrowseNode>2165624031</RecommendedBrowseNode></DescriptionData><ProductData><Sports><ProductType>SportingGoods</ProductType></Sports></ProductData></Product></Message></AmazonEnvelope>\n";
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
