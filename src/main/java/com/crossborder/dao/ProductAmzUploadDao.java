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

    List<ProductAmzUpload> selectNeedPulishList();

    List<ProductAmzUpload> selectListByUser(@Param("userId") String userId);

    void deleteByAmzId(@Param("amzId") String id, @Param("shopId") String shopId);

    List<ProductAmzUpload> selectByAmzId(@Param("amzId") String id, @Param("shopId") String shopId);

    ProductAmzUpload selectBySku(@Param("itemSku") String itemSku, @Param("amzSku") String amzSku);

    void updateBySku(@Param("amzId") String productAmzId, @Param("amzSku") String amzSku,@Param("type") String externalProductIdType,@Param("productId") String externalProductId);

    List<ProductAmzUpload> selectListGroupByShop(Map<String, Object> pStatus);
}