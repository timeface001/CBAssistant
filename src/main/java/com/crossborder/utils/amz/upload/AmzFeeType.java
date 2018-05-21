package com.crossborder.utils.amz.upload;

public enum AmzFeeType {

    PRODUCT_FEED("_POST_PRODUCT_DATA_"),
    RELATIONSHIPS_FEED("_POST_PRODUCT_RELATIONSHIP_DATA_"),
    SINGLE_FORMAT_ITEM_FEED("_POST_ITEM_DATA_"),
    PRODUCT_IMAGES_FEED("_POST_PRODUCT_IMAGE_DATA_"),
    PRICING_FEED("_POST_PRODUCT_PRICING_DATA_"),
    INVENTORY_FEED("_POST_INVENTORY_AVAILABILITY_DATA_");


    private String val;

    AmzFeeType(String str) {
        this.val = str;
    }

    public String getVal() {
        return val;
    }


}
