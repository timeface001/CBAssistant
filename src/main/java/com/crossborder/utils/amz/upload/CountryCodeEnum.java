package com.crossborder.utils.amz.upload;

public enum CountryCodeEnum {


    US("US"), CA("CA"), MX("MX"), JP("JP"), CN("CN"), GB("GB"), DE("DE"), FR("FR"), IT("IT"), ES("ES");

    CountryCodeEnum(String val) {
        this.val = val;
    }

    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public boolean equal(String val) {
        return this.val.equals(val);
    }
}
