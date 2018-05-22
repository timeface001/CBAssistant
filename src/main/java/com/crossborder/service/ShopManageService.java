package com.crossborder.service;

import com.crossborder.dao.ShopManageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/4/7.
 */
@Service
public class ShopManageService {
    @Resource
    private ShopManageDao shopManageDao;

    public int addShop(Map<String, Object> map) {
        return shopManageDao.addShop(map);
    }

    public List<Map<String, Object>> selectShops(Map<String, Object> map) {
        return shopManageDao.selectShops(map);
    }

    public List<Map<String, Object>> selectShopsForImportCategory() {
        return shopManageDao.selectShopsForImport();
    }

    public int updateShopState(Map<String, Object> map) {
        return shopManageDao.updateShopState(map);
    }

    public int deleteShop(String id) {
        return shopManageDao.deleteShop(id);
    }

    public int updateShop(Map<String, Object> map) {
        return shopManageDao.updateShop(map);
    }

    public List<Map<String, Object>> selectShopsById(Map<String, Object> map) {
        return shopManageDao.selectShopsById(map);
    }

}
