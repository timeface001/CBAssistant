package com.crossborder.entity;

/**
 * Created by s on 2018/4/27.
 */
public class WayBill {
    private String WayBillNumber;
    private String TrackingNumber;
    private String OrderNumber;
    private String TransactionNumber;
    private int Length;
    private int Width;
    private int Height;
    private int PackageNumber;
    private double Weight;
    private int ApplicationType;
    private int IsReturn;//是否退回,包裹无人签收时是否退回，1-退回，0-不退回，默认0
    private int EnableTariffPrepay;//关税预付服务费，1-参加关税预付，0-不参加关税预付，默认0
    private int InsuranceType;//包裹投保类型，0-不参保，1-按件，2-按比例，默认0，表示不参加运输保险
    private double InsureAmount;//保险的最高额度，单位RMB
    private int SensitiveTypeID;
    private String SourceCode;
    private ShippingInfo shippingInfo;
    private SenderInfo senderInfo;

    public String getWayBillNumber() {
        return WayBillNumber;
    }

    public void setWayBillNumber(String wayBillNumber) {
        WayBillNumber = wayBillNumber;
    }

    public String getTrackingNumber() {
        return TrackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        TrackingNumber = trackingNumber;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getTransactionNumber() {
        return TransactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        TransactionNumber = transactionNumber;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getPackageNumber() {
        return PackageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        PackageNumber = packageNumber;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public int getApplicationType() {
        return ApplicationType;
    }

    public void setApplicationType(int applicationType) {
        ApplicationType = applicationType;
    }

    public int getIsReturn() {
        return IsReturn;
    }

    public void setIsReturn(int isReturn) {
        IsReturn = isReturn;
    }

    public int getEnableTariffPrepay() {
        return EnableTariffPrepay;
    }

    public void setEnableTariffPrepay(int enableTariffPrepay) {
        EnableTariffPrepay = enableTariffPrepay;
    }

    public int getInsuranceType() {
        return InsuranceType;
    }

    public void setInsuranceType(int insuranceType) {
        InsuranceType = insuranceType;
    }

    public double getInsureAmount() {
        return InsureAmount;
    }

    public void setInsureAmount(double insureAmount) {
        InsureAmount = insureAmount;
    }

    public int getSensitiveTypeID() {
        return SensitiveTypeID;
    }

    public void setSensitiveTypeID(int sensitiveTypeID) {
        SensitiveTypeID = sensitiveTypeID;
    }

    public String getSourceCode() {
        return SourceCode;
    }

    public void setSourceCode(String sourceCode) {
        SourceCode = sourceCode;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }
}
