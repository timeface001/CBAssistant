package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.crossborder.dao.ProductClaimDao;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ProductSkuTypeService;
import com.crossborder.service.SystemManageService;
import com.crossborder.utils.BaiduTranApi;
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
    @Autowired
    private ProductClaimDao productClaimDao;
    @Resource
    private SystemManageService systemManageService;

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
        PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
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
                BigDecimal price = BigDecimal.ZERO;
                for (ProductItemVar va : list) {
                    totalInventory += va.getQuantity();
                    price = (va.getPrice().compareTo(price) > 0 && price.compareTo(BigDecimal.ZERO) > 0 ? price : va.getPrice());
                }
                product.setQuantity(totalInventory);
                product.setPrice(price);
                var.setQuantity(totalInventory);
                var.setPrice(price);
            }
        }
        list.add(var);
        try {
            //剔除描述标签中属性
            GeneralUtils.removeProductDescriptionAttr(product);
            productManagerService.save(product);
            productSkuTypeService.save(list, product.getId());
            return JSON.toJSONString(ResponseGen.genSuccess());
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("值太大")) {
                return JSON.toJSONString(ResponseGen.genFailMsg("产品描述过长，保存失败"));
            } else {
                return JSON.toJSONString(ResponseGen.genFailMsg("保存失败"));
            }
        }
    }

    @RequestMapping(value = "/product/claim/delete", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String delte(String id) {
        try {
            productClaimDao.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseGen.genFailMsg("删除失败"));
        }
        return JSON.toJSONString(ResponseGen.genSuccessMsg("删除成功"));
    }


    @RequestMapping(value = "/product/claim/detail", produces = "text/plain;charset=UTF-8")
    public ModelAndView detail(String id, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("forward:/assistant/index/product/product_claim_edit.jsp");
        ClaimProduct claimProduct = productManagerService.selectClaimProduct(id);
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
            productManagerService.prePublishProduct(id, type, getUserId(), "");
            //productManagerService.prePublishProduct(id, type);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseGen.genFail());
        }
        return ResponseGen.genSuccessData(null);
    }

    @RequestMapping(value = "/product/translate", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String translate(String data, String language, String type) {
        if (org.apache.commons.lang3.StringUtils.isBlank(language)) {
            return JSON.toJSONString(ResponseGen.genFail());
        }
        List<TranslateDto> resultList = null;
        try {
            List<String> dataList = JSON.parseArray(data, String.class);
            resultList = getTanslateList(dataList, language, type);
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

    public List<TranslateDto> getTanslateList(List<String> zhs, String language, String type) {
        Map<String, Object> paramMap = new HashMap<>();
        if (type.equals("1")) {
            paramMap.put("type", "1");
        } else {
            paramMap.put("type", "2");
        }
        List<Map<String, Object>> translationList = systemManageService.selectTranslations(paramMap);
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
        List<TranslateDto> list = new ArrayList<>();
        if (type.equals("1")) {
            String key = translationList.get(0).get("SECRET_KEY").toString();//"AIzaSyAN1tQ7mgCZ7fVPtc6PCKMw69P-TbZv-5w";
            Translate translate = TranslateOptions.newBuilder().setApiKey(key).build().getService();
            Translation translation = null;
            for (String s : zhs) {
                String zh = "";
                String uk = "";
                String jp = "";
                String de = "";
                String fr = "";
                String es = "";
                String it = "";
                s = s.replaceAll("\\n", "<p>");
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
                } else if (lang.equals("it")) {
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
                dto.setCn(zh.replaceAll("<p>", "\n"));
                dto.setJp(jp.replaceAll("<p>", "\n"));
                dto.setDe(de.replaceAll("<p>", "\n"));
                dto.setEs(es.replaceAll("<p>", "\n"));
                dto.setFr(fr.replaceAll("<p>", "\n"));
                dto.setIt(it.replaceAll("<p>", "\n"));
                dto.setUk(uk.replaceAll("<p>", "\n"));
                list.add(dto);
            }
        } else {
            Map<String, Object> translationMap = null;
            BaiduTranApi baiduTranApi = null;
            for (int i = 0; i < translationList.size(); i++) {
                translationMap = translationList.get(i);
                baiduTranApi = BaiduTranApi.getInstance(translationMap.get("ACCOUNT").toString(),
                        translationMap.get("SECRET_KEY").toString());
                String test = baiduTranApi.zh2Uk("你");
                if (!test.equals("54004")) {
                    break;
                }
            }
            for (String s : zhs) {
                String zh = "";
                String uk = "";
                String jp = "";
                String de = "";
                String fr = "";
                String es = "";
                String it = "";
                s = s.replaceAll("\\n", "<p>");
                if (lang.equals("zh-CN")) {
                    zh = s;
                    uk = baiduTranApi.zh2Uk(s);
                    jp = baiduTranApi.zh2Jp(s);
                    de = baiduTranApi.zh2De(s);
                    fr = baiduTranApi.zh2Fr(s);
                    es = baiduTranApi.zh2Es(s);
                    it = baiduTranApi.zh2It(s);
                } else if (lang.equals("en")) {
                    zh = baiduTranApi.uk2Zh(s);
                    uk = s;
                    jp = baiduTranApi.uk2Jp(s);
                    de = baiduTranApi.uk2De(s);
                    fr = baiduTranApi.uk2Fr(s);
                    es = baiduTranApi.uk2Es(s);
                    it = baiduTranApi.uk2It(s);
                } else if (lang.equals("ja")) {
                    zh = baiduTranApi.jp2Zh(s);
                    uk = baiduTranApi.jp2Uk(s);
                    jp = s;
                    de = baiduTranApi.jp2De(s);
                    fr = baiduTranApi.jp2Fr(s);
                    es = baiduTranApi.jp2Es(s);
                    it = baiduTranApi.jp2It(s);
                } else if (lang.equals("de")) {
                    zh = baiduTranApi.de2Zh(s);
                    uk = baiduTranApi.de2Uk(s);
                    jp = baiduTranApi.de2Jp(s);
                    de = s;
                    fr = baiduTranApi.de2Fr(s);
                    es = baiduTranApi.de2Es(s);
                    it = baiduTranApi.de2It(s);
                } else if (lang.equals("fr")) {
                    zh = baiduTranApi.fr2Zh(s);
                    uk = baiduTranApi.fr2Uk(s);
                    jp = baiduTranApi.fr2Jp(s);
                    de = baiduTranApi.fr2De(s);
                    fr = s;
                    es = baiduTranApi.fr2Es(s);
                    it = baiduTranApi.fr2It(s);
                } else if (lang.equals("es")) {
                    zh = baiduTranApi.es2Zh(s);
                    uk = baiduTranApi.es2Uk(s);
                    jp = baiduTranApi.es2Jp(s);
                    de = baiduTranApi.es2De(s);
                    fr = baiduTranApi.es2Fr(s);
                    es = s;
                    it = baiduTranApi.es2It(s);
                } else if (lang.equals("it")) {
                    zh = baiduTranApi.it2Zh(s);
                    uk = baiduTranApi.it2Uk(s);
                    jp = baiduTranApi.it2Jp(s);
                    de = baiduTranApi.it2De(s);
                    fr = baiduTranApi.it2Fr(s);
                    es = baiduTranApi.it2Es(s);
                    it = s;
                }
                TranslateDto dto = new TranslateDto();
                dto.setCn(zh.replaceAll("<p>", "\n"));
                dto.setJp(jp.replaceAll("<p>", "\n"));
                dto.setDe(de.replaceAll("<p>", "\n"));
                dto.setEs(es.replaceAll("<p>", "\n"));
                dto.setFr(fr.replaceAll("<p>", "\n"));
                dto.setIt(it.replaceAll("<p>", "\n"));
                dto.setUk(uk.replaceAll("<p>", "\n"));
                list.add(dto);
            }
        }
        return list;
    }

}
