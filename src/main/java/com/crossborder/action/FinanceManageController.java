package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossborder.service.FinanceManageService;
import com.crossborder.utils.HttpClientUtil;
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
 * Created by s on 2018/6/10.
 */
@Controller
@RequestMapping("/finance")
public class FinanceManageController {
    @Resource
    private FinanceManageService financeManageService;

    /**
     * 查询店铺
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectShippings", produces = "text/plain;charset=UTF-8")
    public String selectShops(HttpSession session, String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").equals("600")) {
            paramMap.put("createUser", user.get("USER_ID").toString());
        }
        if (user.get("ROLE_ID").equals("500")) {
            paramMap.put("companyId", user.get("USER_COMPANY").toString());
        }
        try {
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            List<Map<String, Object>> list = financeManageService.selectShippings(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
            map.put("data", list);
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

    @ResponseBody
    @RequestMapping(value = "getShippingPrice", produces = "text/plain;charset=UTF-8")
    public String getShippingPrice(String companyId, String orderId, String custId) {
        if (companyId.equals("YT")) {
            return getYTShippingPrice(orderId, custId);
        } else {
            return getYTShippingPrice(orderId, custId);
        }
    }

    private String getYTShippingPrice(String orderId, String custId) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = HttpClientUtil.doGetRequest("http://api.yunexpress.com/LMS.API/api/GetShippingFeeDetail/GetShippingFeeDetail?wayBillNumber=" + custId);
            JSONObject resultObject = JSONObject.parseObject(result);
            if (resultObject.getString("ResultCode").equals("0000")) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("orderId", orderId);
                financeManageService.updateShipping(paramMap);
                map.put("code", "0");
                map.put("msg", "获取成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "获取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", e.getMessage());
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
}
