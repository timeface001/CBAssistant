package com.crossborder.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/6/10.
 */
@Repository
public interface FinanceManageDao {
    public List<Map<String, Object>> selectShippings(Map<String, Object> map);

    public int updateShipping(Map<String, Object> map);

    public List<Map<String, Object>> selectFees(Map<String, Object> map);


}
