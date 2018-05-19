package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.entity.ProductIdGen;
import com.crossborder.service.ProductManagerService;
import com.crossborder.utils.ResponseGen;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Controller
public class ProductIdGenController extends BaseController {

    @Resource
    private ProductManagerService productManagerService;

    @RequestMapping(value = "/productid/gen/list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String productList(HttpSession session, String data, Integer start, Integer length, Integer draw) {
        Map<String, Object> params = JSON.parseObject(data, Map.class);
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").toString().equals("600")) {
            params.put("userId", user.get("USER_ID"));
        } else if (user.get("ROLE_ID").toString().equals("500")) {
            params.put("companyId", user.get("USER_COMPANY"));
        }
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage((start == null || start < 1) ? 1 : start, (length == null || length < 1) ? 10 : length);
        List<ProductIdGen> list = productManagerService.selectProductIdGenList(params);
        PageInfo pageInfo = new PageInfo<>(list);
        result.put("data", list);
        result.put("draw", draw);
        result.put("recordsTotal", pageInfo.getTotal());
        result.put("recordsFiltered", pageInfo.getTotal());
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/productid/add", produces = "text/plain;charset=UTF-8")
    public String save(String type, String data) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<String> ids = Arrays.asList(data.split("\\n"));
            boolean isSave = productManagerService.saveProductId(type, getUserId(), ids);
            result.put("success", isSave);
            result.put("msg", isSave ? "保存成功" : "保存失败");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", "保存失败");
        }

        return JSON.toJSONString(result);


    }

    @ResponseBody
    @RequestMapping(value = "/productid/use", produces = "text/plain;charset=UTF-8")
    public String use(HttpSession session, String type) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").toString().equals("600")) {
            params.put("userId", user.get("USER_ID"));
        } else if (user.get("ROLE_ID").toString().equals("500")) {
            params.put("companyId", user.get("USER_COMPANY"));
        }
        try {
            List<ProductIdGen> list = productManagerService.selectProductIdForUse(params);
            if (list != null && list.size() > 0) {
                return ResponseGen.genSuccessData(list.get(0));
            } else {
                return JSON.toJSONString(ResponseGen.genFailMsg("无可用产品ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(ResponseGen.genFail());
    }
}
