package com.crossborder.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crossborder.service.SystemManageService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengsong on 2018/4/21.
 */
public class BaiduTranApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;
    @Resource
    private static SystemManageService systemManageService;

    private BaiduTranApi() {

    }

    private static BaiduTranApi api = null;

    public static BaiduTranApi getInstance(String appid, String securityKey) {
        if (api == null) {
            api = new BaiduTranApi();
            api.appid = appid;
            api.securityKey = securityKey;
        }
        return api;
    }

    public String getTransResult(String query, String from, String to) {
        if (StringUtils.isEmpty(query) || query.trim().length() == 0) {
            return query;
        }
        Map<String, String> params = buildParams(query, from, to);
        JSONObject jsonObject = JSON.parseObject(HttpGet.getBaidu(TRANS_API_HOST, params));
        if (jsonObject.getString("error_code")!=null && jsonObject.getString("error_code").equals("54004")) {
            return jsonObject.getString("error_code");
        } else {
            JSONArray jsonArray = jsonObject.getJSONArray("trans_result");
            return jsonArray.getJSONObject(0).getString("dst");
        }
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

    public String uk2Jp(String str) {
        return getTransResult(str, "en", "jp");
    }

    public String uk2Fr(String str) {
        return getTransResult(str, "en", "fra");
    }

    public String uk2De(String str) {
        return getTransResult(str, "en", "de");
    }

    public String uk2Es(String str) {
        return getTransResult(str, "en", "spa");
    }

    public String uk2It(String str) {
        return getTransResult(str, "en", "it");
    }

    public String zh2Jp(String str) {
        return getTransResult(str, "zh", "jp");
    }

    public String jp2Zh(String str) {
        return getTransResult(str, "jp", "zh");
    }

    public String jp2Uk(String str) {
        return getTransResult(str, "jp", "en");
    }

    public String jp2De(String str) {
        return getTransResult(str, "jp", "de");
    }

    public String jp2Fr(String str) {
        return getTransResult(str, "jp", "fra");
    }

    public String jp2Es(String str) {
        return getTransResult(str, "jp", "spa");
    }

    public String jp2It(String str) {
        return getTransResult(str, "jp", "it");
    }

    public String zh2Fr(String str) {
        return getTransResult(str, "zh", "fra");
    }

    public String fr2Zh(String str) {
        return getTransResult(str, "fra", "zh");
    }

    public String fr2Uk(String str) {
        return getTransResult(str, "fra", "en");
    }

    public String fr2Jp(String str) {
        return getTransResult(str, "fra", "jp");
    }

    public String fr2It(String str) {
        return getTransResult(str, "fra", "it");
    }

    public String fr2De(String str) {
        return getTransResult(str, "fra", "de");
    }

    public String fr2Es(String str) {
        return getTransResult(str, "fra", "es");
    }

    public String zh2De(String str) {
        return getTransResult(str, "zh", "de");
    }

    public String de2Zh(String str) {
        return getTransResult(str, "de", "zh");
    }

    public String de2Uk(String str) {
        return getTransResult(str, "de", "en");
    }

    public String de2Jp(String str) {
        return getTransResult(str, "de", "jp");
    }

    public String de2Fr(String str) {
        return getTransResult(str, "de", "fra");
    }

    public String de2Es(String str) {
        return getTransResult(str, "de", "spa");
    }

    public String de2It(String str) {
        return getTransResult(str, "de", "it");
    }

    public String zh2Es(String str) {
        return getTransResult(str, "zh", "spa");
    }

    public String es2Zh(String str) {
        return getTransResult(str, "spa", "zh");
    }

    public String es2Uk(String str) {
        return getTransResult(str, "es", "en");
    }

    public String es2Jp(String str) {
        return getTransResult(str, "es", "jp");
    }

    public String es2Fr(String str) {
        return getTransResult(str, "es", "fra");
    }

    public String es2De(String str) {
        return getTransResult(str, "es", "de");
    }

    public String es2It(String str) {
        return getTransResult(str, "es", "it");
    }

    public String zh2It(String str) {
        return getTransResult(str, "zh", "it");
    }

    public String it2Zh(String str) {
        return getTransResult(str, "it", "zh");
    }

    public String it2Uk(String str) {
        return getTransResult(str, "it", "en");
    }

    public String it2Jp(String str) {
        return getTransResult(str, "it", "jp");
    }

    public String it2Fr(String str) {
        return getTransResult(str, "it", "fra");
    }

    public String it2De(String str) {
        return getTransResult(str, "it", "de");
    }

    public String it2Es(String str) {
        return getTransResult(str, "it", "es");
    }

    public static void main(String[] args) {
        System.out.println(BaiduTranApi.getInstance("20181014000219221", "uJHjCs2ImD37KCYdeqtA").uk2Zh("hello"));
    }


}
