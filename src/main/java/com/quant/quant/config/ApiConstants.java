package com.quant.quant.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConstants {
    private String API_TOKEN;
    private String BASE_URL;
    private final static ApiConstants instance = new ApiConstants();

    public String getApiToken() {
        return API_TOKEN;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public static ApiConstants getInstance() {
        return instance;
    }

    private ApiConstants() {
        Properties properties = new Properties();
        InputStream dirsConf = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(dirsConf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        API_TOKEN = properties.getProperty("api_token");
        BASE_URL=properties.getProperty("Base_url");
    }
}
