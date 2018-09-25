package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ProductUploadValuesMapper;
import com.crossborder.dao.mapper.ext.UploadLogMapper;
import com.crossborder.entity.ProductUploadValues;
import com.crossborder.entity.UploadLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadLogDao extends UploadLogMapper {
    List<UploadLog> selectList();
}