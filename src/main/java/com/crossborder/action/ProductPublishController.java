package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ShopManageService;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ResponseGen;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 预发布产品--
 * >>>>>>>>>>>预发布产品列表
 * <p>
 * Created by fengsong on 2018/4/14.
 */
@Controller
public class ProductPublishController extends BaseController {

    @Resource
    private ProductManagerService productManagerService;
    @Resource
    private ShopManageService shopManageService;

    /**
     * 产品列表
     *
     * @param data
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value = "/product/publish/list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String productList(String data, Integer start, Integer length, Integer draw) {
        Map<String, Object> params = JSON.parseObject(data, Map.class);
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage((start == null || start < 1) ? 1 : start, (length == null || length < 1) ? 10 : length);
        List<ProductAmzUpload> list = productManagerService.selectAmzUploadList(params);
        PageInfo pageInfo = new PageInfo<>(list);
        result.put("data", list);
        result.put("draw", draw);

        result.put("recordsTotal", pageInfo.getTotal());
        result.put("recordsFiltered", pageInfo.getTotal());
        return JSON.toJSONString(result);
    }

    /**
     * 产品详情
     *
     * @return
     */
    @RequestMapping(value = "/product/publish/detail", produces = "text/plain;charset=UTF-8")
    public ModelAndView detail(String id,HttpServletRequest request) {
        ModelAndView view = new ModelAndView("forward:/assistant/index/product/product_upload_edit.jsp");
        ProductAmzUpload product = productManagerService.selectAmzUploadProduct(id);
        request.setAttribute("product",product);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", 1);
        List<Map<String, Object>> result = shopManageService.selectShops(params);

        Map<String, Object> shopKey = new HashMap<>();
        Map<String, List<String>> countryKey = new HashMap<>();

        Map<String, Map<String, Object>> resultD = new HashMap<>();
        if (GeneralUtils.isNotNullOrEmpty(result)) {
            Map<String, Map<String, Object>> resultMap = new HashMap<>();
            for (Map<String, Object> map : result) {
                String key = map.get("MERCHANT_ID").toString();
                shopKey.put(key, map.get("SHOP_NAME").toString());
                if (resultMap.containsKey(key)) {
                    resultMap.get(key).put(map.get("SHOP_ID").toString(), map.get("COUNTRY_NAME").toString());
                } else {
                    resultMap.put(key, new HashMap<String, Object>());
                }
            }

            for(Map.Entry<String,Map<String,Object>> entry:resultMap.entrySet()){
                Object newKey=shopKey.get(entry.getKey());
                if(newKey!=null){
                    resultD.put(newKey.toString(),entry.getValue());
                }
            }
        }


        request.setAttribute("shops", shopKey);
        request.setAttribute("country", countryKey);
        request.setAttribute("maps", resultD);
        return view;
    }

    @RequestMapping(value = "/product/publish", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String publish(ProductAmzUpload product) {
         productManagerService.uploadProduct(product);
        return ResponseGen.genSuccessData(null);
    }




}
