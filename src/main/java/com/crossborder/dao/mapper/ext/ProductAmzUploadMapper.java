package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ProductAmzUpload;
import java.math.BigDecimal;

public interface ProductAmzUploadMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(ProductAmzUpload record);

    int insertSelective(ProductAmzUpload record);

    ProductAmzUpload selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(ProductAmzUpload record);

    int updateByPrimaryKey(ProductAmzUpload record);
}