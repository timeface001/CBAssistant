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
        List<Map<String, Object>> shops = shopManageService.selectShops(shopMap);
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
                List<String> marketplaceId = new ArrayList<String>();
                marketplaceId.add(shop.get("MARKETPLACEID").toString());
                request.setMarketplaceId(marketplaceId);
                List<String> orderStatus = new ArrayList<String>();
                orderStatus.add("Unshipped");
                orderStatus.add("PartiallyShipped");
                orderStatus.add("Shipped");
                request.setOrderStatus(orderStatus);
                result = getListOrders(client, request, user, shop);
            }
            /*updateOrderStatusTest();*/
            return result;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "1");
            map.put("msg", "您还未授权店铺，快去授权吧！");
            return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        }
    }

    public String getListOrders(MarketplaceWebServiceOrders client,
                                ListOrdersRequest request, Map<String, Object> user, Map<String, Object> shop) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ListOrdersResponse response = client.listOrders(request);
            List<Order> orderList = response.getListOrdersResult().getOrders();
            List<LocalOrder> localOrderList = new ArrayList<>();
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
                orderManageService.insertAddress(addressInfo);
                //获取订单详情
                List<ShipmentItem> shipmentItems = getOrderItemFees(shop, order.getAmazonOrderId());
                List<OrderItem> orderItemList = getOrderItem(order.getAmazonOrderId(), shop);
                List<LocalOrderItem> localOrderItemList = new ArrayList<>();
                for (int j = 0; j < orderItemList.size(); j++) {
                    OrderItem orderItem = orderItemList.get(j);
                    Product product = getProduct(orderItem, shop);
                    LocalOrderItem localOrderItem = new LocalOrderItem();
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
                    localOrderItem.setOrderItemId(orderItem.getOrderItemId());
                    localOrderItem.setAmazonOrderId(order.getAmazonOrderId());
                    localOrderItem.setAsin(orderItem.getASIN());
                    localOrderItem.setCurrencyCode(orderItem.getItemPrice().getCurrencyCode());
                    localOrderItem.setItemPrice(Double.parseDouble(orderItem.getItemPrice().getAmount()));
                    localOrderItem.setQuantityShipped(orderItem.getQuantityOrdered());
                    localOrderItem.setSellerSKU(orderItem.getSellerSKU());
                    if (orderItem.getShippingPrice() != null) {
                        localOrderItem.setShippingPrice(Double.parseDouble(orderItem.getShippingPrice().getAmount()));
                        localOrderItem.setShippingPriceRMB(Double.parseDouble(orderItem.getShippingPrice().getAmount()) * Double.parseDouble(shop.get("EXRATE").toString()));
                    } else {
                        localOrderItem.setShippingPrice(0);
                        localOrderItem.setShippingPriceRMB(0);
                    }
                    if (orderItem.getGiftWrapPrice() != null) {
                        localOrderItem.setGiftWrapPrice(Double.parseDouble(orderItem.getGiftWrapPrice().getAmount()));
                        localOrderItem.setGiftWrapPriceRMB(Double.parseDouble(orderItem.getGiftWrapPrice().getAmount()) * Double.parseDouble(shop.get("EXRATE").toString()));
                    } else {
                        localOrderItem.setGiftWrapPrice(0);
                        localOrderItem.setGiftWrapPriceRMB(0);
                    }
                    for (int m = 0; m < shipmentItems.size(); m++) {
                        List<FeeComponent> feeComponents = shipmentItems.get(m).getItemFeeList();
                        double fees = 0;
                        for (int n = 0; n < feeComponents.size(); n++) {
                            fees = fees + feeComponents.get(n).getFeeAmount().getCurrencyAmount().doubleValue();
                        }
                        if (shipmentItems.get(m).getOrderItemId().equals(orderItem.getOrderItemId())) {
                            localOrderItem.setCommission(fees);
                            localOrderItem.setCommissionRMB(fees * Double.parseDouble(shop.get("EXRATE").toString()));
                        }
                    }
                    localOrderItem.setItemPriceRMB(Double.parseDouble(orderItem.getItemPrice().getAmount()) * Double.parseDouble(shop.get("EXRATE").toString()));
                    localOrderItem.setStatus("1");
                    localOrderItem.setCost(0);
                    localOrderItem.setRefundment(0);
                    localOrderItem.setTitle(orderItem.getTitle());
                    localOrderItemList.add(localOrderItem);
                    orderManageService.insertOrderItem(localOrderItem);
                }
                localOrder.setLocalOrderItemList(localOrderItemList);
                localOrderList.add(localOrder);
            }
            /*if (localOrderList.size() > 0) {
                updateOrderStatus(localOrderList,shop);
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

    public void updateOrderStatus(List<LocalOrder> localOrderList, Map<String, Object> shop) {
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">" +
                    "<Header>" +
                    "<DocumentVersion>1.01</DocumentVersion>" +
                    "<MerchantIdentifier>" + shop.get("MARKETPLACEID").toString() + "</MerchantIdentifier>" +
                    "</Header>" +
                    "<MessageType>OrderFulfillment</MessageType>";
            String message = "";
            for (int i = 0; i < localOrderList.size(); i++) {
                message = message + "<Message>" +
                        "<MessageID>" + (i + 1) + "</MessageID>" +
                        "<OrderFulfillment>" +
                        "<MerchantOrderID>" + localOrderList.get(i).getAmazonOrderId() + "</MerchantOrderID>" +
                        "<MerchantFulfillmentID>" + new Date().getTime() + "</MerchantFulfillmentID>" +
                        "<FulfillmentDate>" + DatatypeFactory.newInstance().newXMLGregorianCalendar(cal) + "</FulfillmentDate>" +
                        "<FulfillmentData>" +
                        "<CarrierCode>Yun Express</CarrierCode>" +
                        "<ShippingMethod>SMT-XSA-F</ShippingMethod>" +
                        "<ShipperTrackingNumber>" + Tools.createIntlTrackNum() + "</ShipperTrackingNumber>" +
                        "</FulfillmentData>" +
                        "<Item>" +
                        "<MerchantOrderItemID>" + localOrderList.get(i).getLocalOrderItemList().get(0).getOrderItemId() + "</MerchantOrderItemID>" +
                        "<MerchantFulfillmentItemID>" + new Date().getTime() + "</MerchantFulfillmentItemID>" +
                        "<Quantity>" + localOrderList.get(i).getLocalOrderItemList().get(0).getQuantityShipped() + "</Quantity>" +
                        "</Item>" +
                        "</OrderFulfillment>" +
                        "</Message>";
            }
            String end = "</AmazonEnvelope>";
            StringBuffer stringBuffer = new StringBuffer();
            String fulfillmentXml = stringBuffer.append(header).append(message).append(end).toString();
            FileWriter writer = new FileWriter("d:/home/test.txt");
            writer.write(fulfillmentXml);
            writer.flush();
            writer.close();
            FileInputStream fis = new FileInputStream(new File("d:/home/test.txt"));
            MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
            config.setServiceURL(shop.get("ENDPOINT").toString());
            MarketplaceWebService service = new MarketplaceWebServiceClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), "", "", config);
            SubmitFeedRequest request = new SubmitFeedRequest();
            IdList idList = new IdList();
            List<String> ids = new ArrayList<>();
            ids.add(shop.get("MARKETPLACEID").toString());
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
            } else {

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
                    "<MerchantIdentifier>A38JYHNQ83XYNO</MerchantIdentifier>" +
                    "</Header>" +
                    "<MessageType>OrderFulfillment</MessageType>";
            String message = "<Message>" +
                    "<MessageID>1</MessageID>" +
                    "<OrderFulfillment>" +
                    "<AmazonOrderID>403-6887564-6894727</AmazonOrderID>" +
                    "<FulfillmentDate>" + DatatypeFactory.newInstance().newXMLGregorianCalendar(cal) + "</FulfillmentDate>" +
                    "<FulfillmentData>" +
                    "<CarrierCode>Yun Express</CarrierCode>" +
                    "<ShippingMethod>SMT-XSA-F</ShippingMethod>" +
                    "<ShipperTrackingNumber>" + Tools.createIntlTrackNum() + "</ShipperTrackingNumber>" +
                    "</FulfillmentData>" +
                    "<Item>" +
                    "<AmazonOrderItemCode>06743192371019</AmazonOrderItemCode>" +
                    "<Quantity>1</Quantity>" +
                    "</Item>" +
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
            MarketplaceWebService service = new MarketplaceWebServiceClient("AKIAID6IAW2WZTGHFZKQ", "6CB0q8aTXwwjTUIee2p6cJNsHsIoHr6DIb0ajxt2", "", "", config);
            SubmitFeedRequest request = new SubmitFeedRequest();
            IdList idList = new IdList();
            List<String> ids = new ArrayList<>();
            ids.add("A13V1IB3VIYZZH");
            idList.setId(ids);
            request.setMarketplaceIdList(idList);
            request.setMerchant("A38JYHNQ83XYNO");
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
            } else {

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

    public Product getProduct(OrderItem orderItem, Map<String, Object> shop) {
        MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig();
        config.setServiceURL(shop.get("ENDPOINT").toString() + "/Products/2011-10-01");
        MarketplaceWebServiceProductsClient client = new MarketplaceWebServiceProductsClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), config);
        GetMatchingProductForIdRequest request = new GetMatchingProductForIdRequest();
        request.setSellerId(shop.get("MERCHANT_ID").toString());
        request.setMWSAuthToken("");
        request.setMarketplaceId(shop.get("MARKETPLACEID").toString());
        request.setIdType("ASIN");
        IdListType idList = new IdListType();
        List<String> asins = new ArrayList<>();
        asins.add(orderItem.getASIN());
        idList.setId(asins);
        request.setIdList(idList);
        GetMatchingProductForIdResponse response = client.getMatchingProductForId(request);
        return response.getGetMatchingProductForIdResult().get(0).getProducts().getProduct().get(0);
    }

    /**
     * 获取佣金
     *
     * @param amazonOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCommission", produces = "text/plain;charset=UTF-8")
    public String getCommission(String amazonOrderId, String merchantId, String countryCode) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> shopMap = new HashMap<>();
            shopMap.put("merchantId", merchantId);
            shopMap.put("countryCode", countryCode);
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
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(localOrderList);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < localOrderList.size(); i++) {
                Date createDate = sdf.parse(localOrderList.get(i).get("PURCHASEDATE").toString());
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
        try {
            orderManageService.updateOrder(paramMap);
            orderManageService.updateOrderItem(paramMap);
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
