package com.crossborder.entity;

import java.math.BigDecimal;

public class ProductItemVarType {
    private String variationType;

    private String variationName;

    private String columnName;

    private String state;

    private BigDecimal ord;

    public String getVariationType() {
        return variationType;
    }

    public void setVariationType(String variationType) {
        this.variationType = variationType == null ? null : variationType.trim();
    }

    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName == null ? null : variationName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public BigDecimal getOrd() {
        return ord;
    }

    public void setOrd(BigDecimal ord) {
        this.ord = ord;
    }
}