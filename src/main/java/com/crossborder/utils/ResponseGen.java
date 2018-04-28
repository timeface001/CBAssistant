package com.crossborder.utils;

import com.alibaba.fastjson.JSON;

public class ResponseGen<T> {

    public static ResponseDto genSuccess() {
        return new ResponseDto("操作成功", "0", true, null, 0);
    }

    public static ResponseDto genSuccessMsg(String msg) {
        return new ResponseDto(msg, "0", true, null, 0);
    }

    public static ResponseDto genFailMsg(String msg) {
        return new ResponseDto(msg, "0", false, null, 0);
    }


    public static ResponseDto genFail() {
        return new ResponseDto("操作失败", "-1", false, null, 0);
    }

    public static ResponseDto genSuccessCode(String code, String msg) {
        return new ResponseDto(msg, code, false, null, 0);
    }

    public static <T> String genSuccessData(T data) {
        return JSON.toJSONString(new ResponseDto("操作成功", "0", true, data, 0));
    }

}
