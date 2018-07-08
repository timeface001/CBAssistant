package com.crossborder.entity;

import java.util.List;

/**
 * Created by s on 2018/3/23.
 */
public class LocalOrder {
    private int id;
    private String amazonOrderId;
    private String sellerOrderId;
    private String orderStatus;
    private String localStatus;
    private String purchaseDate;
    private String lastUpdateDate;
    private String updateTime;
    private String fulfillmentChannel;
    private int numberOfItemsShipped;
    private int numberOfItemsUnshipped;
    private String paymentMethod;
    private String marketplaceId;
    private String buyerName;
    private String buyerCounty;
    private String runningTime;
    private String salesMan;
    private String salesCompany;
    private String salesSource;
    private String transportCompany;
    private String intlTrackNum;
    private double orderTotal;
    private String currencyCode;
    private String orderType;
    private double shippingPrice;
    private double commission;
    private List<LocalOrderItem> localOrderItemList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmazonOrderId() {
        return amazonOrderId;
    }

    public void setAmazonOrderId(String amazonOrderId) {
        this.amazonOrderId = amazonOrderId;
    }

    public String getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(String sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getFulfillmentChannel() {
        return fulfillmentChannel;
    }

    public void setFulfillmentChannel(String fulfillmentChannel) {
        this.fulfillmentChannel = fulfillmentChannel;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getNumberOfItemsShipped() {
        return numberOfItemsShipped;
    }

    public void setNumberOfItemsShipped(int numberOfItemsShipped) {
        this.numberOfItemsShipped = numberOfItemsShipped;
    }

    public int getNumberOfItemsUnshipped() {
        return numberOfItemsUnshipped;
    }

    public void setNumberOfItemsUnshipped(int numberOfItemsUnshipped) {
        this.numberOfItemsUnshipped = numberOfItemsUnshipped;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerCounty() {
        return buyerCounty;
    }

    public void setBuyerCounty(String buyerCounty) {
        this.buyerCounty = buyerCounty;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public String getSalesCompany() {
        return salesCompany;
    }

    public void setSalesCompany(String salesCompany) {
        this.salesCompany = salesCompany;
    }

    public String getSalesSource() {
        return salesSource;
    }

    public void setSalesSource(String salesSource) {
        this.salesSource = salesSource;
    }

    public String getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(String transportCompany) {
        this.transportCompany = transportCompany;
    }

    public String getIntlTrackNum() {
        return intlTrackNum;
    }

    public void setIntlTrackNum(String intlTrackNum) {
        this.intlTrackNum = intlTrackNum;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getLocalStatus() {
        return localStatus;
    }

    public void setLocalStatus(String localStatus) {
        this.localStatus = localStatus;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public List<LocalOrderItem> getLocalOrderItemList() {
        return localOrderItemList;
    }

    public void setLocalOrderItemList(List<LocalOrderItem> localOrderItemList) {
        this.localOrderItemList = localOrderItemList;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
