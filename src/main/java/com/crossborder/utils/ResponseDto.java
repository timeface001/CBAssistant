package com.crossborder.utils;

public class ResponseDto<T> {

    private String msg;

    private String code;

    private boolean success;

    private T data;

    private Integer size;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ResponseDto(String msg, String code, boolean success, T data, Integer size) {
        this.msg = msg;
        this.code = code;
        this.success = success;
        this.data = data;
        this.size = size;
    }

    public ResponseDto() {
    }
}
