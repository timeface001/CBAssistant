package com.crossborder.utils;


import com.alibaba.fastjson.JSON;
import com.crossborder.dao.ProductAmzUploadDao;
import com.crossborder.dao.ProductUploadLogDao;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.entity.ProductUploadLog;
import com.crossborder.service.ProductManagerService;
import com.crossborder.service.ShopManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    @Scheduled(cron = "0/5 * * * * ?")
    public void testTask() {
        List<ProductUploadLog> logs = productManagerService.selectLogList("3");
        for (ProductUploadLog log : logs) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", log.getShopId());
            System.out.println(JSON.toJSONString(log));
            ResponseDto dto = amzUpload.getFeedSubResult(shopManageService.selectShops(params).get(0), log.getSubmitId());
            if (dto.isSuccess()) {

            } else {
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
            }
        }
    }
}