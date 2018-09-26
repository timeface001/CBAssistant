package com.crossborder.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductUploadValues {
    private String id;

    private String typeValue;

    private String displayName;

    private BigDecimal status;

    private Date ctime;

    private String necValues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue == null ? null : typeValue.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getNecValues() {
        return necValues;
    }

    public void setNecValues(String necValues) {
        this.necValues = necValues == null ? null : necValues.trim();
    }
}