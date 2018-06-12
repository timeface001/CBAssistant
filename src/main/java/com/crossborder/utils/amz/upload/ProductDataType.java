package com.crossborder.utils.amz.upload;

import java.util.HashMap;
import java.util.Map;

public class ProductDataType {

    private static Map<String,String> typeList=new HashMap<>();

    static {

        //运动
        typeList.put("SportingGoods","Sports");
        typeList.put("GolfClubHybrid","Sports");
        typeList.put("GolfClubIron","Sports");
        typeList.put("GolfClubPutter","Sports");
        typeList.put("GolfClubWedge","Sports");
        typeList.put("GolfClubWood","Sports");
        typeList.put("GolfClubs","Sports");
        typeList.put("SportGloves","Sports");

        //鞋子
        typeList.put("Shoes","Shoes");


    }



    class DataItem{
        private String key;

        private String type;

        public DataItem(String key, String type) {
            this.key = key;
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
