package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ProductUploadValues;

public interface ProductUploadValuesMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductUploadValues record);

    int insertSelective(ProductUploadValues record);

    ProductUploadValues selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductUploadValues record);

    int updateByPrimaryKey(ProductUploadValues record);
}