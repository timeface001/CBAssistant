package com.crossborder.service;

import com.crossborder.entity.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14
 * 2015-06-08T18:02:43.281+08:00
 * Generated source version: 2.7.14
 * 
 */
@WebService(targetNamespace = "http://www.example.org/ShipRate/", name = "ShipRate")
@XmlSeeAlso({ObjectFactory.class})
public interface ShipRate {

    /**
     * 获取提供服务国家名称，而且必须用这里提供的国家名称用于对国费率查询
     */
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "getCountriesResponse", targetNamespace = "http://www.example.org/ShipRate/", partName = "parameters")
    @WebMethod(action = "http://www.example.org/ShipRate/getCountries")
    public Countries getCountries(
            @WebParam(partName = "parameters", name = "getCountriesRequest", targetNamespace = "http://www.example.org/ShipRate/")
            GetCountriesRequest parameters
    );

    @RequestWrapper(localName = "getFeeByOrderCode", targetNamespace = "http://www.example.org/ShipRate/", className = "GetFeeByOrderCode")
    @WebMethod(action = "http://www.example.org/ShipRate/getFeeByOrderCode")
    @ResponseWrapper(localName = "getFeeByOrderCodeResponse", targetNamespace = "http://www.example.org/ShipRate/", className = "GetFeeByOrderCodeResponse")
    public void getFeeByOrderCode(
            @WebParam(name = "HeaderRequest", targetNamespace = "")
            HeaderRequest headerRequest,
            @WebParam(mode = WebParam.Mode.INOUT, name = "orderCode", targetNamespace = "")
            javax.xml.ws.Holder<String> orderCode,
            @WebParam(mode = WebParam.Mode.OUT, name = "ask", targetNamespace = "")
            javax.xml.ws.Holder<String> ask,
            @WebParam(mode = WebParam.Mode.OUT, name = "sysTime", targetNamespace = "")
            javax.xml.ws.Holder<String> sysTime,
            @WebParam(mode = WebParam.Mode.OUT, name = "msg", targetNamespace = "")
            javax.xml.ws.Holder<String> msg,
            @WebParam(mode = WebParam.Mode.OUT, name = "baseFee", targetNamespace = "")
            javax.xml.ws.Holder<String> baseFee,
            @WebParam(mode = WebParam.Mode.OUT, name = "regFee", targetNamespace = "")
            javax.xml.ws.Holder<String> regFee,
            @WebParam(mode = WebParam.Mode.OUT, name = "dealFee", targetNamespace = "")
            javax.xml.ws.Holder<String> dealFee,
            @WebParam(mode = WebParam.Mode.OUT, name = "insurance", targetNamespace = "")
            javax.xml.ws.Holder<String> insurance,
            @WebParam(mode = WebParam.Mode.OUT, name = "totalFee", targetNamespace = "")
            javax.xml.ws.Holder<String> totalFee,
            @WebParam(mode = WebParam.Mode.OUT, name = "currencyCode", targetNamespace = "")
            javax.xml.ws.Holder<String> currencyCode,
            @WebParam(mode = WebParam.Mode.OUT, name = "chargebackTime", targetNamespace = "")
            javax.xml.ws.Holder<String> chargebackTime,
            @WebParam(mode = WebParam.Mode.OUT, name = "chargebackWorkDay", targetNamespace = "")
            javax.xml.ws.Holder<String> chargebackWorkDay,
            @WebParam(mode = WebParam.Mode.OUT, name = "shipTypeCode", targetNamespace = "")
            javax.xml.ws.Holder<String> shipTypeCode,
            @WebParam(mode = WebParam.Mode.OUT, name = "subShipType", targetNamespace = "")
            javax.xml.ws.Holder<String> subShipType,
            @WebParam(mode = WebParam.Mode.OUT, name = "waybillCode", targetNamespace = "")
            javax.xml.ws.Holder<String> waybillCode,
            @WebParam(mode = WebParam.Mode.OUT, name = "discount", targetNamespace = "")
            javax.xml.ws.Holder<String> discount,
            @WebParam(mode = WebParam.Mode.OUT, name = "otherFee", targetNamespace = "")
            javax.xml.ws.Holder<java.util.List<OtherFee>> otherFee,
            @WebParam(mode = WebParam.Mode.OUT, name = "originBaseFee", targetNamespace = "")
            javax.xml.ws.Holder<String> originBaseFee,
            @WebParam(mode = WebParam.Mode.OUT, name = "originAddons", targetNamespace = "")
            javax.xml.ws.Holder<String> originAddons,
            @WebParam(mode = WebParam.Mode.OUT, name = "stDealFee", targetNamespace = "")
            javax.xml.ws.Holder<String> stDealFee,
            @WebParam(mode = WebParam.Mode.OUT, name = "stRegFee", targetNamespace = "")
            javax.xml.ws.Holder<String> stRegFee,
            @WebParam(mode = WebParam.Mode.OUT, name = "feeWeight", targetNamespace = "")
            javax.xml.ws.Holder<String> feeWeight
    );

    /**
     * 查询运输方式
     */
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "getShipTypesResponse", targetNamespace = "http://www.example.org/ShipRate/", partName = "parameters")
    @WebMethod(action = "http://www.example.org/ShipRate/getShipTypes")
    public Shiptypes getShipTypes(
            @WebParam(partName = "parameters", name = "getShipTypesRequest", targetNamespace = "http://www.example.org/ShipRate/")
            GetShipTypesRequest parameters
    );

    @RequestWrapper(localName = "updateOrderStatus", targetNamespace = "http://www.example.org/ShipRate/", className = "UpdateOrderStatus")
    @WebMethod(action = "http://www.example.org/ShipRate/updateOrderStatus")
    @ResponseWrapper(localName = "updateOrderStatusResponse", targetNamespace = "http://www.example.org/ShipRate/", className = "UpdateOrderStatusResponse")
    public void updateOrderStatus(
            @WebParam(name = "HeaderRequest", targetNamespace = "")
            HeaderRequest headerRequest,
            @WebParam(name = "updateOrderInfo", targetNamespace = "")
            UpdateOrderStatusInfoArray updateOrderInfo,
            @WebParam(name = "orderCode", targetNamespace = "")
            String orderCode,
            @WebParam(mode = WebParam.Mode.OUT, name = "ask", targetNamespace = "")
            javax.xml.ws.Holder<String> ask,
            @WebParam(mode = WebParam.Mode.OUT, name = "message", targetNamespace = "")
            javax.xml.ws.Holder<String> message,
            @WebParam(mode = WebParam.Mode.OUT, name = "time", targetNamespace = "")
            javax.xml.ws.Holder<javax.xml.datatype.XMLGregorianCalendar> time
    );

    /**
     * 按寄送类型进行费率查询
     */
    @WebResult(name = "rates", targetNamespace = "")
    @RequestWrapper(localName = "getRatesByType", targetNamespace = "http://www.example.org/ShipRate/", className = "GetRatesByType")
    @WebMethod(action = "http://www.example.org/ShipRate/getRatesByType")
    @ResponseWrapper(localName = "getRatesByTypeResponse", targetNamespace = "http://www.example.org/ShipRate/", className = "Rates")
    public java.util.List<Rate> getRatesByType(
            @WebParam(name = "HeaderRequest", targetNamespace = "")
            HeaderRequest headerRequest,
            @WebParam(name = "ratesRequestInfo", targetNamespace = "")
            GetRatesByTypeRequestInfo ratesRequestInfo
    );

    /**
     * 直接查询使用何种寄送方的费率
     */
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "getRateByModeResponse", targetNamespace = "http://www.example.org/ShipRate/", partName = "parameters")
    @WebMethod(action = "http://www.example.org/ShipRate/getRateByMode")
    public Rate getRateByMode(
            @WebParam(partName = "parameters", name = "getRateByMode", targetNamespace = "http://www.example.org/ShipRate/")
            GetRateByMode parameters
    );

    /**
     * 进行费率查询
     */
    @WebResult(name = "rates", targetNamespace = "")
    @RequestWrapper(localName = "getRates", targetNamespace = "http://www.example.org/ShipRate/", className = "GetRates")
    @WebMethod(action = "http://www.example.org/ShipRate/getRates")
    @ResponseWrapper(localName = "outRates", targetNamespace = "http://www.example.org/ShipRate/", className = "Rates")
    public java.util.List<Rate> getRates(
            @WebParam(name = "HeaderRequest", targetNamespace = "")
            HeaderRequest headerRequest,
            @WebParam(name = "ratesRequestInfo", targetNamespace = "")
            GetRatesRequestInfo ratesRequestInfo
    );

    /**
     * 添加订单
     */
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "addOrderResponse", targetNamespace = "http://www.example.org/ShipRate/", partName = "parameters")
    @WebMethod(action = "http://www.example.org/ShipRate/addOrder")
    public AddOrderResponse addOrder(
            @WebParam(partName = "parameters", name = "addOrderRequest", targetNamespace = "http://www.example.org/ShipRate/")
            AddOrderRequest parameters
    );

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "updateOrderVolumeWeightResponse", targetNamespace = "http://www.example.org/ShipRate/", partName = "parameters")
    @WebMethod(action = "http://www.example.org/ShipRate/updateOrderVolumeWeight")
    public UpdateOrderVolumeWeightResponse updateOrderVolumeWeight(
            @WebParam(partName = "parameters", name = "updateOrderVolumeWeightRequest", targetNamespace = "http://www.example.org/ShipRate/")
            UpdateOrderVolumeWeightRequest parameters
    );

    /**
     * 查询订单
     */
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "searchOrderResponse", targetNamespace = "http://www.example.org/ShipRate/", partName = "parameters")
    @WebMethod(action = "http://www.example.org/ShipRate/searchOrder")
    public SearchOrderResponse searchOrder(
            @WebParam(partName = "parameters", name = "searchOrderRequest", targetNamespace = "http://www.example.org/ShipRate/")
            SearchOrderRequest parameters
    );

    /**
     * 查询运输类型
     */
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "getClassTypesResponse", targetNamespace = "http://www.example.org/ShipRate/", partName = "parameters")
    @WebMethod(action = "http://www.example.org/ShipRate/getClassTypes")
    public Classtypes getClassTypes(
            @WebParam(partName = "parameters", name = "getClassTypesRequest", targetNamespace = "http://www.example.org/ShipRate/")
            GetClassTypesRequest parameters
    );

    @RequestWrapper(localName = "updateOrder", targetNamespace = "http://www.example.org/ShipRate/", className = "UpdateOrder")
    @WebMethod(action = "http://www.example.org/ShipRate/updateOrder")
    @ResponseWrapper(localName = "updateOrderResponse", targetNamespace = "http://www.example.org/ShipRate/", className = "UpdateOrderResponse")
    public void updateOrder(
            @WebParam(name = "HeaderRequest", targetNamespace = "")
            HeaderRequest headerRequest,
            @WebParam(name = "updateOrderInfo", targetNamespace = "")
            AddOrderRequestInfoArray updateOrderInfo,
            @WebParam(name = "orderCode", targetNamespace = "")
            String orderCode,
            @WebParam(mode = WebParam.Mode.OUT, name = "ask", targetNamespace = "")
            javax.xml.ws.Holder<String> ask,
            @WebParam(mode = WebParam.Mode.OUT, name = "message", targetNamespace = "")
            javax.xml.ws.Holder<String> message,
            @WebParam(mode = WebParam.Mode.OUT, name = "time", targetNamespace = "")
            javax.xml.ws.Holder<javax.xml.datatype.XMLGregorianCalendar> time,
            @WebParam(mode = WebParam.Mode.OUT, name = "alert", targetNamespace = "")
            javax.xml.ws.Holder<String> alert
    );

    @RequestWrapper(localName = "batchAddOrders", targetNamespace = "http://www.example.org/ShipRate/", className = "BatchAddOrders")
    @WebMethod(action = "http://www.example.org/ShipRate/batchAddOrders")
    @ResponseWrapper(localName = "batchAddOrdersResponse", targetNamespace = "http://www.example.org/ShipRate/", className = "BatchAddOrdersResponse")
    public void batchAddOrders(
            @WebParam(name = "HeaderRequest", targetNamespace = "")
            HeaderRequest headerRequest,
            @WebParam(name = "orders", targetNamespace = "")
            java.util.List<AddOrderRequestInfoArray> orders,
            @WebParam(mode = WebParam.Mode.OUT, name = "ask", targetNamespace = "")
            javax.xml.ws.Holder<String> ask,
            @WebParam(mode = WebParam.Mode.OUT, name = "operatingTime", targetNamespace = "")
            javax.xml.ws.Holder<String> operatingTime,
            @WebParam(mode = WebParam.Mode.OUT, name = "message", targetNamespace = "")
            javax.xml.ws.Holder<String> message,
            @WebParam(mode = WebParam.Mode.OUT, name = "data", targetNamespace = "")
            javax.xml.ws.Holder<java.util.List<AddOrderResponseData>> data
    );

    @RequestWrapper(localName = "reSetLabel", targetNamespace = "http://www.example.org/ShipRate/", className = "ReSetLabel")
    @WebMethod(action = "http://www.example.org/ShipRate/reSetLabel")
    @ResponseWrapper(localName = "reSetLabelResponse", targetNamespace = "http://www.example.org/ShipRate/", className = "ReSetLabelResponse")
    public void reSetLabel(
            @WebParam(name = "HeaderRequest", targetNamespace = "")
            HeaderRequest headerRequest,
            @WebParam(name = "reSetLabelInfo", targetNamespace = "")
            ReSetLabelInfoArray reSetLabelInfo,
            @WebParam(name = "orderCode", targetNamespace = "")
            String orderCode,
            @WebParam(mode = WebParam.Mode.OUT, name = "ask", targetNamespace = "")
            javax.xml.ws.Holder<String> ask,
            @WebParam(mode = WebParam.Mode.OUT, name = "message", targetNamespace = "")
            javax.xml.ws.Holder<String> message,
            @WebParam(mode = WebParam.Mode.OUT, name = "trackingNumber", targetNamespace = "")
            javax.xml.ws.Holder<String> trackingNumber,
            @WebParam(mode = WebParam.Mode.OUT, name = "time", targetNamespace = "")
            javax.xml.ws.Holder<javax.xml.datatype.XMLGregorianCalendar> time
    );
}
