package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.service.ProductManagerService;
import com.crossborder.utils.ProductStateEnum;
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
 * 产品管理--
 * <p/>
 * Created by fengsong on 2018/4/14.
 */
@Controller
public class ProductManagerController extends BaseController {

    @Resource
    private ProductManagerService productManagerService;

    @RequestMapping(value = "/product/list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String productList(HttpSession session, String data, Integer start, Integer length, Integer draw) {
        Map<String, Object> params = JSON.parseObject(data, Map.class);
        Object endTime = params.get("endTime");
        if (endTime != null && endTime.toString().length() > 0) {
            params.put("endTime", endTime + " 23:59:59");
        }
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").toString().equals("500")) {
            params.put("userId", user.get("USER_ID"));
        } else if (user.get("ROLE_ID").toString().equals("600")) {
            params.put("companyId", user.get("USER_COMPANY"));
        }
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage((start == null || start < 1) ? 1 : start, (length == null || length < 1) ? 10 : length);
        List<Map<String, Object>> list = productManagerService.selectList(params);
        PageInfo pageInfo = new PageInfo<>(list);
        result.put("data", list);
        result.put("draw", draw);
        result.put("recordsTotal", pageInfo.getTotal());
        result.put("recordsFiltered", pageInfo.getTotal());
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/product/save", produces = "text/plain;charset=UTF-8")
    public String save(String data) {

        Map<String, Object> result = new HashMap<>();
        try {
            Map<String, Object> params = JSON.parseObject(data, Map.class);
            params.put("createUserID", getUserId());
            boolean isSave = productManagerService.save(params);
            result.put("success", isSave);
            result.put("msg", isSave ? "保存成功" : "保存失败");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", "保存失败");
        }

        return JSON.toJSONString(result);


    }

    /**
     * 认领
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/product/state", produces = "text/plain;charset=UTF-8")
    public String save(String data, int type) {

        Map<String, Object> result = new HashMap<>();
        try {
            productManagerService.updateState(data.split(","), ProductStateEnum.claim.generate(type));
            result.put("success", true);
            result.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", "操作失败");
        }

        return JSON.toJSONString(result);


    }

    /**
     * 删除
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/product/delete", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String delete(String data) {

        Map<String, Object> result = new HashMap<>();
        try {
            productManagerService.delete(data.split(","));
            result.put("success", true);
            result.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", "操作失败");
        }

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/product/detail", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String detail(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("data", productManagerService.selectOne(id));
            result.put("success", true);
            result.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", "操作失败");
        }

        return JSON.toJSONString(result);
    }
}
