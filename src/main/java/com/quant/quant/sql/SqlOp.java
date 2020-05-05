package com.quant.quant.sql;

import java.sql.*;
import java.util.List;

public class SqlOp {
    //Connection conn;
    //String tableName;
    //List<String> fields;
    //List<String> type;
    /*
    public SqlOp(Connection conn){
        //conn = connectSqlDb();
        this.conn = conn;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    private Connection connectSqlDb(){
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
     */

    public void createTable(String tableName, List<String> fields, List<String> filedTypes,String primeKey, Connection conn){
        //Connection conn = connectSqlDb();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < fields.size(); i++){
            str.append(fields.get(i)).append(" ").append(filedTypes.get(i));
            if (i != fields.size() - 1){
                str.append(",");
            }
        }
        String sql = String.format("CREATE TABLE "
                +  "%1$s"
                + "("
                + "%2$s"
                + ","
                + "PRIMARY KEY (%3$s)"
                + ")"
                + "charset=utf8;",tableName,str.toString(),primeKey);
        System.out.println(sql);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            if(0 == stmt.executeLargeUpdate(sql)) {
                System.out.println("成功创建表！");
            } else {
                System.out.println("创建表失败！");
            }
            stmt.close();
            //conn.close();
            System.out.println("资源关闭");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTable(String tableName,List<String> fields,List<String>[] data,Connection conn){
        for (List<String> piece:data) {
            insertTable(tableName,fields,piece,conn);
        }
    }

    public void insertTable(String tableName,List<String> fields,List<String> data,Connection conn){
        //Connection conn = connectSqlDb();
        String sql;
        if (fields.size() == 7) {
            sql = String.format("insert into %1$s(%2$s,%3$s,%4$s,%5$s,%6$s,%7$s,%8$s) values(?,?,?,?,?,?,?)",
                    tableName,fields.get(0),fields.get(1),fields.get(2),fields.get(3),fields.get(4),fields.get(5),fields.get(6));
        } else if (fields.size() == 9){
            sql = String.format("insert into %1$s(%2$s,%3$s,%4$s,%5$s,%6$s,%7$s,%8$s,%9$s,%10$s) values(?,?,?,?,?,?,?,?,?)",
                    tableName,fields.get(0),fields.get(1),fields.get(2),fields.get(3),fields.get(4),fields.get(5),fields.get(6),fields.get(7),fields.get(8));
        } else{
            sql = "";
        }
        PreparedStatement pstmt = null;//获得预置对象
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < data.size(); i++) {
                pstmt.setString(i + 1, data.get(i));//设置占位符的值
            }
            int res = pstmt.executeUpdate();//执行sql语句
            if (res <= 0) {
                System.out.println("数据录入失败");
            }
            pstmt.close();
            //conn.close();
            System.out.println("资源关闭");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tableIsExit(String tableName,Connection conn) {
        //Connection con = connectSqlDb();
        ResultSet rs = null;
        try {
            rs = conn.getMetaData().getTables(null, null, tableName, null);
            //conn.close();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
