package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crossborder.entity.Menu;
import com.crossborder.entity.SenderInfo;
import com.crossborder.entity.ShippingInfo;
import com.crossborder.entity.WayBill;
import com.crossborder.service.CommonService;
import com.crossborder.utils.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/4/2.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Resource
    private CommonService commonService;

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
        return JSON.toJSONString(map);
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
        return JSON.toJSONString(map);
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
        return JSON.toJSONString(map);
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
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "getShipTypes", produces = "text/plain;charset=UTF-8")
    public String getShipTypes(String countryCode) {
        //http://www.sendfromchina.com/shipfee/ship_type_list
        Map<String, Object> map = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>();
        try {
            String result = HttpClientUtil.doGetRequest("http://120.76.199.53:8034/LMS.API/api/lms/Get?countryCode=" + countryCode);
            /*JSONObject jsonObject = XmlUtil.xml2JSON(result.getBytes());
            JSONArray res = jsonObject.getJSONObject("GetShipTypesListResponse").getJSONArray("shiptypes").getJSONObject(0).getJSONArray("shiptype");*/
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("Item");
            List<Map<String, String>> ships = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
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
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "confirmOrder", produces = "text/plain;charset=UTF-8")
    public String confirmOrder(String json) {
        Map<String, Object> map = new HashMap<>();
        WayBill wayBill = new WayBill();
        ShippingInfo shippingInfo = new ShippingInfo();
        SenderInfo senderInfo = new SenderInfo();
        try {
            String result = HttpClientUtil.doPostRequest("http://120.76.199.53:8034/LMS.API/api/WayBill/BatchAdd", json);
            map.put("data", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
