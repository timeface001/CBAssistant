package com.crossborder.service;

import com.alibaba.fastjson.JSON;
import com.crossborder.dao.ProductAmzUploadDao;
import com.crossborder.dao.ProductClaimDao;
import com.crossborder.dao.ProductManagerDao;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.utils.BaiduTranApi;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ProductStateEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public boolean save(Map<String, Object> product) {
        product.put("createTime", new Date());
        if (product.get("id") != null) {
            return productManagerDao.updateProduct(product) == 1;
        } else {
            return productManagerDao.insertProduct(product) == 1;
        }

    }

    public List<Map<String, Object>> selectList(Map<String, Object> params) {
        return productManagerDao.selectList(params);
    }

    public List<ClaimProduct> selectClaimList(Map<String, Object> params) {
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

                //产品描述翻译
                String productDesc = GeneralUtils.nullToEmpty(product.get("INFO"));
                claimProduct.setProductDescriptionCn(productDesc);
                claimProduct.setProductDescriptionDe(BaiduTranApi.getInstance().zh2De(productDesc));
                claimProduct.setProductDescriptionEs(BaiduTranApi.getInstance().zh2spa(productDesc));
                claimProduct.setProductDescriptionIt(BaiduTranApi.getInstance().zh2It(productDesc));
                claimProduct.setProductDescriptionJp(BaiduTranApi.getInstance().zh2Jp(productDesc));
                claimProduct.setProductDescriptionUk(BaiduTranApi.getInstance().zh2En(productDesc));
                claimProduct.setProductDescriptionFr(BaiduTranApi.getInstance().zh2Fra(productDesc));

                //标题翻译
                String name = GeneralUtils.nullToEmpty(product.get("NAME"));
                claimProduct.setItemCn(productDesc);
                claimProduct.setItemDe(BaiduTranApi.getInstance().zh2De(name));
                claimProduct.setItemEs(BaiduTranApi.getInstance().zh2spa(name));
                claimProduct.setItemIt(BaiduTranApi.getInstance().zh2It(name));
                claimProduct.setItemJp(BaiduTranApi.getInstance().zh2Jp(name));
                claimProduct.setItemUk(BaiduTranApi.getInstance().zh2En(name));
                claimProduct.setItemFr(BaiduTranApi.getInstance().zh2Fra(name));

                claimProduct.setCreateTime(new Date());
                claimProduct.setCreateUser(GeneralUtils.getUserId());
                claimProduct.setSkuType("1");
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
        product.setProductDescriptionEs(BaiduTranApi.getInstance().zh2spa(productDesc));
        product.setProductDescriptionIt(BaiduTranApi.getInstance().zh2It(productDesc));
        product.setProductDescriptionJp(BaiduTranApi.getInstance().zh2Jp(productDesc));
        product.setProductDescriptionUk(BaiduTranApi.getInstance().zh2En(productDesc));
        product.setProductDescriptionFr(BaiduTranApi.getInstance().zh2Fra(productDesc));

        List<String> keyDe = new ArrayList<>();
        List<String> keyEs = new ArrayList<>();
        List<String> keyIt = new ArrayList<>();
        List<String> keyJp = new ArrayList<>();
        List<String> keyUk = new ArrayList<>();
        List<String> keyFr = new ArrayList<>();
        for (String key : keyList) {
            keyDe.add(BaiduTranApi.getInstance().zh2De(key));
            keyEs.add(BaiduTranApi.getInstance().zh2spa(key));
            keyIt.add(BaiduTranApi.getInstance().zh2It(key));
            keyJp.add(BaiduTranApi.getInstance().zh2Jp(key));
            keyUk.add(BaiduTranApi.getInstance().zh2En(key));
            keyFr.add(BaiduTranApi.getInstance().zh2Fra(key));

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
            pointEs.add(BaiduTranApi.getInstance().zh2spa(point));
            pointIt.add(BaiduTranApi.getInstance().zh2It(point));
            pointJp.add(BaiduTranApi.getInstance().zh2Jp(point));
            pointUk.add(BaiduTranApi.getInstance().zh2En(point));
            pointFr.add(BaiduTranApi.getInstance().zh2Fra(point));

        }
        product.setBulletPointCn(product.getKeywordsCn());
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
        return productAmzUploadDao.selectList(params);

    }

    /**
     * 预发布产品
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int prePublishProduct(String id) throws Exception {
        ClaimProduct product = claimProductExtMapper.selectByPrimaryKey(id);
        if (product != null) {
            //GB 英国
            ProductAmzUpload uploadGB = generateCommonProperties(product.getBulletPointUk(), product.getItemUk(), product.getProductDescriptionUk(), product.getKeywordsUk());
            //JP 日本
            ProductAmzUpload uploadJP = generateCommonProperties(product.getBulletPointJp(), product.getItemJp(), product.getProductDescriptionJp(), product.getKeywordsJp());
            //CN
            ProductAmzUpload uploadCN = generateCommonProperties(product.getBulletPointCn(), product.getItemCn(), product.getProductDescriptionCn(), product.getKeywordsCn());
            //DE 德国
            ProductAmzUpload uploadDE = generateCommonProperties(product.getBulletPointDe(), product.getItemDe(), product.getProductDescriptionDe(), product.getKeywordsDe());
            //FR 法国
            ProductAmzUpload uploadFR = generateCommonProperties(product.getBulletPointFr(), product.getItemFr(), product.getProductDescriptionFr(), product.getKeywordsFr());
            //ES 西班牙
            ProductAmzUpload uploadES = generateCommonProperties(product.getBulletPointEs(), product.getItemEs(), product.getProductDescriptionEs(), product.getKeywordsEs());
            //IT意大利
            ProductAmzUpload uploadIT = generateCommonProperties(product.getBulletPointIt(), product.getItemIt(), product.getProductDescriptionIt(), product.getKeywordsIt());

            int i = 0;
            i += productAmzUploadDao.insertSelective(uploadGB);
            i += productAmzUploadDao.insertSelective(uploadES);
            i += productAmzUploadDao.insertSelective(uploadDE);
            i += productAmzUploadDao.insertSelective(uploadFR);
            i += productAmzUploadDao.insertSelective(uploadJP);
            i += productAmzUploadDao.insertSelective(uploadCN);
            i += productAmzUploadDao.insertSelective(uploadIT);

            if (i < 7) {
                throw new Exception("pre publish insert failed.");
            }

        }

        return 0;
    }

    private ProductAmzUpload generateCommonProperties(String points, String productName, String productDesc, String keywords) {
        ProductAmzUpload upload = new ProductAmzUpload();
        generateUploadPoints(upload, points);

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

}
