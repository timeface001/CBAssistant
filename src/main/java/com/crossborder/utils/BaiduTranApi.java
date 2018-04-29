package com.crossborder.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengsong on 2018/4/21.
 */
public class BaiduTranApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    private BaiduTranApi() {

    }

    private static BaiduTranApi api = null;

    public static BaiduTranApi getInstance() {

        if (api == null) {
            api = new BaiduTranApi();
            api.appid = "20180421000148711";
            api.securityKey = "Vw0k9SYUoqNAA_2VJRXx";
        }

        return api;
    }

    public String getTransResult(String query, String from, String to) {
        if (StringUtils.isEmpty(query) || query.trim().length() == 0) {
            return query;
        }
        Map<String, String> params = buildParams(query, from, to);
        return JSON.parseObject(HttpGet.get(TRANS_API_HOST, params)).getJSONArray("trans_result").getJSONObject(0).getString("dst");
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

    public String zh2Uk(String str) {
        return getTransResult(str, "zh", "en");
    }

    public String uk2Zh(String str) {
        return getTransResult(str, "en", "zh");
    }

    public String zh2Jp(String str) {
        return getTransResult(str, "zh", "jp");
    }

    public String jp2Zh(String str) {
        return getTransResult(str, "jp", "zh");
    }

    public String zh2Fr(String str) {
        return getTransResult(str, "zh", "fra");
    }

    public String fr2Zh(String str) {
        return getTransResult(str, "fra", "zh");
    }

    public String zh2De(String str) {
        return getTransResult(str, "zh", "de");
    }

    public String de2Zh(String str) {
        return getTransResult(str, "de", "zh");
    }

    public String zh2Es(String str) {
        return getTransResult(str, "zh", "spa");
    }

    public String es2Zh(String str) {
        return getTransResult(str, "spa", "zh");
    }

    public String zh2It(String str) {
        return getTransResult(str, "zh", "it");
    }

    public String it2Zh(String str) {
        return getTransResult(str, "it", "zh");
    }

    public static void main(String[] args) {
        System.out.println(BaiduTranApi.getInstance().zh2De("你好"));
        System.out.println(BaiduTranApi.getInstance().zh2It("你好"));
        System.out.println(BaiduTranApi.getInstance().zh2Uk("你好"));
        System.out.println(BaiduTranApi.getInstance().zh2Jp("你好"));


        System.out.println(BaiduTranApi.getInstance().zh2Es("你好"));
    }


}
