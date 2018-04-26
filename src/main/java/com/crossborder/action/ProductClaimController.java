package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ProductSkuTypeService;
import com.crossborder.utils.ResponseGen;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品管理--
 * >>>>>>>>>>>产品列表
 * <p>
 * Created by fengsong on 2018/4/14.
 */
@Controller
public class ProductClaimController extends BaseController {

    @Resource
    private ProductManagerService productManagerService;
    @Autowired
    private ProductSkuTypeService productSkuTypeService;

    /**
     * 已认领产品列表
     *
     * @param data
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value = "/product/claim/list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String productList(String data, Integer start, Integer length) {

        Map<String, Object> params = JSON.parseObject(data, Map.class);

        Object endTime = params.get("endTime");
        if (endTime != null && endTime.toString().length() > 0) {
            params.put("endTime", endTime + " 23:59:59");
        }
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage((start == null || start < 1) ? 1 : start, (length == null || length < 1) ? 10 : length);
        List<ClaimProduct> list = productManagerService.selectClaimList(params);
        PageInfo pageInfo = new PageInfo<>(list);
        result.put("data", list);
        result.put("recordsTotal", pageInfo.getTotal());
        result.put("recordsFiltered", list.size());
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/product/claim/save", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String save(String data) {


        return JSON.toJSONString(ResponseGen.genSuccess());
    }


    @RequestMapping(value = "/product/claim/detail", produces = "text/plain;charset=UTF-8")
    public ModelAndView detail(String id, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("forward:/assistant/index/product/product_claim_edit.jsp");
        ClaimProduct claimProduct = productManagerService.selectClaimProduct(id);
        //view.addObject("product", claimProduct);
        request.setAttribute("typeList", productSkuTypeService.selectTypeList());
        request.setAttribute("product", claimProduct);
        request.setAttribute("productStr", JSON.toJSONString(claimProduct));
        return view;
    }


}
