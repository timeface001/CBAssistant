package com.crossborder.utils;

/**
 * Created by fengsong on 2018/4/15.
 */
public enum ProductStateEnum {

    /**
     * 未认领
     */
    un_claim(1),

    /**
     * 认领
     */
    claim(2);

    private int value;


    ProductStateEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ProductStateEnum generate(int value) {
        if (value == un_claim.value) {
            return un_claim;
        }

        if (value == claim.value) {
            return claim;
        }

        return null;
    }

}
