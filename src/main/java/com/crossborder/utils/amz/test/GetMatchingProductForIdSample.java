/*******************************************************************************
 * Copyright 2009-2017 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Products
 * API Version: 2011-10-01
 * Library Version: 2017-03-22
 * Generated: Wed Mar 22 23:24:32 UTC 2017
 */
package com.crossborder.utils.amz.test;

import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;


/**
 * Sample call for GetMatchingProductForId.
 */
public class GetMatchingProductForIdSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     * @return The response.
     */
    public static GetMatchingProductForIdResponse invokeGetMatchingProductForId(
            MarketplaceWebServiceProducts client,
            GetMatchingProductForIdRequest request) {
        try {
            // Call the service.
            GetMatchingProductForIdResponse response = client.getMatchingProductForId(request);
            Product product = response.getGetMatchingProductForIdResult().get(0).getProducts().getProduct().get(0);
            for (Object obj : product.getAttributeSets().getAny()) {
                        Node nd = (Node) obj;
                        for (int m = 0; m < nd.getChildNodes().getLength(); m++) {
                            Node child = (Node) nd.getChildNodes().item(m);
                            if ("ns2:SmallImage".equals(child.getNodeName())) {
                                for (int n = 0; n < child.getChildNodes().getLength(); n++) {
                                    if ("ns2:URL".equals(child.getChildNodes().item(n).getNodeName())) {
                                       String name= child.getChildNodes().item(n).getTextContent();
                                        System.out.print(name);
                                    }
                                }
                            }
                        }
                    }
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: " + rhmd.getRequestId());
            System.out.println("Timestamp: " + rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            return response;
        } catch (MarketplaceWebServiceProductsException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if (rhmd != null) {
                System.out.println("RequestId: " + rhmd.getRequestId());
                System.out.println("Timestamp: " + rhmd.getTimestamp());
            }
            System.out.println("Message: " + ex.getMessage());
            System.out.println("StatusCode: " + ex.getStatusCode());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("ErrorType: " + ex.getErrorType());
            throw ex;
        }
    }

    public static synchronized MarketplaceWebServiceProductsAsyncClient getAsyncClient() {
        MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig();
        config.setServiceURL("https://mws-eu.amazonservices.com");
        MarketplaceWebServiceProductsAsyncClient client = new MarketplaceWebServiceProductsAsyncClient("AKIAJIWEGLTH7K56E4SA", "2dB2TI0LJUJcP2BB+8t5w85fblTTfq5mD10tJU1h", "", "", config, (ExecutorService) null);

        return client;
    }

    /**
     * Command line entry point.
     */
    public static void main(String[] args) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = getAsyncClient();

        // Create a request.
        GetMatchingProductForIdRequest request = new GetMatchingProductForIdRequest();
        String sellerId = "AGCMLTKOLLE0S";
        request.setSellerId(sellerId);
        String mwsAuthToken = "example";
        //request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = "A1PA6795UKMFR9";
        request.setMarketplaceId(marketplaceId);
        String idType = "ASIN";
        request.setIdType(idType);
        IdListType idList = new IdListType();
        idList.setId(Arrays.asList("B0774YSHDP"));
        request.setIdList(idList);

        // Make the call.
        GetMatchingProductForIdSample.invokeGetMatchingProductForId(client, request);

    }

}
