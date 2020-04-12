package com.quant.quant.ashare.impl;

import com.alibaba.fastjson.JSON;
import com.quant.quant.ashare.QueryClient;
import com.quant.quant.ashare.model.ListStatus;
import com.quant.quant.ashare.model.ListStatusData;
import static com.quant.quant.ashare.utils.HttpPostRequest.doPost;

public class TuShareQueryClient implements QueryClient {
    @Override
    public ListStatusData queryListStatus() {
        String result = doPost("\"stock_basic\"","{\"list_staut\":\"L\"}","\"\"");
        ListStatusData listStatusData = JSON.parseObject(result, ListStatus.class).getData();
        return listStatusData;
    }

    public static void main(String[] args){
        TuShareQueryClient tuShareQueryClient = new TuShareQueryClient();
        System.out.println(tuShareQueryClient.queryListStatus().getFields());
    }
}
