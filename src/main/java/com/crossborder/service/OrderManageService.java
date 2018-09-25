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

    public int insertOrders(LocalOrder localOrder) {
        return orderManageDao.insertOrders(localOrder);
    }

    public int insertOrderItem(LocalOrderItem localOrderItem) {
        return orderManageDao.insertOrderItem(localOrderItem);
    }

    public List<Map<String, Object>> selectLocalOrder(Map<String, Object> map) {
        return orderManageDao.selectLocalOrder(map);
    }

    public List<Map<String, Object>> selectLocalOrderItem(Map<String, Object> map) {
        return orderManageDao.selectLocalOrderItem(map);
    }

    public List<Map<String, Object>> selectNewLocalOrderItem() {
        return orderManageDao.selectNewLocalOrderItem();
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

    public int cloneOrder(String amazonOrderId, String date) {
        return orderManageDao.cloneOrder(amazonOrderId, date);
    }

    public int cloneOrderItem(String amazonOrderId, String date) {
        return orderManageDao.cloneOrderItem(amazonOrderId, date);
    }

    public int cloneAddress(String amazonOrderId, String date) {
        return orderManageDao.cloneAddress(amazonOrderId, date);
    }

    public List<Map<String, Object>> selectLocalMergeOrder(Map<String, Object> map) {
        return orderManageDao.selectLocalMergeOrder(map);
    }

    public int updateOrderMergeId(Map<String, Object> map) {
        return orderManageDao.updateOrderMergeId(map);
    }

    public int insertMergedOrder(Map<String, Object> map) {
        return orderManageDao.insertMergedOrder(map);
    }

    public List<Map<String, Object>> selectMergedOrder(Map<String, Object> map) {
        List<Map<String, Object>> list = orderManageDao.selectMergedOrder(map);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> idMap = orderManageDao.selectAmazonOrderId(list.get(i).get("MERGEDID").toString());
            Map<String, Object> imageMap = orderManageDao.selectSmallImage(list.get(i).get("MERGEDID").toString());
            Map<String, Object> addressLineMap = orderManageDao.selectAddressLine(list.get(i).get("MERGEDID").toString()).get(0);
            if (imageMap != null) {
                list.get(i).put("SMALLIMAGE", imageMap.get("SMALLIMAGE"));
            } else {
                list.get(i).put("SMALLIMAGE", null);
            }
            if (idMap != null) {
                list.get(i).put("AMAZONORDERID", idMap.get("AMAZONORDERID"));
            } else {
                list.get(i).put("AMAZONORDERID", null);
            }
            if (addressLineMap != null) {
                list.get(i).put("ADDRESSLINE1", addressLineMap.get("ADDRESSLINE1"));
                list.get(i).put("ADDRESSLINE2", addressLineMap.get("ADDRESSLINE2"));
                list.get(i).put("ADDRESSLINE3", addressLineMap.get("ADDRESSLINE3"));
            } else {
                list.get(i).put("ADDRESSLINE1", null);
                list.get(i).put("ADDRESSLINE2", null);
                list.get(i).put("ADDRESSLINE3", null);
            }
        }
        return list;
    }
}
