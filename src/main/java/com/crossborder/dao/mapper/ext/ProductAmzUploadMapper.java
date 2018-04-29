package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ProductAmzUpload;

public interface ProductAmzUploadMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductAmzUpload record);

    int insertSelective(ProductAmzUpload record);

    ProductAmzUpload selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductAmzUpload record);

    int updateByPrimaryKey(ProductAmzUpload record);
}