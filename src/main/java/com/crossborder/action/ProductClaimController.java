package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ProductSkuTypeService;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ResponseGen;
import com.crossborder.utils.TranslateDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
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

    private Map<String, String> imagePath(String paths) {
        String main = "";
        String attach = "";
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isNotBlank(paths)) {
            String[] arr = paths.split(",");
            if (arr.length > 1) {
                main = paths.substring(0, paths.indexOf(","));
                attach = paths.substring(paths.indexOf(",") + 1);
            } else {
                main = paths;
            }
        }
        map.put("main", main);
        map.put("attach", attach);
        return map;
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

        Map<String, String> imags = imagePath(product.getImagePath());
        var.setQuantity(product.getQuantity());
        var.setMainPath(imags.get("main"));
        var.setAttachPath(imags.get("attach"));
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
        String key = "AIzaSyD9ZFuiV0CJYppKv9G6DQ08QQc2JDpOnHk";
        Translate translate = TranslateOptions.newBuilder().setApiKey(key).build().getService();
        Translation translation = null;
        List<TranslateDto> list = new ArrayList<>();
        String lang = "zh-CN";
        if (language.equals("cn")) {
            lang = "zh-CN";
        } else if (language.equals("uk")) {
            lang = "en";
        } else if (language.equals("de")) {
            lang = "de";
        } else if (language.equals("es")) {
            lang = "es";
        } else if (language.equals("it")) {
            lang = "it";
        } else if (language.equals("jp")) {
            lang = "ja";
        } else if (language.equals("fr")) {
            lang = "fr";
        }
        for (String s : zhs) {
            String zh = "";
            String uk = "";
            String jp = "";
            String de = "";
            String fr = "";
            String es = "";
            String it = "";
            if (lang.equals("zh-CN")) {
                zh = s;
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("en"));
                uk = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("ja"));
                jp = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("de"));
                de = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("fr"));
                fr = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("es"));
                es = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("it"));
                it = translation.getTranslatedText();
            } else if (lang.equals("en")) {
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("zh-CN"));
                zh = translation.getTranslatedText();
                uk = s;
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("ja"));
                jp = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("de"));
                de = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("fr"));
                fr = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("es"));
                es = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("it"));
                it = translation.getTranslatedText();
            } else if (lang.equals("ja")) {
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("zh-CN"));
                zh = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("en"));
                uk = translation.getTranslatedText();
                jp = s;
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("de"));
                de = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("fr"));
                fr = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("es"));
                es = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("it"));
                it = translation.getTranslatedText();
            } else if (lang.equals("de")) {
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("zh-CN"));
                zh = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("en"));
                uk = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("ja"));
                jp = translation.getTranslatedText();
                de = s;
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("fr"));
                fr = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("es"));
                es = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("it"));
                it = translation.getTranslatedText();
            } else if (lang.equals("fr")) {
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("zh-CN"));
                zh = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("en"));
                uk = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("ja"));
                jp = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("de"));
                de = translation.getTranslatedText();
                fr = s;
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("es"));
                es = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("it"));
                it = translation.getTranslatedText();
            } else if (lang.equals("es")) {
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("zh-CN"));
                zh = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("en"));
                uk = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("ja"));
                jp = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("de"));
                de = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("fr"));
                fr = translation.getTranslatedText();
                es = s;
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("it"));
                it = translation.getTranslatedText();
            } else if (lang.equals("it")){
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("zh-CN"));
                zh = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("en"));
                uk = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("ja"));
                jp = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("de"));
                de = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("fr"));
                fr = translation.getTranslatedText();
                translation =
                        translate.translate(
                                s,
                                Translate.TranslateOption.sourceLanguage(lang),
                                Translate.TranslateOption.targetLanguage("es"));
                es = translation.getTranslatedText();
                it = s;
            }
            TranslateDto dto = new TranslateDto();
            dto.setCn(zh);
            dto.setJp(jp);
            dto.setDe(de);
            dto.setEs(es);
            dto.setFr(fr);
            dto.setIt(it);
            dto.setUk(uk);
            list.add(dto);
        }
        return list;
    }

}
