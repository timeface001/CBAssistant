package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ProductItemVar;

public interface ProductItemVarMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductItemVar record);

    int insertSelective(ProductItemVar record);

    ProductItemVar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductItemVar record);

    int updateByPrimaryKey(ProductItemVar record);
}