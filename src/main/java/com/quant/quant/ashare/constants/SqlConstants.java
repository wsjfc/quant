package com.quant.quant.ashare.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlConstants {
    private String JDBC_DRIVER;
    private String DB_URL;
    private String USER;
    private String PASS;

    public String getJDBC_DRIVER() {
        return JDBC_DRIVER;
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASS() {
        return PASS;
    }

    public SqlConstants(){
        Properties properties = new Properties();
        InputStream dirsConf = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(dirsConf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
        DB_URL = properties.getProperty("DB_URL");
        USER = properties.getProperty("USER");
        PASS = properties.getProperty("PASS");
    }
}
