package com.quant.quant.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ReqId {
    private Map<String,String> idTableNameMap;
    private Map<String,String> tableNameIdMap;
    private final static ReqId instance = new ReqId();

    public static ReqId getInstance() {
        return instance;
    }

    public Map<String, String> getIdTableNameMap() {
        return idTableNameMap;
    }

    public Map<String, String> getTableNameIdMap() {
        return tableNameIdMap;
    }

    private ReqId(){
        Properties properties = new Properties();
        InputStream dirsConf = this.getClass().getClassLoader().getResourceAsStream("reqId.properties");
        try {
            properties.load(dirsConf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        idTableNameMap = new HashMap<String, String>((Map) properties);
        tableNameIdMap = new HashMap<String,String>();
        //System.out.println(idTableNameMap);
        Set<String> keySet = idTableNameMap.keySet();
        for(String key : keySet){
            String value = idTableNameMap.get(key);
            tableNameIdMap.put(value,key);
        }
    }
    public static void main(String[] args){
        System.out.println(ReqId.getInstance().tableNameIdMap);
    }

}
