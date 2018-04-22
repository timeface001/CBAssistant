package com.crossborder.dao;

import com.crossborder.dao.mapper.ext.ClaimProductMapper;
import com.crossborder.entity.ClaimProduct;

import java.util.List;
import java.util.Map;

public interface ClaimProductExtMapper extends ClaimProductMapper{
     List<ClaimProduct> selectList(Map<String,Object> params);

}