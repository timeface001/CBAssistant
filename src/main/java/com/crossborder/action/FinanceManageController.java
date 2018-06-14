package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crossborder.entity.HeaderRequest;
import com.crossborder.entity.ShipRate_Service;
import com.crossborder.service.CommonService;
import com.crossborder.service.FinanceManageService;
import com.crossborder.service.OrderManageService;
import com.crossborder.service.ShipRate;
import com.crossborder.utils.ExcelRead;
import com.crossborder.utils.HttpClientUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s on 2018/6/10.
 */
@Controller
@RequestMapping("/finance")
public class FinanceManageController {
    @Resource
    private FinanceManageService financeManageService;
    @Resource
    private CommonService commonService;
    @Resource
    private OrderManageService orderManageService;

    /**
     * 查询店铺
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectShippings", produces = "text/plain;charset=UTF-8")
    public String selectShops(HttpSession session, String data, Integer draw, Integer start, Integer length) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        paramMap.put("logmin", paramMap.get("logmin").toString() + " 00:00:00");
        paramMap.put("logmax", paramMap.get("logmax").toString() + " 23:59:59");
        try {
            PageHelper.startPage(start == null ? 1 : (start / length + 1), length);
            List<Map<String, Object>> list = financeManageService.selectShippings(paramMap);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
            map.put("data", list);
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

    @ResponseBody
    @RequestMapping(value = "getShippingPrice", produces = "text/plain;charset=UTF-8")
    public String getShippingPrice(HttpSession session, String companyId, String orderId, String custId) {
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (companyId.equals("YT")) {
            return getYTShippingPrice(orderId, custId, user);
        } else {
            return getSFCShippingPrice(orderId, custId, user);
        }
    }

    @ResponseBody
    @RequestMapping(value = "updateShipping", produces = "text/plain;charset=UTF-8")
    public String updateShipping(HttpSession session, String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = JSON.parseObject(data, Map.class);
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        paramMap.put("status", "1");
        paramMap.put("operationUser", user.get("USER_ID"));
        try {
            financeManageService.updateShipping(paramMap);
            map.put("code", "0");
            map.put("msg", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "修改失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "auditShipping", produces = "text/plain;charset=UTF-8")
    public String auditShipping(HttpSession session, String freight, String orderId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> orderMap = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        paramMap.put("status", "2");
        paramMap.put("orderId", orderId);
        paramMap.put("freight", freight);
        paramMap.put("operationUser", user.get("USER_ID"));
        try {
            financeManageService.updateShipping(paramMap);
            orderMap.put("amazonOrderId", orderId);
            orderMap.put("shippingPrice", freight);
            orderManageService.updateOrderShipping(orderMap);
            map.put("code", "0");
            map.put("msg", "审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "审核失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 根据上传文件批量更新订单运费
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateByExcel", produces = "text/plain;charset=UTF-8")
    public String updateByExcel(HttpSession session, MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        if (file == null || file.getSize() == 0) {
            map.put("code", "-10");
            map.put("msg", "上传失败");
        } else {
            try {
                List<ArrayList<String>> list = new ExcelRead().readExcel(file);
                for (ArrayList<String> arr : list) {
                    Map<String, Object> item = new HashMap<>();
                    if (!StringUtils.isEmpty(arr.get(0))) {
                        item.put("orderId", arr.get(0));
                        item.put("shippingPrice", arr.get(1));
                        Map<String, Object> rateMap = getShippingRate("shippingRate");
                        double freight = Double.parseDouble(arr.get(1)) * Double.parseDouble(rateMap.get("RATE").toString()) + Double.parseDouble(rateMap.get("DIFFERENCE").toString());
                        item.put("freight", freight);
                        item.put("status", "1");
                        item.put("operationUser", user.get("USER_ID"));
                        financeManageService.updateShipping(item);
                    }
                }
                map.put("code", "0");
                map.put("msg", "修改成功");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", "-10");
                map.put("msg", "修改失败");
            }
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    private String getSFCShippingPrice(String orderId, String orderCode, Map<String, Object> user) {
        Map<String, Object> map = new HashMap<>();
        ShipRate port = createShipRate();
        HeaderRequest _headerRequest = createRequest();
        Holder<String> _getFeeByOrderCode_orderCode = new Holder<String>(orderCode);
        Holder<String> _getFeeByOrderCode_ask = new Holder<String>();
        Holder<String> _getFeeByOrderCode_sysTime = new Holder<String>();
        Holder<String> _getFeeByOrderCode_msg = new Holder<String>();
        Holder<String> _getFeeByOrderCode_baseFee = new Holder<String>();
        Holder<String> _getFeeByOrderCode_regFee = new Holder<String>();
        Holder<String> _getFeeByOrderCode_dealFee = new Holder<String>();
        Holder<String> _getFeeByOrderCode_insurance = new Holder<String>();
        Holder<String> _getFeeByOrderCode_totalFee = new Holder<String>();
        Holder<String> _getFeeByOrderCode_currencyCode = new Holder<String>();
        Holder<String> _getFeeByOrderCode_chargebackTime = new Holder<String>();
        Holder<String> _getFeeByOrderCode_chargebackWorkDay = new Holder<String>();
        Holder<String> _getFeeByOrderCode_shipTypeCode = new Holder<String>();
        Holder<String> _getFeeByOrderCode_subShipType = new Holder<String>();
        Holder<String> _getFeeByOrderCode_waybillCode = new Holder<String>();
        Holder<String> _getFeeByOrderCode_discount = new Holder<String>();
        Holder<java.util.List<com.crossborder.entity.OtherFee>> _getFeeByOrderCode_otherFee = new Holder<java.util.List<com.crossborder.entity.OtherFee>>();
        Holder<String> _getFeeByOrderCode_originBaseFee = new Holder<String>();
        Holder<String> _getFeeByOrderCode_originAddons = new Holder<String>();
        Holder<String> _getFeeByOrderCode_stDealFee = new Holder<String>();
        Holder<String> _getFeeByOrderCode_stRegFee = new Holder<String>();
        Holder<String> _getFeeByOrderCode_feeWeight = new Holder<String>();
        port.getFeeByOrderCode(_headerRequest, _getFeeByOrderCode_orderCode, _getFeeByOrderCode_ask, _getFeeByOrderCode_sysTime, _getFeeByOrderCode_msg, _getFeeByOrderCode_baseFee, _getFeeByOrderCode_regFee, _getFeeByOrderCode_dealFee, _getFeeByOrderCode_insurance, _getFeeByOrderCode_totalFee, _getFeeByOrderCode_currencyCode, _getFeeByOrderCode_chargebackTime, _getFeeByOrderCode_chargebackWorkDay, _getFeeByOrderCode_shipTypeCode, _getFeeByOrderCode_subShipType, _getFeeByOrderCode_waybillCode, _getFeeByOrderCode_discount, _getFeeByOrderCode_otherFee, _getFeeByOrderCode_originBaseFee, _getFeeByOrderCode_originAddons, _getFeeByOrderCode_stDealFee, _getFeeByOrderCode_stRegFee, _getFeeByOrderCode_feeWeight);
        if (StringUtils.isEmpty(_getFeeByOrderCode_totalFee.value)) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("orderId", orderId);
            paramMap.put("shippingPrice", _getFeeByOrderCode_totalFee.value);
            Map<String, Object> rateMap = getShippingRate("shippingRate");
            double freight = Double.parseDouble(_getFeeByOrderCode_totalFee.value) * Double.parseDouble(rateMap.get("RATE").toString()) + Double.parseDouble(rateMap.get("DIFFERENCE").toString());
            paramMap.put("freight", freight);
            paramMap.put("status", "1");
            paramMap.put("operationUser", user.get("USER_ID"));
            financeManageService.updateShipping(paramMap);
            map.put("code", "0");
            map.put("msg", "获取成功");
        } else {
            map.put("code", "-10");
            map.put("msg", "获取失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    private String getYTShippingPrice(String orderId, String wayBillNumber, Map<String, Object> user) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = HttpClientUtil.doGetRequest("http://api.yunexpress.com/LMS.API/api/WayBill/GetShippingFeeDetail?wayBillNumber=" + wayBillNumber);
            JSONObject resultObject = JSONObject.parseObject(result);
            if (resultObject.getString("ResultCode").equals("0000")) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("orderId", orderId);
                paramMap.put("shippingPrice", resultObject.getJSONObject("Item").getString("TotalFee"));
                Map<String, Object> rateMap = getShippingRate("shippingRate");
                double freight = Double.parseDouble(resultObject.getJSONObject("Item").getString("TotalFee")) * Double.parseDouble(rateMap.get("RATE").toString()) + Double.parseDouble(rateMap.get("DIFFERENCE").toString());
                paramMap.put("freight", freight);
                paramMap.put("status", "1");
                paramMap.put("operationUser", user.get("USER_ID"));
                financeManageService.updateShipping(paramMap);
                map.put("code", "0");
                map.put("msg", "获取成功");
            } else {
                map.put("code", "-10");
                map.put("msg", "获取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", e.getMessage());
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    private Map<String, Object> getShippingRate(String code) {
        Map<String, Object> sqlMap = commonService.getSql(code);
        List<Map<String, Object>> list = commonService.getList(sqlMap.get("SQL_TEXT").toString());
        return list.get(0);
    }

    private HeaderRequest createRequest() {
        HeaderRequest _headerRequest = new HeaderRequest();
        _headerRequest.setAppKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCk0t7KGseCOy771g1PHYbIlzEYrckFVRZUn+SCEVm1q2nmcF0Rp3ukJSEWUIm4sQPMoT2V6YMvbT8O5uDgJxVLwp02ahrXBTHCV13KoLPqS/Y3usI/rpmxlMnp4hMNUJq/ezaTi7Wwzku1sZoAZLlqUmkwBGcFnUNFaRT6PVPSRQIDAQAB");
        _headerRequest.setToken("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvFLVKZrw4+mFJ+gpCQDcWT9JMiEtBSvP2R8Q1b7D5LXNod0VVAtXcYgayb+uPlu9m1yu3WAyBR9sQpysJzsBBc8e3l7iVxxkLLGIX15ZDkh7hHVVUgl+k51mlLkEQobyULkfx1Ur61gtttW74yQNspdN2CRHS+zdXcFIhvT2q3QIDAQAB");
        _headerRequest.setUserId("V8226");
        return _headerRequest;
    }

    private ShipRate createShipRate() {
        QName SERVICE_NAME = new QName("http://www.example.org/ShipRate/", "ShipRate");
        URL wsdlURL = ShipRate_Service.WSDL_LOCATION;
        ShipRate_Service ss = new ShipRate_Service(wsdlURL, SERVICE_NAME);
        ShipRate port = ss.getShipRateSOAP();
        return port;
    }
}
