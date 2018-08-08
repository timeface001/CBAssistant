package com.crossborder.service;

import com.crossborder.dao.AccountManageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/8/2.
 */
@Service
public class AccountManageService {
    @Resource
    private AccountManageDao accountManageDao;

    public List<Map<String, Object>> getAccounts(Map<String, Object> map) {
        return accountManageDao.getAccounts(map);
    }

    public int addAccount(Map<String, Object> map) {
        List<Map<String, Object>> accountList = accountManageDao.getAccountByCompany(map.get("userCompany").toString());
        if (accountList != null && accountList.size() > 0) {
            Map<String, Object> account = accountList.get(0);
            if (map.get("feeType").toString().equals("1")) {
                map.put("accBalance", Double.parseDouble(account.get("ACC_BALANCE").toString()) + Double.parseDouble(map.get("amount").toString()));
            } else {
                map.put("accBalance", Double.parseDouble(account.get("ACC_BALANCE").toString()) - Double.parseDouble(map.get("amount").toString()));
            }
        }else{
            if (map.get("feeType").toString().equals("1")) {
                map.put("accBalance", Double.parseDouble(map.get("amount").toString()));
            } else {
                map.put("accBalance", 0 - Double.parseDouble(map.get("amount").toString()));
            }
        }
        return accountManageDao.addAccount(map);
    }
}
