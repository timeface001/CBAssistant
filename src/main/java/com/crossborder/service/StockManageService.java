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

    public int addGoods(Map<String,Object> map){
        return stockManageDao.addGoods(map);
    }

    public List<Map<String,Object>> getGoods(Map<String, Object> map) {
        return stockManageDao.getGoods(map);
    }
}
