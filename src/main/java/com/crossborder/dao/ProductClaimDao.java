package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ClaimProductMapper;
import com.crossborder.entity.ClaimProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductClaimDao extends ClaimProductMapper {
     List<ClaimProduct> selectList(Map<String,Object> params);

     Long countBySku(@Param("sku") String sku);

}