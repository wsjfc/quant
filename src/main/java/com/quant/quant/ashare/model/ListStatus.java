package com.quant.quant.ashare.model;

public class ListStatus {
    private String request_id;
    private int code;
    private String msg;
    private ListStatusData data;

    public String getRequest_id() {
        return request_id;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ListStatusData getData() {
        return data;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(ListStatusData data) {
        this.data = data;
    }
}
