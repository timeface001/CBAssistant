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

    public int insertOrders(List<LocalOrder> list) {
        return orderManageDao.insertOrders(list);
    }

    public int insertOrderItem(List<LocalOrderItem> list) {
        return orderManageDao.insertOrderItem(list);
    }

    public List<Map<String, Object>> selectLocalOrder(Map<String, Object> map) {
        return orderManageDao.selectLocalOrder(map);
    }

    public List<Map<String, Object>> selectLocalOrderItem(Map<String, Object> map) {
        return orderManageDao.selectLocalOrderItem(map);
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

    public int insertAddress(List<AddressInfo> list) {
        return orderManageDao.insertAddress(list);
    }

    public List<Map<String, Object>> selectAddress(String amazonOrderId) {
        return orderManageDao.selectAddress(amazonOrderId);
    }

    public List<Map<String, Object>> selectFees(Map<String, Object> map) {
        return orderManageDao.selectFees(map);
    }

    public int delOrder(String amazonOrderId) {
        return orderManageDao.delOrder(amazonOrderId);
    }

    public int delOrderItem(String amazonOrderId) {
        return orderManageDao.delOrderItem(amazonOrderId);
    }

    public int delAddress(String amazonOrderId) {
        return orderManageDao.delAddress(amazonOrderId);
    }

    public int updateOrderShipping(Map<String, Object> map) {
        return orderManageDao.updateOrderShipping(map);
    }

    public int updateOrderItemCommission(Map<String, Object> map) {
        return orderManageDao.updateOrderItemCommission(map);
    }

    public int cloneOrder(String amazonOrderId) {
        return orderManageDao.cloneOrder(amazonOrderId);
    }

    public int cloneOrderItem(String amazonOrderId) {
        return orderManageDao.cloneOrderItem(amazonOrderId);
    }

    public int cloneAddress(String amazonOrderId) {
        return orderManageDao.cloneAddress(amazonOrderId);
    }
}
