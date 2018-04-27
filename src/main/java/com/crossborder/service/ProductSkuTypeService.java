package com.crossborder.service;

import com.crossborder.dao.ProductItemVarDao;
import com.crossborder.dao.ProductItemVarTypeDao;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.entity.ProductItemVarType;
import com.crossborder.utils.GeneralUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductSkuTypeService {
    @Resource
    private ProductItemVarDao productItemVarDao;
    @Resource
    private ProductItemVarTypeDao productItemVarTypeDao;


    public List<ProductItemVarType> selectTypeList() {
        return productItemVarTypeDao.selectList();
    }

    public List<ProductItemVar> selectListByProductId(String productId) {
        return productItemVarDao.selectListByProductId(productId);
    }

    public void save(List<ProductItemVar> list,String productId) {
        if(list!=null&&!list.isEmpty()){
            productItemVarDao.clear(productId);
            for(ProductItemVar var:list){
                productItemVarDao.insertSelective(var);
            }
        }
    }
}
