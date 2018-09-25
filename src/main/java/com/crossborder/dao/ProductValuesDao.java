package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductUploadValuesMapper;
import com.crossborder.entity.ProductItemVarType;
import com.crossborder.entity.ProductUploadValues;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductValuesDao extends ProductUploadValuesMapper {
    List<ProductUploadValues> selectList();
}