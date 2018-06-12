package com.crossborder.utils.amz.upload;

import com.crossborder.entity.ProductAmzUpload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UploadRequest {

    private Map<String, ProductAmzUpload> skuMap;

    private Map<String, Object> shop;

    private List<String> submitIds;

    private List<ProductAmzUpload> products;

    public List<ProductAmzUpload> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAmzUpload> products) {
        this.products = products;
    }

    public void setProductId(ProductAmzUpload productId) {
        this.products.add(productId);
    }

    public List<String> getSubmitIds() {
        return submitIds;
    }

    public void setSubmitIds(List<String> submitIds) {
        this.submitIds = submitIds;
    }

    public UploadRequest() {
        this.products = new ArrayList<>();
    }

    public Map<String, ProductAmzUpload> getSkuMap() {
        return skuMap;
    }

    public void setSkuMap(Map<String, ProductAmzUpload> skuMap) {
        this.skuMap = skuMap;
    }

    public Map<String, Object> getShop() {
        return shop;
    }

    public void setShop(Map<String, Object> shop) {
        this.shop = shop;
    }

    public ProductAmzUpload getUploadResponse(String sku) {
        return this.skuMap.containsKey(sku) ? skuMap.get(sku) : new ProductAmzUpload();
    }

    /*public List<String> getSubmitIds() throws Exception {
        if (this.submitMap == null || this.submitMap.size() == 0) {
            throw new Exception("submitId为空");
        }

        List<String> submitIds = new ArrayList<>();
        for (Map.Entry<String, ProductAmzUpload> entry : submitMap.entrySet()) {
            submitIds.add(entry.getKey());
        }

        return submitIds;
    }*/
}
