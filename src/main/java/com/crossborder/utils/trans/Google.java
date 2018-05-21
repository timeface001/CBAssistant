package com.crossborder.utils.trans;

import com.crossborder.utils.lang.Lang;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.io.IOException;

public final class Google extends Translator {
    private static final String url = "https://translate.google.cn/translate_a/single";

    public Google() {
        super(url);
        langMap.put(Lang.ZH, "zh-CN");
        langMap.put(Lang.EN, "en");
        langMap.put(Lang.JP, "ja");
        langMap.put(Lang.KOR, "ko");
        langMap.put(Lang.FRA, "fr");
        langMap.put(Lang.RU, "ru");
        langMap.put(Lang.DE, "de");
        langMap.put(Lang.SPA, "es");
        langMap.put(Lang.IT, "it");
    }

    @Override
    public void getParams(Lang from, Lang to, String text) {
        params.put("client", "t");
        params.put("sl", langMap.get(from));
        params.put("tl", langMap.get(to));
        params.put("hl", "zh-CN");
        params.put("dt", "at");
        params.put("dt", "bd");
        params.put("dt", "ex");
        params.put("dt", "ld");
        params.put("dt", "md");
        params.put("dt", "qca");
        params.put("dt", "rw");
        params.put("dt", "rm");
        params.put("dt", "ss");
        params.put("dt", "t");
        params.put("ie", "UTF-8");
        params.put("oe", "UTF-8");
        params.put("source", "btn");
        params.put("ssel", "0");
        params.put("tsel", "0");
        params.put("kc", "0");
        params.put("tk", token(text));
        /*try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        params.put("q", text);
    }

    @Override
    public String query() throws Exception {
        URIBuilder uri = new URIBuilder(url);
        for (String key : params.keySet()) {
            String value = params.get(key);
            uri.addParameter(key, value);
        }
        HttpUriRequest request = new HttpPost(uri.toString());
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        EntityUtils.consume(entity);
        response.getEntity().getContent().close();
        response.close();
        System.out.println("TEXT1====" + result);
        return result;
    }

    @Override
    public String parses(String text) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //允许使用未带引号的字段名
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //允许使用单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        String result = "";
        System.out.println("TEXT2====" + text);
        for (int i = 0; i < mapper.readTree(text).get(0).size(); i++) {
            result = result + mapper.readTree(text).get(0).get(i).get(0).toString();
        }
        System.out.println("result===" + result);
        return result;
    }

    private String token(String text) {
        String tk = "";
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        try {
            FileReader reader = new FileReader("/home/tk/Google.js");
            engine.eval(reader);
            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable) engine;
                tk = String.valueOf(invoke.invokeFunction("token", text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
}
