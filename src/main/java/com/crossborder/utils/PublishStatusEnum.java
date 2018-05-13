package com.crossborder.utils;

public enum PublishStatusEnum {
    NOT(0),
    FAILED(1),
    SUCCESS(2),
    PROCESS(3);

    //0未发布  1发布失败 2发布成功


    private int val;

    PublishStatusEnum(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String toString(){
        return String.valueOf(val);
    }
}
