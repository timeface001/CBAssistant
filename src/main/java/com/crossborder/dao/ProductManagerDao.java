package com.crossborder.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by fengsong on 2018/4/14.
 */
@Repository
public interface ProductManagerDao {
    int insertProduct(Map<String, Object> product);

    List<Map<String, Object>> selectList(Map<String, Object> params);

    int updateState(Map<String,Object> params);

    void delete(@Param("id") String id);
}
