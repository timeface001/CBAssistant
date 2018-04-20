package com.crossborder.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by fengsong on 2018/4/14.
 */
@Repository
public interface ProductClaimDao {

    void saveOrUpdate(Map<String,Object> params);

}
