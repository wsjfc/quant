package com.quant.quant.ashare.model;

import lombok.Data;

@Data
public class ListStatus {
    private String request_id;
    private int code;
    private String msg;
    private ListStatusData data;
}
