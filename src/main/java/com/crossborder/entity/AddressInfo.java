package com.crossborder.entity;

/**
 * Created by s on 2018/3/23.
 */
public class AddressInfo {
    private String name;
    private String company;
    private String phone;
    private String countryCode;
    private String postalCode;
    private String city;
    private String stateOrRegion;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String createUser;
    private String amazonOrderId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrRegion() {
        return stateOrRegion;
    }

    public void setStateOrRegion(String stateOrRegion) {
        this.stateOrRegion = stateOrRegion;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getAmazonOrderId() {
        return amazonOrderId;
    }

    public void setAmazonOrderId(String amazonOrderId) {
        this.amazonOrderId = amazonOrderId;
    }
}
