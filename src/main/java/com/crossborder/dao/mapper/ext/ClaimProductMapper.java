package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ClaimProduct;

public interface ClaimProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(ClaimProduct record);

    int insertSelective(ClaimProduct record);

    ClaimProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ClaimProduct record);

    int updateByPrimaryKey(ClaimProduct record);
}