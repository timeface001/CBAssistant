package com.crossborder.utils.amz.upload;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class UploadItem {
    private String productStr;
    private String imageStr;
    private String priceStr;
    private String inventoryStr;
    private String relationsStr;
    private Map<String, Object> shop;

    public Map<String, Object> getShop() {
        return shop;
    }

    public void setShop(Map<String, Object> shop) {
        this.shop = shop;
    }

    public String getProductStr() {
        return AmzXmlTemplate.addUploadProductStrHead(productStr, getShop());
        //return productStr;
    }

    public void setProductStr(String productStr) {
        this.productStr = this.productStr + productStr;
    }

    public String getImageStr() {
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

    public String getInventoryStr() {
        return AmzXmlTemplate.addUploadInventoryStrHead(inventoryStr, getShop());
    }

    public void setInventoryStr(String inventoryStr) {
        this.inventoryStr = this.inventoryStr + inventoryStr;
    }

    public boolean isRelationEmpty() {
        return StringUtils.isEmpty(this.relationsStr);
    }

    public String getRelationsStr() {
        return AmzXmlTemplate.addUploadRelationsStrHead(relationsStr, getShop());
    }

    public void setRelationsStr(String relationsStr) {
        this.relationsStr = this.relationsStr + relationsStr;
    }

    public UploadItem() {
        this.imageStr = "";
        this.productStr = "";
        this.inventoryStr = "";
        this.priceStr = "";
        this.relationsStr = "";
    }
}
