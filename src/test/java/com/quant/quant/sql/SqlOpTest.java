package com.quant.quant.sql;

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SqlOpTest extends TestCase {
    boolean isOpen = true;
    public void testExecute() throws SQLException {
        if (isOpen) {
            Connection conn = SqlConn.connectSqlDb();
            SqlOp sqlOp = new SqlOp();
            String tableName = "writeInfoTable";
            Map<String,String> termMap = new HashMap<String, String>(){{
                put("tableName","test");
                put("time","test");
                put("state","test2");
            }};
            boolean result = sqlOp.recordIsExit(tableName, termMap,conn);
            System.out.println(result);
            conn.close();
        }
    }
}
