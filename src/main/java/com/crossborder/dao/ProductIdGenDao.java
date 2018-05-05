package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductIdGenMapper;
import com.crossborder.entity.ProductIdGen;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by fengsong on 2018/4/14.
 */
@Repository
public interface ProductIdGenDao extends ProductIdGenMapper {

    List<ProductIdGen> selectProductIdGenList(Map<String, Object> params);

    Long selectProductIdExist(@Param("type") String type, @Param("productId") String productId);

    ProductIdGen selectProductIdForUse(@Param("type") String type);

}