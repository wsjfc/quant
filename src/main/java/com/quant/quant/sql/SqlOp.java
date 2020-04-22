package com.quant.quant.sql;

import com.quant.quant.sql.SqlConstants;

import java.sql.*;
import java.util.List;

public class SqlOp {
    private static Connection connectSqlDb(){
        SqlConstants sqlConstants = new SqlConstants();
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

    public static void createTable(String creatSql){
        Connection conn = connectSqlDb();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            if(0 == stmt.executeLargeUpdate(creatSql)) {
                System.out.println("成功创建表！");
            } else {
                System.out.println("创建表失败！");
            }
            stmt.close();
            conn.close();
            System.out.println("资源关闭");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTable(String tableName,List<String> fields,List<List<String>> data){
        Connection conn = connectSqlDb();
        String sql;
        if (fields.size() == 7) {
            sql = String.format("insert into %1$s(%2$s,%3$s,%4$s,%5$s,%6$s,%7$s,%8$s) values(?,?,?,?,?,?,?)",
                    tableName,fields.get(0),fields.get(1),fields.get(2),fields.get(3),fields.get(4),fields.get(5),fields.get(6));
        } else{
            sql = "";
        }
        PreparedStatement pstmt = null;//获得预置对象
        try {
            pstmt = conn.prepareStatement(sql);
            for (List<String> piece:data) {
                for (int i = 0; i < piece.size(); i++) {
                    pstmt.setString(i + 1, piece.get(i));//设置占位符的值
                }
                int res = pstmt.executeUpdate();//执行sql语句
                if (res <= 0) {
                    System.out.println("数据录入失败");
                }
            }
            pstmt.close();
            conn.close();
            System.out.println("资源关闭");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean tableIsExit(String tableName) {
        Connection con = connectSqlDb();
        ResultSet rs = null;
        try {
            rs = con.getMetaData().getTables(null, null, tableName, null);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
