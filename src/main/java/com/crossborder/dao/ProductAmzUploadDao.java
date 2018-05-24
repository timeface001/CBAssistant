package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductAmzUploadMapper;
import com.crossborder.entity.ProductAmzUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductAmzUploadDao extends ProductAmzUploadMapper{
     List<ProductAmzUpload> selectList(Map<String, Object> params);

    void deleteByAmzId(@Param("amzId") String id, @Param("shopId") String shopId);

    List<ProductAmzUpload> selectByAmzId(@Param("amzId") String id, @Param("shopId") String shopId);
}