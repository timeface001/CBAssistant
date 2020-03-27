package com.crossborder.utils.amz.upload;

import com.crossborder.dao.ProductAmzUploadDao;
import com.crossborder.dao.ProductUploadLogDao;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ShopManageService;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.PublishStatusEnum;
import org.apache.commons.lang3.time.DateFormatUtils;
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

    @Scheduled(cron = "0 0/30 * * * ?")
    public void upload() {
        System.out.println("定时批量发布开始...." + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

        //List<ProductAmzUpload> list = list();
        List<ProductAmzUpload> list = productAmzUploadDao.selectNeedPulishList();
        //分组
        List<UploadServiceRequest> resultMap = new ArrayList<>();
        for (ProductAmzUpload product : list) {

            if (product.getShopId() == null) {
                continue;
            }


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

        String startTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        if (GeneralUtils.isNotNullOrEmpty(list)) {
            System.out.println("发布批次开始。。。。。【" + startTime + "】");
            System.out.println("所有可发布数据数量。。。。。。:" + list.size());
            for (UploadServiceRequest re : resultMap) {
                amzUpload.bathUploadProduct(re);
            }

            System.out.println("定时批量发布结束。。。。"+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        } else {
            System.out.println("无可发布数据【" + startTime + "】");
        }

    }


    private List<ProductAmzUpload> listByUser() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", "bren0003");
        params.put("pStatus", 0);
        return productAmzUploadDao.selectListByUser("bren0003");

    }
    private List<ProductAmzUpload> list() {
        List<ProductAmzUpload> list = new ArrayList<>();

        String[] arr = {"18022546733"};


        for (String id : arr) {
            ProductAmzUpload pp = productAmzUploadDao.selectByPrimaryKey(id);
            if (!pp.getPublishStatus().equals(PublishStatusEnum.NOT.getVal())) {
                continue;
            }

            list.add(pp);
        }
        //获取预发布的产品

        return list;
    }


}
