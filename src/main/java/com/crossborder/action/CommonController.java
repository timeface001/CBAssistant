package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.entity.Menu;
import com.crossborder.service.CommonService;
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
                session.setAttribute("user", list.get(0));
                map.put("data", list.get(0));
                map.put("code", "0");
                map.put("msg", "登录成功");
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
}
