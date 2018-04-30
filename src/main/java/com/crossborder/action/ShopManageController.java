package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersConfig;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersResponse;
import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.crossborder.service.CommonService;
import com.crossborder.service.ShopManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

/**
 * Created by s on 2018/4/7.
 */
@Controller
@RequestMapping("/shop")
public class ShopManageController {
    @Resource
    private ShopManageService shopManageService;
    @Resource
    private CommonService commonService;

    /**
     * 添加店铺
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addShop", produces = "text/plain;charset=UTF-8")
    public String addShop(String data, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        paramMap.put("createUser", user.get("USER_ID").toString());
        paramMap.put("shopName", paramMap.get("shopName") + "-" + user.get("USER_NAME").toString());
        try {
            List<Map<String, Object>> shops = shopManageService.selectShops(paramMap);
            if (shops != null && shops.size() > 0) {
                map.put("code", "1");
                map.put("msg", "店铺名称已存在");
            } else {
                List<Map<String, Object>> list = commonService.selectCountryByCode(paramMap.get("countryCode").toString());
                paramMap.put("marketPlaceId", list.get(0).get("MARKETPLACEID").toString());
                paramMap.put("endPoint", list.get(0).get("ENDPOINT").toString());
                if (validateShop(paramMap)) {
                    for (int i = 0; i < list.size(); i++) {
                        paramMap.put("countryCode", list.get(i).get("ID").toString());
                        shopManageService.addShop(paramMap);
                    }
                    map.put("code", "0");
                    map.put("msg", "授权成功");
                } else {
                    map.put("code", "-10");
                    map.put("msg", "授权失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "授权失败");
        }
        return JSON.toJSONString(map);
    }

    public boolean validateShop(Map<String, Object> shop) {
        try {
            MarketplaceWebServiceOrdersConfig config = new MarketplaceWebServiceOrdersConfig();
            config.setServiceURL(shop.get("endPoint").toString());
            MarketplaceWebServiceOrdersClient client = new MarketplaceWebServiceOrdersClient(shop.get("accesskeyId").toString(), shop.get("secretKey").toString(), config);
            ListOrdersRequest request = new ListOrdersRequest();
            request.setSellerId(shop.get("merchantId").toString());
            request.setMWSAuthToken("");
            GregorianCalendar cal = new GregorianCalendar();
            XMLGregorianCalendar createdAfter = null;
            XMLGregorianCalendar createdBefore = null;
            try {
                cal.setTime(new Date(new Date().getTime() - 2 * 24 * 60 * 60 * 1000));
                createdAfter = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                cal.setTime(new Date(new Date().getTime() - 24 * 60 * 60 * 1000));
                createdBefore = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setCreatedAfter(createdAfter);
            request.setCreatedBefore(createdBefore);
            List<String> marketplaceId = new ArrayList<String>();
            marketplaceId.add(shop.get("marketPlaceId").toString());
            request.setMarketplaceId(marketplaceId);
            List<String> orderStatus = new ArrayList<String>();
            orderStatus.add("Unshipped");
            orderStatus.add("PartiallyShipped");
            orderStatus.add("Shipped");
            request.setOrderStatus(orderStatus);
            ListOrdersResponse response = client.listOrders(request);
            List<Order> orderList = response.getListOrdersResult().getOrders();
            if (orderList != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询店铺
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectShops", produces = "text/plain;charset=UTF-8")
    public String selectShops(HttpSession session, String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").equals("600")) {
            paramMap.put("createUser", user.get("USER_ID").toString());
        }
        if (user.get("ROLE_ID").equals("500")) {
            paramMap.put("companyId", user.get("USER_COMPANY").toString());
        }
        paramMap.put("id", id);
        try {
            List<Map<String, Object>> list = shopManageService.selectShops(paramMap);
            map.put("data", list);
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
     * 查询店铺
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectShopsById", produces = "text/plain;charset=UTF-8")
    public String selectShopsById(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").equals("600")) {
            paramMap.put("userId", user.get("USER_ID").toString());
        } else if (user.get("ROLE_ID").equals("500")) {
            paramMap.put("companyId", user.get("USER_COMPANY").toString());
        }
        try {
            List<Map<String, Object>> list = shopManageService.selectShopsById(paramMap);
            map.put("data", list);
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
     * 修改店铺信息
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateShop", produces = "text/plain;charset=UTF-8")
    public String updateShop(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        try {
            int count = shopManageService.updateShop(paramMap);
            map.put("count", count);
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
     * 启用或停用
     *
     * @param id
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateShopState", produces = "text/plain;charset=UTF-8")
    public String updateShopState(String id, String state) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("state", state);
        try {
            int count = shopManageService.updateShopState(paramMap);
            map.put("count", count);
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
     * 删除店铺
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteShop", produces = "text/plain;charset=UTF-8")
    public String deleteShop(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = shopManageService.deleteShop(id);
            map.put("count", count);
            map.put("code", "0");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败");
        }
        return JSON.toJSONString(map);
    }
}
