package com.quant.quant.sql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.quant.quant.config.SqlConstants;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConn {
    public static Connection connectSqlDb(){
        SqlConstants sqlConstants = SqlConstants.getInstance();
        final String JDBC_DRIVER = sqlConstants.getJDBC_DRIVER();
        final String DB_URL = sqlConstants.getDB_URL();
        final String USER = sqlConstants.getUSER();
        final String PASS = sqlConstants.getPASS();
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库...");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ComboPooledDataSource connPool(){
        SqlConstants sqlConstants = SqlConstants.getInstance();
        final String JDBC_DRIVER = sqlConstants.getJDBC_DRIVER();
        final String DB_URL = sqlConstants.getDB_URL();
        final String USER = sqlConstants.getUSER();
        final String PASS = sqlConstants.getPASS();
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(DB_URL);
        cpds.setUser(USER);
        cpds.setPassword(PASS);
        cpds.setMinPoolSize(10);
        cpds.setMaxPoolSize(300);
        cpds.setAcquireIncrement(5);

        return cpds;
    }
}
