package com.crossborder.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class ClaimProduct {
    protected String id;

    protected String sku;

    protected String createUser;

    protected BigDecimal price;

    protected Integer quantity;

    protected String imagePath;

    protected String itemUk;

    protected String itemFr;

    protected String itemDe;

    protected String itemIt;

    protected String itemEs;

    protected String itemJp;

    protected String itemCn;

    protected String bulletPointUk;

    protected String bulletPointFr;

    protected String bulletPointDe;

    protected String bulletPointIt;

    protected String bulletPointEs;

    protected String bulletPointJp;

    protected String bulletPointCn;

    protected String keywordsUk;

    protected String keywordsFr;

    protected String keywordsDe;

    protected String keywordsIt;

    protected String keywordsEs;

    protected String keywordsJp;

    protected String keywordsCn;

    protected String productDescriptionUk;

    protected String productDescriptionFr;

    protected String productDescriptionDe;

    protected String productDescriptionIt;

    protected String productDescriptionEs;

    protected String productDescriptionJp;

    protected String productDescriptionCn;

    protected Timestamp createTime;

    protected Timestamp updateTime;

    protected String productId;

    protected String updateState;

    protected String skuType;

    protected String source;

    protected String typeId;

    protected String typeName;

    protected String userName;

    protected String isPrepublish;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getItemUk() {
        return itemUk;
    }

    public void setItemUk(String itemUk) {
        this.itemUk = itemUk == null ? null : itemUk.trim();
    }

    public String getItemFr() {
        return itemFr;
    }

    public void setItemFr(String itemFr) {
        this.itemFr = itemFr == null ? null : itemFr.trim();
    }

    public String getItemDe() {
        return itemDe;
    }

    public void setItemDe(String itemDe) {
        this.itemDe = itemDe == null ? null : itemDe.trim();
    }

    public String getItemIt() {
        return itemIt;
    }

    public void setItemIt(String itemIt) {
        this.itemIt = itemIt == null ? null : itemIt.trim();
    }

    public String getItemEs() {
        return itemEs;
    }

    public void setItemEs(String itemEs) {
        this.itemEs = itemEs == null ? null : itemEs.trim();
    }

    public String getItemJp() {
        return itemJp;
    }

    public void setItemJp(String itemJp) {
        this.itemJp = itemJp == null ? null : itemJp.trim();
    }

    public String getItemCn() {
        return itemCn;
    }

    public void setItemCn(String itemCn) {
        this.itemCn = itemCn == null ? null : itemCn.trim();
    }

    public String getBulletPointUk() {
        return bulletPointUk;
    }

    public void setBulletPointUk(String bulletPointUk) {
        this.bulletPointUk = bulletPointUk == null ? null : bulletPointUk.trim();
    }

    public String getBulletPointFr() {
        return bulletPointFr;
    }

    public void setBulletPointFr(String bulletPointFr) {
        this.bulletPointFr = bulletPointFr == null ? null : bulletPointFr.trim();
    }

    public String getBulletPointDe() {
        return bulletPointDe;
    }

    public void setBulletPointDe(String bulletPointDe) {
        this.bulletPointDe = bulletPointDe == null ? null : bulletPointDe.trim();
    }

    public String getBulletPointIt() {
        return bulletPointIt;
    }

    public void setBulletPointIt(String bulletPointIt) {
        this.bulletPointIt = bulletPointIt == null ? null : bulletPointIt.trim();
    }

    public String getBulletPointEs() {
        return bulletPointEs;
    }

    public void setBulletPointEs(String bulletPointEs) {
        this.bulletPointEs = bulletPointEs == null ? null : bulletPointEs.trim();
    }

    public String getBulletPointJp() {
        return bulletPointJp;
    }

    public void setBulletPointJp(String bulletPointJp) {
        this.bulletPointJp = bulletPointJp == null ? null : bulletPointJp.trim();
    }

    public String getBulletPointCn() {
        return bulletPointCn;
    }

    public void setBulletPointCn(String bulletPointCn) {
        this.bulletPointCn = bulletPointCn == null ? null : bulletPointCn.trim();
    }

    public String getKeywordsUk() {
        return keywordsUk;
    }

    public void setKeywordsUk(String keywordsUk) {
        this.keywordsUk = keywordsUk == null ? null : keywordsUk.trim();
    }

    public String getKeywordsFr() {
        return keywordsFr;
    }

    public void setKeywordsFr(String keywordsFr) {
        this.keywordsFr = keywordsFr == null ? null : keywordsFr.trim();
    }

    public String getKeywordsDe() {
        return keywordsDe;
    }

    public void setKeywordsDe(String keywordsDe) {
        this.keywordsDe = keywordsDe == null ? null : keywordsDe.trim();
    }

    public String getKeywordsIt() {
        return keywordsIt;
    }

    public void setKeywordsIt(String keywordsIt) {
        this.keywordsIt = keywordsIt == null ? null : keywordsIt.trim();
    }

    public String getKeywordsEs() {
        return keywordsEs;
    }

    public void setKeywordsEs(String keywordsEs) {
        this.keywordsEs = keywordsEs == null ? null : keywordsEs.trim();
    }

    public String getKeywordsJp() {
        return keywordsJp;
    }

    public void setKeywordsJp(String keywordsJp) {
        this.keywordsJp = keywordsJp == null ? null : keywordsJp.trim();
    }

    public String getKeywordsCn() {
        return keywordsCn;
    }

    public void setKeywordsCn(String keywordsCn) {
        this.keywordsCn = keywordsCn == null ? null : keywordsCn.trim();
    }

    public String getProductDescriptionUk() {
        return productDescriptionUk;
    }

    public void setProductDescriptionUk(String productDescriptionUk) {
        this.productDescriptionUk = productDescriptionUk == null ? null : productDescriptionUk.trim();
    }

    public String getProductDescriptionFr() {
        return productDescriptionFr;
    }

    public void setProductDescriptionFr(String productDescriptionFr) {
        this.productDescriptionFr = productDescriptionFr == null ? null : productDescriptionFr.trim();
    }

    public String getProductDescriptionDe() {
        return productDescriptionDe;
    }

    public void setProductDescriptionDe(String productDescriptionDe) {
        this.productDescriptionDe = productDescriptionDe == null ? null : productDescriptionDe.trim();
    }

    public String getProductDescriptionIt() {
        return productDescriptionIt;
    }

    public void setProductDescriptionIt(String productDescriptionIt) {
        this.productDescriptionIt = productDescriptionIt == null ? null : productDescriptionIt.trim();
    }

    public String getProductDescriptionEs() {
        return productDescriptionEs;
    }

    public void setProductDescriptionEs(String productDescriptionEs) {
        this.productDescriptionEs = productDescriptionEs == null ? null : productDescriptionEs.trim();
    }

    public String getProductDescriptionJp() {
        return productDescriptionJp;
    }

    public void setProductDescriptionJp(String productDescriptionJp) {
        this.productDescriptionJp = productDescriptionJp == null ? null : productDescriptionJp.trim();
    }

    public String getProductDescriptionCn() {
        return productDescriptionCn;
    }

    public void setProductDescriptionCn(String productDescriptionCn) {
        this.productDescriptionCn = productDescriptionCn == null ? null : productDescriptionCn.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getUpdateState() {
        return updateState;
    }

    public void setUpdateState(String updateState) {
        this.updateState = updateState == null ? null : updateState.trim();
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType == null ? null : skuType.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getIsPrepublish() {
        return isPrepublish;
    }

    public void setIsPrepublish(String isPrepublish) {
        this.isPrepublish = isPrepublish == null ? null : isPrepublish.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}