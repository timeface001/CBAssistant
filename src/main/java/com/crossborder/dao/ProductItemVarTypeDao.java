package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductItemVarTypeMapper;
import com.crossborder.entity.ProductItemVarType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemVarTypeDao extends ProductItemVarTypeMapper {
    List<ProductItemVarType> selectList();
}