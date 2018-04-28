package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ProductSkuTypeService;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ResponseGen;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        if (params == null) {
            params = new HashMap<>();
        }

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
        result.put("recordsFiltered", pageInfo.getTotal());
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/product/claim/save", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String save(ClaimProduct product, String saleStartTime, String saleEndTime,String vars) {

        productManagerService.save(product);
        List<ProductItemVar> list = new ArrayList<>();

        ProductItemVar var = new ProductItemVar();
        var.setProductId(product.getId());
        var.setPrice(product.getPrice());
        var.setQuantity(product.getQuantity());
        var.setSaleStartTime(GeneralUtils.getDateFromStr(saleStartTime));
        var.setSaleEndTime(GeneralUtils.getDateFromStr(saleEndTime));
        var.setSku(product.getSku());
        list.add(var);
        if (product.getSkuType().equals("2")) {//变体
            if(org.apache.commons.lang3.StringUtils.isNotBlank(vars)){
                list=JSON.parseArray(vars,ProductItemVar.class);
            }

        }
        list.add(var);
        productSkuTypeService.save(list, product.getId());
        return JSON.toJSONString(ResponseGen.genSuccess());
    }


    @RequestMapping(value = "/product/claim/detail", produces = "text/plain;charset=UTF-8")
    public ModelAndView detail(String id, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("forward:/assistant/index/product/product_claim_edit.jsp");
        ClaimProduct claimProduct = productManagerService.selectClaimProduct(id);
        //view.addObject("product", claimProduct);
        request.setAttribute("typeList", productSkuTypeService.selectTypeList());
        claimProduct.setSku(GeneralUtils.getRandomString(16));
        request.setAttribute("product", claimProduct);
        String keywords = claimProduct.getKeywordsCn();
        String points = claimProduct.getBulletPointCn();
        List<String> keyJson = JSON.parseArray(keywords, String.class);
        List<String> pointJson = JSON.parseArray(points, String.class);

        if (keyJson == null) {
            keyJson = new ArrayList<>(5);
        }
        if (pointJson == null) {
            pointJson = new ArrayList<>(5);
        }

        request.setAttribute("productStr", JSON.toJSONString(claimProduct));
        request.setAttribute("keywords", keyJson);
        request.setAttribute("points", pointJson);
        List<ProductItemVar> vars = productSkuTypeService.selectListByProductId(id);
        if (claimProduct.getSkuType().equals("1")) {
            if (GeneralUtils.isNotNullOrEmpty(vars)) {
                request.setAttribute("productVar", vars.get(0));
            } else {
                request.setAttribute("productVar", new ProductItemVar());
            }
        }else{
            //todo 多变种回显
        }
        return view;
    }

    @RequestMapping(value = "/product/claim/prePublish", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String prePublish(String id) {

        try {
            productManagerService.prePublishProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseGen.genFail());
        }
        return ResponseGen.genSuccessData(null);
    }


}
