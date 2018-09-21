package com.crossborder.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/4/7.
 */
@Repository
public interface ShopManageDao {
    public int addShop(Map<String, Object> map);

    public List<Map<String, Object>> selectShops(Map<String, Object> map);

    public int updateShopState(Map<String, Object> map);

    public int deleteShop(String id);

    public int updateShop(Map<String, Object> map);

    public List<Map<String, Object>> selectShopsById(Map<String, Object> map);

    public List<Map<String, Object>> selectShopsForImport();

    public Map<String, Object> selectShopById(@Param("id") String id);

    public List<Map<String, Object>> selectShopByCountry(Map<String, Object> map);

    public List<Map<String, Object>> getMerchantId(Map<String, Object> map);

    public List<Map<String, Object>> getMarketPlaceId(Map<String, Object> map);

    public String getExrate(String currencyCode);

    public Map<String, Object> getDevelopInfo(@Param("countryCode") String countryCode);
}
