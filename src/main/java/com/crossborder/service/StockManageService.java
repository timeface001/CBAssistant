package com.crossborder.service;

import com.crossborder.dao.StockManageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/8/8.
 */
@Service
public class StockManageService {
    @Resource
    private StockManageDao stockManageDao;

    public int addGoods(Map<String, Object> map) {
        return stockManageDao.addGoods(map);
    }

    public List<Map<String, Object>> getGoods(Map<String, Object> map) {
        return stockManageDao.getGoods(map);
    }

    public int addGoodsIn(Map<String, Object> map) {
        return stockManageDao.addGoodsIn(map);
    }

    public List<Map<String, Object>> getGoodsIn(Map<String, Object> map) {
        return stockManageDao.getGoodsIn(map);
    }

    public int addGoodsList(Map<String, Object> map) {
        return stockManageDao.addGoodsList(map);
    }

    public int updateGoodsList(Map<String, Object> map) {
        return stockManageDao.updateGoodsList(map);
    }

    public List<Map<String, Object>> getGoodsList(Map<String, Object> map) {
        return stockManageDao.getGoodsList(map);
    }

    public List<Map<String, Object>> getGoodsOut(Map<String, Object> map) {
        return stockManageDao.getGoodsOut(map);
    }

    public int updateGoodsIn(Map<String, Object> map) {
        return stockManageDao.updateGoodsIn(map);
    }

    public int addGoodsOut(Map<String, Object> map) {
        return stockManageDao.addGoodsOut(map);
    }

    public int delGoods(String pSku) {
        return stockManageDao.delGoods(pSku);
    }
    public int delGoodsList(String pSku) {
        return stockManageDao.delGoodsList(pSku);
    }

    public int updateGoods(Map<String, Object> map) {
        return stockManageDao.updateGoods(map);
    }

    public int delGoodsIn(String id) {
        return stockManageDao.delGoodsIn(id);
    }
}
