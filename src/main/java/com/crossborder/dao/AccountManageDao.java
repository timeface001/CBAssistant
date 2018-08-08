package com.crossborder.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/8/2.
 */
@Repository
public interface AccountManageDao {
    public List<Map<String, Object>> getAccounts(Map<String, Object> map);

    public int addAccount(Map<String, Object> map);

    public List<Map<String, Object>> getAccountByCompany(String userCompany);
}
