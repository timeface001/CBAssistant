package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductUploadCategoryMapper;
import com.crossborder.dao.mapper.ext.ProductUploadLogMapper;
import com.crossborder.entity.ProductUploadCategory;
import com.crossborder.entity.ProductUploadLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fengsong on 2018/4/14.
 */
@Repository
public interface ProductUploadLogDao extends ProductUploadLogMapper {

    List<ProductUploadLog> selectLogList(@Param("status") String s);
}
