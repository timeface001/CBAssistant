package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossborder.service.AccountManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/8/2.
 */
@Controller
@RequestMapping("/account")
public class AccountManageController {
    @Resource
    private AccountManageService accountManageService;

    /**
     * 查询账户流水记录
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAccounts", produces = "text/plain;charset=UTF-8")
    public String getAccounts(String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> accountList = new ArrayList<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
            paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            accountList = accountManageService.getAccounts(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(accountList);
            map.put("data", accountList);
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
     * 新增财务
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addAccount", produces = "text/plain;charset=UTF-8")
    public String addAccount(String data) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            int count = accountManageService.addAccount(paramMap);
            if (count > 0) {
                map.put("code", "0");
                map.put("msg", "新增成功");
            } else {
                map.put("code", "0");
                map.put("msg", "新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "新增失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
}
