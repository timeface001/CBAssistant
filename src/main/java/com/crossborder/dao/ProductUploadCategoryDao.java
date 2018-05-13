package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductUploadCategoryMapper;
import com.crossborder.entity.ProductUploadCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fengsong on 2018/4/14.
 */
@Repository
public interface ProductUploadCategoryDao extends ProductUploadCategoryMapper {


    void deleteByShopId(@Param("shopId") String shopId, @Param("code") String code);

    List<ProductUploadCategory> selectListParent(@Param("shopId") String shopId);

    List<ProductUploadCategory> selectList(@Param("parentId") String parentId, @Param("shopId") String shopId);

    void batchInsert(@Param("list") List<ProductUploadCategory> list);

}
