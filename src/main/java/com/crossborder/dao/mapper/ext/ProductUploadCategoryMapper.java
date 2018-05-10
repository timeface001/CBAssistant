package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ProductUploadCategory;

public interface ProductUploadCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductUploadCategory record);

    int insertSelective(ProductUploadCategory record);

    ProductUploadCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductUploadCategory record);

    int updateByPrimaryKey(ProductUploadCategory record);
}