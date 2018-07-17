package com.crossborder.utils.amz.upload;

import com.crossborder.entity.ProductAmzUpload;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class UploadItem {
    private String productStr;
    private String imageStr;
    private String priceStr;
    private String inventoryStr;
    private String relationsStr;
    private UploadServiceRequest.ShopReq shop;
    private Map<String,ProductAmzUpload> productMap;

    private List<ProductAmzUpload> products;

    public List<ProductAmzUpload> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAmzUpload> products) {
        this.products = products;
    }

    public Map<String, ProductAmzUpload> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<String, ProductAmzUpload> productMap) {
        this.productMap = productMap;
    }

    public UploadServiceRequest.ShopReq getShop() {
        return shop;
    }

    public void setShop(UploadServiceRequest.ShopReq shop) {
        this.shop = shop;
    }

    public String getProductStrHead() {
        return AmzXmlTemplate.addUploadProductStrHead(productStr, getShop());
        //return productStr;
    }

    public void setProductStr(String productStr) {
        this.productStr = this.productStr + productStr;
    }

    public String getImageStrHead() {
        return AmzXmlTemplate.addUploadImageStrHead(imageStr, getShop());
    }

    public void setImageStr(String imageStr) {
        this.imageStr = this.imageStr + imageStr;
    }

    public String getPriceStr() {
        return AmzXmlTemplate.addUploadPriceStrHead(priceStr, getShop());
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = this.priceStr + priceStr;
    }

    public String getInventoryStrHead() {
        return AmzXmlTemplate.addUploadInventoryStrHead(inventoryStr, getShop());
    }

    public void setInventoryStr(String inventoryStr) {
        this.inventoryStr = this.inventoryStr + inventoryStr;
    }

    public boolean isRelationEmpty() {
        return StringUtils.isEmpty(this.relationsStr);
    }

    public String getRelationsStrHead() {
        return AmzXmlTemplate.addUploadRelationsStrHead(relationsStr, getShop());
    }

    public void setRelationsStr(String relationsStr) {
        this.relationsStr = this.relationsStr + relationsStr;
    }

    public String getProductStr() {
        return productStr;
    }

    public String getImageStr() {
        return imageStr;
    }

    public String getInventoryStr() {
        return inventoryStr;
    }

    public String getRelationsStr() {
        return relationsStr;
    }

    public UploadItem() {
        this.imageStr = "";
        this.productStr = "";
        this.inventoryStr = "";
        this.priceStr = "";
        this.relationsStr = "";
    }
}
