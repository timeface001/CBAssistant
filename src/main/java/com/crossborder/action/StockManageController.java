package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossborder.service.OrderManageService;
import com.crossborder.service.StockManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/8/8.
 */
@Controller
@RequestMapping("/stock")
public class StockManageController {
    @Resource
    private StockManageService stockManageService;
    @Resource
    private OrderManageService orderManageService;
    private DecimalFormat df = new DecimalFormat("#.00");
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 添加商品
     *
     * @param data
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addGoods", produces = "text/plain;charset=UTF-8")
    public String addGoods(String data, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            paramMap.put("createUser", user.get("USER_ID").toString());
            Map<String, Object> pSkuMap = new HashMap<>();
            pSkuMap.put("pSku", paramMap.get("pSku"));
            List<Map<String, Object>> goods = stockManageService.getGoods(pSkuMap);
            if (goods != null && goods.size() > 0) {
                map.put("code", "-5");
                map.put("msg", "同一个商品只能添加一次");
            } else {
                int count = stockManageService.addGoods(paramMap);
                stockManageService.addGoodsList(paramMap);
                if (count == 1) {
                    map.put("code", "0");
                    map.put("msg", "添加成功");
                } else {
                    map.put("code", "-10");
                    map.put("msg", "添加失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "添加失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询商品
     *
     * @param data
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoods", produces = "text/plain;charset=UTF-8")
    public String getGoods(String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
            paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            List<Map<String, Object>> goodsList = stockManageService.getGoods(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(goodsList);
            map.put("data", goodsList);
            map.put("draw", draw);
            map.put("recordsTotal", pageInfo.getTotal());
            map.put("recordsFiltered", pageInfo.getTotal());
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询商品
     *
     * @param pSku
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoodsBySku", produces = "text/plain;charset=UTF-8")
    public String getGoodsBySku(String pSku) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("pSku", pSku);
            List<Map<String, Object>> goodsList = stockManageService.getGoods(paramMap);
            map.put("data", goodsList);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 修改商品
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateGoods", produces = "text/plain;charset=UTF-8")
    public String updateGoods(String data) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            int count = stockManageService.updateGoods(paramMap);
            if (count == 1) {
                map.put("code", "0");
                map.put("msg", "修改成功");
            } else {
                map.put("code", "0");
                map.put("msg", "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "修改失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 入库商品
     *
     * @param data
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addGoodsIn", produces = "text/plain;charset=UTF-8")
    public String addGoodsIn(String data, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            paramMap.put("createUser", user.get("USER_ID").toString());
            int count = stockManageService.addGoodsIn(paramMap);
            Map<String, Object> pSkuMap = new HashMap<>();
            pSkuMap.put("pSku", paramMap.get("pSku"));
            Map<String, Object> itemMap = stockManageService.getGoodsList(pSkuMap).get(0);
            Map<String, Object> goodsListMap = new HashMap<>();
            goodsListMap.put("pSku", paramMap.get("pSku").toString());
            goodsListMap.put("pInNum", Integer.parseInt(paramMap.get("pInNum").toString()) + Integer.parseInt(itemMap.get("P_WAY_NUM").toString()));
            goodsListMap.put("shelfNo", paramMap.get("shelfNo"));
            goodsListMap.put("wareRoom", paramMap.get("wareRoom"));
            goodsListMap.put("trackNo", paramMap.get("trackNo"));
            stockManageService.updateGoodsList(goodsListMap);
            if (count == 1) {
                map.put("code", "0");
                map.put("msg", "入库成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "入库失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "入库失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 审核入库商品
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "auditGoodsIn", produces = "text/plain;charset=UTF-8")
    public String auditGoodsIn(String data) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            int pInNum = Integer.parseInt(paramMap.get("pInNum").toString());
            int actualPInNum = Integer.parseInt(paramMap.get("actualPInNum").toString());
            double totalPrice = Double.parseDouble(paramMap.get("totalPrice").toString());
            /*paramMap.put("actualTotalPrice", df.format(totalPrice / pInNum * actualPInNum));*/
            int count = stockManageService.updateGoodsIn(paramMap);
            Map<String, Object> pSkuMap = new HashMap<>();
            pSkuMap.put("pSku", paramMap.get("pSku"));
            Map<String, Object> goodsListMap = stockManageService.getGoodsList(pSkuMap).get(0);
            pSkuMap.put("pState", 1);
            List<Map<String, Object>> goodsInList = stockManageService.getGoodsIn(pSkuMap);
            int wayNum = 0;
            if (goodsInList != null) {
                for (int i = 0; i < goodsInList.size(); i++) {
                    wayNum = wayNum + Integer.parseInt(goodsInList.get(i).get("P_IN_NUM").toString());
                }
            }
            pSkuMap.put("pStockNum", Integer.parseInt(goodsListMap.get("P_STOCK_NUM").toString()) + actualPInNum);
            pSkuMap.put("pInNum", wayNum + "");
            pSkuMap.put("totalPrice", Double.parseDouble(goodsListMap.get("TOTAL_PRICE").toString()) + totalPrice);
            pSkuMap.put("price", df.format(Double.parseDouble(pSkuMap.get("totalPrice").toString()) / Integer.parseInt(pSkuMap.get("pStockNum").toString())));
            stockManageService.updateGoodsList(pSkuMap);
            if (count == 1) {
                map.put("code", "0");
                map.put("msg", "签收成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "签收失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "签收失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 修改入库商品
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateGoodsIn", produces = "text/plain;charset=UTF-8")
    public String updateGoodsIn(String data) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            int count = stockManageService.updateGoodsIn(paramMap);
            if (count == 1) {
                map.put("code", "0");
                map.put("msg", "修改成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "修改失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询入库
     *
     * @param data
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoodsIn", produces = "text/plain;charset=UTF-8")
    public String getGoodsIn(String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
            paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            List<Map<String, Object>> goodsList = stockManageService.getGoodsIn(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(goodsList);
            map.put("data", goodsList);
            map.put("draw", draw);
            map.put("recordsTotal", pageInfo.getTotal());
            map.put("recordsFiltered", pageInfo.getTotal());
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询商品
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoodsInById", produces = "text/plain;charset=UTF-8")
    public String getGoodsInById(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", id);
            List<Map<String, Object>> goodsList = stockManageService.getGoodsIn(paramMap);
            map.put("data", goodsList);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询商品清单
     *
     * @param data
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoodsList", produces = "text/plain;charset=UTF-8")
    public String getGoodsList(String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
            paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            List<Map<String, Object>> goodsList = stockManageService.getGoodsList(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(goodsList);
            map.put("data", goodsList);
            map.put("draw", draw);
            map.put("recordsTotal", pageInfo.getTotal());
            map.put("recordsFiltered", pageInfo.getTotal());
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询出库
     *
     * @param data
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoodsOut", produces = "text/plain;charset=UTF-8")
    public String getGoodsOut(String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
            paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            List<Map<String, Object>> goodsList = stockManageService.getGoodsOut(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(goodsList);
            map.put("data", goodsList);
            map.put("draw", draw);
            map.put("recordsTotal", pageInfo.getTotal());
            map.put("recordsFiltered", pageInfo.getTotal());
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 出库商品
     *
     * @param data
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addGoodsOut", produces = "text/plain;charset=UTF-8")
    public String addGoodsOut(String data, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            paramMap.put("createUser", user.get("USER_ID").toString());
            Map<String, Object> pSkuMap = new HashMap<>();
            pSkuMap.put("pSku", paramMap.get("pSku"));
            Map<String, Object> goodsListMap = stockManageService.getGoodsList(pSkuMap).get(0);
            String platSku = goodsListMap.get("PLAT_SKU").toString();
            int pStockNum = Integer.parseInt(goodsListMap.get("P_STOCK_NUM").toString());
            String[] orderItemIds = new String[1];
            if (paramMap.get("orderItemId").toString().contains(",")) {
                orderItemIds = paramMap.get("orderItemId").toString().split(",");
            } else {
                orderItemIds[0] = paramMap.get("orderItemId").toString();
            }
            for (int i = 0; i < orderItemIds.length; i++) {
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("orderItemId", orderItemIds[i]);
                List<Map<String, Object>> orderItems = orderManageService.selectLocalOrderItem(itemMap);
                if (orderItems != null && orderItems.size() > 0) {
                    Map<String, Object> orderItem = orderItems.get(0);
                    if (platSku.contains(orderItem.get("SELLERSKU").toString())) {
                        int num = Integer.parseInt(orderItem.get("QUANTITYSHIPPED").toString());
                        if (pStockNum >= num) {
                            Map<String, Object> updateMap = new HashMap<>();
                            updateMap.put("status", "2");
                            updateMap.put("orderItemId", orderItemIds[i]);
                            updateMap.put("amazonOrderId", orderItem.get("AMAZONORDERID"));
                            updateMap.put("cost", num * Double.parseDouble(goodsListMap.get("PRICE").toString()));
                            updateMap.put("trackNum", goodsListMap.get("TRACK_NUBMER"));
                            updateMap.put("purchaseNum", "12432423");
                            updateMap.put("updateTime", simpleDateFormat.format(new Date()));
                            orderManageService.updateOrder(updateMap);
                            orderManageService.updateOrderItem(updateMap);
                            pStockNum = pStockNum - num;
                            pSkuMap.put("pStockNum", pStockNum);
                            pSkuMap.put("totalPrice", Double.parseDouble(goodsListMap.get("PRICE").toString()) * pStockNum);
                            stockManageService.updateGoodsList(pSkuMap);
                        }
                    }
                }
            }
            paramMap.put("outNum", (Integer.parseInt(goodsListMap.get("P_STOCK_NUM").toString()) - pStockNum) + "");
            paramMap.put("shelfNo", goodsListMap.get("SHELF_NO"));
            paramMap.put("wareRoom", goodsListMap.get("WAREROOM"));
            paramMap.put("pStockNum", pStockNum);
            int count = stockManageService.addGoodsOut(paramMap);
            if (count == 1) {
                map.put("code", "0");
                map.put("msg", "出库成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "出库失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "出库失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 批量出库商品
     *
     * @param data
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addAllGoodsOut", produces = "text/plain;charset=UTF-8")
    public String addAllGoodsOut(String data, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
            int outNum = Integer.parseInt(paramMap.get("outNum").toString());
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            paramMap.put("createUser", user.get("USER_ID").toString());
            paramMap.put("orderItemId", "批量出库");
            Map<String, Object> pSkuMap = new HashMap<>();
            pSkuMap.put("pSku", paramMap.get("pSku"));
            Map<String, Object> goodsListMap = stockManageService.getGoodsList(pSkuMap).get(0);
            String platSku = goodsListMap.get("PLAT_SKU").toString();
            int pStockNum = Integer.parseInt(goodsListMap.get("P_STOCK_NUM").toString());
            if (pStockNum >= outNum) {
                List<Map<String, Object>> newOrderItems = orderManageService.selectNewLocalOrderItem();
                for (int i = 0; i < newOrderItems.size(); i++) {
                    Map<String, Object> newOrderItem = newOrderItems.get(i);
                    if (newOrderItem.get("SELLERSKU") != null
                            && platSku.contains(newOrderItem.get("SELLERSKU").toString())) {
                        int num = Integer.parseInt(newOrderItem.get("QUANTITYSHIPPED").toString());
                        if (outNum >= num) {
                            Map<String, Object> updateMap = new HashMap<>();
                            updateMap.put("status", "2");
                            updateMap.put("orderItemId", newOrderItem.get("ORDERITEMID"));
                            updateMap.put("amazonOrderId", newOrderItem.get("AMAZONORDERID"));
                            updateMap.put("cost", num * Double.parseDouble(goodsListMap.get("PRICE").toString()));
                            updateMap.put("trackNum", goodsListMap.get("TRACK_NUBMER"));
                            updateMap.put("purchaseNum", "12432423");
                            updateMap.put("updateTime", simpleDateFormat.format(new Date()));
                            orderManageService.updateOrder(updateMap);
                            orderManageService.updateOrderItem(updateMap);
                            outNum = outNum - num;
                            pStockNum = pStockNum - num;
                            pSkuMap.put("pStockNum", pStockNum);
                            pSkuMap.put("totalPrice", Double.parseDouble(goodsListMap.get("PRICE").toString()) * pStockNum);
                            stockManageService.updateGoodsList(pSkuMap);
                        }
                    }
                }
                paramMap.put("outNum", (Integer.parseInt(goodsListMap.get("P_STOCK_NUM").toString()) - pStockNum) + "");
                paramMap.put("shelfNo", goodsListMap.get("SHELF_NO"));
                paramMap.put("wareRoom", goodsListMap.get("WAREROOM"));
                paramMap.put("pStockNum", pStockNum);
                int count = stockManageService.addGoodsOut(paramMap);
                if (count == 1) {
                    map.put("code", "0");
                    map.put("msg", "出库成功");
                } else {
                    map.put("code", "-10");
                    map.put("msg", "出库失败");
                }
            } else {
                map.put("code", "-5");
                map.put("msg", "出库量大于库存，无法出库！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "出库失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除商品
     *
     * @param pSku
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delGoods", produces = "text/plain;charset=UTF-8")
    public String delGoods(String pSku) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count1 = stockManageService.delGoods(pSku);
            int count2 = stockManageService.delGoodsList(pSku);
            if (count1 == 1 && count2 == 1) {
                map.put("code", "0");
                map.put("msg", "删除成功！");
            } else {
                map.put("code", "-10");
                map.put("msg", "删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败！");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除入库新单
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delGoodsIn", produces = "text/plain;charset=UTF-8")
    public String delGoodsIn(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> idMap = new HashMap<>();
            idMap.put("id", id);
            Map<String, Object> itemMap = stockManageService.getGoodsIn(idMap).get(0);
            int count = stockManageService.delGoodsIn(id);
            Map<String, Object> pSkuMap = new HashMap<>();
            pSkuMap.put("pSku", itemMap.get("P_SKU"));
            pSkuMap.put("pState", 1);
            List<Map<String, Object>> goodsInList = stockManageService.getGoodsIn(pSkuMap);
            int wayNum = 0;
            if (goodsInList != null) {
                for (int i = 0; i < goodsInList.size(); i++) {
                    wayNum = wayNum + Integer.parseInt(goodsInList.get(i).get("P_IN_NUM").toString());
                }
            }
            pSkuMap.put("pInNum", wayNum + "");
            stockManageService.updateGoodsList(pSkuMap);
            if (count == 1) {
                map.put("code", "0");
                map.put("msg", "删除成功！");
            } else {
                map.put("code", "-10");
                map.put("msg", "删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "删除失败！");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
}
