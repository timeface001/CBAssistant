package com.crossborder.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UploadLog {
    private Integer id;

    private String productId;

    private String uploadMsg;

    private BigDecimal status;

    private String statusDesc;

    private Date ctime;

    private Date utime;

    private String faildMsg;

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

    public String getUploadMsg() {
        return uploadMsg;
    }

    public void setUploadMsg(String uploadMsg) {
        this.uploadMsg = uploadMsg == null ? null : uploadMsg.trim();
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc == null ? null : statusDesc.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public String getFaildMsg() {
        return faildMsg;
    }

    public void setFaildMsg(String faildMsg) {
        this.faildMsg = faildMsg == null ? null : faildMsg.trim();
    }
}