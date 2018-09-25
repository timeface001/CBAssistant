package com.crossborder.utils.amz.upload;

public enum PublishTypeEnum {

    MAIN(0), PRICE(1), INVENTORY(2), RELATIONS(3), PICTURE(4);


    PublishTypeEnum(Integer val) {
        this.val = val;
    }

    private Integer val;

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public String getValStr() {
        return String.valueOf(val);
    }
}