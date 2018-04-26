package com.crossborder.service;

import com.crossborder.dao.ClaimProductDao;
import com.crossborder.dao.ProductManagerDao;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.utils.BaiduTranApi;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ProductStateEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    private ClaimProductDao claimProductExtMapper;

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
                claimProduct.setBulletPointCn(productDesc);
                claimProduct.setBulletPointDe(BaiduTranApi.getInstance().zh2De(productDesc));
                claimProduct.setBulletPointEs(BaiduTranApi.getInstance().zh2spa(productDesc));
                claimProduct.setBulletPointIt(BaiduTranApi.getInstance().zh2It(productDesc));
                claimProduct.setBulletPointJp(BaiduTranApi.getInstance().zh2Jp(productDesc));
                claimProduct.setBulletPointUk(BaiduTranApi.getInstance().zh2En(productDesc));
                claimProduct.setBulletPointFr(BaiduTranApi.getInstance().zh2Fra(productDesc));

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

    public void save(ClaimProduct product){

        claimProductExtMapper.updateByPrimaryKeySelective(product);
    }

    public ClaimProduct selectClaimProduct(String id) {
        return claimProductExtMapper.selectByPrimaryKey(id);
    }

}
