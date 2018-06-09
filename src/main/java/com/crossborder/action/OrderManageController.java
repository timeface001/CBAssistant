package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.amazon.mws.finances._2015_05_01.MWSFinancesServiceClient;
import com.amazon.mws.finances._2015_05_01.MWSFinancesServiceConfig;
import com.amazon.mws.finances._2015_05_01.model.FeeComponent;
import com.amazon.mws.finances._2015_05_01.model.FinancialEvents;
import com.amazon.mws.finances._2015_05_01.model.ListFinancialEventsRequest;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.model.IdList;
import com.amazonaws.mws.model.SubmitFeedRequest;
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
import com.crossborder.utils.ExcelRead;
import com.crossborder.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.io.InputStream;
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
            int count = 0;
            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                List<FeeComponent> feeComponents = getOrderItemFees(shop, order.getAmazonOrderId());
                double fees = 0;
                for (int m = 0; m < feeComponents.size(); m++) {
                    fees = fees + feeComponents.get(m).getFeeAmount().getCurrencyAmount().doubleValue();
                }
                //插入订单信息
                LocalOrder localOrder = new LocalOrder();
                localOrder.setAmazonOrderId(order.getAmazonOrderId());
                localOrder.setOrderStatus(order.getOrderStatus());
                localOrder.setSellerOrderId(order.getSellerOrderId());
                localOrder.setLocalStatus("1");
                localOrder.setCommission(fees * Double.parseDouble(shop.get("EXRATE").toString()));
                Date createDate = order.getPurchaseDate().toGregorianCalendar().getTime();
                localOrder.setPurchaseDate(simpleDateFormat.format(createDate));
                Date updateDate = order.getLastUpdateDate().toGregorianCalendar().getTime();
                localOrder.setLastUpdateDate(simpleDateFormat.format(updateDate));
                localOrder.setCurrencyCode(order.getOrderTotal().getCurrencyCode());
                localOrder.setOrderTotal(Double.parseDouble(order.getOrderTotal().getAmount()));
                localOrder.setNumberOfItemsShipped(order.getNumberOfItemsShipped());
                localOrder.setNumberOfItemsUnshipped(order.getNumberOfItemsUnshipped());
                localOrder.setBuyerCounty(order.getShippingAddress().getCountryCode());
                localOrder.setBuyerName(order.getBuyerName());
                localOrder.setFulfillmentChannel(order.getFulfillmentChannel());
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
                List<OrderItem> orderItemList = getOrderItem(order.getAmazonOrderId(), shop);
                for (int j = 0; j < orderItemList.size(); j++) {
                    OrderItem orderItem = orderItemList.get(j);
                    //Product product = getProduct(orderItem, shop);
                    LocalOrderItem localOrderItem = new LocalOrderItem();
                    /*for (Object obj : product.getAttributeSets().getAny()) {
                        Node nd = (Node) obj;
                        for (int m = 0; m < nd.getChildNodes().getLength(); i++) {
                            Node child = nd.getChildNodes().item(i);
                            if ("ns2:SmallImage".equals(child.getNodeName())) {
                                for (int n = 0; n < child.getChildNodes().getLength(); n++) {
                                    if ("ns2:URL".equals(child.getChildNodes().item(n).getNodeName())) {
                                        localOrderItem.setSmallImage(child.getChildNodes().item(n).getTextContent());
                                    }
                                }
                            }
                        }
                    }*/
                    localOrderItem.setOrderItemId(orderItem.getOrderItemId());
                    localOrderItem.setAmazonOrderId(order.getAmazonOrderId());
                    localOrderItem.setAsin(orderItem.getASIN());
                    localOrderItem.setCurrencyCode(orderItem.getItemPrice().getCurrencyCode());
                    localOrderItem.setItemPrice(Double.parseDouble(orderItem.getItemPrice().getAmount()));
                    localOrderItem.setQuantityShipped(orderItem.getQuantityOrdered());
                    localOrderItem.setSellerSKU(orderItem.getSellerSKU());
                    localOrderItem.setShippingPrice(Double.parseDouble(orderItem.getShippingPrice().getAmount()));
                    localOrderItem.setItemPriceRMB(Double.parseDouble(orderItem.getItemPrice().getAmount()) * Double.parseDouble(shop.get("EXRATE").toString()));
                    localOrderItem.setShippingPriceRMB(Double.parseDouble(orderItem.getShippingPrice().getAmount()) * Double.parseDouble(shop.get("EXRATE").toString()));
                    localOrderItem.setStatus("1");
                    localOrderItem.setCost(0);
                    localOrderItem.setRefundment(0);
                    localOrderItem.setTitle(orderItem.getTitle());
                    orderManageService.insertOrderItem(localOrderItem);
                }
            }
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

    public void updateOrderStatus(String amazonOrderId, Map<String, Object> shop) {
        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
        config.setServiceURL(shop.get("ENDPOINT").toString());
        MarketplaceWebServiceClient client = new MarketplaceWebServiceClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), "", "", config);
        SubmitFeedRequest request = new SubmitFeedRequest();
        IdList idList = new IdList();
        List<String> ids = new ArrayList<>();
        ids.add(shop.get("MARKETPLACEID").toString());
        idList.setId(ids);
        request.setMarketplaceIdList(idList);
        request.setMerchant(shop.get("MERCHANT_ID").toString());
        request.setFeedContent(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });
        request.setFeedType(Order_Fulfillment_Fee);
        request.setContentMD5("");
    }

    public List<FeeComponent> getOrderItemFees(Map<String, Object> shop, String amazonOrderId) {
        MWSFinancesServiceConfig config = new MWSFinancesServiceConfig();
        config.setServiceURL(shop.get("ENDPOINT").toString());
        MWSFinancesServiceClient client = new MWSFinancesServiceClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), "", "", config);
        ListFinancialEventsRequest request = new ListFinancialEventsRequest();
        request.setSellerId(shop.get("MERCHANT_ID").toString());
        request.setAmazonOrderId(amazonOrderId);
        FinancialEvents financialEvents = client.listFinancialEvents(request).getListFinancialEventsResult().getFinancialEvents();
        if (financialEvents.getShipmentEventList() != null && financialEvents.getShipmentEventList().size() > 0) {
            return financialEvents.getShipmentEventList().get(0).getShipmentItemList().get(0).getItemFeeList();
        } else {
            return new ArrayList<FeeComponent>();
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
       /* List<FeesEstimateRequest> feesEstimateRequests = new ArrayList<>();
        FeesEstimateRequest feesEstimateRequest = new FeesEstimateRequest();
        feesEstimateRequest.setMarketplaceId(shop.get("MARKETPLACEID").toString());
        feesEstimateRequest.setIdType("ASIN");
        feesEstimateRequest.setIdValue(orderItem.getASIN());
        PriceToEstimateFees priceToEstimateFees = new PriceToEstimateFees();
        MoneyType listingPrice = new MoneyType();
        listingPrice.setAmount(new BigDecimal(orderItem.getItemPrice().getAmount()));
        listingPrice.setCurrencyCode(orderItem.getItemPrice().getCurrencyCode());
        priceToEstimateFees.setListingPrice(listingPrice);
        feesEstimateRequest.setPriceToEstimateFees(priceToEstimateFees);
        feesEstimateRequest.setIdentifier("00001");
        feesEstimateRequest.setIsAmazonFulfilled(true);
        feesEstimateRequests.add(feesEstimateRequest);
        MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig();
        config.setServiceURL(shop.get("ENDPOINT").toString());
        MarketplaceWebServiceProductsClient client = new MarketplaceWebServiceProductsClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), config);
        GetMyFeesEstimateRequest request = new GetMyFeesEstimateRequest();
        request.setSellerId(shop.get("MERCHANT_ID").toString());
        request.setMWSAuthToken("");
        FeesEstimateRequestList feesEstimateRequestList = new FeesEstimateRequestList();
        feesEstimateRequestList.setFeesEstimateRequest(feesEstimateRequests);
        request.setFeesEstimateRequestList(feesEstimateRequestList);
        GetMyFeesEstimateResponse response = client.getMyFeesEstimate(request);*/
        MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig();
        config.setServiceURL(shop.get("ENDPOINT").toString());
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
     * @param session
     * @param amazonOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCommission", produces = "text/plain;charset=UTF-8")
    public String getCommission(HttpSession session, String amazonOrderId) {
        Map<String, Object> map = new HashMap<>();
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
     * 根据上传文件批量更新订单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateByExcel", produces = "text/plain;charset=UTF-8")
    public String updateByExcel(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (file == null || file.getSize() == 0) {
            map.put("code", "-10");
            map.put("msg", "上传失败");
        } else {
            try {
                List<ArrayList<String>> list = new ExcelRead().readExcel(file);
                for (ArrayList<String> arr : list) {
                    Map<String, Object> item = new HashMap<>();
                    if (!StringUtils.isEmpty(arr.get(0))) {
                        item.put("amazonOrderId", arr.get(0));
                        item.put("intlTrackNum", arr.get(1));
                        item.put("shippingPrice", arr.get(2));
                        orderManageService.updateOrder(item);
                    }
                }
                map.put("code", "0");
                map.put("msg", "更新成功");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", "-10");
                map.put("msg", "更新失败");
            }
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
