package com.crossborder.service;

import com.alibaba.fastjson.JSON;
import com.crossborder.dao.*;
import com.crossborder.entity.*;
import com.crossborder.utils.CommonSet;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ProductStateEnum;
import com.crossborder.utils.PublishStatusEnum;
import com.crossborder.utils.amz.upload.AmzUpload;
import com.crossborder.utils.util.ChineseAndEnglish;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fengsong on 2018/4/14.
 */
@Service
public class ProductManagerService {
    @Resource
    private ProductManagerDao productManagerDao;
    @Resource
    private ProductClaimDao claimProductExtMapper;
    @Resource
    private ProductAmzUploadDao productAmzUploadDao;
    @Resource
    private ProductSkuTypeService productSkuTypeService;
    @Autowired
    private AmzUpload amzUpload;
    @Autowired
    private ProductUploadCategoryDao productUploadCategoryDao;

    private Logger logger = Logger.getLogger(ProductManagerService.class);

    public boolean save(Map<String, Object> product) {
        product.put("createTime", new Date());
        if (product.get("id") != null && StringUtils.isNotBlank(product.get("id").toString())) {
            return productManagerDao.updateProduct(product) == 1;
        } else {
            return productManagerDao.insertProduct(product) == 1;
        }

    }

    public List<Map<String, Object>> selectList(Map<String, Object> params) {
        return productManagerDao.selectList(params);
    }

