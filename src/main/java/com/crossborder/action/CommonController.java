package com.crossborder.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.model.*;
import com.crossborder.entity.*;
import com.crossborder.org.tempuri.*;
import com.crossborder.service.CommonService;
import com.crossborder.service.OrderManageService;
import com.crossborder.service.ShipRate;
import com.crossborder.service.ShopManageService;
import com.crossborder.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by s on 2018/4/2.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Resource
    private CommonService commonService;
    @Resource
    private OrderManageService orderManageService;
    @Resource
    private ShopManageService shopManageService;
    @Autowired
    private CommonSet commonSet;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String Order_Fulfillment_Fee = "_POST_ORDER_FULFILLMENT_DATA_";

    /**
     * 登录
     *
     * @param userId
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", produces = "text/plain;charset=UTF-8")
    public String login(String userId, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        try {
            paramMap.put("userId", userId);
            paramMap.put("password", password);
            List<Map<String, Object>> list = commonService.login(paramMap);
            if (list != null && list.size() == 1) {
               /* if (list.get(0).get("ISLOGIN").toString().equals("1")) {
                    map.put("code", "1");
                    map.put("msg", "当前用户已登录");
                } else {*/
                paramMap.put("isLogin", 1);
                commonService.updateLogin(paramMap);
                session.setAttribute("user", list.get(0));
                session.setAttribute("productPath", commonSet.getProductImagePath());
                map.put("data", list.get(0));
                map.put("code", "0");
                map.put("msg", "登录成功");
               /* }*/
            } else {
                map.put("code", "-10");
                map.put("msg", "登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "登录失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 登出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logout", produces = "text/plain;charset=UTF-8")
    public String logout(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userId", user.get("USER_ID"));
            paramMap.put("isLogin", 0);
            commonService.updateLogin(paramMap);
            session.removeAttribute("user");
            session.invalidate();
            map.put("code", "0");
            map.put("msg", "退出成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "退出失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 获取菜单
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "selectMenus", produces = "text/plain;charset=UTF-8")
    public String selectMenus(Model model, String roleId, String userId) {
        Map<String, Object> paramMap = new HashMap<>();
        try {
            paramMap.put("roleId", roleId);
            List<Map<String, Object>> list = commonService.selectMenus(paramMap);
            List<Menu> menus = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Menu menu = new Menu();
                if (list.get(i).get("MENU_PID") != null) {
                    if (list.get(i).get("MENU_PID").toString().equals("0")) {
                        menu.setMenuId(list.get(i).get("MENU_ID").toString());
                        menu.setMenuPid(list.get(i).get("MENU_PID").toString());
                        menu.setPath(list.get(i).get("PATH").toString());
                        menu.setMenuName(list.get(i).get("MENU_NAME").toString());
                        menu.setIconPath(list.get(i).get("ICONPATH").toString());
                        List<Menu> childMenus = new ArrayList<>();
                        for (int j = 0; j < list.size(); j++) {
                            Menu childMenu = new Menu();
                            if (list.get(j).get("MENU_PID") != null) {
                                if (list.get(i).get("MENU_ID").toString().equals(list.get(j).get("MENU_PID").toString())) {
                                    childMenu.setMenuId(list.get(j).get("MENU_ID").toString());
                                    childMenu.setMenuPid(list.get(j).get("MENU_PID").toString());
                                    childMenu.setPath(list.get(j).get("PATH").toString());
                                    childMenu.setMenuName(list.get(j).get("MENU_NAME").toString());
                                    childMenus.add(childMenu);
                                }
                            }
                            menu.setMenuList(childMenus);
                        }
                        menus.add(menu);
                    }
                }
            }
            model.addAttribute("menus", menus);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "assistant/index/main";
    }

    /**
     * 通用查询接口，主要用来查询下拉列表
     *
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getList", produces = "text/plain;charset=UTF-8")
    public String getList(String code) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> sqlMap = commonService.getSql(code);
            List<Map<String, Object>> list = commonService.getList(sqlMap.get("SQL_TEXT").toString());
            map.put("data", list);
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
     * 获取产品分类
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectTypes", produces = "text/plain;charset=UTF-8")
    public String selectTypes(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Map<String, Object>> list = commonService.selectTypes(id);
            if (list != null && list.size() > 0) {
                map.put("data", list);
                map.put("code", "0");
                map.put("msg", "查询成功");
            } else {
                map.put("code", "1");
                map.put("msg", "查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 通用查询接口，主要用来查询tree列表
     *
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTreeMenus", produces = "text/plain;charset=UTF-8")
    public String getTreeMenus(String code) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> sqlMap = commonService.getSql(code);
            List<Map<String, Object>> list = commonService.getTreeList(sqlMap.get("SQL_TEXT").toString());
            List<Menu> menus = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Menu menu = new Menu();
                if (list.get(i).get("MENU_PID") != null) {
                    if (list.get(i).get("MENU_PID").toString().equals("0")) {
                        menu.setMenuId(list.get(i).get("MENU_ID").toString());
                        menu.setMenuPid(list.get(i).get("MENU_PID").toString());
                        menu.setPath(list.get(i).get("PATH").toString());
                        menu.setMenuName(list.get(i).get("MENU_NAME").toString());
                        menu.setIconPath(list.get(i).get("ICONPATH").toString());
                        List<Menu> childMenus = new ArrayList<>();
                        for (int j = 0; j < list.size(); j++) {
                            Menu childMenu = new Menu();
                            if (list.get(j).get("MENU_PID") != null) {
                                if (list.get(i).get("MENU_ID").toString().equals(list.get(j).get("MENU_PID").toString())) {
                                    childMenu.setMenuId(list.get(j).get("MENU_ID").toString());
                                    childMenu.setMenuPid(list.get(j).get("MENU_PID").toString());
                                    childMenu.setPath(list.get(j).get("PATH").toString());
                                    childMenu.setMenuName(list.get(j).get("MENU_NAME").toString());
                                    childMenus.add(childMenu);
                                }
                            }
                            menu.setMenuList(childMenus);
                        }
                        menus.add(menu);
                    }
                }
            }
            map.put("data", menus);
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
    @RequestMapping(value = "getShipTypes", produces = "text/plain;charset=UTF-8")
    public String getShipTypes(String countryCode, String companyId, String weight) {
        if (companyId.contains("SFC")) {
            return getSFCShipTypes(countryCode, weight);
        } else if (companyId.contains("YT")) {
            return getYTShipTypes(countryCode);
        } else if (companyId.contains("Equick")) {
            return getEquickShipTypes(countryCode);
        } else {
            return getYTShipTypes(countryCode);
        }
    }

    private String getEquickShipTypes(String countryCode) {
        Map<String, Object> map = new HashMap<>();
        try {
            BookingQuickTypeDataSetResponseBookingQuickTypeDataSetResult result = EOCWebServicesWS.BookingQuickTypeDataSet();
            List<Map<String, String>> ships = new ArrayList<>();
            for (Object obj : result.get_any()) {
                Node nd = (Node) obj;
                for (int m = 0; m < nd.getChildNodes().getLength(); m++) {
                    Node child = (Node) nd.getChildNodes().item(m);
                    if ("NewDataSet".equals(child.getNodeName())) {
                        for (int n = 0; n < child.getChildNodes().getLength(); n++) {
                            Node childTypes = (Node) child.getChildNodes().item(n);
                            if ("QuickType".equals(childTypes.getNodeName())) {
                                Map<String, String> ship = new HashMap<>();
                                for (int i = 0; i < childTypes.getChildNodes().getLength(); i++) {
                                    Node childType = (Node) childTypes.getChildNodes().item(i);
                                    if ("EOCQuickTypeName".equals(childType.getNodeName())) {
                                        ship.put("name", childType.getValue());
                                    } else if ("EOCQuickType".equals(childType.getNodeName())) {
                                        ship.put("code", childType.getValue());
                                    }
                                }
                                ships.add(ship);
                            }
                        }
                    }
                }
            }
            map.put("data", ships);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    public String getYTShipTypes(String countryCode) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = HttpClientUtil.doGetRequest("http://api.yunexpress.com/LMS.API/api/lms/Get?countryCode=" + countryCode);
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("Item");
            List<Map<String, String>> ships = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, String> shipMap = new HashMap<>();
                shipMap.put("code", jsonArray.getJSONObject(i).getString("Code"));
                shipMap.put("name", jsonArray.getJSONObject(i).getString("FullName"));
                ships.add(shipMap);
            }
            map.put("data", ships);
            map.put("code", "0");
            map.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "查询失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
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

    public String getSFCShipTypes(String countryCode, String weight) {
        Map<String, Object> map = new HashMap<>();
        try {
            ShipRate port = createShipRate();
            HeaderRequest _headerRequest = createRequest();
            GetRatesRequestInfo getRatesRequestInfo = new GetRatesRequestInfo();
            getRatesRequestInfo.setCountry(countryCode);
            getRatesRequestInfo.setWeight(Float.valueOf(weight));
            List<Rate> rates = port.getRates(_headerRequest, getRatesRequestInfo);
            List<Map<String, String>> ships = new ArrayList<>();
            for (int i = 0; i < rates.size(); i++) {
                Map<String, String> shipMap = new HashMap<>();
                shipMap.put("code", rates.get(i).getShiptypecode());
                shipMap.put("name", rates.get(i).getShiptypecnname());
                ships.add(shipMap);
            }
            map.put("data", ships);
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
    @RequestMapping(value = "confirmOrder", produces = "text/plain;charset=UTF-8")
    public String confirmOrder(String amazonOrderId, String json, String companyId, String salesMan, String salesCompany, String marketplaceId, String merchantId, String alikeOrderId, HttpSession session) {
        if (companyId.contains("SFC")) {
            return addSFCOrder(session, json, amazonOrderId, salesMan, salesCompany, companyId, marketplaceId, merchantId, alikeOrderId);
        } else if (companyId.contains("Yun")) {
            return addYTOrder(session, json, amazonOrderId, salesMan, salesCompany, companyId, marketplaceId, merchantId, alikeOrderId);
        } else if (companyId.contains("Equick")) {
            return addEquickOrder(session, json, amazonOrderId, salesMan, salesCompany, companyId, marketplaceId, merchantId, alikeOrderId);
        } else {
            return addYTOrder(session, json, amazonOrderId, salesMan, salesCompany, companyId, marketplaceId, merchantId, alikeOrderId);
        }
    }

    private String addEquickOrder(HttpSession session,
                                  String json,
                                  String amazonOrderId,
                                  String salesMan,
                                  String salesCompany,
                                  String companyId,
                                  String marketplaceId,
                                  String merchantId,
                                  String alikeOrderId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
            JSONObject jsonObject = JSONObject.parseArray(json).getJSONObject(0);
            JSONObject shippingObject = jsonObject.getJSONObject("ShippingInfo");
            JSONArray applicationInfos = jsonObject.getJSONArray("ApplicationInfos");
            EOCShipmentRequest request = new EOCShipmentRequest();
            Authentication authentication = new Authentication("W160960", "W160960");
            EOCBookingShipment bookingShipment = new EOCBookingShipment();
            bookingShipment.setBookingCustDate(Calendar.getInstance());
            bookingShipment.setBookingCustRefNo(jsonObject.getString("OrderNumber"));
            bookingShipment.setQuickType(jsonObject.getString("ShippingMethodCode"));
            bookingShipment.setReturnQuest("N");
            bookingShipment.setToCustLinkman(shippingObject.getString("ShippingFirstName"));
            bookingShipment.setToCustCountry(shippingObject.getString("CountryCode"));
            bookingShipment.setToCustCanton(shippingObject.getString("ShippingState"));
            bookingShipment.setToCustCity(shippingObject.getString("ShippingCity"));
            bookingShipment.setToCustZipCode(shippingObject.getString("ShippingZip"));
            bookingShipment.setToCustAddress(shippingObject.getString("ShippingAddress") + "\n \n \n");
            bookingShipment.setToCustTel(shippingObject.getString("ShippingPhone"));
            bookingShipment.setWHTFactWHT(Float.valueOf(jsonObject.getString("Weight")));
            bookingShipment.setWHTHeight(Float.valueOf(jsonObject.getString("Height")));
            bookingShipment.setWHTLength(Float.valueOf(jsonObject.getString("Length")));
            bookingShipment.setWHTWidth(Float.valueOf(jsonObject.getString("Width")));
            float declareWorth = 0;
            String enName = "";
            String cnName = "";
            String sku = "";
            int count = 0;
            for (int i = 0; i < applicationInfos.size(); i++) {
                JSONObject good = applicationInfos.getJSONObject(i);
                enName = enName + good.getString("ApplicationName") + " AND ";
                cnName = cnName + good.getString("PickingName") + " AND ";
                bookingShipment.setGoodsDesc(good.getString("ApplicationName"));
                sku = sku + good.getString("SKU") + " AND ";
                count += Integer.parseInt(good.getString("Qty"));
                declareWorth = declareWorth + Float.valueOf(good.getString("Qty")) * Float.valueOf(good.getString("UnitPrice"));
            }
            bookingShipment.setGoodsPieceNum(count);
            bookingShipment.setGoodsNameENG(enName);
            bookingShipment.setGoodsNameCHS(cnName);
            bookingShipment.setGoodsSKU(sku);
            bookingShipment.setGoodsValue(declareWorth);
            request.setBookingShipment(bookingShipment);
            request.setAuthentication(authentication);
            EOCShipmentResponse response = EOCWebServicesWS.RequestEOCShipment(request);
            if (response.getShipmentCompleted().getReturnValue() == -1) {
                String intlTrackNum = response.getShipmentCompleted().getEquickWBNo();
                updateLocalOrderStatus(amazonOrderId, shippingObject, jsonObject, intlTrackNum, salesMan, salesCompany, user);
                updateAmazonOrderStatus(amazonOrderId, companyId, intlTrackNum, marketplaceId, merchantId);
                map.put("data", response.getShipmentCompleted().getEquickWBNo());
                map.put("code", "0");
                map.put("msg", "发货成功");
            } else {
                map.put("code", "-10");
                map.put("msg", response.getShipmentCompleted().getReturnMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", e.getMessage());
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    private String addSFCOrder(HttpSession session,
                               String json,
                               String amazonOrderId,
                               String salesMan,
                               String salesCompany,
                               String companyId,
                               String marketplaceId,
                               String merchantId,
                               String alikeOrderId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        try {
            ShipRate port = createShipRate();
            HeaderRequest _headerRequest = createRequest();
            AddOrderRequestInfoArray _addOrdersRequestInfo = new AddOrderRequestInfoArray();
            AddOrderRequest _addOrdersRequest = new AddOrderRequest();
            java.util.List<GoodsDetailsArray> _goodsDetailsArray = _addOrdersRequestInfo.getGoodsDetails();
            GoodsDetailsArray _goodsDetails = new GoodsDetailsArray();
            _addOrdersRequest.setHeaderRequest(_headerRequest);

            JSONObject jsonObject = JSONObject.parseArray(json).getJSONObject(0);
            JSONObject shippingObject = jsonObject.getJSONObject("ShippingInfo");
            JSONArray applicationInfos = jsonObject.getJSONArray("ApplicationInfos");
            _addOrdersRequestInfo.setCustomerOrderNo(jsonObject.getString("OrderNumber"));
            _addOrdersRequestInfo.setShipperAddressType(2);
            _addOrdersRequestInfo.setShipperName("Zhang YongJun");
            _addOrdersRequestInfo.setShipperAddress("Shenzhen BaoAnQu ZhouShiLu HengFengGongYeCheng");
            _addOrdersRequestInfo.setShipperPhone("19902908635");
            _addOrdersRequestInfo.setShipperZipCode("518126");
            _addOrdersRequestInfo.setShipperCompanyName("Zhang YongJun");
            _addOrdersRequestInfo.setShipperEmail("458572850@qq.com");
            _addOrdersRequestInfo.setShippingMethod(jsonObject.getString("ShippingMethodCode"));
            _addOrdersRequestInfo.setRecipientName(shippingObject.getString("ShippingFirstName"));
            _addOrdersRequestInfo.setRecipientCountry(shippingObject.getString("CountryCode"));
            _addOrdersRequestInfo.setRecipientCity(shippingObject.getString("ShippingCity"));
            _addOrdersRequestInfo.setRecipientState(shippingObject.getString("ShippingState"));
            _addOrdersRequestInfo.setRecipientEmail("");
            _addOrdersRequestInfo.setRecipientPhone(shippingObject.getString("ShippingPhone"));
            _addOrdersRequestInfo.setRecipientZipCode(shippingObject.getString("ShippingZip"));
            _addOrdersRequestInfo.setRecipientAddress(shippingObject.getString("ShippingAddress"));
            _addOrdersRequestInfo.setGoodsQuantity(jsonObject.getString("PackageNumber"));
            _addOrdersRequestInfo.setGoodsHeight(Float.valueOf(jsonObject.getString("Height")));
            _addOrdersRequestInfo.setGoodsLength(Float.valueOf(jsonObject.getString("Length")));
            _addOrdersRequestInfo.setGoodsWeight(Float.valueOf(jsonObject.getString("Weight")));
            _addOrdersRequestInfo.setGoodsWidth(Float.valueOf(jsonObject.getString("Width")));
            _addOrdersRequestInfo.setGoodsDescription(applicationInfos.getJSONObject(0).getString("ApplicationName"));
            _addOrdersRequestInfo.setIsRemoteConfirm("0");
            //拼商品信息
            double declareWorth = 0;
            for (int i = 0; i < applicationInfos.size(); i++) {
                JSONObject good = applicationInfos.getJSONObject(i);
                _goodsDetails.setDetailDescription(good.getString("ApplicationName"));
                _goodsDetails.setDetailDescriptionCN(good.getString("PickingName"));
                _goodsDetails.setDetailQuantity(good.getString("Qty"));
                _goodsDetails.setDetailWorth(good.getString("UnitPrice"));
                _goodsDetails.setDetailWeight(Float.valueOf(good.getString("UnitWeight")));
                _goodsDetails.setHsCode(good.getString("SKU"));
                declareWorth = declareWorth + Double.valueOf(good.getString("Qty")) * Double.valueOf(good.getString("UnitPrice"));
                _goodsDetailsArray.add(_goodsDetails);
            }
            _addOrdersRequestInfo.setGoodsDeclareWorth(declareWorth + "");
            /*_addOrdersRequestInfo.setEvaluate(declareWorth + "");*/
            _addOrdersRequest.setAddOrderRequestInfo(_addOrdersRequestInfo);
            AddOrderResponse _addOrder__return = port.addOrder(_addOrdersRequest);
            if (_addOrder__return.getOrderActionStatus().equals("Y")) {
                String intlTrackNum = _addOrder__return.getOrderCode();
                updateLocalOrderStatus(amazonOrderId, shippingObject, jsonObject, intlTrackNum, salesMan, salesCompany, user);
                updateAmazonOrderStatus(amazonOrderId, companyId, intlTrackNum, marketplaceId, merchantId);
                map.put("data", _addOrder__return.getOrderCode());
                map.put("code", "0");
                map.put("msg", "发货成功");
            } else {
                map.put("code", "-10");
                map.put("msg", _addOrder__return.getNote());
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", e.getMessage());
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    private String addYTOrder(HttpSession session,
                              String json,
                              String amazonOrderId,
                              String salesMan,
                              String salesCompany,
                              String companyId,
                              String marketplaceId,
                              String merchantId,
                              String alikeOrderId) {
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseArray(json).getJSONObject(0);
        JSONObject shippingObject = jsonObject.getJSONObject("ShippingInfo");
        JSONObject senderObjecct = new JSONObject();
        senderObjecct.put("CountryCode", "CN");
        senderObjecct.put("SenderFirstName", "Yongjun");
        senderObjecct.put("SenderLastName", " Zhang");
        senderObjecct.put("SenderCompany", "Yongjun Zhang");
        senderObjecct.put("SenderAddress", "BaoAnQu");
        senderObjecct.put("SenderCity", "Shenzhen");
        senderObjecct.put("SenderState", "Guangdong");
        senderObjecct.put("SenderZip", "518126");
        senderObjecct.put("SenderPhone", "19902908635");
        JSONArray paramArray = JSON.parseArray(json);
        JSONObject paramObject = paramArray.getJSONObject(0);
        paramObject.put("SenderInfo", senderObjecct);
        json = JSON.toJSONString(paramArray);
        try {
            String result = HttpClientUtil.doPostRequest("http://api.yunexpress.com/LMS.API/api/WayBill/BatchAdd", json);
            JSONObject resultObject = JSONObject.parseObject(result);
            if (resultObject.getString("ResultCode").equals("0000")) {
                String intlTrackNum = resultObject.getJSONArray("Item").getJSONObject(0).getString("WayBillNumber");
                updateLocalOrderStatus(amazonOrderId, shippingObject, jsonObject, intlTrackNum, salesMan, salesCompany, user);
                updateAmazonOrderStatus(amazonOrderId, companyId, intlTrackNum, marketplaceId, merchantId);
                map.put("data", resultObject);
                map.put("code", "0");
                map.put("msg", "发货成功");
            } else {
                map.put("code", "-10");
                map.put("msg", resultObject.getJSONArray("Item").getJSONObject(0).getString("Feedback"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", e.getMessage());
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    public void updateAmazonOrderStatus(String amazonOrderId,
                                        String companyId,
                                        String trackingNumber,
                                        String marketplaceId,
                                        String merchantId) {
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">" +
                    "<Header>" +
                    "<DocumentVersion>1.01</DocumentVersion>" +
                    "<MerchantIdentifier>" + merchantId + "</MerchantIdentifier>" +
                    "</Header>" +
                    "<MessageType>OrderFulfillment</MessageType>";
            String message = "<Message>" +
                    "<MessageID>1</MessageID>" +
                    "<OrderFulfillment>" +
                    "<AmazonOrderID>" + amazonOrderId + "</AmazonOrderID>" +
                    "<FulfillmentDate>" + DatatypeFactory.newInstance().newXMLGregorianCalendar(cal) + "</FulfillmentDate>" +
                    "<FulfillmentData>" +
                    "<CarrierName>" + companyId + "</CarrierName>" +
                    "<ShippingMethod>Standard</ShippingMethod>" +
                    "<ShipperTrackingNumber>" + trackingNumber + "</ShipperTrackingNumber>" +
                    "</FulfillmentData>" +
            /*        "<Item>" +
                    "<AmazonOrderItemCode>06743192371019</AmazonOrderItemCode>" +
                    "<Quantity>1</Quantity>" +
                    "</Item>" +*/
                    "</OrderFulfillment>" +
                    "</Message>";
            String end = "</AmazonEnvelope>";
            StringBuffer stringBuffer = new StringBuffer();
            String fulfillmentXml = stringBuffer.append(header).append(message).append(end).toString();
            FileWriter writer = new FileWriter("/home/amz/updateStatus.txt");
            writer.write(fulfillmentXml);
            writer.flush();
            writer.close();
            FileInputStream fis = new FileInputStream(new File("/home/amz/updateStatus.txt"));
            Map<String, Object> shopMap = new HashMap<>();
            shopMap.put("merchantId", merchantId);
            shopMap.put("marketplaceId", marketplaceId);
            Map<String, Object> shop = shopManageService.selectShopByCountry(shopMap).get(0);
            MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
            config.setServiceURL(shop.get("ENDPOINT").toString());
            MarketplaceWebService service = new MarketplaceWebServiceClient(shop.get("ACCESSKEY_ID").toString(), shop.get("SECRET_KEY").toString(), "", "", config);
            SubmitFeedRequest request = new SubmitFeedRequest();
            IdList idList = new IdList();
            List<String> ids = new ArrayList<>();
            ids.add(marketplaceId);
            idList.setId(ids);
            request.setMarketplaceIdList(idList);
            request.setMerchant(shop.get("MERCHANT_ID").toString());
            request.setFeedContent(fis);
            request.setFeedType(Order_Fulfillment_Fee);
            request.setContentMD5(MD5.computeContentMD5HeaderValue(fis));
            SubmitFeedResponse response = service.submitFeed(request);
            if (response.isSetSubmitFeedResult()) {
                SubmitFeedResult submitFeedResult = response
                        .getSubmitFeedResult();
                if (submitFeedResult.isSetFeedSubmissionInfo()) {
                    FeedSubmissionInfo feedSubmissionInfo = submitFeedResult
                            .getFeedSubmissionInfo();
                    if (feedSubmissionInfo.isSetFeedSubmissionId()) {

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAlikeOrder(String alikeOrderId, String intlTrackNum, JSONObject jsonObject, Map<String, Object> user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("amazonOrderId", alikeOrderId);
        paramMap.put("status", "4");
        paramMap.put("intlTrackNum", intlTrackNum);
        paramMap.put("updateTime", simpleDateFormat.format(new Date()));
        paramMap.put("transportCompany", jsonObject.getString("transportCompany"));
        orderManageService.updateOrder(paramMap);
        orderManageService.updateOrderItem(paramMap);
        insertOperationLog(alikeOrderId, "2", "4", user.get("USER_ID").toString(), "");
    }

    private void updateLocalOrderStatus(String amazonOrderId, JSONObject shippingObject,
                                        JSONObject jsonObject, String intlTrackNum,
                                        String salesMan, String salesCompany, Map<String, Object> user) {
        Map<String, Object> shippingMap = shippingObject;
        shippingMap.put("amazonOrderId", amazonOrderId);
        commonService.updateAddress(shippingMap);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("amazonOrderId", amazonOrderId);
        paramMap.put("status", "4");
        paramMap.put("intlTrackNum", intlTrackNum);
        paramMap.put("updateTime", simpleDateFormat.format(new Date()));
        paramMap.put("transportCompany", jsonObject.getString("transportCompany"));
        orderManageService.updateOrder(paramMap);
        orderManageService.updateOrderItem(paramMap);
        insertOperationLog(amazonOrderId, "2", "4", user.get("USER_ID").toString(), "");
        Map<String, Object> shipMap = new HashMap<>();
        shipMap.put("amazonOrderId", amazonOrderId);
        shipMap.put("companyId", jsonObject.getString("transportCompany"));
        shipMap.put("typeId", jsonObject.getString("ShippingMethodCode"));
        shipMap.put("custId", jsonObject.getString("OrderNumber"));
        shipMap.put("length", jsonObject.getString("Length"));
        shipMap.put("width", jsonObject.getString("Width"));
        shipMap.put("height", jsonObject.getString("Height"));
        shipMap.put("packages", jsonObject.getString("PackageNumber"));
        shipMap.put("weight", jsonObject.getString("Weight"));
        shipMap.put("trackNum", intlTrackNum);
        shipMap.put("orderCode", intlTrackNum);
        shipMap.put("salesMan", salesMan);
        shipMap.put("salesCompany", salesCompany);
        shipMap.put("status", "0");
        commonService.insertShipMent(shipMap);
        JSONArray jsonArray = jsonObject.getJSONArray("ApplicationInfos");
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, Object> customsInfo = jsonArray.getJSONObject(i);
            customsInfo.put("custId", jsonObject.getString("OrderNumber"));
            commonService.insertCustomsInfo(customsInfo);
        }
    }

    @ResponseBody
    @RequestMapping(value = "print", produces = "text/plain;charset=UTF-8")
    public String print(String orderNumbers, String companyId, String orderCode) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (companyId.contains("SFC")) {
                String url = "http://www.sendfromchina.com/api/label?orderCodeList=" + orderCode + "&printType=1&print_type=pdf&printSize=3&printSort=1";
                map.put("data", url);
                map.put("code", "0");
                map.put("msg", "打印成功");
            } else if (companyId.contains("Yun")) {
                String result = HttpClientUtil.doPostRequest("http://api.yunexpress.com/LMS.API.Lable/Api/PrintUrl", orderNumbers);
                JSONObject jsonObject = JSONObject.parseObject(result);
                if (jsonObject.getString("ResultCode").equals("0000")) {
                    JSONObject item = jsonObject.getJSONArray("Item").getJSONObject(0);
                    map.put("data", item.getString("Url"));
                    map.put("code", "0");
                    map.put("msg", "打印成功");
                } else {
                    map.put("code", "-10");
                    map.put("msg", "打印失败");
                }
            } else if (companyId.contains("Equick")) {
                Authentication authentication = new Authentication("W160960", "W160960");
                EOCQuickLabelRequest request = new EOCQuickLabelRequest();
                request.setAuthentication(authentication);
                /*request.setQuickLabelType("Lbl10x10");*/
                request.setEquickWBNo(orderCode);
                EOCQuickLabelResponse response = EOCWebServicesWS.RequestEOCQuickLabel(request);
                if (response.getQuickLabelCompleted().getReturnValue() == -1) {
                    map.put("code", "0");
                    map.put("msg", "打印成功");
                } else {
                    map.put("code", "-10");
                    map.put("msg", "打印失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "打印失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    @ResponseBody
    @RequestMapping(value = "printByOrderId", produces = "text/plain;charset=UTF-8")
    public String printByOrderId(String orderId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("orderId", orderId);
            Map<String, Object> shipMent = commonService.selectShipMent(paramMap).get(0);
            String companyId = shipMent.get("TRANS_COMPANY_ID").toString();
            String orderCode = shipMent.get("ORDER_CODE").toString();
            String[] custOrderIds = {shipMent.get("CUST_ORDER_ID").toString()};
            if (companyId.contains("SFC")) {
                String url = "http://www.sendfromchina.com/api/label?orderCodeList=" + orderCode + "&printType=1&print_type=pdf&printSize=3&printSort=1";
                map.put("data", url);
                map.put("code", "0");
                map.put("msg", "打印成功");
            } else if (companyId.contains("Yun")) {
                String result = HttpClientUtil.doPostRequest("http://api.yunexpress.com/LMS.API.Lable/Api/PrintUrl", JSON.toJSONString(custOrderIds));
                JSONObject jsonObject = JSONObject.parseObject(result);
                if (jsonObject.getString("ResultCode").equals("0000")) {
                    JSONObject item = jsonObject.getJSONArray("Item").getJSONObject(0);
                    map.put("data", item.getString("Url"));
                    map.put("code", "0");
                    map.put("msg", "打印成功");
                } else {
                    map.put("code", "-10");
                    map.put("msg", "打印失败");
                }
            } else if (companyId.contains("Equick")) {
                Authentication authentication = new Authentication("W160960", "W160960");
                EOCQuickLabelRequest request = new EOCQuickLabelRequest();
                request.setAuthentication(authentication);
                /*request.setQuickLabelType("Lbl10x10");*/
                request.setEquickWBNo(orderCode);
                EOCQuickLabelResponse response = EOCWebServicesWS.RequestEOCQuickLabel(request);
                if (response.getQuickLabelCompleted().getReturnValue() == -1) {
                    map.put("code", "0");
                    map.put("msg", "打印成功");
                } else {
                    map.put("code", "-10");
                    map.put("msg", "打印失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-10");
            map.put("msg", "打印失败");
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    public void insertOperationLog(String amazonOrderId, String preStatus, String status, String userId, String cost) {
        Map<String, Object> operationMap = new HashMap<>();
        operationMap.put("user", userId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        operationMap.put("time", simpleDateFormat.format(new Date()));
        operationMap.put("amazonOrderId", amazonOrderId);
        operationMap.put("type", status);
        if (status.equals("0")) {
            operationMap.put("info", "产品" + amazonOrderId + "添加了备注");
        } else {
            operationMap.put("info", "产品" + amazonOrderId + "状态由【" + Tools.getStatusStr(preStatus) + "】变为【" + Tools.getStatusStr(status) + "】；成本由【0.00】变为【" + cost + "】");
        }
        orderManageService.inserOperationLog(operationMap);
    }
}
