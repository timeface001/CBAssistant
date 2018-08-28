package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.amazon.mws.finances._2015_05_01.MWSFinancesServiceClient;
import com.amazon.mws.finances._2015_05_01.MWSFinancesServiceConfig;
import com.amazon.mws.finances._2015_05_01.model.FeeComponent;
import com.amazon.mws.finances._2015_05_01.model.FinancialEvents;
import com.amazon.mws.finances._2015_05_01.model.ListFinancialEventsRequest;
import com.amazon.mws.finances._2015_05_01.model.ShipmentItem;
import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.model.*;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrders;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersConfig;
import com.amazonservices.mws.orders._2013_09_01.model.*;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsConfig;
import com.amazonservices.mws.products.model.GetMatchingProductForIdRequest;
import com.amazonservices.mws.products.model.GetMatchingProductForIdResponse;
import com.amazonservices.mws.products.model.IdListType;
import com.amazonservices.mws.products.model.Product;
import com.crossborder.entity.AddressInfo;
import com.crossborder.entity.LocalOrder;
import com.crossborder.entity.LocalOrderItem;
import com.crossborder.service.OrderManageService;
import com.crossborder.service.ShopManageService;
import com.crossborder.utils.MD5;
import com.crossborder.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Node;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by s on 2018/3/22.
 */
@Controller
@RequestMapping("/order")
public class OrderManageController {
    @Resource
    private OrderManageService orderManageService;
    @Resource
    private ShopManageService shopManageService;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String Order_Fulfillment_Fee = "_POST_ORDER_FULFILLMENT_DATA_";

