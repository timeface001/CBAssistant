package com.crossborder.entity;

import java.math.BigDecimal;

public class ProductUploadCategory {
    private String id;

    private String parentId;

    private String name;

    private String hasChild;

    private BigDecimal childCount;

    private String typeDef;

    private String countryCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild == null ? null : hasChild.trim();
    }

    public BigDecimal getChildCount() {
        return childCount;
    }

    public void setChildCount(BigDecimal childCount) {
        this.childCount = childCount;
    }

    public String getTypeDef() {
        return typeDef;
    }

    public void setTypeDef(String typeDef) {
        this.typeDef = typeDef == null ? null : typeDef.trim();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }
}