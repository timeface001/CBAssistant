package com.crossborder.utils;

public enum VarTypeEnum {

    Color("Color"), Size("Size"), colorsize("colorsize"), material("material"), size_material("size-material"),
    color_itempackagequantity("color-itempackagequantity"), itempackagequantity_size("itempackagequantity-size"), color_material("color-material"), itempackagequantity_material("itempackagequantity-material"), itempackagequantity("itempackagequantity");

    private String val;

    VarTypeEnum(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public boolean is(String s) {
        return this.val.equals(s);
    }
}
