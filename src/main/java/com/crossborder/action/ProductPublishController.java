package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.dao.ProductAmzUploadDao;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.entity.ProductUploadCategory;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ShopManageService;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ResponseDto;
import com.crossborder.utils.ResponseGen;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预发布产品
 * <p/>
 * Created by fengsong on 2018/4/14.
 */
@Controller
public class ProductPublishController extends BaseController {

    @Resource
    private ProductManagerService productManagerService;
    @Resource
    private ShopManageService shopManageService;

    Logger logger = Logger.getLogger(ProductPublishController.class);

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
    public String productList(HttpSession session, String data, Integer start, Integer length, Integer draw) {
        Map<String, Object> params = JSON.parseObject(data, Map.class);
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").toString().equals("500")) {
            params.put("userId", user.get("USER_ID"));
        } else if (user.get("ROLE_ID").toString().equals("600")) {
            params.put("companyId", user.get("USER_COMPANY"));
        }
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
    public ModelAndView detail(HttpSession session, String id, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("forward:/assistant/index/product/product_upload_edit.jsp");
        ProductAmzUpload product = productManagerService.selectAmzUploadProduct(id);
        request.setAttribute("product", product);
        //ProductAmzUpload product = productManagerService.selectAmzUploadProduct(id);
        //request.setAttribute("product", product);
        String type = request.getParameter("type");
        if (type.equals("1")) {
            product = productManagerService.selectAmzUploadProduct(id);
            request.setAttribute("type", 1);
        } else {
            product = productManagerService.selectOneByAmzID(id);
            if (product == null || product.getId() == null) {
                product = new ProductAmzUpload();
                ClaimProduct claimProduct = productManagerService.selectClaimProduct(id);
                product.setId(claimProduct.getId());
                product.setItemName(claimProduct.getItemCn());
                request.setAttribute("type", 0);
            } else {
                request.setAttribute("type", 1);
            }
        }
        request.setAttribute("product", product);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", 1);
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").equals("600")) {
            params.put("createUser", user.get("USER_ID").toString());
        }
        if (user.get("ROLE_ID").equals("500")) {
            params.put("companyId", user.get("USER_COMPANY").toString());
        }
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
                    resultMap.get(key).put(map.get("SHOP_ID").toString(), map.get("COUNTRY_NAME").toString());
                }
            }
            for (Map.Entry<String, Map<String, Object>> entry : resultMap.entrySet()) {
                Object newKey = shopKey.get(entry.getKey());
                if (newKey != null) {
                    resultD.put(newKey.toString(), entry.getValue());
                }
            }
        }
        request.setAttribute("shops", shopKey);
        request.setAttribute("country", countryKey);
        request.setAttribute("maps", resultD);
        request.setAttribute("id", id);
        return view;
    }

    @Resource
    private ProductAmzUploadDao productAmzUploadDao;

    @RequestMapping(value = "/product/publish", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String publish(ProductAmzUpload product, String type) {
        ResponseDto dto = new ResponseDto();
        dto.setSuccess(true);
        dto.setMsg("数据提交成功");
        try {
            if (type.equals("1")) {
                if (StringUtils.isNotBlank(product.getShopId())) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", product.getShopId());
                    productAmzUploadDao.updateByPrimaryKeySelective(product);
                    product = productAmzUploadDao.selectByPrimaryKey(product.getId());
                    productManagerService.uploadProduct(product, shopManageService.selectShops(params).get(0));
                }
            } else {//认领列表直接发布
                Map<String, Object> params = new HashMap<>();
                params.put("id", product.getShopId());
                List<Map<String, Object>> list = shopManageService.selectShops(params);
                String id = "";
                try {
                    id = productManagerService.prePublishProduct(product.getId(), list.get(0).get("COUNTRY_CODE").toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.debug("publish from claim list: upload id :" + id);
                product = productAmzUploadDao.selectByPrimaryKey(id);
                productManagerService.uploadProduct(product, list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            dto.setSuccess(false);
            dto.setMsg("发布失败");
        }
        return ResponseGen.genSuccessData(dto);
    }

    @RequestMapping("public/category/init")
    @ResponseBody
    public String init() {
        productManagerService.initshopCategory();
        return "success";
    }


    @RequestMapping("publish/category")
    @ResponseBody
    public String listCategory(String parentId, String countryCode) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (parentId.equals("-1")) {
                map.put("data", productManagerService.selectListParent(countryCode));
                map.put("code", "0");
                map.put("msg", "查询成功");
            } else {
                List<ProductUploadCategory> list = productManagerService.selectList(parentId, countryCode);
                if (list != null && list.size() > 0) {
                    map.put("data", list);
                    map.put("code", "0");
                    map.put("msg", "查询成功");
                } else {
                    map.put("code", "1");
                    map.put("msg", "查询成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map);
    }
}
