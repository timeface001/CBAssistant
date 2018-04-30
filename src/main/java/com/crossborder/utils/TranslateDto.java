package com.crossborder.utils;

/**
 * Created by fengsong on 2018/4/29.
 */
public class TranslateDto {

    private String cn;
    private String de;
    private String es;
    private String jp;
    private String fr;
    private String it;
    private String uk;

    public TranslateDto() {
    }

    public TranslateDto(String cn, String de, String es, String jp, String fr, String it, String uk) {
        this.cn = cn;
        this.de = de;
        this.es = es;
        this.jp = jp;
        this.fr = fr;
        this.it = it;
        this.uk = uk;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }
}
