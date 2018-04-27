package com.crossborder.entity;

/**
 * Created by s on 2018/4/27.
 */
public class ApplicationInfo {
    private String ApplicationName;
    private String HSCode;
    private int Qty;//申报数量,必填一项,打印CN22用
    private double UnitPrice;//申报价格,单位USD,必填一项,打印CN22用
    private double UnitWeight;//申报重量，单位kg,打印CN22用
    private String PickingName;
    private String Remark;
    private String ProductUrl;
    private String SKU;

    public String getApplicationName() {
        return ApplicationName;
    }

    public void setApplicationName(String applicationName) {
        ApplicationName = applicationName;
    }

    public String getHSCode() {
        return HSCode;
    }

    public void setHSCode(String HSCode) {
        this.HSCode = HSCode;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getUnitWeight() {
        return UnitWeight;
    }

    public void setUnitWeight(double unitWeight) {
        UnitWeight = unitWeight;
    }

    public String getPickingName() {
        return PickingName;
    }

    public void setPickingName(String pickingName) {
        PickingName = pickingName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getProductUrl() {
        return ProductUrl;
    }

    public void setProductUrl(String productUrl) {
        ProductUrl = productUrl;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }
}
