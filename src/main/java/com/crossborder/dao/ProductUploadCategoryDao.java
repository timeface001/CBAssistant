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

    List<ProductUploadCategory> selectListParent(@Param("countryCode") String countryCode);

    List<ProductUploadCategory> selectList(@Param("parentId") String parentId, @Param("countryCode") String countryCode);

    void batchInsert(@Param("list") List<ProductUploadCategory> list);

}
