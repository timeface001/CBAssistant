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

    //@Scheduled(cron = "0 0/24 * * * ?")
    public void upload() {
        System.out.println("定时批量发布开始...." + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

        /*ProductAmzUpload pp = productAmzUploadDao.selectByPrimaryKey("4747");
        if (!pp.getPublishStatus() .equals( PublishStatusEnum.NOT.getVal())) {
            return;
        }*/
        //获取预发布的产品
        //List<ProductAmzUpload> list = Arrays.asList(pp);


        List<ProductAmzUpload> list = productAmzUploadDao.selectList(GeneralUtils.genMap("pStatus", PublishStatusEnum.NOT.getVal()));
        //分组
        List<UploadServiceRequest> resultMap = new ArrayList<>();
        for (ProductAmzUpload product : list) {

            Map<String, Object> shop = shopManageService.selectShopById(product.getShopId());
            boolean isContains = false;
            for (UploadServiceRequest request : resultMap) {
                if (request.add(shop, product)) {
                    isContains = true;
                    break;
                } else {
                    isContains = false;
                }
            }

            if (!isContains) {
                UploadServiceRequest mid = new UploadServiceRequest(shop);
                mid.add(shop, product);
                resultMap.add(mid);
            }

        }

        if (GeneralUtils.isNotNullOrEmpty(list)) {
            System.out.println("发布开始。。。。。。");
            System.out.println("所有可发布数据。。。。。。:" + JSON.toJSONString(resultMap, true));
            for (UploadServiceRequest re : resultMap) {
                amzUpload.bathUploadProduct(re);
            }

            System.out.println("发布结束。。。。。。");
        }
        System.out.println("定时批量发布结束。。。。");
    }
}
