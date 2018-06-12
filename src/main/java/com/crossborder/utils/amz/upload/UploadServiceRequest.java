package com.crossborder.utils.amz.upload;

import com.crossborder.entity.ProductAmzUpload;

import java.math.BigDecimal;
import java.util.*;

public class UploadServiceRequest {

    private List<ProductAmzUpload> products;

    private List<String> marketIds;

    private ShopReq shop;

    private Map<String, ShopReq> exrateMap;

    private Map<String, List<ProductAmzUpload>> exrateList;

    public UploadServiceRequest(Map<String, Object> shop) {
        this.shop = new ShopReq();
        this.marketIds = new ArrayList<>();
        this.exrateMap = new HashMap<>();
        this.exrateList = new HashMap<>();
        this.products=new ArrayList<>();
        initShop(shop);
    }

    public boolean add(Map<String, Object> shop, ProductAmzUpload product) {
        if (isContains(shop)) {

            products.add(product);


            String key = shop.get("CURRENCYCODE").toString();
            String marketId = shop.get("MARKETPLACEID").toString();
            this.marketIds.add(marketId);

            if (!exrateMap.containsKey(key)) {
                ShopReq mid = new ShopReq();
                mid.setMarketIds(Arrays.asList(marketId));
                mid.setExrate(new BigDecimal(shop.get("EXRATE").toString()));
                mid.setCurrency(key);
                exrateMap.put(key, mid);
            } else {
                ShopReq mid = exrateMap.get(key);
                mid.getMarketIds().add(marketId);
            }

            if (exrateList.containsKey(key)) {
                exrateList.get(key).add(product);
            } else {
                exrateList.put(key, Arrays.asList(product));
            }

            return true;
        }

        return false;

    }

    private void initShop(Map<String, Object> shop) {
        String accessKeyId = shop.get("ACCESSKEY_ID").toString();
        String secretAccessKey = shop.get("SECRET_KEY").toString();
        String serviceUrl = shop.get("ENDPOINT").toString();
        String merchantId = shop.get("MERCHANT_ID").toString();


        this.shop.setAccessKey(accessKeyId);
        this.shop.setSecretKey(secretAccessKey);
        this.shop.setServiceUrl(serviceUrl);
        this.shop.setMerchantId(merchantId);

    }

    public boolean isContains(Map<String, Object> shop) {
        if (this.shop == null) {
            return false;
        }

        String accessKeyId = shop.get("ACCESSKEY_ID").toString();
        String secretAccessKey = shop.get("SECRET_KEY").toString();
        String serviceUrl = shop.get("ENDPOINT").toString();
        String merchantId = shop.get("MERCHANT_ID").toString();


        return merchantId.equals(this.shop.getMerchantId()) && accessKeyId.equals(this.shop.getAccessKey()) && secretAccessKey.equals(this.shop.getSecretKey()) && serviceUrl.equals(this.shop.getServiceUrl());
    }


    public Map<String, ShopReq> getExrateMap() {
        return exrateMap;
    }

    public void setExrateMap(Map<String, ShopReq> exrateMap) {
        this.exrateMap = exrateMap;
    }

    public Map<String, List<ProductAmzUpload>> getExrateList() {
        return exrateList;
    }

    public void setExrateList(Map<String, List<ProductAmzUpload>> exrateList) {
        this.exrateList = exrateList;
    }

    public ShopReq getShop() {
        return shop;
    }

    public void setShop(ShopReq shop) {
        this.shop = shop;
    }

    public List<ProductAmzUpload> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAmzUpload> products) {
        this.products = products;
    }

    public List<String> getMarketIds() {
        return marketIds;
    }

    public void setMarketIds(List<String> marketIds) {
        this.marketIds = marketIds;
    }


    public class ShopReq {
        private String accessKey;
        private String secretKey;
        private String merchantId;
        private String countryCode;
        private String shopId;
        private String serviceUrl;
        private String countryId;
        private BigDecimal exrate;
        private String currency;
        private List<String> marketIds;

        public List<String> getMarketIds() {
            return marketIds;
        }

        public void setMarketIds(List<String> marketIds) {
            this.marketIds = marketIds;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getServiceUrl() {
            return serviceUrl;
        }

        public void setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public BigDecimal getExrate() {
            return exrate;
        }

        public void setExrate(BigDecimal exrate) {
            this.exrate = exrate;
        }
    }
}
