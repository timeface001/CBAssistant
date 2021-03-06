package com.crossborder.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class CommonSet {

    private String productImagePath;

    private String amzUploadProductPath;

    private String productCategoryPath;


    public String getProductCategoryPath() {
        return productCategoryPath;
    }

    @Value("${productCategoryPath}")
    public void setProductCategoryPath(String productCategoryPath) {
        this.productCategoryPath = productCategoryPath;
    }

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
