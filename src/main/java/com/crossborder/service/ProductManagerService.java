package com.crossborder.service;

import com.alibaba.fastjson.JSON;
import com.crossborder.dao.*;
import com.crossborder.entity.*;
import com.crossborder.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
            productManagerDao.updateState(GeneralUtils.genMap("id", id, "pState", stateEnum.getValue()));
            if (stateEnum.compareTo(ProductStateEnum.claim) == 0) {//认领插入
                Map<String, Object> product = productManagerDao.selectOne(id);
                ClaimProduct claimProduct = new ClaimProduct();
                claimProduct.setCreateUser(GeneralUtils.getUserId());
                claimProduct.setImagePath(GeneralUtils.nullToEmpty(product.get("MAIN_PATH")));
                claimProduct.setPrice(new BigDecimal(String.valueOf(product.get("PRICE"))));
                claimProduct.setProductId(product.get("ID").toString());
                claimProduct.setTypeId(product.get("TYPE_ID").toString());

                //产品描述翻译
                String productDesc = GeneralUtils.nullToEmpty(product.get("INFO"));
                claimProduct.setProductDescriptionCn(productDesc);
                claimProduct.setProductDescriptionDe(BaiduTranApi.getInstance().zh2De(productDesc));
                claimProduct.setProductDescriptionEs(BaiduTranApi.getInstance().zh2Es(productDesc));
                claimProduct.setProductDescriptionIt(BaiduTranApi.getInstance().zh2It(productDesc));
                claimProduct.setProductDescriptionJp(BaiduTranApi.getInstance().zh2Jp(productDesc));
                claimProduct.setProductDescriptionUk(BaiduTranApi.getInstance().zh2Uk(productDesc));
                claimProduct.setProductDescriptionFr(BaiduTranApi.getInstance().zh2Fr(productDesc));

                //标题翻译
                String name = GeneralUtils.nullToEmpty(product.get("NAME"));
                claimProduct.setItemCn(name);
                claimProduct.setItemDe(BaiduTranApi.getInstance().zh2De(name));
                claimProduct.setItemEs(BaiduTranApi.getInstance().zh2Es(name));
                claimProduct.setItemIt(BaiduTranApi.getInstance().zh2It(name));
                claimProduct.setItemJp(BaiduTranApi.getInstance().zh2Jp(name));
                claimProduct.setItemUk(BaiduTranApi.getInstance().zh2Uk(name));
                claimProduct.setItemFr(BaiduTranApi.getInstance().zh2Fr(name));

                claimProduct.setCreateTime(new Date());
                claimProduct.setCreateUser(GeneralUtils.getUserId());
                claimProduct.setSkuType("1");
                claimProduct.setSource(product.get("SOURCE") != null ? product.get("SOURCE").toString() : "");
                claimProductExtMapper.insertSelective(claimProduct);

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
        String productDesc = product.getProductDescriptionCn();
        product.setProductDescriptionCn(productDesc);
        product.setProductDescriptionDe(BaiduTranApi.getInstance().zh2De(productDesc));
        product.setProductDescriptionEs(BaiduTranApi.getInstance().zh2Es(productDesc));
        product.setProductDescriptionIt(BaiduTranApi.getInstance().zh2It(productDesc));
        product.setProductDescriptionJp(BaiduTranApi.getInstance().zh2Jp(productDesc));
        product.setProductDescriptionUk(BaiduTranApi.getInstance().zh2Uk(productDesc));
        product.setProductDescriptionFr(BaiduTranApi.getInstance().zh2Fr(productDesc));

        List<String> keyDe = new ArrayList<>();
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
        product.setKeywordsFr(JSON.toJSONString(keyFr));


        List<String> pointDe = new ArrayList<>();
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
        product.setBulletPointFr(JSON.toJSONString(pointFr));
        product.setUpdateState("1");//已编辑
        product.setUpdateTime(new Date());
        claimProductExtMapper.updateByPrimaryKeySelective(product);
    }

    public ClaimProduct selectClaimProduct(String id) {
        return claimProductExtMapper.selectByPrimaryKey(id);
    }

    public List<ProductAmzUpload> selectAmzUploadList(Map<String, Object> params) {
        params.put("name", params.get("name") != null && StringUtils.isNotBlank(params.get("name").toString()) ? ("%" + params.get("name") + "%") : "");
        return productAmzUploadDao.selectList(params);
    }

    /**
     * 预发布产品
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void prePublishProduct(String id, String language) throws Exception {
        ClaimProduct product = claimProductExtMapper.selectByPrimaryKey(id);
        if (product != null) {

            List<ProductItemVar> vars = productSkuTypeService.selectListByProductId(id);


            if (language.equals("GB")) {
                //GB 英国
                ProductAmzUpload uploadGB = generateCommonProperties(product, "GB", product.getBulletPointUk(), product.getItemUk(), product.getProductDescriptionUk(), product.getKeywordsUk());
                saveAmzUploadBySku(uploadGB, product, vars);
            }

            if (language.equals("JP")) {
                //JP 日本
                ProductAmzUpload uploadJP = generateCommonProperties(product, "JP", product.getBulletPointJp(), product.getItemJp(), product.getProductDescriptionJp(), product.getKeywordsJp());
                saveAmzUploadBySku(uploadJP, product, vars);
            }

            if (language.equals("CN")) {
                //CN
                ProductAmzUpload uploadCN = generateCommonProperties(product, "CN", product.getBulletPointCn(), product.getItemCn(), product.getProductDescriptionCn(), product.getKeywordsCn());
                saveAmzUploadBySku(uploadCN, product, vars);
            }

            if (language.equals("DE")) {
                //DE 德国
                ProductAmzUpload uploadDE = generateCommonProperties(product, "DE", product.getBulletPointDe(), product.getItemDe(), product.getProductDescriptionDe(), product.getKeywordsDe());
                saveAmzUploadBySku(uploadDE, product, vars);
            }

            if (language.equals("FR")) {
                //FR 法国
                ProductAmzUpload uploadFR = generateCommonProperties(product, "FR", product.getBulletPointFr(), product.getItemFr(), product.getProductDescriptionFr(), product.getKeywordsFr());
                saveAmzUploadBySku(uploadFR, product, vars);
            }

            if (language.equals("ES")) {
                //ES 西班牙
                ProductAmzUpload uploadES = generateCommonProperties(product, "ES", product.getBulletPointEs(), product.getItemEs(), product.getProductDescriptionEs(), product.getKeywordsEs());
                saveAmzUploadBySku(uploadES, product, vars);
            }

            if (language.equals("IT")) {
                //IT意大利
                ProductAmzUpload uploadIT = generateCommonProperties(product, "IT", product.getBulletPointIt(), product.getItemIt(), product.getProductDescriptionIt(), product.getKeywordsIt());
                saveAmzUploadBySku(uploadIT, product, vars);
            }

            ClaimProduct update = new ClaimProduct();
            update.setId(product.getId());
            update.setIsPrepublish("1");
            claimProductExtMapper.updateByPrimaryKeySelective(update);


        }

    }

    private int saveAmzUploadBySku(ProductAmzUpload upload, ClaimProduct product, List<ProductItemVar> vars) throws Exception {
        int i = 0;
        if (GeneralUtils.isNotNullOrEmpty(vars)) {
            for (ProductItemVar var : vars) {
                upload.setpState("1");
                upload.setQuantity(var.getQuantity());
                upload.setPublishStatus("0");

                if (StringUtils.isBlank(var.getVariationType())) {//主体
                    upload.setParentChild("parent");


                } else {//变体
                    upload.setParentChild("child");
                    upload.setRelationshipType("Variation");
                    upload.setVariationTheme(var.getVariationType());
                    upload.setParentSku(product.getSku());

                }

                upload.setSaleEndDate(var.getSaleEndTime());
                upload.setSaleFromDate(var.getSaleStartTime());
                upload.setSizeMap(var.getSizeMap());
                upload.setSizeName(var.getSizeName());
                upload.setSalePrice(var.getSalePrice());
                upload.setMaterialType(var.getMaterialType());
                upload.setItemPackageQuantity(var.getItemPackageQuantity());
                upload.setStandardPrice(var.getPrice());
                upload.setVariationTheme(var.getVariationType());
                upload.setQuantity(var.getQuantity());
                upload.setProductAmzId(product.getId());
                upload.setCreateTime(new Date());
                upload.setAmzSku(product.getSku());

                i += productAmzUploadDao.insertSelective(upload);

            }
        } else {
            throw new Exception("product sku is null,id :" + product.getId());
        }

        return i;
    }

    private ProductAmzUpload generateCommonProperties(ClaimProduct product, String languageId, String points, String productName, String productDesc, String keywords) {
        ProductAmzUpload upload = new ProductAmzUpload();
        generateUploadPoints(upload, points);

        upload.setLanguageId(languageId);
        upload.setItemName(productName);
        upload.setProductDescription(productDesc);

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

    public void uploadProduct(ProductAmzUpload product, Map<String, Object> shop) {

        ResponseDto response = amzUpload.uploadProduct(product, shop);
        System.out.println(JSON.toJSONString(response));
        ProductUploadLog log = new ProductUploadLog();
        log.setProductId(product.getId());
        log.setStatus("3");
        log.setSubmitId(response.getData().toString());
        log.setShopId(shop.get("SHOP_ID").toString());
        productUploadLogDao.insert(log);

        //1发布失败 2发布成功 3发布中
        product.setPublishStatus("3");
        product.setUpdateDelete("update");
        product.setPublishTime(new Date());


        productAmzUploadDao.updateByPrimaryKeySelective(product);

    }

    @Resource
    private ProductIdGenDao productIdGenDao;

    public List<ProductIdGen> selectProductIdGenList(Map<String, Object> params) {
        return productIdGenDao.selectProductIdGenList(params);
    }

    public boolean saveProductId(String type, List<String> ids) {

        for (String id : ids) {
            ProductIdGen gen = new ProductIdGen();
            gen.setProductId(id);
            gen.setType(type);
            gen.setCreateTime(new Date());
            if (productIdGenDao.selectProductIdExist(type, id) == 0L) {
                productIdGenDao.insertSelective(gen);

            }
        }

        return true;
    }

    public ProductIdGen selectProductIdForUse(String type) {
        return productIdGenDao.selectProductIdForUse(type);
    }


    @Resource
    private ShopManageService shopManageService;
    @Autowired
    private CommonSet set;

    public void initshopCategory() {
        Map<String,Object> params=new HashMap<>();
        final List<Map<String, Object>> shops = shopManageService.selectShops(params);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 100, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));

        for (Map<String, Object> shop : shops) {
            if (!shop.get("SHOP_ID").toString().equals("1039")) {

                initCategory(set.getProductCategoryPath() + shop.get("SHOP_ID") + ".txt", shop);
            }
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

        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(path));

            SAXBuilder sb = new SAXBuilder();
            org.jdom2.Document doc = sb.build(isr);

            Element root = doc.getRootElement();
            List<Element> list = root.getChildren("Node");
            int i = 0;
            List<String> ids = new ArrayList<>();
            String code = shop.get("COUNTRY_CODE").toString();
            productUploadCategoryDao.deleteByShopId(shop.get("SHOP_ID").toString(), code);
            List<ProductUploadCategory> categories = new ArrayList<>();
            for (Element e : list) {
                i++;
                ProductUploadCategory category = new ProductUploadCategory();
                boolean hasChildren = e.getChild("hasChildren").getText().equals("true");
                if (hasChildren) {
                    category.setHasChild("1");
                    category.setChildCount(Integer.parseInt(e.getChild("childNodes").getAttribute("count").getValue()));
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
                    System.out.println(JSON.toJSONString(pc));
                    if (ids.indexOf(pc.getId()) == -1) {
                        categories.add(pc);
                        ids.add(pc.getId());
                    }
                }
                category.setId(e.getChild("browseNodeId").getText());

                category.setParentId(pathp.split(",")[pathp.split(",").length - 2]);
                category.setPath(pathp);
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
        }
    }


    public List<ProductUploadCategory> selectList(String parentId, String shopId) {
        return productUploadCategoryDao.selectList(parentId, shopId);
    }

    public List<ProductUploadCategory> selectListParent(String shopId) {
        return productUploadCategoryDao.selectListParent(shopId);
    }


    public List<ProductUploadLog> selectLogList(String s) {

        return productUploadLogDao.selectLogList(s);
    }
}