    public List<ClaimProduct> selectClaimList(Map<String, Object> params) {
        params.put("name", params.get("name") != null && StringUtils.isNotBlank(params.get("name").toString()) ? ("%" + params.get("name") + "%") : "");
        params.put("sku", params.get("sku") != null && StringUtils.isNotBlank(params.get("sku").toString()) ? ("%" + params.get("sku") + "%") : "");
        return claimProductExtMapper.selectList(params);
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateState(String[] ids, ProductStateEnum stateEnum) {
        for (String id : ids) {
            if (stateEnum.compareTo(ProductStateEnum.claim) == 0) {
                Map<String, Object> product = productManagerDao.selectOne(id);
                ClaimProduct claimProduct = new ClaimProduct();
                claimProduct.setCreateUser(GeneralUtils.getUserId());
                claimProduct.setImagePath(GeneralUtils.nullToEmpty(product.get("MAIN_PATH")) + "," + GeneralUtils.nullToEmpty(product.get("IMAGE_PATH")));
                claimProduct.setPrice(new BigDecimal(String.valueOf(product.get("PRICE"))));
                claimProduct.setProductId(product.get("ID").toString());
                claimProduct.setTypeId(product.get("TYPE_ID").toString());

                //产品描述翻译
                String productDesc = GeneralUtils.nullToEmpty(product.get("INFO"));
                /*querier.setParams(Lang.ZH, Lang.EN, productDesc);
                String uk = querier.execute().get(0);
                uk = uk.substring(1, uk.length() - 1);
                querier.setParams(Lang.ZH, Lang.JP, productDesc);
                String jp = querier.execute().get(0);
                jp = jp.substring(1, jp.length() - 1);
                querier.setParams(Lang.ZH, Lang.DE, productDesc);
                String de = querier.execute().get(0);
                de = de.substring(1, de.length() - 1);
                querier.setParams(Lang.ZH, Lang.FRA, productDesc);
                String fra = querier.execute().get(0);
                fra = fra.substring(1, fra.length() - 1);
                querier.setParams(Lang.ZH, Lang.SPA, productDesc);
                String spa = querier.execute().get(0);
                spa = spa.substring(1, spa.length() - 1);
                querier.setParams(Lang.ZH, Lang.IT, productDesc);
                String it = querier.execute().get(0);
                it = it.substring(1, it.length() - 1);*/
                if (ChineseAndEnglish.isChinese(productDesc)) {
                    claimProduct.setProductDescriptionCn(productDesc);
                    claimProduct.setProductDescriptionUk("");
                } else {
                    claimProduct.setProductDescriptionCn("");
                    claimProduct.setProductDescriptionUk(productDesc);
                }
                claimProduct.setProductDescriptionDe("");
                claimProduct.setProductDescriptionEs("");
                claimProduct.setProductDescriptionIt("");
                claimProduct.setProductDescriptionJp("");
                claimProduct.setProductDescriptionFr("");

                //标题翻译
                String name = GeneralUtils.nullToEmpty(product.get("NAME"));
                String key = "AIzaSyD9ZFuiV0CJYppKv9G6DQ08QQc2JDpOnHk";
                Translate translate = TranslateOptions.newBuilder().setApiKey(key).build().getService();
                Translation translation = null;
                String cn = "";
                String uk = "";
                String jp = "";
                String de = "";
                String fr = "";
                String es = "";
                String it = "";
                if (ChineseAndEnglish.isChinese(name)) {
                    cn = name;
                    translation =
                            translate.translate(
                                    name,
                                    Translate.TranslateOption.targetLanguage("en"));
                    uk = translation.getTranslatedText();
                } else {
                    translation =
                            translate.translate(
                                    name,
                                    Translate.TranslateOption.targetLanguage("zh-CN"));
                    cn = translation.getTranslatedText();
                    uk = name;
                }
                translation =
                        translate.translate(
                                name,
                                Translate.TranslateOption.targetLanguage("ja"));
                jp = translation.getTranslatedText();
                translation =
                        translate.translate(
                                name,
                                Translate.TranslateOption.targetLanguage("de"));
                de = translation.getTranslatedText();
                translation =
                        translate.translate(
                                name,
                                Translate.TranslateOption.targetLanguage("fr"));
                fr = translation.getTranslatedText();
                translation =
                        translate.translate(
                                name,
                                Translate.TranslateOption.targetLanguage("es"));
                es = translation.getTranslatedText();
                translation =
                        translate.translate(
                                name,
                                Translate.TranslateOption.targetLanguage("it"));
                it = translation.getTranslatedText();
                claimProduct.setItemCn(cn);
                claimProduct.setItemDe(de);
                claimProduct.setItemEs(es);
                claimProduct.setItemIt(it);
                claimProduct.setItemJp(jp);
                claimProduct.setItemUk(uk);
                claimProduct.setItemFr(fr);

                claimProduct.setCreateUser(GeneralUtils.getUserId());
                claimProduct.setSkuType("1");
                claimProduct.setSource(product.get("SOURCE") != null ? product.get("SOURCE").toString() : "");
                claimProductExtMapper.insertSelective(claimProduct);
                productManagerDao.updateState(GeneralUtils.genMap("id", id, "pState", stateEnum.getValue()));
            }
        }
    }

    @Transactional(readOnly = false)
    public void delete(String[] ids) {

        for (String id : ids) {
            productManagerDao.delete(id);
        }
    }

    public Map<String, Object> selectOne(String id) {
        return productManagerDao.selectOne(id);
    }

    public void save(ClaimProduct product) {
        //翻译产品描述  产品概要  关键词翻译
        List<String> keyList = JSON.parseArray(product.getKeywordsCn(), String.class);
        List<String> pointList = JSON.parseArray(product.getBulletPointCn(), String.class);
/*        String productDesc = product.getProductDescriptionCn();
        product.setProductDescriptionCn(productDesc);
        product.setProductDescriptionDe(BaiduTranApi.getInstance().zh2De(productDesc));
        product.setProductDescriptionEs(BaiduTranApi.getInstance().zh2Es(productDesc));
        product.setProductDescriptionIt(BaiduTranApi.getInstance().zh2It(productDesc));
        product.setProductDescriptionJp(BaiduTranApi.getInstance().zh2Jp(productDesc));
        product.setProductDescriptionUk(BaiduTranApi.getInstance().zh2Uk(productDesc));
        product.setProductDescriptionFr(BaiduTranApi.getInstance().zh2Fr(productDesc));*/

        /*List<String> keyDe = new ArrayList<>();
        List<String> keyEs = new ArrayList<>();
        List<String> keyIt = new ArrayList<>();
        List<String> keyJp = new ArrayList<>();
        List<String> keyUk = new ArrayList<>();
        List<String> keyFr = new ArrayList<>();
        for (String key : keyList) {
            keyDe.add(BaiduTranApi.getInstance().zh2De(key));
            keyEs.add(BaiduTranApi.getInstance().zh2Es(key));
            keyIt.add(BaiduTranApi.getInstance().zh2It(key));
            keyJp.add(BaiduTranApi.getInstance().zh2Jp(key));
            keyUk.add(BaiduTranApi.getInstance().zh2Uk(key));
            keyFr.add(BaiduTranApi.getInstance().zh2Fr(key));

        }
        product.setKeywordsCn(JSON.toJSONString(keyList));
        product.setKeywordsDe(JSON.toJSONString(keyDe));
        product.setKeywordsEs(JSON.toJSONString(keyEs));
        product.setKeywordsIt(JSON.toJSONString(keyIt));
        product.setKeywordsJp(JSON.toJSONString(keyJp));
        product.setKeywordsUk(JSON.toJSONString(keyUk));
        product.setKeywordsFr(JSON.toJSONString(keyFr));*/


/*        List<String> pointDe = new ArrayList<>();
        List<String> pointEs = new ArrayList<>();
        List<String> pointIt = new ArrayList<>();
        List<String> pointJp = new ArrayList<>();
        List<String> pointUk = new ArrayList<>();
        List<String> pointFr = new ArrayList<>();
        for (String point : pointList) {
            pointDe.add(BaiduTranApi.getInstance().zh2De(point));
            pointEs.add(BaiduTranApi.getInstance().zh2Es(point));
            pointIt.add(BaiduTranApi.getInstance().zh2It(point));
            pointJp.add(BaiduTranApi.getInstance().zh2Jp(point));
            pointUk.add(BaiduTranApi.getInstance().zh2Uk(point));
            pointFr.add(BaiduTranApi.getInstance().zh2Fr(point));

        }
        product.setBulletPointCn(JSON.toJSONString(pointList));
        product.setBulletPointDe(JSON.toJSONString(pointDe));
        product.setBulletPointEs(JSON.toJSONString(pointEs));
        product.setBulletPointIt(JSON.toJSONString(pointIt));
        product.setBulletPointJp(JSON.toJSONString(pointJp));
        product.setBulletPointUk(JSON.toJSONString(pointUk));
        product.setBulletPointFr(JSON.toJSONString(pointFr));*/
        product.setUpdateState("1");//已编辑
        product.setIsPrepublish(PublishStatusEnum.NOT.getVal());
        claimProductExtMapper.updateByPrimaryKeySelective(product);
    }

    public ClaimProduct selectClaimProduct(String id) {
        return claimProductExtMapper.selectByPrimaryKey(id);
    }

    public List<ProductAmzUpload> selectAmzUploadList(Map<String, Object> params) {
        params.put("name", params.get("name") != null && StringUtils.isNotBlank(params.get("name").toString()) ? ("%" + params.get("name") + "%") : "");
        params.put("sku", params.get("sku") != null && StringUtils.isNotBlank(params.get("sku").toString()) ? ("%" + params.get("sku") + "%") : "");
        List<ProductAmzUpload> result= productAmzUploadDao.selectList(params);
        if(GeneralUtils.isNotNullOrEmpty(result)){
            for(ProductAmzUpload upload:result){
               if(StringUtils.isNotBlank(upload.getUploadDesc())){
                   upload.setUploadDesc(upload.getUploadDesc().length()>200?(upload.getUploadDesc().substring(0,200)+"..."):upload.getUploadDesc());
               }
            }
        }
        return result;
    }

    /**
     * 预发布产品
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String prePublishProduct(String id, String language, String userId, String shopId) throws Exception {
        ClaimProduct product = claimProductExtMapper.selectByPrimaryKey(id);
        if (product != null) {
            List<ProductItemVar> vars = productSkuTypeService.selectListByProductId(id);
            if (language.equals("GB") || language.equals("US") || language.equals("AU")|| language.equals("CA")) {
                //GB 英国
                ProductAmzUpload uploadGB = generateCommonProperties(product, language, product.getBulletPointUk(), product.getItemUk(), product.getProductDescriptionUk(), product.getKeywordsUk());
                saveAmzUploadBySku(uploadGB, product, vars, userId,shopId);
                return uploadGB.getId();
            }

            if (language.equals("JP")) {
                //JP 日本
                ProductAmzUpload uploadJP = generateCommonProperties(product, language, product.getBulletPointJp(), product.getItemJp(), product.getProductDescriptionJp(), product.getKeywordsJp());
                saveAmzUploadBySku(uploadJP, product, vars, userId,shopId);
                return uploadJP.getId();
            }

            if (language.equals("CN")) {
                //CN
                ProductAmzUpload uploadCN = generateCommonProperties(product, language, product.getBulletPointCn(), product.getItemCn(), product.getProductDescriptionCn(), product.getKeywordsCn());
                saveAmzUploadBySku(uploadCN, product, vars, userId,shopId);
                return uploadCN.getId();
            }

            if (language.equals("DE")) {
                //DE 德国
                ProductAmzUpload uploadDE = generateCommonProperties(product, language, product.getBulletPointDe(), product.getItemDe(), product.getProductDescriptionDe(), product.getKeywordsDe());
                saveAmzUploadBySku(uploadDE, product, vars, userId,shopId);
                return uploadDE.getId();
            }

            if (language.equals("FR")) {
                //FR 法国
                ProductAmzUpload uploadFR = generateCommonProperties(product, language, product.getBulletPointFr(), product.getItemFr(), product.getProductDescriptionFr(), product.getKeywordsFr());
                saveAmzUploadBySku(uploadFR, product, vars, userId,shopId);
                return uploadFR.getId();
            }

            if (language.equals("ES") || language.equals("MX")) {
                //ES 西班牙
                ProductAmzUpload uploadES = generateCommonProperties(product, language, product.getBulletPointEs(), product.getItemEs(), product.getProductDescriptionEs(), product.getKeywordsEs());
                saveAmzUploadBySku(uploadES, product, vars, userId,shopId);
                return uploadES.getId();
            }

            if (language.equals("IT")) {
                //IT意大利
                ProductAmzUpload uploadIT = generateCommonProperties(product, language, product.getBulletPointIt(), product.getItemIt(), product.getProductDescriptionIt(), product.getKeywordsIt());
                saveAmzUploadBySku(uploadIT, product, vars, userId, shopId);
                return uploadIT.getId();
            }

            ClaimProduct update = new ClaimProduct();
            update.setId(product.getId());
            update.setIsPrepublish("1");
            claimProductExtMapper.updateByPrimaryKeySelective(update);
        }
        return "";

    }

    private int saveAmzUploadBySku(ProductAmzUpload upload, ClaimProduct product, List<ProductItemVar> vars, String userId, String shopId) throws Exception {
        int i = 0;
        if (GeneralUtils.isNotNullOrEmpty(vars)) {
            String theme = "";
            //清除之前预发布的数据
            productAmzUploadDao.deleteByAmzId(product.getId(), shopId);
            for (ProductItemVar var : vars) {
                if (StringUtils.isNotBlank(var.getVariationType())) {
                    theme = var.getVariationType();
                    break;
                }
            }
            for (ProductItemVar var : vars) {
                upload.setpState("1");
                upload.setPublishStatus("0");

                if (StringUtils.isBlank(var.getVariationType())) {//主体
                    upload.setQuantity(product.getQuantity());
                    upload.setParentChild("parent");
                    upload.setVariationTheme(theme);
                    upload.setMainPath(var.getMainPath());
                    upload.setAmzSku(product.getSku());

                } else {//变体
                    upload.setQuantity(var.getQuantity());
                    upload.setParentChild("child");
                    upload.setRelationshipType("Variation");
                    upload.setVariationTheme(var.getVariationType());
                    upload.setParentSku(product.getSku());
                    upload.setAmzSku(product.getSku() + "-" + GeneralUtils.getVarValue(var));

                }

                upload.setSaleEndDate(var.getSaleEndTime());
                upload.setSaleFromDate(var.getSaleStartTime());
                upload.setSizeMap(var.getSizeMap());
                upload.setSizeName(var.getSizeName());
                upload.setSalePrice(var.getSalePrice());
                upload.setMaterialType(var.getMaterialType());
                upload.setItemPackageQuantity(var.getQuantity());
                upload.setStandardPrice(var.getPrice());
                //upload.setVariationTheme(var.getVariationType());
                upload.setQuantity(var.getQuantity());
                upload.setProductAmzId(product.getId());
                upload.setCreateTime(new Date());

                upload.setUserId(userId);
                upload.setShopId(shopId);


                i += productAmzUploadDao.insertSelective(upload);


               /* if (vars.size() > 1) {//主体
                    if (StringUtils.isBlank(var.getVariationType())) {

                        upload.setId(selectOneByAmzID(product.getId(), shopId).getId());
                    }
                } else {

                }*/

                System.out.println(selectOneByAmzID(product.getId(), shopId).getId());
                upload.setId(selectOneByAmzID(product.getId(), shopId).getId());

            }
        } else {
            throw new Exception("product sku is null,id :" + product.getId());
        }

        return i;
    }

    public ProductAmzUpload selectOneByAmzID(String id, String shopId) {
        List<ProductAmzUpload> list = productAmzUploadDao.selectByAmzId(id, shopId);
        if (list.size() == 1) {
            return GeneralUtils.isNotNullOrEmpty(list) ? list.get(0) : new ProductAmzUpload();

        } else {
            for (ProductAmzUpload amzUpload : list) {
                if (amzUpload.getParentChild().equals("parent")) {
                    return amzUpload;
                }
            }

            return new ProductAmzUpload();
        }
    }

    private ProductAmzUpload generateCommonProperties(ClaimProduct product, String languageId, String points, String productName, String productDesc, String keywords) {
        ProductAmzUpload upload = new ProductAmzUpload();
        generateUploadPoints(upload, points);

        upload.setLanguageId(languageId);
        upload.setItemName(productName);
        upload.setProductDescription(GeneralUtils.removeAttr(productDesc));
        try {
            upload.setItemWeight(new BigDecimal(product.getProductWeight()));
            upload.setWebsiteShippingWeight(new BigDecimal(product.getDeliveryWeight()));

        } catch (Exception e) {
            System.out.println("商品或运输重量为空");
        }
        generateUploadKeywords(upload, keywords);

        return upload;
    }

    private void generateUploadKeywords(ProductAmzUpload uploadGB, String points) {
        List<String> keywordsGB = JSON.parseArray(points, String.class);
        uploadGB.setGenericKeywords1(keywordsGB.get(0));
        uploadGB.setGenericKeywords2(keywordsGB.get(1));
        uploadGB.setGenericKeywords3(keywordsGB.get(2));
        uploadGB.setGenericKeywords4(keywordsGB.get(3));
        uploadGB.setGenericKeywords5(keywordsGB.get(4));
    }

    private void generateUploadPoints(ProductAmzUpload uploadGB, String keywords) {
        List<String> pointGB = JSON.parseArray(keywords, String.class);
        uploadGB.setBulletPoint1(pointGB.get(0));
        uploadGB.setBulletPoint2(pointGB.get(1));
        uploadGB.setBulletPoint3(pointGB.get(2));
        uploadGB.setBulletPoint4(pointGB.get(3));
        uploadGB.setBulletPoint5(pointGB.get(4));
    }

    @Resource
    private ProductUploadLogDao productUploadLogDao;

    public ProductAmzUpload selectAmzUploadProduct(String id) {
        return productAmzUploadDao.selectByPrimaryKey(id);
    }

    public void batchPublish(List<String> ids) {
        for (String id : ids) {
            if (StringUtils.isBlank(id)) {
                continue;
            }
            ProductAmzUpload product = productAmzUploadDao.selectByPrimaryKey(id);
            product.setPublishStatus(PublishStatusEnum.NOT.getVal());
            product.setUpdateDelete("update");
            product.setPublishTime(new Date());

            //产品魔板暂时都为空
            product.setProductTypeName("  ");

            updateClaimProduct(PublishStatusEnum.NOT, product.getProductAmzId());
            productAmzUploadDao.updateByPrimaryKeySelective(product);
        }
    }

    public void uploadProduct(ProductAmzUpload product) {

        logger.debug("************  start upload product ****************  id -----> " + product.getId());
        //List<ProductItemVar> vars = productSkuTypeService.selectListByProductId(product.getProductAmzId());


        //ResponseDto response = amzUpload.uploadProduct(product, shop, vars);
       /* logger.debug("call amz result:  " + JSON.toJSONString(response));
        if (response.isSuccess()) {
            ProductUploadLog log = new ProductUploadLog();
            log.setProductId(product.getId());
            if (StringUtils.isNotBlank(response.getCode())) {
                log.setStatus(response.getCode().equals("001") ? PublishStatusEnum.FAILED.toString() : PublishStatusEnum.PROCESS.toString());
            } else {
                log.setStatus(PublishStatusEnum.PROCESS.toString());
            }
            log.setSubmitId(response.getData() == null ? "" : response.getData().toString());
            log.setShopId(shop.get("SHOP_ID").toString());
            log.setResponse(response.getMsg());
            productUploadLogDao.insert(log);
        } else {
            product.setUploadDesc(response.getMsg());
        }*/

        //1发布失败 2发布成功 3发布中 0预发布
        product.setPublishStatus(PublishStatusEnum.NOT.getVal());
        product.setUpdateDelete("update");
        product.setPublishTime(new Date());

        //产品魔板暂时都为空
        product.setProductTypeName("  ");

        updateClaimProduct(PublishStatusEnum.NOT, product.getProductAmzId());
        productAmzUploadDao.updateByPrimaryKeySelective(product);
        logger.debug("************  end upload product ****************  id -----> " + product.getId());
    }

    public void updateClaimProduct(PublishStatusEnum statusEnum, String claimId) {
        ClaimProduct product = new ClaimProduct();
        product.setId(claimId);
        product.setIsPrepublish(statusEnum.toString());
        claimProductExtMapper.updateByPrimaryKeySelective(product);
    }

    @Resource
    private ProductIdGenDao productIdGenDao;

    public List<ProductIdGen> selectProductIdGenList(Map<String, Object> params) {
        return productIdGenDao.selectProductIdGenList(params);
    }

    public boolean saveProductId(String type, String createUser, List<String> ids) {
        for (String id : ids) {
            ProductIdGen gen = new ProductIdGen();
            gen.setProductId(id);
            gen.setType(type);
            gen.setCreateUser(createUser);
            if (productIdGenDao.selectProductIdExist(type, id) == 0L) {
                productIdGenDao.insertSelective(gen);
            }
        }
        return true;
    }

    public List<ProductIdGen> selectProductIdForUse(Map<String, Object> params) {
        return productIdGenDao.selectProductIdForUse(params);
    }


    @Resource
    private ShopManageService shopManageService;
    @Autowired
    private CommonSet set;

    public void initshopCategory(String id) {
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>> shops = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            //shops= shopManageService.selectShopsForImportCategory();
        } else {
            Map<String, Object> shop = shopManageService.selectShopById(id);
            shops.add(shop);
        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 100, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));

        for (Map<String, Object> shop : shops) {

            initCategory(set.getProductCategoryPath() + shop.get("SHOP_ID") + ".txt", shop);
            /*executor.execute(new Runnable() {
                @Override
                public void run() {
                    initCategory(set.getProductCategoryPath() + shop.get("SHOP_ID") + ".txt", shop);
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executor.isTerminated());


    }

    // "C:\\Users\\fengsong\\Desktop\\cate.txt"
    public void initCategory(String path, Map<String, Object> shop) {

        amzUpload.write(path, shop);

        /*InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(path));

            SAXBuilder sb = new SAXBuilder();
            org.jdom.Document doc = sb.build(isr);

            Element root = doc.getRootElement();
            List<Element> list = root.getChildren("Node");
            int i = 0;
            List<String> ids = new ArrayList<>();
            String code = shop.get("COUNTRY_CODE").toString();
            productUploadCategoryDao.deleteByShopId(shop.get("SHOP_ID").toString(), code);
            List<ProductUploadCategory> categories = new ArrayList<>();
            for (Element e : list) {
                i++;
                String itemType = e.getChild("browseNodeAttributes").getChildText("attribute") == null ? "" : e.getChild("browseNodeAttributes").getChildText("attribute");
                ProductUploadCategory category = new ProductUploadCategory();
                boolean hasChildren = e.getChild("hasChildren").getText().equals("true");
                if (hasChildren) {
                    category.setHasChild("1");
                    category.setChildCount(Integer.parseInt(e.getChild("childNodes").getAttribute("count").getValue()));
                } else {
                    category.setHasChild("0");
                    category.setChildCount(0);
                }
                String pathp = e.getChild("browsePathById").getText();
                category.setName(e.getChild("browseNodeStoreContextName").getText());
                if (StringUtils.isBlank(category.getName())) {
                    category.setName(e.getChild("browsePathByName").getText());
                    ProductUploadCategory pc = new ProductUploadCategory();
                    pc.setName(e.getChild("browseNodeName").getText());
                    pc.setParentId("0");
                    pc.setId(pathp.split(",")[0]);
                    pc.setCountryCode(code);
                    pc.setShopId(shop.get("SHOP_ID").toString());
                    pc.setHasChild("0");
                    pc.setTypeDef("");
                    pc.setItemType(itemType);
                    pc.setPath("");
                    pc.setChildCount(0);

                    System.out.println(JSON.toJSONString(pc));
                    if (ids.indexOf(pc.getId()) == -1) {
                        categories.add(pc);
                        ids.add(pc.getId());
                    }
                }
                category.setId(e.getChild("browseNodeId").getText());

                category.setParentId(pathp.split(",")[pathp.split(",").length - 2]);
                category.setPath(pathp);
                category.setItemType(itemType);
                category.setCountryCode(code);
                category.setShopId(shop.get("SHOP_ID").toString());
                category.setTypeDef(e.getChildText("productTypeDefinitions"));
                System.out.println(JSON.toJSONString(category));
                if (ids.indexOf(category.getId()) == -1) {
                    categories.add(category);
                    ids.add(category.getId());
                }

                if (categories.size() > 100) {
                    productUploadCategoryDao.batchInsert(categories);
                    categories = new ArrayList<>(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }


    public List<ProductUploadCategory> selectList(String parentId, String countryCode) {
        return productUploadCategoryDao.selectList(parentId, countryCode);
    }

    public List<ProductUploadCategory> selectListParent(String countryCode) {
        return productUploadCategoryDao.selectListParent(countryCode);
    }


    public List<ProductUploadLog> selectLogList(String s) {

        return productUploadLogDao.selectLogList(s);
    }
}
