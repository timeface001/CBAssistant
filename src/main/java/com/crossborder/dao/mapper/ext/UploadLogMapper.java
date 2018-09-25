package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.UploadLog;

public interface UploadLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UploadLog record);

    int insertSelective(UploadLog record);

    UploadLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UploadLog record);

    int updateByPrimaryKeyWithBLOBs(UploadLog record);

    int updateByPrimaryKey(UploadLog record);
}