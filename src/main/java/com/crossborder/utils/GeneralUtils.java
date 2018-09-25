package com.crossborder.utils;

import com.alibaba.fastjson.JSON;
import com.crossborder.action.ProductClaimController;
import com.crossborder.entity.ClaimProduct;
import com.crossborder.entity.ProductItemVar;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fengsong on 2018/4/15.
 */
public class GeneralUtils {

    public static String absPath(){
        return ClassUtils.getDefaultClassLoader().getResource("").getPath();
    }


    public static Map<String, Object> genMap(Object... keyAndValues) {
        Map<String, Object> result = new HashMap<>();
        if (keyAndValues != null && keyAndValues.length % 2 == 0) {
            for (int i = 0; i < keyAndValues.length; i++) {
                result.put(keyAndValues[i].toString(), keyAndValues[++i]);
            }
        }
        return result;
    }

    public static String getUUID32() {

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();

    }

    public static String getUUID16() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

    public static String getUserId(){
        //return "libing";
        return  ((Map<String, Object>) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user")).get("USER_ID").toString();
    }

    public static Map<String, Object> getUser() {
        return ((Map<String, Object>) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user"));
    }

    public static String nullToEmpty(Object obj) {
        return obj == null ? "" : obj.toString();
    }


    public static String getRandomString(int length){
        String str="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();

        StringBuffer sb=new StringBuffer();

        for (int i = 0; i < length; i++) {

            int number =random.nextInt(46);

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static Date getDateFromStr(String time) {
        if (org.apache.commons.lang3.StringUtils.isBlank(time)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String localToUTC(Date localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate = localTime;

        long localTimeInMillis = localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate = new Date(calendar.getTimeInMillis());
        return GeneralUtils.formatDate(utcDate, "yyyy-MM-dd'T'hh:mm:ss'Z'");
    }


    public static boolean isNotNullOrEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static String formatTwo(BigDecimal val) {
        java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");

        return myformat.format(val);
    }

    public static String mutiHalfTwo(BigDecimal val) {
        DecimalFormat df1 = new DecimalFormat("0.00");
        return df1.format(val);

    }

    public static String replaceHtmlSign(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            str=str.replace("&","&amp;");
            str = removeAttr(str);
            return str.replace("<", "&lt;").replace(">", "&gt;");
        }

        return "";
    }


    public static void removeProductDescriptionAttr(ClaimProduct product) {
        if (product != null) {
            product.setProductDescriptionCn(removeAttr(product.getProductDescriptionCn()));
            product.setProductDescriptionDe(removeAttr(product.getProductDescriptionDe()));
            product.setProductDescriptionUk(removeAttr(product.getProductDescriptionUk()));
            product.setProductDescriptionEs(removeAttr(product.getProductDescriptionEs()));
            product.setProductDescriptionJp(removeAttr(product.getProductDescriptionJp()));
            product.setProductDescriptionFr(removeAttr(product.getProductDescriptionFr()));
            product.setProductDescriptionIt(removeAttr(product.getProductDescriptionIt()));
        }
    }

    /*删除指定的属性*/
    public static String deleteAttr(String attr, String html) {
        if (StringUtils.isBlank(html)) {
            return html;
        }
        Pattern p = Pattern.compile(attr + "=\"([^\"]+)\"");
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }




    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("1","2");
        map.put("3","4");
        String ss="{\"MaterialType\":\"1\",\"OuterMaterialType\":\"2\",\"StyleName\":\"3\",\"ClosureType\":\"4\"}";
        System.out.println((JSON.parseObject(ss,Map.class).size()));
    }


    public static String removeAttr(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        str = deleteAttr("lang", str);
        str = deleteAttr("class", str);
        str = deleteAttr("style", str);
        str = deleteAttr("tabindex", str);
        str = deleteAttr("title", str);
        str = deleteAttr("id", str);
        str = deleteAttr("width", str);
        str = deleteAttr("heght", str);
        str = deleteAttr("cellspacing", str);
        str = deleteAttr("cellpadding", str);
        str = deleteAttr("border", str);
        str = deleteAttr("align", str);
        //str = str.replace("<span>", "").replace("</span>", "");
        str=filterHtml(str,"<\\s*span\\s+([^>]*)\\s*>");
        str=filterHtml(str,"</span>");
        str=filterHtml(str,"<span>");
        str=filterHtml(str,"<o:p>");
        str=filterHtml(str,"</o:p>");
        str=filterHtml(str,"</div>");
        str=filterHtml(str,"<div>");
        str=filterHtml(str,"</table>");
        str=filterHtml(str,"<table>");
        str=filterHtml(str,"<tbody>");
        str=filterHtml(str,"</tbody>");

        str=filterHtml(str,"<\\s*td\\s+([^>]*)\\s*>");
        str=filterHtml(str,"<\\s*table\\s+([^>]*)\\s*>");
        str=filterHtml(str,"<\\s*tr\\s+([^>]*)\\s*>");
        str=filterHtml(str,"</td>");
        str=filterHtml(str,"<td>");
        str=filterHtml(str,"</tr>");
        str=filterHtml(str,"<\\s*div\\s+([^>]*)\\s*>");

        return str;
    }


    /**
     * 基本功能：过滤所有以"<"开头以">"结尾的标签
     * <p>
     *
     * @param str
     * @return String
     */
    public static String filterHtml(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    public static String setProductTitle(ProductItemVar var) {
        if (VarTypeEnum.Color.is(var.getVariationType())) {
            return "," + var.getColorMap();
        } else if (VarTypeEnum.Size.is(var.getVariationType())) {
            return "," + var.getSizeMap();
        } else if (VarTypeEnum.colorsize.is(var.getVariationType())) {
            return "," + var.getColorMap() + "," + var.getSizeMap();
        } else if (VarTypeEnum.size_material.is(var.getVariationType())) {
            return "," + var.getSizeMap() + "," + var.getMaterialType();
        } else if (VarTypeEnum.material.is(var.getVariationType())) {
            return "," + var.getMaterialType();
        } else if (VarTypeEnum.color_itempackagequantity.is(var.getVariationType())) {
            return "," + var.getColorMap() + "," + var.getItemPackageQuantity();
        } else if (VarTypeEnum.itempackagequantity.is(var.getVariationType())) {
            return "," + var.getItemPackageQuantity();
        } else if (VarTypeEnum.itempackagequantity_material.is(var.getVariationType())) {
            return "," + var.getItemPackageQuantity() + "," + var.getMaterialType();
        } else if (VarTypeEnum.itempackagequantity_size.is(var.getVariationType())) {
            return "," + var.getItemPackageQuantity() + "," + var.getSizeMap();
        } else if (VarTypeEnum.color_material.is(var.getVariationType())) {
            return "," + var.getColorMap() + "," + var.getMaterialType();
        }
        return "";
    }

    public static String getVarValue(ProductItemVar var) {
        if (VarTypeEnum.Color.is(var.getVariationType())) {
            return var.getColorMap();
        } else if (VarTypeEnum.Size.is(var.getVariationType())) {
            return var.getSizeMap();
        } else if (VarTypeEnum.colorsize.is(var.getVariationType())) {
            return var.getColorMap() + "-" + var.getSizeMap();
        } else if (VarTypeEnum.size_material.is(var.getVariationType())) {
            return var.getSizeMap() + "-" + var.getMaterialType();
        } else if (VarTypeEnum.material.is(var.getVariationType())) {
            return var.getMaterialType();
        } else if (VarTypeEnum.color_itempackagequantity.is(var.getVariationType())) {
            return var.getColorMap() + "-" + var.getItemPackageQuantity();
        } else if (VarTypeEnum.itempackagequantity.is(var.getVariationType())) {
            return "" + var.getItemPackageQuantity();
        } else if (VarTypeEnum.itempackagequantity_material.is(var.getVariationType())) {
            return var.getItemPackageQuantity() + "-" + var.getMaterialType();
        } else if (VarTypeEnum.itempackagequantity_size.is(var.getVariationType())) {
            return var.getItemPackageQuantity() + "-" + var.getSizeMap();
        } else if (VarTypeEnum.color_material.is(var.getVariationType())) {
            return var.getColorMap() + "-" + var.getMaterialType();
        }

        return "";
    }

    public static String cuurentDateStr() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static boolean isAllChinese(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches("[\\u4e00-\\u9fa5]+");
    }

    /*public static String translate(String str, String lang, CountryCodeEnum responseLang) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        list.add(str);
        TranslateDto dto = ProductClaimController.getTanslateList(list, lang).get(0);
        if (CountryCodeEnum.FR.equal(responseLang.getVal())) {
            return dto.getFr();
        } else if (CountryCodeEnum.ES.equal(responseLang.getVal())) {
            return dto.getEs();
        } else if (CountryCodeEnum.JP.equal(responseLang.getVal())) {
            return dto.getJp();
        } else if (CountryCodeEnum.CN.equal(responseLang.getVal())) {
            return dto.getCn();
        } else if (CountryCodeEnum.GB.equal(responseLang.getVal())) {
            return dto.getUk();
        } else if (CountryCodeEnum.IT.equal(responseLang.getVal())) {
            return dto.getIt();
        } else if (CountryCodeEnum.DE.equal(responseLang.getVal())) {
            return dto.getDe();
        }
        return "";
    }*/

}
