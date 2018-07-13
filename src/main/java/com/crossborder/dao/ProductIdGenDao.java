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

    List<String> oneCompanyUsers(String userId);

    Long selectProductIdExist(@Param("type") String type, @Param("productId") String productId);

    List<ProductIdGen> selectProductIdForUse(Map<String, Object> params);

    ProductIdGen selectProductIdForUseOne(@Param("type") String type, @Param("userId") String user);

    ProductIdGen selectProductIdByAmzSku(@Param("amzSku") String amzSku);

    Long updateUsed(@Param("type") String type, @Param("uploadId") String uploadId, @Param("productId") String id, @Param("amzSku") String amzSku);

}
