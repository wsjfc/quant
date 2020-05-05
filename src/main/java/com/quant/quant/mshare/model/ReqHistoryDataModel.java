package com.quant.quant.mshare.model;

import com.ib.client.Contract;
import lombok.Data;

@Data
public class ReqHistoryDataModel {
    private Contract contract;
    private String start;
    private String end;
    private String tableName;
    private String interval;
    private String dataType;
    public ReqHistoryDataModel(Contract contract,String start,String end,String tableName,String interval,String dataType){
        this.contract = contract;
        this.start = start;
        this.end = end;
        this.tableName = tableName;
        this.interval = interval;
        this.dataType = dataType;
    }
}
