package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ProductUploadLog;

public interface ProductUploadLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductUploadLog record);

    int insertSelective(ProductUploadLog record);

    ProductUploadLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductUploadLog record);

    int updateByPrimaryKey(ProductUploadLog record);
}