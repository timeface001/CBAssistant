package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossborder.service.StockManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/8/8.
 */
@Controller
@RequestMapping("/stock")
public class StockManageController {
    @Resource
    private StockManageService stockManageService;

    /**
     * 添加商品
     *
     * @param data
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addGoods", produces = "text/plain;charset=UTF-8")
    public String addGoods(String data, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            paramMap.put("createUser", user.get("USER_ID").toString());
            int count = stockManageService.addGoods(paramMap);
            if (count == 1) {
                map.put("code", "0");
                map.put("msg", "添加成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询商品
     *
     * @param data
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoods", produces = "text/plain;charset=UTF-8")
    public String getGoods(String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
            paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            List<Map<String, Object>> goodsList = stockManageService.getGoods(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(goodsList);
            map.put("data", goodsList);
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
}
