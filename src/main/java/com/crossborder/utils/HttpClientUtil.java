package com.crossborder.utils;


import org.apache.axis.encoding.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HttpClientUtil {

    //get方式
    public static String doGetRequest(String url) throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "text/json");
        httpGet.setHeader("Accept-Language", "zh-cn");
        String token = "C88888&JCJaDQ68amA=";
        httpGet.setHeader("Authorization","Basic "+ Base64.encode(token.getBytes()));
        HttpResponse response = client.execute(httpGet);
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            buffer.append(line + "\n");
        }
        reader.close();
        return buffer.toString();
    }

    //post方式
    public static String doPostRequest(String url,String json) throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "text/json");
        httpPost.setHeader("Accept-Language", "zh-cn");
        String token = "C88888&JCJaDQ68amA=";
        httpPost.setHeader("Authorization","Basic "+ Base64.encode(token.getBytes()));
        StringEntity entity = new StringEntity(json, HTTP.UTF_8);
        httpPost.setEntity(entity);
        entity.setContentType("application/json");
        HttpResponse response = client.execute(httpPost);
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            buffer.append(line + "\n");
        }
        reader.close();
        return buffer.toString();
    }

}