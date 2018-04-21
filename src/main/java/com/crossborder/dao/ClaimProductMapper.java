package com.crossborder.dao;

import com.crossborder.entity.ClaimProduct;

public interface ClaimProductMapper {
    int insert(ClaimProduct record);

    int insertSelective(ClaimProduct record);
}