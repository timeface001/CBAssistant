package com.crossborder.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/4/2.
 */
@Repository
public interface CommonDao {
    public List<Map<String, Object>> login(Map<String, Object> map);

    public List<Map<String, Object>> selectMenus(Map<String, Object> map);

    public List<Map<String, Object>> getSql(String code);

    public List<Map<String, Object>> getList(@Param("sqlText") String sqlText);

    public List<Map<String, Object>> getTreeList(@Param("sqlText") String sqlText);

    public List<Map<String, Object>> selectCountryByCode(String id);

}
