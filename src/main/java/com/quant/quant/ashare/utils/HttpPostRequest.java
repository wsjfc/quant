package com.quant.quant.ashare.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import com.quant.quant.config.ApiConstants;

public class HttpPostRequest {
    public static String doPost(String apiName,String params,String fields){
        ApiConstants apiConstants = ApiConstants.getInstance();
        final String API_TOKEN = apiConstants.getApiToken();
        final String BASE_URL = apiConstants.getBaseUrl();
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        String jsonStr =
                String.format("{" +
                        "\"api_name\":%1$s, " +
                        "\"token\": %2$s, " +
                        "\"params\": %3$s," +
                        "\"fields\": %4$s}",
                        apiName,
                        API_TOKEN,
                        params,
                        fields);
        //System.out.println(jsonStr);
        try{
            URL url = new URL(BASE_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //发送POST请求必须设置为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(1000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //获取输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            //String jsonStr = "{\"api_name\":\"\", \"name\":\"Tim\"}";
            out.write(jsonStr);
            out.flush();
            out.close();
            //取得输入流，并使用Reader读取
            if (200 == conn.getResponseCode()){
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null){
                    result.append(URLDecoder.decode(line,"UTF-8"));
                    //System.out.println(URLDecoder.decode(line,"UTF-8"));
                }
            }else{
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        return result.toString();
    }

    public static void main(String[] args){
        //System.out.println("{\"qry_by\":\"name\", \"name\":\"Tim\"}");
        //postParamsString = "{\"api_name\": \"stock_basic\", \"token\": \"390d4a5d2cafe74fd85181e25b8108ac35f651f4f4ad0c5dc2932b32\", \"params\": {\"list_stauts\":\"L\"},\"fields\": \"ts_code,name,area,industry,list_date\"}";
        /*
        String creatsql = "CREATE TABLE pepole("
                + "name varchar(10) not null,"
                + "age int(4) not null"
                + ")charset=utf8;";
        System.out.println(creatsql);

         */
        String result = doPost("\"daily\"","{\"ts_code\":\"000001.SZ\",\"start_date\":\"20200405\",\"end_date\":\"20200414\"}","\"\"");
    }
}
