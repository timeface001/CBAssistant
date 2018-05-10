package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossborder.entity.Menu;
import com.crossborder.service.CommonService;
import com.crossborder.service.OrderManageService;
import com.crossborder.utils.HttpClientUtil;
import com.crossborder.utils.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by s on 2018/4/2.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Resource
    private CommonService commonService;
    @Resource
    private OrderManageService orderManageService;

    /**
     * 登录
     *
     * @param userId
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", produces = "text/plain;charset=UTF-8")
    public String login(String userId, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        try {
            paramMap.put("userId", userId);
            paramMap.put("password", password);
            List<Map<String, Object>> list = commonService.login(paramMap);
            if (list != null && list.size() == 1) {
               /* if (list.get(0).get("ISLOGIN").toString().equals("1")) {
                    map.put("code", "1");
                    map.put("msg", "当前用户已登录");
                } else {*/
                paramMap.put("isLogin", 1);
                commonService.updateLogin(paramMap);
                session.setAttribute("user", list.get(0));
                map.put("data", list.get(0));
                map.put("code", "0");
                map.put("msg", "登录成功");
               /* }*/
            } else {
                map.put("code", "-10");
                map.put("msg", "登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "登录失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 登出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logout", produces = "text/plain;charset=UTF-8")
    public String logout(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userId", user.get("USER_ID"));
            paramMap.put("isLogin", 0);
            commonService.updateLogin(paramMap);
            session.removeAttribute("user");
            session.invalidate();
            map.put("code", "0");
            map.put("msg", "退出成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "退出失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 获取菜单
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "selectMenus", produces = "text/plain;charset=UTF-8")
    public String selectMenus(Model model, String roleId, String userId) {
        Map<String, Object> paramMap = new HashMap<>();
        try {
            paramMap.put("roleId", roleId);
            List<Map<String, Object>> list = commonService.selectMenus(paramMap);
            List<Menu> menus = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Menu menu = new Menu();
                if (list.get(i).get("MENU_PID").toString().equals("0")) {
                    menu.setMenuId(list.get(i).get("MENU_ID").toString());
                    menu.setMenuPid(list.get(i).get("MENU_PID").toString());
                    menu.setPath(list.get(i).get("PATH").toString());
                    menu.setMenuName(list.get(i).get("MENU_NAME").toString());
                    menu.setIconPath(list.get(i).get("ICONPATH").toString());
                    List<Menu> childMenus = new ArrayList<>();
                    for (int j = 0; j < list.size(); j++) {
                        Menu childMenu = new Menu();
                        if (list.get(i).get("MENU_ID").toString().equals(list.get(j).get("MENU_PID").toString())) {
                            childMenu.setMenuId(list.get(j).get("MENU_ID").toString());
                            childMenu.setMenuPid(list.get(j).get("MENU_PID").toString());
                            childMenu.setPath(list.get(j).get("PATH").toString());
                            childMenu.setMenuName(list.get(j).get("MENU_NAME").toString());
                            childMenus.add(childMenu);
                        }
                        menu.setMenuList(childMenus);
                    }
                    menus.add(menu);
                }
            }
            model.addAttribute("menus", menus);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "assistant/index/main";
    }

    /**
     * 通用查询接口，主要用来查询下拉列表
     *
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getList", produces = "text/plain;charset=UTF-8")
    public String getList(String code) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> sqlMap = commonService.getSql(code);
            List<Map<String, Object>> list = commonService.getList(sqlMap.get("SQL_TEXT").toString());
            map.put("data", list);
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
     * 通用查询接口，主要用来查询tree列表
     *
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTreeMenus", produces = "text/plain;charset=UTF-8")
    public String getTreeMenus(String code) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> sqlMap = commonService.getSql(code);
            List<Map<String, Object>> list = commonService.getTreeList(sqlMap.get("SQL_TEXT").toString());
            List<Menu> menus = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Menu menu = new Menu();
                if (list.get(i).get("MENU_PID").toString().equals("0")) {
                    menu.setMenuId(list.get(i).get("MENU_ID").toString());
                    menu.setMenuPid(list.get(i).get("MENU_PID").toString());
                    menu.setPath(list.get(i).get("PATH").toString());
                    menu.setMenuName(list.get(i).get("MENU_NAME").toString());
                    menu.setIconPath(list.get(i).get("ICONPATH").toString());
                    List<Menu> childMenus = new ArrayList<>();
                    for (int j = 0; j < list.size(); j++) {
                        Menu childMenu = new Menu();
                        if (list.get(i).get("MENU_ID").toString().equals(list.get(j).get("MENU_PID").toString())) {
                            childMenu.setMenuId(list.get(j).get("MENU_ID").toString());
                            childMenu.setMenuPid(list.get(j).get("MENU_PID").toString());
                            childMenu.setPath(list.get(j).get("PATH").toString());
                            childMenu.setMenuName(list.get(j).get("MENU_NAME").toString());
                            childMenus.add(childMenu);
                        }
                        menu.setMenuList(childMenus);
                    }
                    menus.add(menu);
                }
            }
            map.put("data", menus);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "getShipTypes", produces = "text/plain;charset=UTF-8")
    public String getShipTypes(String countryCode, String companyId) {
        //http://www.sendfromchina.com/shipfee/ship_type_list
        Map<String, Object> map = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>();
        try {
            String result = HttpClientUtil.doGetRequest("http://api.yunexpress.com/LMS.API/api/lms/Get?countryCode=" + countryCode);
            /*JSONObject jsonObject = XmlUtil.xml2JSON(result.getBytes());
            JSONArray res = jsonObject.getJSONObject("GetShipTypesListResponse").getJSONArray("shiptypes").getJSONObject(0).getJSONArray("shiptype");*/
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("Item");
            List<Map<String, String>> ships = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                /*Map<String, Object> jsonMap = (Map<String, Object>) jsonArray.get(i);
                jsonMap.put("state", "1");
                jsonMap.put("countryId", countryCode);
                jsonMap.put("companyId", companyId);
                commonService.insertShips(jsonMap);*/
                Map<String, String> shipMap = new HashMap<>();
                shipMap.put("code", jsonArray.getJSONObject(i).getString("Code"));
                shipMap.put("name", jsonArray.getJSONObject(i).getString("FullName"));
                ships.add(shipMap);
            }
            map.put("data", ships);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
    /*@ResponseBody
    @RequestMapping(value = "getShipTypes", produces = "text/plain;charset=UTF-8")
    public String getShipTypes(String companyId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("companyId", companyId);
        try {
            List<Map<String, String>> ships = commonService.getShipTypes(paramMap);
            map.put("data", ships);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }*/

    @ResponseBody
    @RequestMapping(value = "confirmOrder", produces = "text/plain;charset=UTF-8")
    public String confirmOrder(String amazonOrderId, String json, HttpSession session) {
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseArray(json).getJSONObject(0);
        JSONObject shippingObject = jsonObject.getJSONObject("ShippingInfo");
        Map<String, Object> shippingMap = shippingObject;
        shippingMap.put("amazonOrderId", amazonOrderId);
        try {
            String result = HttpClientUtil.doPostRequest("http://api.yunexpress.com/LMS.API/api/WayBill/BatchAdd", json);
            JSONObject resultObject = JSONObject.parseObject(result);
            if (resultObject.getString("ResultCode").equals("0000")) {
                commonService.updateAddress(shippingMap);
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("amazonOrderId", amazonOrderId);
                paramMap.put("status", "4");
                paramMap.put("transportCompany", jsonObject.getString("transportCompany"));
                orderManageService.updateOrder(paramMap);
                orderManageService.updateOrderItem(paramMap);
                insertOperationLog(amazonOrderId, "2", "4", user.get("USER_ID").toString(), "");
                Map<String, Object> shipMap = new HashMap<>();
                shipMap.put("amazonOrderId", amazonOrderId);
                shipMap.put("companyId", jsonObject.getString("transportCompany"));
                shipMap.put("typeId", jsonObject.getString("ShippingMethodCode"));
                shipMap.put("custId", jsonObject.getString("OrderNumber"));
                shipMap.put("length", jsonObject.getString("Length"));
                shipMap.put("width", jsonObject.getString("Width"));
                shipMap.put("height", jsonObject.getString("Height"));
                shipMap.put("packages", jsonObject.getString("PackageNumber"));
                shipMap.put("weight", jsonObject.getString("Weight"));
                shipMap.put("trackNum", jsonObject.getString("trackNum"));
                commonService.insertShipMent(shipMap);
                JSONArray jsonArray = jsonObject.getJSONArray("ApplicationInfos");
                for (int i = 0; i < jsonArray.size(); i++) {
                    Map<String, Object> customsInfo = jsonArray.getJSONObject(i);
                    customsInfo.put("custId", jsonObject.getString("OrderNumber"));
                    commonService.insertCustomsInfo(customsInfo);
                }
                map.put("data", result);
                map.put("code", "0");
                map.put("msg", "发货成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "发货失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "发货失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "print", produces = "text/plain;charset=UTF-8")
    public String print(String orderNumbers) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = HttpClientUtil.doPostRequest("http://api.yunexpress.com/LMS.API.Lable/Api/PrintUrl", orderNumbers);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("ResultCode").equals("0000")) {
                JSONObject item = jsonObject.getJSONArray("Item").getJSONObject(0);
                map.put("data", item.getString("Url"));
                map.put("code", "0");
                map.put("msg", "打印成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "打印失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "打印失败");
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
