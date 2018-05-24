package com.crossborder.utils;

public enum PublishStatusEnum {
    NOT("0"),
    FAILED("1"),
    SUCCESS("2"),
    PROCESS("3");

    //0未发布  1发布失败 2发布成功 3发布中


    private String val;

    PublishStatusEnum(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String toString(){
        return String.valueOf(val);
    }
}
