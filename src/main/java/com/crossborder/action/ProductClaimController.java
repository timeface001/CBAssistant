package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ProductSkuTypeService;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ResponseGen;
import com.crossborder.utils.TranslateDto;
import com.crossborder.utils.lang.Lang;
import com.crossborder.utils.querier.Querier;
import com.crossborder.utils.trans.Google;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品管理--
 * <p/>
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
    public String productList(HttpSession session, String data, Integer start, Integer length, Integer draw) {
        Map<String, Object> params = JSON.parseObject(data, Map.class);
        if (params == null) {
            params = new HashMap<>();
        }
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (user.get("ROLE_ID").toString().equals("600")) {
            params.put("userId", user.get("USER_ID"));
        } else if (user.get("ROLE_ID").toString().equals("500")) {
            params.put("companyId", user.get("USER_COMPANY"));
        }
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage((start == null || start < 1) ? 1 : start, (length == null || length < 1) ? 10 : length);
        List<ClaimProduct> list = productManagerService.selectClaimList(params);
        PageInfo pageInfo = new PageInfo<>(list);
        result.put("data", list);
        result.put("draw", draw);
        result.put("recordsTotal", pageInfo.getTotal());
        result.put("recordsFiltered", pageInfo.getTotal());
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/product/claim/save", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String save(ClaimProduct product, String saleStartTime, String saleEndTime, String salePrice, String vars) {
        List<ProductItemVar> list = new ArrayList<>();
        ProductItemVar var = new ProductItemVar();
        var.setProductId(product.getId());
        var.setPrice(product.getPrice());
        if (!StringUtils.isEmpty(salePrice)) {
            var.setSalePrice(new BigDecimal(salePrice));
        }
        var.setQuantity(product.getQuantity());
        var.setSaleStartTime(GeneralUtils.getDateFromStr(saleStartTime));
        var.setSaleEndTime(GeneralUtils.getDateFromStr(saleEndTime));
        var.setSku(product.getSku());
        if (product.getSkuType().equals("2")) {//变体
            if (org.apache.commons.lang3.StringUtils.isNotBlank(vars)) {
                list = JSON.parseArray(vars, ProductItemVar.class);
                Integer totalInventory = 0;
                for (ProductItemVar va : list) {
                    totalInventory += va.getQuantity();
                }
                product.setQuantity(totalInventory);
                var.setQuantity(totalInventory);
            }
        }
        list.add(var);
        productManagerService.save(product);
        productSkuTypeService.save(list, product.getId());
        return JSON.toJSONString(ResponseGen.genSuccess());
    }


    @RequestMapping(value = "/product/claim/detail", produces = "text/plain;charset=UTF-8")
    public ModelAndView detail(String id, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("forward:/assistant/index/product/product_claim_edit.jsp");
        ClaimProduct claimProduct = productManagerService.selectClaimProduct(id);
        //view.addObject("product", claimProduct);
        request.setAttribute("typeList", productSkuTypeService.selectTypeList());
        claimProduct.setSku(StringUtils.isNoneBlank(claimProduct.getSku()) ? claimProduct.getSku() : GeneralUtils.getRandomString(8));
        request.setAttribute("product", claimProduct);
        request.setAttribute("productStr", JSON.toJSONString(claimProduct));
        //关键词返回
        request.setAttribute("keywordsCn", null2ZeroSize(claimProduct.getKeywordsCn()));
        request.setAttribute("keywordsJp", null2ZeroSize(claimProduct.getKeywordsJp()));
        request.setAttribute("keywordsEs", null2ZeroSize(claimProduct.getKeywordsEs()));
        request.setAttribute("keywordsDe", null2ZeroSize(claimProduct.getKeywordsDe()));
        request.setAttribute("keywordsUk", null2ZeroSize(claimProduct.getKeywordsUk()));
        request.setAttribute("keywordsIt", null2ZeroSize(claimProduct.getKeywordsIt()));
        request.setAttribute("keywordsFr", null2ZeroSize(claimProduct.getKeywordsFr()));
        //简要描述返回
        request.setAttribute("pointsCn", null2ZeroSize(claimProduct.getBulletPointCn()));
        request.setAttribute("pointsJp", null2ZeroSize(claimProduct.getBulletPointJp()));
        request.setAttribute("pointsEs", null2ZeroSize(claimProduct.getBulletPointEs()));
        request.setAttribute("pointsDe", null2ZeroSize(claimProduct.getBulletPointDe()));
        request.setAttribute("pointsUk", null2ZeroSize(claimProduct.getBulletPointUk()));
        request.setAttribute("pointsIt", null2ZeroSize(claimProduct.getBulletPointIt()));
        request.setAttribute("pointsFr", null2ZeroSize(claimProduct.getBulletPointFr()));

        List<ProductItemVar> vars = productSkuTypeService.selectListByProductId(id);
        request.setAttribute("skuType", claimProduct.getSkuType());
        if (claimProduct.getSkuType().equals("1")) {
            if (GeneralUtils.isNotNullOrEmpty(vars)) {
                request.setAttribute("productVar", vars.get(0));
            } else {
                request.setAttribute("productVar", new ProductItemVar());
            }
        } else {
            request.setAttribute("productVars", JSON.toJSONString(vars));
        }
        return view;
    }

    private List<String> null2ZeroSize(String str) {
        List<String> list = new ArrayList<>(5);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            list = JSON.parseArray(str, String.class);
        }
        return list;
    }

    @RequestMapping(value = "/product/claim/prePublish", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String prePublish(String id, String type) {
        try {
            productManagerService.prePublishProduct(id, type);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseGen.genFail());
        }
        return ResponseGen.genSuccessData(null);
    }

    @RequestMapping(value = "/product/translate", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String translate(String data, String language) {
        if (org.apache.commons.lang3.StringUtils.isBlank(language)) {
            return JSON.toJSONString(ResponseGen.genFail());
        }
        List<TranslateDto> resultList = null;
        try {
            List<String> dataList = JSON.parseArray(data, String.class);
            resultList = getTanslateList(dataList, language);
            if (resultList != null && resultList.size() > 0) {
                if (!(resultList.get(0).getCn().equals("Translate failed"))) {
                    return ResponseGen.genSuccessData(resultList);
                } else {
                    return JSON.toJSONString(ResponseGen.genFail());
                }
            } else {
                return JSON.toJSONString(ResponseGen.genFail());
            }
            /*if (language.equals("cn")) {
                resultList = getTanslateList(dataList);
            } else if (language.equals("uk")) {
                for (String s : dataList) {
                    midList.add(api.uk2Zh(s));
                }
                resultList = getTanslateList(midList);
            } else if (language.equals("de")) {
                for (String s : dataList) {
                    midList.add(api.de2Zh(s));
                }
                resultList = getTanslateList(midList);
            } else if (language.equals("jp")) {
                for (String s : dataList) {
                    midList.add(api.jp2Zh(s));
                }
                resultList = getTanslateList(midList);
            } else if (language.equals("it")) {
                for (String s : dataList) {
                    midList.add(api.it2Zh(s));
                }
                resultList = getTanslateList(midList);
            } else if (language.equals("es")) {
                for (String s : dataList) {
                    midList.add(api.es2Zh(s));
                }
                resultList = getTanslateList(midList);
            } else if (language.equals("fr")) {
                for (String s : dataList) {
                    midList.add(api.fr2Zh(s));
                }
                resultList = getTanslateList(midList);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseGen.genFail());
        }
    }

    public List<TranslateDto> getTanslateList(List<String> zhs, String language) {
        Querier querier = Querier.getQuerier();
        Google google = new Google();
        querier.attach(google);
        List<TranslateDto> list = new ArrayList<>();
        Lang lang = Lang.ZH;
        if (language.equals("cn")) {
            lang = Lang.ZH;
        } else if (language.equals("uk")) {
            lang = Lang.EN;
        } else if (language.equals("de")) {
            lang = Lang.DE;
        } else if (language.equals("es")) {
            lang = Lang.SPA;
        } else if (language.equals("it")) {
            lang = Lang.IT;
        } else if (language.equals("jp")) {
            lang = Lang.JP;
        } else if (language.equals("fr")) {
            lang = Lang.FRA;
        }
        for (String s : zhs) {
            querier.setParams(lang, Lang.ZH, s);
            String zh = querier.execute().get(0);
            zh = zh.substring(1, zh.length() - 1);
            querier.setParams(lang, Lang.EN, s);
            String uk = querier.execute().get(0);
            uk = uk.substring(1, uk.length() - 1);
            querier.setParams(lang, Lang.JP, s);
            String jp = querier.execute().get(0);
            jp = jp.substring(1, jp.length() - 1);
            querier.setParams(lang, Lang.DE, s);
            String de = querier.execute().get(0);
            de = de.substring(1, de.length() - 1);
            querier.setParams(lang, Lang.FRA, s);
            String fra = querier.execute().get(0);
            fra = fra.substring(1, fra.length() - 1);
            querier.setParams(lang, Lang.SPA, s);
            String spa = querier.execute().get(0);
            spa = spa.substring(1, spa.length() - 1);
            querier.setParams(lang, Lang.IT, s);
            String it = querier.execute().get(0);
            it = it.substring(1, it.length() - 1);
            TranslateDto dto = new TranslateDto();
            dto.setCn(zh);
            dto.setJp(jp);
            dto.setDe(de);
            dto.setEs(spa);
            dto.setFr(fra);
            dto.setIt(it);
            dto.setUk(uk);
            list.add(dto);
        }
        querier.detach(google);
        return list;
    }


}
