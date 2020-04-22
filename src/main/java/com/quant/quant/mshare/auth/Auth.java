package com.quant.quant.mshare.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Auth {
    public static void main(String[] args) {
        CloseableHttpClient client = HttpClients.createDefault();
        String url = "http://localhost:5000";
//		String url3="https://www.apiopen.top/journalismApi";
//		String url2="https://api.weixin.qq.com/sns/oauth2/access_token?appid=appid&code=code&grant_type=authorization_code";
        HttpGet get = new HttpGet(url);
        ProtocolVersion protocolVersion = get.getProtocolVersion();
        System.out.println(protocolVersion.getProtocol());
        try {
            //该网页需要认证（用户名、密码）
            HttpClientContext context = new HttpClientContext();
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("quant0098", "jf199410"));
            context.setCredentialsProvider(credentialsProvider);
            CloseableHttpResponse execute = client.execute(get, context);
            //----以下一样
            HttpEntity entity = execute.getEntity();
            InputStream in = entity.getContent();
            StringBuilder builder = new StringBuilder();
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(in));
            for (String temp = bufreader.readLine(); temp != null; temp = bufreader.readLine()) {
                builder.append(temp);
            }
            System.out.println(builder.toString());
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


