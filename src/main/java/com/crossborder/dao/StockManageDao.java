package com.crossborder.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/8/8.
 */
@Repository
public interface StockManageDao {
    int addGoods(Map<String, Object> map);

    List<Map<String,Object>> getGoods(Map<String, Object> map);
}
