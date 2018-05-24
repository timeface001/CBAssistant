package com.crossborder.utils.amz.upload;


import com.alibaba.fastjson.JSON;
import com.crossborder.dao.ProductAmzUploadDao;
import com.crossborder.dao.ProductUploadLogDao;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.entity.ProductUploadLog;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ShopManageService;
import com.crossborder.utils.PublishStatusEnum;
import com.crossborder.utils.ResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("taskJob")
public class QuartProduct {
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

    /**
     * CRON表达式                含义
     "0 0 12 * * ?"            每天中午十二点触发
     "0 15 10 ? * *"            每天早上10：15触发
     "0 15 10 * * ?"            每天早上10：15触发
     "0 15 10 * * ? *"        每天早上10：15触发
     "0 15 10 * * ? 2005"    2005年的每天早上10：15触发
     "0 * 14 * * ?"            每天从下午2点开始到2点59分每分钟一次触发
     "0 0/5 14 * * ?"        每天从下午2点开始到2：55分结束每5分钟一次触发
     "0 0/5 14,18 * * ?"        每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
     "0 0-5 14 * * ?"        每天14:00至14:05每分钟一次触发
     "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
     "0 15 10 ? * MON-FRI"   每个周一、周二、周三、周四、周五的10：15触发
     */

    /**
     * 每天5点触发（清空验证码表t_captcha中的数据）
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    public void testTask() {
        System.out.println("execte quart......");
        List<ProductUploadLog> logs = productManagerService.selectLogList("3");
        for (ProductUploadLog log : logs) {
            if((log.getSubmitDate().getTime()+20000)>(new Date().getTime())){
                continue;
            }
            Map<String, Object> params = new HashMap<>();
            params.put("id", log.getShopId());
            System.out.println(JSON.toJSONString(log));
            Map<String, Object> shop = shopManageService.selectShops(params).get(0);
            String[] arr = log.getSubmitId().split(",");
            for (String submitID : arr) {
                if (StringUtils.isBlank(submitID)) {
                    continue;
                }

                ResponseDto dto = amzUpload.getFeedSubResult(shop, submitID);
                System.out.println("dto:" + JSON.toJSONString(dto));
                if (dto.isSuccess()) {
                    ProductAmzUpload upload = new ProductAmzUpload();
                    upload.setId(log.getProductId());
                    upload.setPublishStatus(String.valueOf(PublishStatusEnum.SUCCESS.toString()));
                    upload.setUploadDesc(" ");
                    productAmzUploadDao.updateByPrimaryKeySelective(upload);

                    ProductUploadLog ll = new ProductUploadLog();
                    ll.setId(log.getId());
                    ll.setStatus(PublishStatusEnum.SUCCESS.toString());
                    ll.setResponse(dto.getMsg());
                    productUploadLogDao.updateByPrimaryKeySelective(ll);

                    ProductAmzUpload dd = productAmzUploadDao.selectByPrimaryKey(log.getProductId());
                    productManagerService.updateClaimProduct(PublishStatusEnum.SUCCESS, dd.getProductAmzId());
                } else if (StringUtils.isNotBlank(dto.getMsg())) {
                    ProductAmzUpload upload = new ProductAmzUpload();
                    upload.setId(log.getProductId());
                    upload.setPublishStatus(String.valueOf(PublishStatusEnum.FAILED.toString()));
                    upload.setUploadDesc(dto.getMsg());
                    productAmzUploadDao.updateByPrimaryKeySelective(upload);

                    ProductUploadLog ll = new ProductUploadLog();
                    ll.setId(log.getId());
                    ll.setStatus(PublishStatusEnum.FAILED.toString());
                    ll.setResponse(dto.getMsg());
                    productUploadLogDao.updateByPrimaryKeySelective(ll);

                    ProductAmzUpload dd = productAmzUploadDao.selectByPrimaryKey(log.getProductId());
                    productManagerService.updateClaimProduct(PublishStatusEnum.FAILED, dd.getProductAmzId());
                }
            }
        }
    }
}
