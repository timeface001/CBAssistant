package com.crossborder.utils;


import org.apache.axis.encoding.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;


public class HttpClientUtil {

    //get方式
    public static String doGetRequest(String url) throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "text/json");
        httpGet.setHeader("Accept-Language", "zh-cn");
        String token = "C98031&HHTGPrA8drI=";
        httpGet.setHeader("Authorization", "Basic " + Base64.encode(token.getBytes()));
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
    public static String doPostRequest(String url, String json) throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "text/json");
        httpPost.setHeader("Accept-Language", "zh-cn");
        String token = "C98031&HHTGPrA8drI=";
        httpPost.setHeader("Authorization", "Basic " + Base64.encode(token.getBytes()));
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

    /**
     * post请求(用于key-value格式的参数)
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {    //请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                return sb.toString();
            } else {
                System.out.println("状态码：" + code);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String... args) throws Exception {
        String key = "AIzaSyD9ZFuiV0CJYppKv9G6DQ08QQc2JDpOnHk";
        String url = "https://translation.googleapis.com/language/translate/v2";
        Map<String,String> map = new HashMap<>();
        map.put("q","您好，李兵！");
        map.put("target","en");
        map.put("key",key);
        String result = doPost(url,map);
        System.out.print(result);
    }
}