package com.crossborder.utils.amz.upload;

import com.crossborder.entity.ProductAmzUpload;

import java.math.BigDecimal;
import java.util.*;

public class UploadServiceRequest {

    private List<ProductAmzUpload> products;

    private Set<String> marketIds;

    private ShopReq shop;

    private Map<String, ShopReq> exrateMap;

    private Map<String, List<ProductAmzUpload>> exrateList;

    public UploadServiceRequest(Map<String, Object> shop) {
        this.shop = new ShopReq();
        this.marketIds = new HashSet<>();
        this.exrateMap = new HashMap<>();
        this.exrateList = new HashMap<>();
        this.products=new ArrayList<>();
        initShop(shop);
    }

    public boolean add(Map<String, Object> shop, ProductAmzUpload product) {
        if (isContains(shop)) {



            String key = shop.get("CURRENCYCODE").toString();
            String marketId = shop.get("MARKETPLACEID").toString();
            this.marketIds.add(marketId);


            if (!exrateMap.containsKey(key)) {
                ShopReq mid = new ShopReq();
                mid.setMarketIds(new HashSet<String>(Arrays.asList(marketId)));
                mid.setExrate(new BigDecimal(shop.get("EXRATE").toString()));
                mid.setCurrency(key);
                exrateMap.put(key, mid);
            } else {
                ShopReq mid = exrateMap.get(key);
                mid.getMarketIds().add(marketId);
            }

            if (exrateList.containsKey(key)) {
                List<ProductAmzUpload> list = exrateList.get(key);
                boolean isContains = false;
                for (ProductAmzUpload s : list) {
                    if (s.getProductAmzId().equals(product.getProductAmzId())) {
                        isContains = true;
                    }
                }
                if (!isContains) {
                    list.add(product);

                }
            } else {
                exrateList.put(key, new ArrayList<ProductAmzUpload>(Arrays.asList(product)));
            }

            if (isCanAddProduct(product)) {
                products.add(product);
            }


            return true;
        }

        return false;

    }

    private boolean isCanAddProduct(ProductAmzUpload product) {
        if (this.products.size() == 0) {
            return true;
        }

        for (ProductAmzUpload pp : this.products) {
            if (pp.getProductAmzId().equals(product.getProductAmzId())) {
                return false;
            }
        }

        return true;
    }

    private void initShop(Map<String, Object> shop) {
        String accessKeyId = shop.get("ACCESSKEY_ID").toString();
        String secretAccessKey = shop.get("SECRET_KEY").toString();
        String serviceUrl = shop.get("ENDPOINT").toString();
        String merchantId = shop.get("MERCHANT_ID").toString();
        String language = shop.get("LANGUAGE").toString();


        this.shop.setAccessKey(accessKeyId);
        this.shop.setSecretKey(secretAccessKey);
        this.shop.setServiceUrl(serviceUrl);
        this.shop.setMerchantId(merchantId);
        this.shop.setLanguage(language);

    }

    public boolean isContains(Map<String, Object> shop) {
        if (this.shop == null) {
            return false;
        }

        String accessKeyId = shop.get("ACCESSKEY_ID").toString();
        String secretAccessKey = shop.get("SECRET_KEY").toString();
        String serviceUrl = shop.get("ENDPOINT").toString();
        String merchantId = shop.get("MERCHANT_ID").toString();
        String language = shop.get("LANGUAGE").toString();


        return language.equals(this.shop.getLanguage()) && merchantId.equals(this.shop.getMerchantId()) && accessKeyId.equals(this.shop.getAccessKey()) && secretAccessKey.equals(this.shop.getSecretKey()) && serviceUrl.equals(this.shop.getServiceUrl());
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
        return new ArrayList<>(marketIds);
    }

    public void setMarketIds(Set<String> marketIds) {
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
        private Set<String> marketIds;
        private String language;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Set<String> getMarketIds() {
            return marketIds;
        }

        public void setMarketIds(Set<String> marketIds) {
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
