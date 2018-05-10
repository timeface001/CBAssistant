package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductIdGenMapper;
import com.crossborder.dao.mapper.ext.ProductUploadCategoryMapper;
import com.crossborder.entity.ProductIdGen;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by fengsong on 2018/4/14.
 */
@Repository
public interface ProductUploadCategoryDao extends ProductUploadCategoryMapper {



}
