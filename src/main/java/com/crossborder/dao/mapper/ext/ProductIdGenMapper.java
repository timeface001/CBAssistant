package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ProductIdGen;

public interface ProductIdGenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductIdGen record);

    int insertSelective(ProductIdGen record);

    ProductIdGen selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductIdGen record);

    int updateByPrimaryKey(ProductIdGen record);
}