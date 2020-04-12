package com.quant.quant.ashare;

import java.sql.SQLException;

public interface SqlClient {
    public void createListStatusTable() throws SQLException, ClassNotFoundException;
    public void insertListStatusTable() throws SQLException, ClassNotFoundException;
}
