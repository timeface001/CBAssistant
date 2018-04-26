package com.crossborder.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductItemVar {
    private String id;

    private String sku;

    private String variationType;

    private String colorName;

    private String colorMap;

    private String sizeName;

    private String sizeMap;

    private String materialType;

    private BigDecimal itemPackageQuantity;

    private String productId;

    private BigDecimal price;

    private BigDecimal salePrice;

    private Date saleStartTime;

    private Date saleEndTime;

    private BigDecimal quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getVariationType() {
        return variationType;
    }

    public void setVariationType(String variationType) {
        this.variationType = variationType == null ? null : variationType.trim();
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName == null ? null : colorName.trim();
    }

    public String getColorMap() {
        return colorMap;
    }

    public void setColorMap(String colorMap) {
        this.colorMap = colorMap == null ? null : colorMap.trim();
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName == null ? null : sizeName.trim();
    }

    public String getSizeMap() {
        return sizeMap;
    }

    public void setSizeMap(String sizeMap) {
        this.sizeMap = sizeMap == null ? null : sizeMap.trim();
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType == null ? null : materialType.trim();
    }

    public BigDecimal getItemPackageQuantity() {
        return itemPackageQuantity;
    }

    public void setItemPackageQuantity(BigDecimal itemPackageQuantity) {
        this.itemPackageQuantity = itemPackageQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Date getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(Date saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    public Date getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}