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
    public String productList(String data, Integer start, Integer length, Integer draw) {

        Map<String, Object> params = JSON.parseObject(data, Map.class);


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
            boolean isSave = productManagerService.saveProductId(type, ids);
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
    public String use(String type) {

        try {
            ProductIdGen gen = productManagerService.selectProductIdForUse(type);
            return ResponseGen.genSuccessData(gen);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return JSON.toJSONString(ResponseGen.genFail());


    }


}
