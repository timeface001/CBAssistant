package com.crossborder.service;

import com.crossborder.dao.FinanceManageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/6/10.
 */
@Service
public class FinanceManageService {
    @Resource
    private FinanceManageDao financeManageDao;

    public List<Map<String, Object>> selectShippings(Map<String, Object> map) {
        return financeManageDao.selectShippings(map);
    }

    public int updateShipping(Map<String, Object> map) {
        return financeManageDao.updateShipping(map);
    }

    public List<Map<String, Object>> selectFees(Map<String, Object> map) {
        return financeManageDao.selectFees(map);
    }
}
