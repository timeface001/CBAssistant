package com.crossborder.utils.amz.upload;

public enum CountryCodeEnum {


    US("US"), CA("CA"), MX("MX"), JP("JP"), CN("CN"), GB("GB"), DE("DE"), FR("FR"), IT("IT"), ES("ES"),AU("AU");

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

    public static CountryCodeEnum sign(String str) {
        if ("英语".equals(str)) {
            return GB;
        }

        if ("西班牙语".equals(str)) {
            return ES;
        }

        if ("德语".equals(str)) {
            return DE;
        }

        if ("日语".equals(str)) {
            return JP;
        }

        if ("法语".equals(str)) {
            return FR;
        }
        if ("意大利语".equals(str)) {
            return IT;
        }
        return null;
    }
}
