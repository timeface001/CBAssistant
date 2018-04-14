package com.crossborder.service;

import com.crossborder.dao.OrderManageDao;
import com.crossborder.entity.AddressInfo;
import com.crossborder.entity.LocalOrder;
import com.crossborder.entity.LocalOrderItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/3/23.
 */
@Service
public class OrderManageService {
    @Resource
    private OrderManageDao orderManageDao;

    public int insertOrders(LocalOrder order) {
        return orderManageDao.insertOrders(order);
    }

    public int insertOrderItem(LocalOrderItem localOrderItem) {
        return orderManageDao.insertOrderItem(localOrderItem);
    }

    public List<Map<String, Object>> selectLocalOrder(Map<String, Object> map) {
        return orderManageDao.selectLocalOrder(map);
    }

    public List<Map<String, Object>> selectLocalOrderItem(String amazonOrderId) {
        return orderManageDao.selectLocalOrderItem(amazonOrderId);
    }

    public int updateOrder(Map<String, Object> map) {
        return orderManageDao.updateOrder(map);
    }

    public int updateOrderItem(Map<String, Object> map) {
        return orderManageDao.updateOrderItem(map);
    }

    public int inserOperationLog(Map<String, Object> map) {
        return orderManageDao.inserOperationLog(map);
    }

    public int updateOrderRemark(Map<String, Object> map) {
        return orderManageDao.updateOrderRemark(map);
    }

    public List<Map<String, Object>> selectOperationLog(String amazonOrderId) {
        return orderManageDao.selectOperationLog(amazonOrderId);
    }

    public int insertAddress(AddressInfo addressInfo) {
        return orderManageDao.insertAddress(addressInfo);
    }

    public List<Map<String, Object>> selectAddress(String amazonOrderId) {
        return orderManageDao.selectAddress(amazonOrderId);
    }
}