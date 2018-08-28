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

    int addGoodsIn(Map<String, Object> map);

    int addGoodsList(Map<String, Object> map);

    int updateGoodsList(Map<String, Object> map);

    List<Map<String, Object>> getGoodsList(Map<String, Object> map);

    List<Map<String, Object>> getGoods(Map<String, Object> map);

    List<Map<String, Object>> getGoodsIn(Map<String, Object> map);

    List<Map<String, Object>> getGoodsOut(Map<String, Object> map);

    int updateGoodsIn(Map<String, Object> map);

    int addGoodsOut(Map<String, Object> map);

    int delGoods(String pSku);

    int delGoodsList(String pSku);

    int updateGoods(Map<String, Object> map);

    int delGoodsIn(String id);
}
