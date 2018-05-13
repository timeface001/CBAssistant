package com.crossborder.service;

import com.crossborder.dao.CommonDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/4/2.
 */
@Service
public class CommonService {
    @Resource
    private CommonDao commonDao;

    public List<Map<String, Object>> login(Map<String, Object> map) {
        return commonDao.login(map);
    }

    public List<Map<String, Object>> selectMenus(Map<String, Object> map) {
        return commonDao.selectMenus(map);
    }

    public Map<String, Object> getSql(String code) {
        return commonDao.getSql(code).get(0);
    }

    public List<Map<String, Object>> getList(String sqlText) {
        return commonDao.getList(sqlText);
    }

    public List<Map<String, Object>> getTreeList(String sqlText) {
        return commonDao.getTreeList(sqlText);
    }

    public List<Map<String, Object>> selectCountryByCode(String id) {
        return commonDao.selectCountryByCode(id);
    }

    public int updateLogin(Map<String, Object> map) {
        return commonDao.updateLogin(map);
    }

    public int updateAddress(Map<String, Object> map) {
        return commonDao.updateAddress(map);
    }

    public int insertShips(Map<String, Object> map) {
        return commonDao.insertShips(map);
    }

    public List<Map<String, String>> getShipTypes(Map<String, String> map) {
        return commonDao.getShipTypes(map);
    }

    public int insertShipMent(Map<String, Object> map) {
        return commonDao.insertShipMent(map);
    }

    public int insertCustomsInfo(Map<String, Object> map) {
        return commonDao.insertCustomsInfo(map);
    }

    public List<Map<String, Object>> selectTypes(String id) {
        return commonDao.selectTypes(id);
    }
}
