
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.crossborder.entity;

import com.crossborder.service.ShipRate;

import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 2.7.14
 * 2015-06-08T18:02:43.218+08:00
 * Generated source version: 2.7.14
 */

@WebService(
        serviceName = "ShipRate",
        portName = "ShipRateSOAP",
        targetNamespace = "http://www.example.org/ShipRate/",
        wsdlLocation = "http://www.sfcservice.com/ishipsvc/web-service?wsdl",
        endpointInterface = "com.crossborder.service.ShipRate")

public class ShipRateImpl implements ShipRate {

    private static final Logger LOG = Logger.getLogger(ShipRateImpl.class.getName());

    /* (non-Javadoc)
     * @see ""ShipRate#getCountries(""GetCountriesRequest  parameters )*
     */
    public Countries getCountries(GetCountriesRequest parameters) {
        LOG.info("Executing operation getCountries");
        System.out.println(parameters);
        try {
            Countries _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#getFeeByOrderCode(""HeaderRequest  headerRequest ,)java.lang.String  orderCode ,)java.lang.String  ask ,)java.lang.String  sysTime ,)java.lang.String  msg ,)java.lang.String  baseFee ,)java.lang.String  regFee ,)java.lang.String  dealFee ,)java.lang.String  insurance ,)java.lang.String  totalFee ,)java.lang.String  currencyCode ,)java.lang.String  chargebackTime ,)java.lang.String  chargebackWorkDay ,)java.lang.String  shipTypeCode ,)java.lang.String  subShipType ,)java.lang.String  waybillCode ,)java.lang.String  discount ,)java.util.List<""OtherFee>  otherFee ,)java.lang.String  originBaseFee ,)java.lang.String  originAddons ,)java.lang.String  stDealFee ,)java.lang.String  stRegFee ,)java.lang.String  feeWeight )*
     */
    public void getFeeByOrderCode(HeaderRequest headerRequest, javax.xml.ws.Holder<String> orderCode, javax.xml.ws.Holder<String> ask, javax.xml.ws.Holder<String> sysTime, javax.xml.ws.Holder<String> msg, javax.xml.ws.Holder<String> baseFee, javax.xml.ws.Holder<String> regFee, javax.xml.ws.Holder<String> dealFee, javax.xml.ws.Holder<String> insurance, javax.xml.ws.Holder<String> totalFee, javax.xml.ws.Holder<String> currencyCode, javax.xml.ws.Holder<String> chargebackTime, javax.xml.ws.Holder<String> chargebackWorkDay, javax.xml.ws.Holder<String> shipTypeCode, javax.xml.ws.Holder<String> subShipType, javax.xml.ws.Holder<String> waybillCode, javax.xml.ws.Holder<String> discount, javax.xml.ws.Holder<java.util.List<OtherFee>> otherFee, javax.xml.ws.Holder<String> originBaseFee, javax.xml.ws.Holder<String> originAddons, javax.xml.ws.Holder<String> stDealFee, javax.xml.ws.Holder<String> stRegFee, javax.xml.ws.Holder<String> feeWeight) {
        LOG.info("Executing operation getFeeByOrderCode");
        System.out.println(headerRequest);
        System.out.println(orderCode.value);
        try {
            String askValue = "";
            ask.value = askValue;
            String sysTimeValue = "";
            sysTime.value = sysTimeValue;
            String msgValue = "";
            msg.value = msgValue;
            String baseFeeValue = "";
            baseFee.value = baseFeeValue;
            String regFeeValue = "";
            regFee.value = regFeeValue;
            String dealFeeValue = "";
            dealFee.value = dealFeeValue;
            String insuranceValue = "";
            insurance.value = insuranceValue;
            String totalFeeValue = "";
            totalFee.value = totalFeeValue;
            String currencyCodeValue = "";
            currencyCode.value = currencyCodeValue;
            String chargebackTimeValue = "";
            chargebackTime.value = chargebackTimeValue;
            String chargebackWorkDayValue = "";
            chargebackWorkDay.value = chargebackWorkDayValue;
            String shipTypeCodeValue = "";
            shipTypeCode.value = shipTypeCodeValue;
            String subShipTypeValue = "";
            subShipType.value = subShipTypeValue;
            String waybillCodeValue = "";
            waybillCode.value = waybillCodeValue;
            String discountValue = "";
            discount.value = discountValue;
            java.util.List<OtherFee> otherFeeValue = null;
            otherFee.value = otherFeeValue;
            String originBaseFeeValue = "";
            originBaseFee.value = originBaseFeeValue;
            String originAddonsValue = "";
            originAddons.value = originAddonsValue;
            String stDealFeeValue = "";
            stDealFee.value = stDealFeeValue;
            String stRegFeeValue = "";
            stRegFee.value = stRegFeeValue;
            String feeWeightValue = "";
            feeWeight.value = feeWeightValue;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#getShipTypes(""GetShipTypesRequest  parameters )*
     */
    public Shiptypes getShipTypes(GetShipTypesRequest parameters) {
        LOG.info("Executing operation getShipTypes");
        System.out.println(parameters);
        try {
            Shiptypes _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#updateOrderStatus(""HeaderRequest  headerRequest ,)""UpdateOrderStatusInfoArray  updateOrderInfo ,)java.lang.String  orderCode ,)java.lang.String  ask ,)java.lang.String  message ,)javax.xml.datatype.XMLGregorianCalendar  time )*
     */
    public void updateOrderStatus(HeaderRequest headerRequest, UpdateOrderStatusInfoArray updateOrderInfo, String orderCode, javax.xml.ws.Holder<String> ask, javax.xml.ws.Holder<String> message, javax.xml.ws.Holder<javax.xml.datatype.XMLGregorianCalendar> time) {
        LOG.info("Executing operation updateOrderStatus");
        System.out.println(headerRequest);
        System.out.println(updateOrderInfo);
        System.out.println(orderCode);
        try {
            String askValue = "";
            ask.value = askValue;
            String messageValue = "";
            message.value = messageValue;
            javax.xml.datatype.XMLGregorianCalendar timeValue = null;
            time.value = timeValue;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#getRatesByType(""HeaderRequest  headerRequest ,)""GetRatesByTypeRequestInfo  ratesRequestInfo )*
     */
    public java.util.List<Rate> getRatesByType(HeaderRequest headerRequest, GetRatesByTypeRequestInfo ratesRequestInfo) {
        LOG.info("Executing operation getRatesByType");
        System.out.println(headerRequest);
        System.out.println(ratesRequestInfo);
        try {
            java.util.List<Rate> _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#getRateByMode(""GetRateByMode  parameters )*
     */
    public Rate getRateByMode(GetRateByMode parameters) {
        LOG.info("Executing operation getRateByMode");
        System.out.println(parameters);
        try {
            Rate _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#getRates(""HeaderRequest  headerRequest ,)""GetRatesRequestInfo  ratesRequestInfo )*
     */
    public java.util.List<Rate> getRates(HeaderRequest headerRequest, GetRatesRequestInfo ratesRequestInfo) {
        LOG.info("Executing operation getRates");
        System.out.println(headerRequest);
        System.out.println(ratesRequestInfo);
        try {
            java.util.List<Rate> _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#addOrder(""AddOrderRequest  parameters )*
     */
    public AddOrderResponse addOrder(AddOrderRequest parameters) {
        LOG.info("Executing operation addOrder");
        System.out.println(parameters);
        try {
            AddOrderResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#updateOrderVolumeWeight(""UpdateOrderVolumeWeightRequest  parameters )*
     */
    public UpdateOrderVolumeWeightResponse updateOrderVolumeWeight(UpdateOrderVolumeWeightRequest parameters) {
        LOG.info("Executing operation updateOrderVolumeWeight");
        System.out.println(parameters);
        try {
            UpdateOrderVolumeWeightResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#searchOrder(""SearchOrderRequest  parameters )*
     */
    public SearchOrderResponse searchOrder(SearchOrderRequest parameters) {
        LOG.info("Executing operation searchOrder");
        System.out.println(parameters);
        try {
            SearchOrderResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#getClassTypes(""GetClassTypesRequest  parameters )*
     */
    public Classtypes getClassTypes(GetClassTypesRequest parameters) {
        LOG.info("Executing operation getClassTypes");
        System.out.println(parameters);
        try {
            Classtypes _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#updateOrder(""HeaderRequest  headerRequest ,)""AddOrderRequestInfoArray  updateOrderInfo ,)java.lang.String  orderCode ,)java.lang.String  ask ,)java.lang.String  message ,)javax.xml.datatype.XMLGregorianCalendar  time ,)java.lang.String  alert )*
     */
    public void updateOrder(HeaderRequest headerRequest, AddOrderRequestInfoArray updateOrderInfo, String orderCode, javax.xml.ws.Holder<String> ask, javax.xml.ws.Holder<String> message, javax.xml.ws.Holder<javax.xml.datatype.XMLGregorianCalendar> time, javax.xml.ws.Holder<String> alert) {
        LOG.info("Executing operation updateOrder");
        System.out.println(headerRequest);
        System.out.println(updateOrderInfo);
        System.out.println(orderCode);
        try {
            String askValue = "";
            ask.value = askValue;
            String messageValue = "";
            message.value = messageValue;
            javax.xml.datatype.XMLGregorianCalendar timeValue = null;
            time.value = timeValue;
            String alertValue = "";
            alert.value = alertValue;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#batchAddOrders(""HeaderRequest  headerRequest ,)java.util.List<""AddOrderRequestInfoArray>  orders ,)java.lang.String  ask ,)java.lang.String  operatingTime ,)java.lang.String  message ,)java.util.List<""AddOrderResponseData>  data )*
     */
    public void batchAddOrders(HeaderRequest headerRequest, java.util.List<AddOrderRequestInfoArray> orders, javax.xml.ws.Holder<String> ask, javax.xml.ws.Holder<String> operatingTime, javax.xml.ws.Holder<String> message, javax.xml.ws.Holder<java.util.List<AddOrderResponseData>> data) {
        LOG.info("Executing operation batchAddOrders");
        System.out.println(headerRequest);
        System.out.println(orders);
        try {
            String askValue = "";
            ask.value = askValue;
            String operatingTimeValue = "";
            operatingTime.value = operatingTimeValue;
            String messageValue = "";
            message.value = messageValue;
            java.util.List<AddOrderResponseData> dataValue = null;
            data.value = dataValue;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ""ShipRate#reSetLabel(""HeaderRequest  headerRequest ,)""ReSetLabelInfoArray  reSetLabelInfo ,)java.lang.String  orderCode ,)java.lang.String  ask ,)java.lang.String  message ,)java.lang.String  trackingNumber ,)javax.xml.datatype.XMLGregorianCalendar  time )*
     */
    public void reSetLabel(HeaderRequest headerRequest, ReSetLabelInfoArray reSetLabelInfo, String orderCode, javax.xml.ws.Holder<String> ask, javax.xml.ws.Holder<String> message, javax.xml.ws.Holder<String> trackingNumber, javax.xml.ws.Holder<javax.xml.datatype.XMLGregorianCalendar> time) {
        LOG.info("Executing operation reSetLabel");
        System.out.println(headerRequest);
        System.out.println(reSetLabelInfo);
        System.out.println(orderCode);
        try {
            String askValue = "";
            ask.value = askValue;
            String messageValue = "";
            message.value = messageValue;
            String trackingNumberValue = "";
            trackingNumber.value = trackingNumberValue;
            javax.xml.datatype.XMLGregorianCalendar timeValue = null;
            time.value = timeValue;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
