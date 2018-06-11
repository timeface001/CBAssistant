package com.crossborder.utils.amz.upload;

import com.alibaba.fastjson.JSON;
import com.crossborder.dao.ProductAmzUploadDao;
import com.crossborder.dao.ProductUploadLogDao;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ShopManageService;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.PublishStatusEnum;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("quart")
public class QuartBathUpload {

    @Resource
    private ProductManagerService productManagerService;
    @Autowired
    private AmzUpload amzUpload;
    @Autowired
    private ShopManageService shopManageService;
    @Autowired
    private ProductAmzUploadDao productAmzUploadDao;
    @Autowired
    private ProductUploadLogDao productUploadLogDao;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void upload() {
        System.out.println("定时批量发布开始...." + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //获取预发布的产品
        List<ProductAmzUpload> list = productAmzUploadDao.selectList(GeneralUtils.genMap("pStatus", PublishStatusEnum.NOT.getVal()));
        //分组
        Map<String, List<ProductAmzUpload>> resultMap = new HashMap<>();
        Map<String, Map<String, Object>> shops = new HashMap<>();
        for (ProductAmzUpload product : list) {
            if (resultMap.containsKey(product.getShopId())) {
                List<ProductAmzUpload> ps = resultMap.get(product.getShopId());
                ps.add(product);
                resultMap.put(product.getShopId(), ps);
            } else {
                List<ProductAmzUpload> ps = new ArrayList<ProductAmzUpload>();
                ps.add(product);
                resultMap.put(product.getShopId(), ps);
                shops.put(product.getShopId(), shopManageService.selectShopById(product.getShopId()));
            }
        }

        if (GeneralUtils.isNotNullOrEmpty(list)) {
            System.out.println("发布开始。。。。。。");
            System.out.println("所有可发布数据。。。。。。:" + JSON.toJSONString(resultMap, true));
            for (Map.Entry<String, List<ProductAmzUpload>> entry : resultMap.entrySet()) {
                amzUpload.bathUploadProduct(entry.getValue(), shops.get(entry.getKey()));
            }

            System.out.println("发布结束。。。。。。");
        }
        System.out.println("定时批量发布结束。。。。");
    }
}
