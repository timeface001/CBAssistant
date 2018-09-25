package com.crossborder.utils.amz.upload;

import com.crossborder.entity.ProductAmzUpload;

import java.math.BigDecimal;
import java.util.*;

public class UploadServiceRequest {

    private List<ProductAmzUpload> products;

    private Set<String> marketIds;

    private ShopReq shop;


    private Map<String, SplitRequest> exrateList;

    private Map<String, SplitRequest> languageList;

    private List<String> updateIds;


    public UploadServiceRequest(Map<String, Object> shop) {
        this.shop = initShop(shop);
        this.marketIds = new HashSet<>();
        this.exrateList = new HashMap<>();
        this.products=new ArrayList<>();
        this.languageList = new HashMap<>();
        this.updateIds=new ArrayList<>();
    }

    public boolean add(Map<String, Object> shop, ProductAmzUpload product) {
        if (isContains(shop)) {

            String key = shop.get("CURRENCYCODE").toString();
            String marketId = shop.get("MARKETPLACEID").toString();
            this.marketIds.add(marketId);



            if (exrateList.containsKey(key)) {
                exrateList.get(key).add(product);
                exrateList.get(key).getShopReq().getMarketIds().add(marketId);
            } else {

                ShopReq mid = initShop(shop);
                mid.setMarketIds(new HashSet<String>(Arrays.asList(marketId)));
                mid.setExrate(new BigDecimal(shop.get("EXRATE").toString()));
                mid.setCurrency(key);

                exrateList.put(key, new SplitRequest(new ArrayList<ProductAmzUpload>(Arrays.asList(product)), mid));
            }

            String language = shop.get("LANGUAGE").toString();
            if (languageList.containsKey(language)) {
                languageList.get(language).add(product);
                languageList.get(language).getShopReq().getMarketIds().add(marketId);
            } else {

                ShopReq mid = initShop(shop);
                mid.setMarketIds(new HashSet<String>(Arrays.asList(marketId)));
                languageList.put(language, new SplitRequest(new ArrayList<ProductAmzUpload>(Arrays.asList(product)), mid));
            }
            products.add(product);
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

    private ShopReq initShop(Map<String, Object> shop) {
        ShopReq result = new ShopReq();
        String accessKeyId = shop.get("ACCESSKEY_ID").toString();
        String secretAccessKey = shop.get("SECRET_KEY").toString();
        String serviceUrl = shop.get("ENDPOINT").toString();
        String merchantId = shop.get("MERCHANT_ID").toString();
        String language = shop.get("LANGUAGE").toString();
        String rate = (shop.get("EXRATE").toString());
        String authToken = shop.get("MWSAUTHTOKEN")==null?null:shop.get("MWSAUTHTOKEN").toString();


        result.setAccessKey(accessKeyId);
        result.setSecretKey(secretAccessKey);
        result.setServiceUrl(serviceUrl);
        result.setMerchantId(merchantId);
        result.setLanguage(language);
        result.setExrate(new BigDecimal(rate));
        result.setAuthToken(authToken);
        return result;

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


        return merchantId.equals(this.shop.getMerchantId()) && accessKeyId.equals(this.shop.getAccessKey()) && secretAccessKey.equals(this.shop.getSecretKey()) && serviceUrl.equals(this.shop.getServiceUrl());
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

    public Map<String, SplitRequest> getExrateList() {
        return exrateList;
    }

    public void setExrateList(Map<String, SplitRequest> exrateList) {
        this.exrateList = exrateList;
    }

    public Map<String, SplitRequest> getLanguageList() {
        return languageList;
    }

    public List<String> getUpdateIds() {
        return updateIds;
    }

    public void setUpdateIds(List<String> updateIds) {
        this.updateIds = updateIds;
    }

    public void setLanguageList(Map<String, SplitRequest> languageList) {
        this.languageList = languageList;
    }

    public class ShopReq {
        private String accessKey;
        private String secretKey;
        private String merchantId;
        private String authToken;
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

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }
    }

    public class SplitRequest {
        private List<ProductAmzUpload> list;

        private ShopReq shopReq;

        public SplitRequest() {
            this.list = new ArrayList<>();

            shop = new ShopReq();
        }

        public void add(ProductAmzUpload product) {
            if (list == null) {
                list = new ArrayList<>();
                list.add(product);
            }

            if (!isContains(product)) {

                list.add(product);
            }
        }

        public boolean isContains(ProductAmzUpload product) {
            boolean isContains = false;
            for (ProductAmzUpload s : list) {
                if (s.getProductAmzId().equals(product.getProductAmzId())) {
                    isContains = true;
                }
            }

            return isContains;
        }

        public SplitRequest(List<ProductAmzUpload> list, ShopReq shopReq) {
            this.list = list;
            this.shopReq = shopReq;
        }

        public List<ProductAmzUpload> getList() {
            return list;
        }

        public void setList(List<ProductAmzUpload> list) {
            this.list = list;
        }

        public ShopReq getShopReq() {
            return shopReq;
        }

        public void setShopReq(ShopReq shopReq) {
            this.shopReq = shopReq;
        }

    }
}
