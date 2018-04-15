package com.crossborder.service;

import com.crossborder.dao.ProductManagerDao;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ProductStateEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fengsong on 2018/4/14.
 */
@Service
public class ProductManagerService {
    @Resource
    private ProductManagerDao productManagerDao;

    public boolean save(Map<String, Object> product) {
        product.put("createTime", new Date());
        return productManagerDao.insertProduct(product) == 1;
    }

    public List<Map<String, Object>> selectList(Map<String, Object> params) {
        return productManagerDao.selectList(params);
    }

    @Transactional(readOnly = false)
    public void updateState(String[] ids, ProductStateEnum stateEnum) {
        for (String id : ids) {
            productManagerDao.updateState(GeneralUtils.genMap("id", id, "pState", stateEnum.getValue()));
        }
    }

    @Transactional(readOnly = false)
    public void delete(String[] ids) {

        for (String id : ids) {
            productManagerDao.delete(id);
        }
    }
}
