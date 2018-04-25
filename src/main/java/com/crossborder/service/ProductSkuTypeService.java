package com.crossborder.service;

import com.crossborder.dao.ProductItemVarDao;
import com.crossborder.dao.ProductItemVarTypeDao;
import com.crossborder.entity.ProductItemVarType;
import org.springframework.stereotype.Service;

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
}