    /*
     *下载订单
     */
    @ResponseBody
    @RequestMapping(value = "downloadOrder", produces = "text/plain;charset=UTF-8")
    public String downloadOrder(String data, HttpSession session) {
        String result = "";
        Map<String, Object> shopMap = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        shopMap.put("createUser", user.get("USER_ID").toString());
        List<Map<String, Object>> shops = shopManageService.getMerchantId(shopMap);
        if (shops != null && shops.size() > 0) {
            for (int i = 0; i < shops.size(); i++) {
                Map<String, Object> shop = shops.get(i);
                Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
                MarketplaceWebServiceOrdersConfig config = new MarketplaceWebServiceOrdersConfig();
                config.setServiceURL(shop.get("ENDPOINT").toString());
                MarketplaceWebServiceOrdersClient client = new MarketplaceWebServiceOrdersClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), config);
                ListOrdersRequest request = new ListOrdersRequest();
                request.setSellerId(shop.get("MERCHANT_ID").toString());
                request.setMWSAuthToken("");
                GregorianCalendar cal = new GregorianCalendar();
                XMLGregorianCalendar createdAfter = null;
                XMLGregorianCalendar createdBefore = null;
                try {
                    cal.setTime(Tools.strToDateLong(paramMap.get("logmin").toString() + " 00:00:00"));
                    createdAfter = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                    if (Tools.isToday(paramMap.get("logmax").toString(), "yyyy-MM-dd")) {
                        long curren = System.currentTimeMillis() - 3 * 60 * 1000;
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        cal.setTime(Tools.strToDateLong(paramMap.get("logmax").toString() + " " + sdf.format(new Date(curren))));
                    } else {
                        cal.setTime(Tools.strToDateLong(paramMap.get("logmax").toString() + " 24:00:00"));
                    }
                    createdBefore = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setCreatedAfter(createdAfter);
                request.setCreatedBefore(createdBefore);
                List<Map<String, Object>> marketplaceIds = shopManageService.getMarketPlaceId(shop);
                List<String> marketplaceId = new ArrayList<String>();
                for (int m = 0; m < marketplaceIds.size(); m++) {
                    marketplaceId.add(marketplaceIds.get(m).get("MARKETPLACEID").toString());
                }
                request.setMarketplaceId(marketplaceId);
                List<String> orderStatus = new ArrayList<String>();
                orderStatus.add("Unshipped");
                orderStatus.add("PartiallyShipped");
                orderStatus.add("Shipped");
                request.setOrderStatus(orderStatus);
                result = getListOrders(client, request, user, shop, marketplaceIds);
            }
            return result;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "1");
            map.put("msg", "您还未授权店铺，快去授权吧！");
            return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        }
    }

    private List<Order> getNextTokenOrders(String nextToken, MarketplaceWebServiceOrders client, List<Order> orderList, String sellerId) {
        ListOrdersByNextTokenRequest nextTokenRequest = new ListOrdersByNextTokenRequest();
        nextTokenRequest.setSellerId(sellerId);
        nextTokenRequest.setNextToken(nextToken);
        ListOrdersByNextTokenResponse nextTokenResponse = client.listOrdersByNextToken(nextTokenRequest);
        orderList.addAll(nextTokenResponse.getListOrdersByNextTokenResult().getOrders());
        if (StringUtils.isNotBlank(nextTokenResponse.getListOrdersByNextTokenResult().getNextToken())) {
            getNextTokenOrders(nextTokenResponse.getListOrdersByNextTokenResult().getNextToken(), client, orderList, sellerId);
        }
        return orderList;
    }

    public String getListOrders(MarketplaceWebServiceOrders client,
                                ListOrdersRequest request, Map<String, Object> user, Map<String, Object> shop, List<Map<String, Object>> marketplaceIds) {
        Map<String, Object> map = new HashMap<>();
        try {
            ListOrdersResponse response = client.listOrders(request);
            List<Order> orderList = response.getListOrdersResult().getOrders();
            if (StringUtils.isNotBlank(response.getListOrdersResult().getNextToken())) {
                orderList = getNextTokenOrders(response.getListOrdersResult().getNextToken(), client, orderList, request.getSellerId());
            }
            //修改亚马逊订单状态
            if (orderList.size() > 0) {
                updateOrderStatus(orderList, shop, marketplaceIds);
            }
            List<LocalOrder> localOrderList = new ArrayList<>();
            List<LocalOrderItem> localOrderItemList = new ArrayList<>();
            List<AddressInfo> addressInfoList = new ArrayList<>();
            int count = 0;
            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                //插入订单信息
                LocalOrder localOrder = new LocalOrder();
                localOrder.setAmazonOrderId(order.getAmazonOrderId());
                localOrder.setOrderStatus(order.getOrderStatus());
                localOrder.setSellerOrderId(order.getSellerOrderId());
                localOrder.setLocalStatus("1");
                Date createDate = order.getPurchaseDate().toGregorianCalendar().getTime();
                localOrder.setPurchaseDate(simpleDateFormat.format(createDate));
                Date updateDate = order.getLastUpdateDate().toGregorianCalendar().getTime();
                localOrder.setLastUpdateDate(simpleDateFormat.format(updateDate));
                localOrder.setUpdateTime(simpleDateFormat.format(new Date()));
                localOrder.setCurrencyCode(order.getOrderTotal().getCurrencyCode());
                localOrder.setOrderTotal(Double.parseDouble(order.getOrderTotal().getAmount()));
                String exrate = shopManageService.getExrate(order.getOrderTotal().getCurrencyCode());
                localOrder.setNumberOfItemsShipped(order.getNumberOfItemsShipped());
                localOrder.setNumberOfItemsUnshipped(order.getNumberOfItemsUnshipped());
                localOrder.setBuyerCounty(order.getShippingAddress().getCountryCode());
                localOrder.setBuyerName(order.getBuyerName());
                if (order.getFulfillmentChannel().equals("MFN")) {
                    localOrder.setFulfillmentChannel("FBM");
                } else {
                    localOrder.setFulfillmentChannel("FBA");
                }
                localOrder.setMarketplaceId(order.getMarketplaceId());
                localOrder.setPaymentMethod(order.getPaymentMethod());
                localOrder.setSalesMan(user.get("USER_ID").toString());
                localOrder.setSalesSource(shop.get("MERCHANT_ID").toString());
                localOrder.setSalesCompany(user.get("USER_COMPANY").toString());
                localOrder.setRunningTime(Tools.timeDifference(createDate.getTime(), new Date().getTime()));
                localOrder.setOrderType(order.getOrderType());
                localOrder.setShippingPrice(0);
                localOrder.setIntlTrackNum("");
                orderManageService.insertOrders(localOrder);
                AddressInfo addressInfo = new AddressInfo();
                addressInfo.setAmazonOrderId(order.getAmazonOrderId());
                addressInfo.setName(order.getShippingAddress().getName());
                addressInfo.setCity(order.getShippingAddress().getCity());
                addressInfo.setAddressLine1(order.getShippingAddress().getAddressLine1());
                addressInfo.setAddressLine2(order.getShippingAddress().getAddressLine2());
                addressInfo.setAddressLine3(order.getShippingAddress().getAddressLine3());
                addressInfo.setCompany("");//order.getShippingAddress().getName()
                addressInfo.setPhone(order.getShippingAddress().getPhone());
                addressInfo.setCreateUser(user.get("USER_ID").toString());
                addressInfo.setCountryCode(order.getShippingAddress().getCountryCode());
                addressInfo.setPostalCode(order.getShippingAddress().getPostalCode());
                addressInfo.setStateOrRegion(order.getShippingAddress().getStateOrRegion());
                addressInfoList.add(addressInfo);
                orderManageService.insertAddress(addressInfo);
                //获取订单详情
                if (i > 29) {
                    Thread.sleep(2 * 1000);
                }
//                System.out.println(simpleDateFormat.format(new Date()));
                List<OrderItem> orderItemList = getOrderItem(order.getAmazonOrderId(), shop);
               /* List<LocalOrderItem> localOrderItemList = new ArrayList<>();*/
                for (int j = 0; j < orderItemList.size(); j++) {
                    OrderItem orderItem = orderItemList.get(j);
                    Product product = getProduct(orderItem, shop, order.getMarketplaceId());
                    LocalOrderItem localOrderItem = new LocalOrderItem();
                    if (product != null) {
                        for (Object obj : product.getAttributeSets().getAny()) {
                            Node nd = (Node) obj;
                            for (int m = 0; m < nd.getChildNodes().getLength(); m++) {
                                Node child = nd.getChildNodes().item(m);
                                if ("ns2:SmallImage".equals(child.getNodeName())) {
                                    for (int n = 0; n < child.getChildNodes().getLength(); n++) {
                                        if ("ns2:URL".equals(child.getChildNodes().item(n).getNodeName())) {
                                            localOrderItem.setSmallImage(child.getChildNodes().item(n).getTextContent());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    localOrderItem.setOrderItemId(orderItem.getOrderItemId());
                    localOrderItem.setAmazonOrderId(order.getAmazonOrderId());
                    localOrderItem.setAsin(orderItem.getASIN());
                    if (orderItem.getItemPrice() != null) {
                        localOrderItem.setCurrencyCode(orderItem.getItemPrice().getCurrencyCode());
                        localOrderItem.setItemPrice(Double.parseDouble(orderItem.getItemPrice().getAmount()));
                        localOrderItem.setItemPriceRMB(Double.parseDouble(orderItem.getItemPrice().getAmount()) * Double.parseDouble(exrate));
                    } else {
                        localOrderItem.setCurrencyCode("");
                        localOrderItem.setItemPrice(0);
                        localOrderItem.setItemPriceRMB(0);
                    }
                    localOrderItem.setQuantityShipped(orderItem.getQuantityOrdered());
                    localOrderItem.setSellerSKU(orderItem.getSellerSKU());
                    if (orderItem.getShippingPrice() != null) {
                        localOrderItem.setShippingPrice(Double.parseDouble(orderItem.getShippingPrice().getAmount()));
                        localOrderItem.setShippingPriceRMB(Double.parseDouble(orderItem.getShippingPrice().getAmount()) * Double.parseDouble(exrate));
                    } else {
                        localOrderItem.setShippingPrice(0);
                        localOrderItem.setShippingPriceRMB(0);
                    }
                    if (orderItem.getGiftWrapPrice() != null) {
                        localOrderItem.setGiftWrapPrice(Double.parseDouble(orderItem.getGiftWrapPrice().getAmount()));
                        localOrderItem.setGiftWrapPriceRMB(Double.parseDouble(orderItem.getGiftWrapPrice().getAmount()) * Double.parseDouble(exrate));
                    } else {
                        localOrderItem.setGiftWrapPrice(0);
                        localOrderItem.setGiftWrapPriceRMB(0);
                    }
                    /*List<ShipmentItem> shipmentItems = getOrderItemFees(shop, order.getAmazonOrderId());*/
                    /*for (int m = 0; m < shipmentItems.size(); m++) {
                        List<FeeComponent> feeComponents = shipmentItems.get(m).getItemFeeList();
                        double fees = 0;
                        for (int n = 0; n < feeComponents.size(); n++) {
                            fees = fees + feeComponents.get(n).getFeeAmount().getCurrencyAmount().doubleValue();
                        }
                        if (shipmentItems.get(m).getOrderItemId().equals(orderItem.getOrderItemId())) {
                            localOrderItem.setCommission(fees);
                            localOrderItem.setCommissionRMB(fees * Double.parseDouble(exrate));
                        }
                    }*/
                    localOrderItem.setStatus("1");
                    localOrderItem.setCost(0);
                    localOrderItem.setRefundment(0);
                    localOrderItem.setTitle(orderItem.getTitle());
                    orderManageService.insertOrderItem(localOrderItem);
                    localOrderItemList.add(localOrderItem);
                }
                /*localOrder.setLocalOrderItemList(localOrderItemList);*/
                localOrderList.add(localOrder);
            }
            /*if (localOrderList.size() > 0) {
                orderManageService.insertOrders(localOrderList);
            }
            if (localOrderItemList.size() > 0) {
                orderManageService.insertOrderItem(localOrderItemList);
            }
            if (addressInfoList.size() > 0) {
                orderManageService.insertAddress(addressInfoList);
            }*/
            map.put("count", orderList.size());
            map.put("code", "0");
            map.put("msg", "下载成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "下载失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    public void updateOrderStatus(List<Order> orderList, Map<String, Object> shop, List<Map<String, Object>> marketplaceIds) {
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">" +
                    "<Header>" +
                    "<DocumentVersion>1.01</DocumentVersion>" +
                    "<MerchantIdentifier>" + shop.get("MERCHANT_ID").toString() + "</MerchantIdentifier>" +
                    "</Header>" +
                    "<MessageType>OrderFulfillment</MessageType>";
            String message = "";
            for (int i = 0; i < orderList.size(); i++) {
                if (!orderList.get(i).getOrderStatus().equals("Shipped")) {//已发货的不需要再修改状态
                    message = message + "<Message>" +
                            "<MessageID>" + (i + 1) + "</MessageID>" +
                            "<OrderFulfillment>" +
                            "<AmazonOrderID>" + orderList.get(i).getAmazonOrderId() + "</AmazonOrderID>" +
                            "<FulfillmentDate>" + DatatypeFactory.newInstance().newXMLGregorianCalendar(cal) + "</FulfillmentDate>" +
                            "<FulfillmentData>" +
                            "<CarrierName>Yun Express</CarrierName>" +
                            "<ShippingMethod>Standard</ShippingMethod>" +
                            "<ShipperTrackingNumber>" + Tools.createIntlTrackNum() + "</ShipperTrackingNumber>" +
                            "</FulfillmentData>" +
                       /* "<Item>" +
                        "<MerchantOrderItemID>" + localOrderList.get(i).getLocalOrderItemList().get(0).getOrderItemId() + "</MerchantOrderItemID>" +
                        "<MerchantFulfillmentItemID>" + new Date().getTime() + "</MerchantFulfillmentItemID>" +
                        "<Quantity>" + localOrderList.get(i).getLocalOrderItemList().get(0).getQuantityShipped() + "</Quantity>" +
                        "</Item>" +*/
                            "</OrderFulfillment>" +
                            "</Message>";
                }
            }
            String end = "</AmazonEnvelope>";
            StringBuffer stringBuffer = new StringBuffer();
            String fulfillmentXml = stringBuffer.append(header).append(message).append(end).toString();
            FileWriter writer = new FileWriter("/home/amz/updateStatus.txt");
            writer.write(fulfillmentXml);
            writer.flush();
            writer.close();
            FileInputStream fis = new FileInputStream(new File("/home/amz/updateStatus.txt"));
            MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
            config.setServiceURL(shop.get("ENDPOINT").toString());
            MarketplaceWebService service = new MarketplaceWebServiceClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), "", "", config);
            SubmitFeedRequest request = new SubmitFeedRequest();
            IdList idList = new IdList();
            List<String> ids = new ArrayList<>();
            for (int i = 0; i < marketplaceIds.size(); i++) {
                ids.add(marketplaceIds.get(i).get("MARKETPLACEID").toString());
            }
            idList.setId(ids);
            request.setMarketplaceIdList(idList);
            request.setMerchant(shop.get("MERCHANT_ID").toString());
            request.setFeedContent(fis);
            request.setFeedType(Order_Fulfillment_Fee);
            request.setContentMD5(MD5.computeContentMD5HeaderValue(fis));
            SubmitFeedResponse response = service.submitFeed(request);
            if (response.isSetSubmitFeedResult()) {
                SubmitFeedResult submitFeedResult = response
                        .getSubmitFeedResult();
                if (submitFeedResult.isSetFeedSubmissionInfo()) {
                    FeedSubmissionInfo feedSubmissionInfo = submitFeedResult
                            .getFeedSubmissionInfo();
                    if (feedSubmissionInfo.isSetFeedSubmissionId()) {

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOrderStatusTest() {
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">" +
                    "<Header>" +
                    "<DocumentVersion>1.01</DocumentVersion>" +
                    "<MerchantIdentifier>APC8KTCSFWL6Q</MerchantIdentifier>" +
                    "</Header>" +
                    "<MessageType>OrderFulfillment</MessageType>";
            String message = "<Message>" +
                    "<MessageID>1</MessageID>" +
                    "<OrderFulfillment>" +
                    "<AmazonOrderID>403-7603796-8465911</AmazonOrderID>" +
                    "<FulfillmentDate>" + DatatypeFactory.newInstance().newXMLGregorianCalendar(cal) + "</FulfillmentDate>" +
                    "<FulfillmentData>" +
                    "<CarrierName>Yun Express</CarrierName>" +
                    "<ShippingMethod>Standard</ShippingMethod>" +
                    "<ShipperTrackingNumber>" + Tools.createIntlTrackNum() + "</ShipperTrackingNumber>" +
                    "</FulfillmentData>" +
            /*        "<Item>" +
                    "<AmazonOrderItemCode>06743192371019</AmazonOrderItemCode>" +
                    "<Quantity>1</Quantity>" +
                    "</Item>" +*/
                    "</OrderFulfillment>" +
                    "</Message>";
            String end = "</AmazonEnvelope>";
            StringBuffer stringBuffer = new StringBuffer();
            String fulfillmentXml = stringBuffer.append(header).append(message).append(end).toString();
            FileWriter writer = new FileWriter("d:/home/test.txt");
            writer.write(fulfillmentXml);
            writer.flush();
            writer.close();
            FileInputStream fis = new FileInputStream(new File("d:/home/test.txt"));
            MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
            config.setServiceURL("https://mws-eu.amazonservices.com");
            MarketplaceWebService service = new MarketplaceWebServiceClient("AKIAJYOHHDMS3SD5C7MQ", "YgpkkQ4HnDyiBSqwW5pV0vgFH8ctvw64lh8K2OW0", "", "", config);
            SubmitFeedRequest request = new SubmitFeedRequest();
            IdList idList = new IdList();
            List<String> ids = new ArrayList<>();
            ids.add("A13V1IB3VIYZZH");
            idList.setId(ids);
            request.setMarketplaceIdList(idList);
            request.setMerchant("APC8KTCSFWL6Q");
            request.setFeedContent(fis);
            request.setFeedType(Order_Fulfillment_Fee);
            request.setContentMD5(MD5.computeContentMD5HeaderValue(fis));
            SubmitFeedResponse response = service.submitFeed(request);
            if (response.isSetSubmitFeedResult()) {
                SubmitFeedResult submitFeedResult = response
                        .getSubmitFeedResult();
                if (submitFeedResult.isSetFeedSubmissionInfo()) {
                    FeedSubmissionInfo feedSubmissionInfo = submitFeedResult
                            .getFeedSubmissionInfo();
                    if (feedSubmissionInfo.isSetFeedSubmissionId()) {

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取orderItem佣金
     *
     * @param shop
     * @param amazonOrderId
     * @return
     */
    public List<ShipmentItem> getOrderItemFees(Map<String, Object> shop, String amazonOrderId) {
        MWSFinancesServiceConfig config = new MWSFinancesServiceConfig();
        config.setServiceURL(shop.get("ENDPOINT").toString());
        MWSFinancesServiceClient client = new MWSFinancesServiceClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), "", "", config);
        ListFinancialEventsRequest request = new ListFinancialEventsRequest();
        request.setSellerId(shop.get("MERCHANT_ID").toString());
        request.setAmazonOrderId(amazonOrderId);
        FinancialEvents financialEvents = client.listFinancialEvents(request).getListFinancialEventsResult().getFinancialEvents();
        if (financialEvents.getShipmentEventList() != null && financialEvents.getShipmentEventList().size() > 0) {
            return financialEvents.getShipmentEventList().get(0).getShipmentItemList();
        } else {
            return new ArrayList<ShipmentItem>();
        }
    }

    public List<OrderItem> getOrderItem(String amazonOrderId, Map<String, Object> shop) {
        MarketplaceWebServiceOrdersConfig config = new MarketplaceWebServiceOrdersConfig();
        config.setServiceURL(shop.get("ENDPOINT").toString());
        MarketplaceWebServiceOrdersClient client = new MarketplaceWebServiceOrdersClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), config);
        ListOrderItemsRequest request = new ListOrderItemsRequest();
        request.setSellerId(shop.get("MERCHANT_ID").toString());
        request.setMWSAuthToken("");
        request.setAmazonOrderId(amazonOrderId);
        ListOrderItemsResponse response = client.listOrderItems(request);
        return response.getListOrderItemsResult().getOrderItems();
    }

    public Product getProduct(OrderItem orderItem, Map<String, Object> shop, String marketplaceId) {
        try {
            MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig();
            config.setServiceURL(shop.get("ENDPOINT").toString() + "/Products/2011-10-01");
            MarketplaceWebServiceProductsClient client = new MarketplaceWebServiceProductsClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), config);
            GetMatchingProductForIdRequest request = new GetMatchingProductForIdRequest();
            request.setSellerId(shop.get("MERCHANT_ID").toString());
            request.setMWSAuthToken("");
            request.setMarketplaceId(marketplaceId);
            request.setIdType("ASIN");
            IdListType idList = new IdListType();
            List<String> asins = new ArrayList<>();
            asins.add(orderItem.getASIN());
            idList.setId(asins);
            request.setIdList(idList);
            GetMatchingProductForIdResponse response = client.getMatchingProductForId(request);
            return response.getGetMatchingProductForIdResult().get(0).getProducts().getProduct().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取佣金
     *
     * @param amazonOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCommission", produces = "text/plain;charset=UTF-8")
    public String getCommission(String amazonOrderId, String merchantId, String marketplaceId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> shopMap = new HashMap<>();
            shopMap.put("merchantId", merchantId);
            shopMap.put("marketplaceId", marketplaceId);
            Map<String, Object> shop = shopManageService.selectShopByCountry(shopMap).get(0);
            List<ShipmentItem> shipmentItems = getOrderItemFees(shop, amazonOrderId);
            for (int i = 0; i < shipmentItems.size(); i++) {
                List<FeeComponent> feeComponents = shipmentItems.get(i).getItemFeeList();
                double fees = 0;
                for (int m = 0; m < feeComponents.size(); m++) {
                    fees = fees + feeComponents.get(m).getFeeAmount().getCurrencyAmount().doubleValue();
                }
                Map<String, Object> commissionMap = new HashMap<>();
                commissionMap.put("amazonOrderId", amazonOrderId);
                commissionMap.put("orderItemId", shipmentItems.get(i).getOrderItemId());
                commissionMap.put("commission", fees);
                commissionMap.put("commissionRMB", fees * Double.parseDouble(shop.get("EXRATE").toString()));
                orderManageService.updateOrderItemCommission(commissionMap);
            }
            map.put("code", "0");
            map.put("msg", "获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "获取失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询订单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectLocalOrder", produces = "text/plain;charset=UTF-8")
    public String selectLocalOrder(HttpSession session, String data, Integer draw, Integer start, Integer length) {
        System.out.println("时间1：" + simpleDateFormat.format(new Date()));
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").toString().equals("600")) {
            paramMap.put("salesMan", user.get("USER_ID"));
        } else if (user.get("ROLE_ID").toString().equals("500")) {
            paramMap.put("salesCompany", user.get("USER_COMPANY"));
        }
        List<Map<String, Object>> localOrderList = new ArrayList<>();
        /*if (paramMap.get("localStatus").toString().equals("0")) {
            paramMap.put("localStatus", "");
        }*/
        paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
        paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
        try {
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            localOrderList = orderManageService.selectLocalOrder(paramMap);
            System.out.println("时间2：" + simpleDateFormat.format(new Date()));
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(localOrderList);
            for (int i = 0; i < localOrderList.size(); i++) {
                Date createDate = simpleDateFormat.parse(localOrderList.get(i).get("PURCHASEDATE").toString());
                localOrderList.get(i).put("RUNNINGTIME", Tools.timeDifference(createDate.getTime(), new Date().getTime()));
            }
            map.put("data", localOrderList);
            map.put("draw", draw);
            map.put("recordsTotal", pageInfo.getTotal());
            map.put("recordsFiltered", pageInfo.getTotal());
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询订单item
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectLocalOrderItem", produces = "text/plain;charset=UTF-8")
    public String selectLocalOrderItem(String amazonOrderId, String orderItemId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("amazonOrderId", amazonOrderId);
        paramMap.put("orderItemId", orderItemId);
        try {
            List<Map<String, Object>> localOrderItemList = orderManageService.selectLocalOrderItem(paramMap);
            map.put("data", localOrderItemList);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询总费用明细
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectFees", produces = "text/plain;charset=UTF-8")
    public String selectFees(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
        paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
        try {
            map.put("data", orderManageService.selectFees(paramMap).get(0));
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询订单详情
     *
     * @param amazonOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectOrderInfo", produces = "text/plain;charset=UTF-8")
    public String selectOrderInfo(String amazonOrderId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("amazonOrderId", amazonOrderId);
        try {
            map.put("address", orderManageService.selectAddress(amazonOrderId).get(0));
            map.put("localOrder", orderManageService.selectLocalOrder(paramMap).get(0));
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }


    /**
     * 更新订单信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateOrderInfo", produces = "text/plain;charset=UTF-8")
    public String updateOrderInfo(String amazonOrderId, String preStatus, String status, String orderItemId, String cost, String refundment, String trackNum, String purchaseNum, String shippingPrice, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        paramMap.put("orderItemId", orderItemId);
        paramMap.put("amazonOrderId", amazonOrderId);
        paramMap.put("cost", cost);
        paramMap.put("refundment", refundment);
        paramMap.put("trackNum", trackNum);
        paramMap.put("purchaseNum", purchaseNum);
        paramMap.put("shippingPrice", shippingPrice);
        paramMap.put("updateTime", simpleDateFormat.format(new Date()));
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        boolean flag = true;
        try {
            orderManageService.updateOrderItem(paramMap);
            if (status.equals("2")) {
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("amazonOrderId", amazonOrderId);
                List<Map<String, Object>> orderItems = orderManageService.selectLocalOrderItem(itemMap);
                for (int i = 0; i < orderItems.size(); i++) {
                    if (!orderItems.get(i).get("STATUS").equals("2")) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                orderManageService.updateOrder(paramMap);
            }
            insertOperationLog(amazonOrderId, preStatus, status, user.get("USER_ID").toString(), cost);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 更新订单备注
     *
     * @param remark
     * @param amazonOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateOrderRemark", produces = "text/plain;charset=UTF-8")
    public String updateOrderRemark(String remark, String amazonOrderId, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        paramMap.put("remark", remark);
        paramMap.put("amazonOrderId", amazonOrderId);
        try {
            orderManageService.updateOrderRemark(paramMap);
            insertOperationLog(amazonOrderId, "", "0", user.get("USER_ID").toString(), "");
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除订单
     *
     * @param amazonOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delOrder", produces = "text/plain;charset=UTF-8")
    public String delOrder(String amazonOrderId) {
        Map<String, Object> map = new HashMap<>();
        try {
            orderManageService.delOrder(amazonOrderId);
            orderManageService.delOrderItem(amazonOrderId);
            orderManageService.delAddress(amazonOrderId);
            map.put("code", "0");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 克隆订单
     *
     * @param amazonOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cloneOrder", produces = "text/plain;charset=UTF-8")
    public String cloneOrder(String amazonOrderId) {
        Map<String, Object> map = new HashMap<>();
        try {
            orderManageService.cloneOrder(amazonOrderId);
            orderManageService.cloneOrderItem(amazonOrderId);
            orderManageService.cloneAddress(amazonOrderId);
            map.put("code", "0");
            map.put("msg", "克隆成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "克隆失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 添加订单
     *
     * @param orderData
     * @param orderItemData
     * @param addressData
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addOrder", produces = "text/plain;charset=UTF-8")
    public String addOrder(String orderData, String orderItemData, String addressData, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            LocalOrder localOrder = JSON.parseObject(orderData, LocalOrder.class);
            localOrder.setUpdateTime(simpleDateFormat.format(new Date()));
            localOrder.setLocalStatus("1");
            String exrate = localOrder.getMarketplaceId().split("\\|")[1];
            localOrder.setMarketplaceId(localOrder.getMarketplaceId().split("\\|")[0]);
            orderManageService.insertOrders(localOrder);
            AddressInfo addressInfo = JSON.parseObject(addressData, AddressInfo.class);
            addressInfo.setCreateUser(user.get("USER_ID").toString());
            addressInfo.setAmazonOrderId(localOrder.getAmazonOrderId());
            orderManageService.insertAddress(addressInfo);
            List<LocalOrderItem> localOrderItemList = JSON.parseArray(orderItemData, LocalOrderItem.class);
            for (int i = 0; i < localOrderItemList.size(); i++) {
                localOrderItemList.get(i).setAmazonOrderId(localOrder.getAmazonOrderId());
                localOrderItemList.get(i).setStatus("1");
                localOrderItemList.get(i).setShippingPriceRMB(localOrderItemList.get(i).getShippingPrice() * Double.parseDouble(exrate));
                localOrderItemList.get(i).setGiftWrapPriceRMB(localOrderItemList.get(i).getGiftWrapPrice() * Double.parseDouble(exrate));
                localOrderItemList.get(i).setCommissionRMB(localOrderItemList.get(i).getCommission() * Double.parseDouble(exrate));
                orderManageService.insertOrderItem(localOrderItemList.get(i));
            }
            map.put("code", "0");
            map.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 获取订单操作日志
     *
     * @param amazonOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectOperationLog", produces = "text/plain;charset=UTF-8")
    public String selectOperationLog(String amazonOrderId) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Map<String, Object>> operationLogList = orderManageService.selectOperationLog(amazonOrderId);
            map.put("data", operationLogList);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    public void insertOperationLog(String amazonOrderId, String preStatus, String status, String userId, String cost) {
        Map<String, Object> operationMap = new HashMap<>();
        operationMap.put("user", userId);
        operationMap.put("time", simpleDateFormat.format(new Date()));
        operationMap.put("amazonOrderId", amazonOrderId);
        operationMap.put("type", status);
        if (status.equals("0")) {
            operationMap.put("info", "产品" + amazonOrderId + "添加了备注");
        } else {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【" + Tools.getStatusStr(preStatus) + "】变为【" + Tools.getStatusStr(status) + "】；成本由【0.00】变为【" + cost + "】");
        }
        orderManageService.inserOperationLog(operationMap);
    }


}
