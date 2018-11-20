package com.crossborder.action;

import com.amazon.mws.finances._2015_05_01.MWSFinancesServiceClient;
import com.amazon.mws.finances._2015_05_01.MWSFinancesServiceConfig;
import com.amazon.mws.finances._2015_05_01.model.FeeComponent;
import com.amazon.mws.finances._2015_05_01.model.FinancialEvents;
import com.amazon.mws.finances._2015_05_01.model.ListFinancialEventsRequest;
import com.amazon.mws.finances._2015_05_01.model.ShipmentItem;
import com.crossborder.service.OrderManageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/9/28.
 */
@Component
public class GetCommissionTask {
    @Resource
    private OrderManageService orderManageService;

    @Scheduled(cron = "0 0/2 9 ? * *")
    public void startTask() {

        Map<String, Object> paramMap = new HashMap<>();
        List<Map<String, Object>> orders = orderManageService.selectCommissionOrder(paramMap);
        for (int i = 0; i < orders.size(); i++) {
            try {
                Map<String, Object> order = orders.get(i);
                if (order.get("ACCESSKEY_ID") != null && !order.get("AMAZONORDERID").toString().contains("clone")) {
                    List<ShipmentItem> shipmentItems = getOrderItemFees(order);
                    for (int j = 0; j < shipmentItems.size(); j++) {
                        List<FeeComponent> feeComponents = shipmentItems.get(j).getItemFeeList();
                        double fees = 0;
                        for (int m = 0; m < feeComponents.size(); m++) {
                            fees = fees + feeComponents.get(m).getFeeAmount().getCurrencyAmount().doubleValue();
                        }
                        Map<String, Object> commissionMap = new HashMap<>();
                        commissionMap.put("amazonOrderId", order.get("AMAZONORDERID").toString());
                        commissionMap.put("orderItemId", shipmentItems.get(j).getOrderItemId());
                        commissionMap.put("commission", fees);
                        commissionMap.put("commissionRMB", fees * Double.parseDouble(order.get("EXRATE").toString()));
                        orderManageService.updateOrderIsCommission(commissionMap);
                        orderManageService.updateOrderItemCommission(commissionMap);
                    }
                    System.out.println("定时任务执行-------获取佣金" + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }

    }

    /**
     * 获取orderItem佣金
     *
     * @param order
     * @return
     */
    public List<ShipmentItem> getOrderItemFees(Map<String, Object> order) {
        MWSFinancesServiceConfig config = new MWSFinancesServiceConfig();
        config.setServiceURL(order.get("ENDPOINT").toString());
        MWSFinancesServiceClient client = new MWSFinancesServiceClient(order.get("ACCESSKEY_ID").toString(), order.get("SECRET_KEY").toString(), "", "", config);
        ListFinancialEventsRequest request = new ListFinancialEventsRequest();
        request.setSellerId(order.get("MERCHANT_ID").toString());
        request.setAmazonOrderId(order.get("AMAZONORDERID").toString());
        request.setMWSAuthToken(order.get("MWSAUTHTOKEN").toString());
        FinancialEvents financialEvents = client.listFinancialEvents(request).getListFinancialEventsResult().getFinancialEvents();
        if (financialEvents.getShipmentEventList() != null && financialEvents.getShipmentEventList().size() > 0) {
            return financialEvents.getShipmentEventList().get(0).getShipmentItemList();
        } else {
            return new ArrayList<ShipmentItem>();
        }
    }
}
