package com.crossborder.action;

import com.alibaba.fastjson.JSON;
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
import com.crossborder.utils.ExcelRead;
import com.crossborder.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
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
                getOrderItemFees(shop);
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
            return JSON.toJSONString(map);
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
                if (order.getOrderStatus().equals("Unshipped")) {

                }
                //插入订单信息
                LocalOrder localOrder = new LocalOrder();
                localOrder.setAmazonOrderId(order.getAmazonOrderId());
                localOrder.setOrderStatus(order.getOrderStatus());
                localOrder.setLocalStatus("1");
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
                orderManageService.insertOrders(localOrder);
                AddressInfo addressInfo = new AddressInfo();
                addressInfo.setAmazonOrderId(order.getAmazonOrderId());
                addressInfo.setName(order.getShippingAddress().getName());
                addressInfo.setCity(order.getShippingAddress().getCity());
                addressInfo.setAddressLine1(order.getShippingAddress().getAddressLine1());
                addressInfo.setAddressLine2(order.getShippingAddress().getAddressLine2());
                addressInfo.setAddressLine3(order.getShippingAddress().getAddressLine3());
                addressInfo.setCompany(order.getShippingAddress().getName());
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
                    Product product = getProduct(orderItem.getASIN(), shop);
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
                    localOrderItem.setQuantityShipped(orderItem.getQuantityShipped());
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
        return JSON.toJSONString(map);
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

    public void getOrderItemFees(Map<String, Object> shop) {
        try {
            MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
            config.setServiceURL(shop.get("ENDPOINT").toString());
            MarketplaceWebServiceClient client = new MarketplaceWebServiceClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), "", "", config);
            GetReportListRequest request = new GetReportListRequest();
            request.setMerchant(shop.get("MERCHANT_ID").toString());
            TypeList typeList = new TypeList();
            List<String> types = new ArrayList<>();
            types.add("_GET_FLAT_FILE_ACTIONABLE_ORDER_DATA_");
            typeList.setType(types);
            request.setReportTypeList(typeList);
            GetReportListResponse response = client.getReportList(request);
            List<ReportInfo> reportInfos = response.getGetReportListResult().getReportInfoList();
        } catch (Exception e) {
            e.printStackTrace();
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

    public Product getProduct(String asin, Map<String, Object> shop) {
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
        asins.add(asin);
        idList.setId(asins);
        request.setIdList(idList);
        GetMatchingProductForIdResponse response = client.getMatchingProductForId(request);
        return response.getGetMatchingProductForIdResult().get(0).getProducts().getProduct().get(0);
    }

    /**
     * 查询订单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectLocalOrder", produces = "text/plain;charset=UTF-8")
    public String selectLocalOrder(String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        List<Map<String, Object>> localOrderList = new ArrayList<>();
        if (paramMap.get("localStatus").toString().equals("0")) {
            paramMap.put("localStatus", "");
        }
        paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
        paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
        try {
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            localOrderList = orderManageService.selectLocalOrder(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(localOrderList);
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
        return JSON.toJSONString(map);
    }

    /**
     * 查询订单item
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectLocalOrderItem", produces = "text/plain;charset=UTF-8")
    public String selectLocalOrderItem(String amazonOrderId, String sku) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("amazonOrderId", amazonOrderId);
        paramMap.put("sku", sku);
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
        return JSON.toJSONString(map);
    }

    /**
     * 查询订单详情
     *
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
        return JSON.toJSONString(map);
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
                    item.put("amazonOrderId", arr.get(0));
                    item.put("intlTrackNum", arr.get(1));
                    item.put("shippingPrice", arr.get(2));
                    orderManageService.updateOrder(item);
                }
                map.put("code", "0");
                map.put("msg", "更新成功");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", "-10");
                map.put("msg", "更新失败");
            }
        }
        return JSON.toJSONString(map);
    }

    /**
     * 更新订单信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateOrderInfo", produces = "text/plain;charset=UTF-8")
    public String updateOrderInfo(String amazonOrderId, String status, String sku, String cost, String refundment, String trackNum, String purchaseNum, String shippingPrice) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        paramMap.put("sku", sku);
        paramMap.put("amazonOrderId", amazonOrderId);
        paramMap.put("cost", cost);
        paramMap.put("refundment", refundment);
        paramMap.put("trackNum", trackNum);
        paramMap.put("purchaseNum", purchaseNum);
        paramMap.put("shippingPrice", shippingPrice);
        try {
            orderManageService.updateOrder(paramMap);
            orderManageService.updateOrderItem(paramMap);
            insertOperationLog(amazonOrderId, status);
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map);
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
    public String updateOrderRemark(String remark, String amazonOrderId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("remark", remark);
        paramMap.put("amazonOrderId", amazonOrderId);
        try {
            orderManageService.updateOrderRemark(paramMap);
            insertOperationLog(amazonOrderId, "0");
            map.put("code", "0");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "更新失败");
        }
        return JSON.toJSONString(map);
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
        return JSON.toJSONString(map);
    }

    public void insertOperationLog(String amazonOrderId, String type) {
        Map<String, Object> operationMap = new HashMap<>();
        operationMap.put("user", "libing");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        operationMap.put("time", simpleDateFormat.format(new Date()));
        operationMap.put("amazonOrderId", amazonOrderId);
        operationMap.put("type", type);
        if (type.equals("0")) {
            operationMap.put("info", "产品" + amazonOrderId + "添加了备注");
        } else if (type.equals("2")) {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【新单】变为【备货】；成本由【0.00】变为【】");
        } else if (type.equals("3")) {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【新单】变为【缺货】；成本由【0.00】变为【】");
        } else if (type.equals("4")) {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【新单】变为【发货】；成本由【0.00】变为【】");
        } else if (type.equals("5")) {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【新单】变为【问题】；成本由【0.00】变为【】");
        } else if (type.equals("6")) {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【新单】变为【退款】；成本由【0.00】变为【】");
        } else if (type.equals("7")) {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【新单】变为【妥投】；成本由【0.00】变为【】");
        } else if (type.equals("8")) {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【新单】变为【代发】；成本由【0.00】变为【】");
        }
        orderManageService.inserOperationLog(operationMap);
    }

}
