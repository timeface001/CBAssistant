package com.crossborder.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class CommonSet {

    private String productImagePath;

    private String amzUploadProductPath;

    public String getProductImagePath() {
        return productImagePath;
    }

    @Value("${productImagePath}")
    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }

    public String getAmzUploadProductPath() {
        return amzUploadProductPath;
    }

    @Value("${amzUploadProductPath}")
    public void setAmzUploadProductPath(String amzUploadProductPath) {
        this.amzUploadProductPath = amzUploadProductPath;
    }
}
