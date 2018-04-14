package com.crossborder.dao;

import com.crossborder.entity.AddressInfo;
import com.crossborder.entity.LocalOrder;
import com.crossborder.entity.LocalOrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/3/23.
 */
@Repository
public interface OrderManageDao {
    public int insertOrders(LocalOrder order);

    public int insertOrderItem(LocalOrderItem localOrderItem);

    public List<Map<String, Object>> selectLocalOrder(Map<String, Object> map);

    public List<Map<String, Object>> selectLocalOrderItem(String amazonOrderId);

    public int updateOrder(Map<String, Object> map);

    public int updateOrderItem(Map<String, Object> map);

    public int inserOperationLog(Map<String, Object> map);

    public int updateOrderRemark(Map<String, Object> map);

    public List<Map<String, Object>> selectOperationLog(String amazonOrderId);

    public int insertAddress(AddressInfo addressInfo);

    public List<Map<String, Object>> selectAddress(String amazonOrderId);
}