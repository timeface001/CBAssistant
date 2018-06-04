package com.crossborder.service;

import com.crossborder.dao.ProductItemVarDao;
import com.crossborder.dao.ProductItemVarTypeDao;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.entity.ProductItemVarType;
import org.apache.commons.lang3.StringUtils;
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

    public List<ProductItemVar> selectListByProductId(String productId) {
        return productItemVarDao.selectListByProductId(productId);
    }

    public void save(List<ProductItemVar> list,String productId) {
        if(list!=null&&!list.isEmpty()){
            productItemVarDao.clear(productId);
            String mainPath = "";
            String otherPath = "";
            int i = 0;
            for(ProductItemVar var:list){
                if (i == 0) {
                    mainPath = var.getMainPath();
                    otherPath = var.getAttachPath();
                }
                i++;

                if (StringUtils.isBlank(var.getMainPath())) {
                    var.setMainPath(mainPath);
                    var.setAttachPath(otherPath);
                }
                productItemVarDao.insertSelective(var);
            }
        }
    }
}
