package com.quant.quant.ashare.impl;

import com.quant.quant.ashare.SqlClient;
import com.quant.quant.ashare.model.ListStatusData;

import java.sql.SQLException;
import java.util.List;

import static com.quant.quant.sql.SqlOp.*;

public class TuShareSqlClient implements SqlClient{
    private TuShareQueryClient tuShareQueryClient = new TuShareQueryClient();
    @Override
    public void createListStatusTable() throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE listStatus("
                + "ts_code varchar(10) not null,"
                + "symbol varchar(10),"
                + "name varchar(20),"
                + "area varchar(10),"
                + "industry varchar(10),"
                + "market varchar(10),"
                + "list_date varchar(10)"
                + ")charset=utf8;";
        createTable(sql);
    }

    public void insertListStatusTable() throws SQLException, ClassNotFoundException {
        ListStatusData listStatusData = tuShareQueryClient.queryListStatus();
        List<String> fields = listStatusData.getFields();
        List<List<String>> data = listStatusData.getItems();
        String tableName = "listStatus";
        insertTable(tableName,fields,data);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TuShareSqlClient tuShareSqlClient = new TuShareSqlClient();
        tuShareSqlClient.insertListStatusTable();
    }
}
