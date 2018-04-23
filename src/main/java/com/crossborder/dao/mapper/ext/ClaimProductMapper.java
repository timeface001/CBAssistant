package com.crossborder.dao.mapper.ext;

import com.crossborder.entity.ClaimProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(ClaimProduct record);

    int insertSelective(ClaimProduct record);

    ClaimProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ClaimProduct record);

    int updateByPrimaryKey(ClaimProduct record);
}