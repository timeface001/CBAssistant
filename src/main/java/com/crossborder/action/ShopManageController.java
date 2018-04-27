package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.service.CommonService;
import com.crossborder.service.ShopManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<Map<String, Object>> list = commonService.selectCountryByCode(paramMap.get("countryCode").toString());
            for (int i = 0; i < list.size(); i++) {
                paramMap.put("countryCode", list.get(i).get("ID").toString());
                shopManageService.addShop(paramMap);
            }
            map.put("code", "0");
            map.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map);
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
