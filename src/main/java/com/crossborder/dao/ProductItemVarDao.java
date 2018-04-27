package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductItemVarMapper;
import com.crossborder.entity.ProductItemVar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemVarDao extends ProductItemVarMapper {

    List<ProductItemVar> selectListByProductId(@Param("productId")String productId);

    void clear(@Param("productId")String productId);
}