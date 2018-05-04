package com.crossborder.entity;

import java.util.Date;

public class ProductIdGen {
    private Integer id;

    private String productId;

    private String type;

    private String useStatus;

    private Date createTime;

    private String amzUploadId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus == null ? null : useStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAmzUploadId() {
        return amzUploadId;
    }

    public void setAmzUploadId(String amzUploadId) {
        this.amzUploadId = amzUploadId == null ? null : amzUploadId.trim();
    }
}