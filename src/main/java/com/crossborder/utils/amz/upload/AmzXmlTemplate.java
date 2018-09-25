package com.crossborder.utils.amz.upload;

import com.alibaba.fastjson.JSON;
import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.utils.GeneralUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_DOWN;

public class AmzXmlTemplate {

    private static String getByProductType(ProductAmzUpload product) {
        String str = "<ProductType>SportingGoods</ProductType>";
        /*if (StringUtils.isNotBlank(product.getProductTypeName())) {
            str = "<ProductType>" + product.getProductTypeName() + "</ProductType>";
        }*/
        if (product.getProductTypeName().equals("Shoes")) {
            str = getExtInfo(product);
        }

        return str;
    }

    private static String variationData(ProductItemVar var) {

        if (var.getVariationType().equals("colorsize")) {
            return "<VariationTheme>ColorSize</VariationTheme>" +
                    "<Color>" + var.getColorName() + "</Color><Size>" + var.getSizeName() + "</Size>";
        } else if (var.getVariationType().equals("Color")) {
            return "<VariationTheme>" + var.getVariationType() + "</VariationTheme>" +
                    "<Color>" + var.getColorName() + "</Color>";
        } else if (var.getVariationType().equals("Size")) {
            return "<VariationTheme>" + var.getVariationType() + "</VariationTheme>" +
                    "<Size>" + var.getSizeName() + "</Size>";
        } else if (var.getVariationType().equals("material")) {
            return "<VariationTheme>Material</VariationTheme>" +
                    "<Material>" + var.getMaterialType() + "</Material>";
        } else if (var.getVariationType().equals("size-material")) {
            return "<VariationTheme>MaterialSize</VariationTheme>" +
                    "<Material>" + var.getMaterialType() + "</Material><Size>" + var.getSizeName() + "</Size>";
        } else if (var.getVariationType().equals("color-itempackagequantity")) {
            return "<VariationTheme>MaterialSize</VariationTheme>" +
                    "<Material>" + var.getMaterialType() + "</Material><Size>" + var.getSizeName() + "</Size>";
        }

        return "";
    }

    private static String variationTheme(String varTheme) {
        if (varTheme.equals("colorsize")) {
            return "<VariationTheme>ColorSize</VariationTheme>";

        } else if (varTheme.equals("Color")) {
            return "<VariationTheme>" + varTheme + "</VariationTheme>";

        } else if (varTheme.equals("Size")) {
            return "<VariationTheme>" + varTheme + "</VariationTheme>";
        } else if (varTheme.equals("material")) {
            return "<VariationTheme>Material</VariationTheme>";
        } else if (varTheme.equals("size-material")) {
            return "<VariationTheme>MaterialSize</VariationTheme>";
        } else if (varTheme.equals("color-itempackagequantity")) {
            return "<VariationTheme>MaterialSize</VariationTheme>";
        }

        return "";
    }



    private static String getPriceStr(BigDecimal rate, BigDecimal price, String code) {
        if (rate.compareTo(BigDecimal.ONE) > 0) {
            rate = rate.setScale(0, ROUND_DOWN);
        }

        if (CountryCodeEnum.US.equal(code)) {
            return "<StandardPrice currency='USD'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.GB.equal(code)) {
            return "<StandardPrice currency='GBP'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.CA.equal(code)) {
            return "<StandardPrice currency='CAD'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.DE.equal(code)) {
            return "<StandardPrice currency='EUR'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.IT.equal(code)) {
            return "<StandardPrice currency='EUR'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.FR.equal(code)) {
            return "<StandardPrice currency='EUR'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.MX.equal(code)) {
            return "<StandardPrice currency='MXN'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.ES.equal(code)) {
            return "<StandardPrice currency='EUR'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.AU.equal(code)) {
            return "<StandardPrice currency='AUD'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>";
        }

        if (CountryCodeEnum.CN.equal(code)) {
            return "<StandardPrice currency='RMB'>" + price + "</StandardPrice>";
        }
        return "";
    }


    public static String addUploadInventoryStrHead(String str, UploadServiceRequest.ShopReq shop) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>\n" +
                "<DocumentVersion>1.01</DocumentVersion>\n" +
                "<MerchantIdentifier>" + shop.getMerchantId() + "</MerchantIdentifier>\n" +
                "</Header>\n" +
                "<MessageType>Inventory</MessageType>\n" +
                str +
                "</AmazonEnvelope>";
    }

    public static String getUploadInventoryStr(ProductAmzUpload product, List<ProductItemVar> vars) {
        String varStr = "";
        int i = 1;
        for (ProductItemVar var : vars) {
            i++;
            varStr += "<Message>\n" +
                    "<MessageID>" + product.getId() + numFor1000(i) + "</MessageID>\n" +
                    "<OperationType>Update</OperationType>\n" +
                    "<Inventory>\n" +
                    "<SKU>" + product.getItemSku() + "-" + var.getSku() + "</SKU>\n" +
                    "<Quantity>" + var.getQuantity() + "</Quantity>\n" +
                    "</Inventory></Message>";
        }
        String text = varStr;

        //System.out.println(text);
        return text;
    }

    public static String addUploadProductStrHead(String str, UploadServiceRequest.ShopReq shop) {

        return "<?xml version=\"1.0\" ?>" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>" +
                "<DocumentVersion>1.01</DocumentVersion>" +
                "<MerchantIdentifier>" + shop.getMerchantId() + "</MerchantIdentifier>\n" +
                "</Header>" +
                "<MessageType>Product</MessageType>\n" +
                "<PurgeAndReplace>false</PurgeAndReplace>" + str + "</AmazonEnvelope>";
    }

    public static String getClassificationData(String values) {
        String result = "";
        try {
            if (StringUtils.isNotBlank(values)) {
                Map<String, String> map = JSON.parseObject(values, Map.class);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    result += "<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">";
                }
                return "<ClassificationData>" + result + "</ClassificationData>";

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static String getExtInfo(ProductAmzUpload product) {
        String result = "";
        if (product.getProductTypeName().equals("Shoes")) {

            return "<ClothingType>Shoes</ClothingType>";

        }

        return result;
    }

    public static String getUploadProductStr(ProductAmzUpload product, ProductItemVar var, boolean isSingle) {
        boolean isParent = StringUtils.isBlank(var.getVariationType());
        String productTypeName = StringUtils.isNotBlank(product.getProductType())&& StringUtils.isNotBlank(product.getProductTypeName()) ? product.getProductTypeName() : "Sports";
        String productData = "<ProductData>" +
                "<" + productTypeName + ">" + getByProductType(product) +
                (isSingle ? "" : ("<VariationData>" +
                        "<Parentage>" + (isParent ? "parent" : "child") + "</Parentage>" +
                        (isParent ? variationTheme(product.getVariationTheme()) : (variationData(var))) +
                        "</VariationData>")) + getClassificationData(product.getProductType()) +

                "</" + productTypeName + ">" +
                "</ProductData>";

        String text =
                "<Message>" +
                "<MessageID>"+product.getId()+"0"+var.getId()+"</MessageID>" +
                "<OperationType>Update</OperationType>" +
                "<Product>" +
                "<SKU>" + product.getItemSku() + "-" + var.getSku() + "</SKU>" +
                (isParent && !isSingle ? "" : ("<StandardProductID><Type>" + product.getExternalProductIdType() + "</Type><Value>" + product.getExternalProductId() + "</Value></StandardProductID>")) +
                "<ItemPackageQuantity>" + (var.getQuantity() == null ? "0" : var.getQuantity()) + "</ItemPackageQuantity>" +
                /*"<LaunchDate>" + GeneralUtils.formatDate(new Date(), "yyyy-MM-dd'T'hh:mm:ss") + "</LaunchDate>" +*/
                "<DescriptionData>" +
                "<Title>" + GeneralUtils.replaceHtmlSign(product.getItemName()) + "</Title>" +
                "<Brand>" + GeneralUtils.replaceHtmlSign(product.getBrandName()) + "</Brand> " +
                "<Description>" + GeneralUtils.replaceHtmlSign(product.getProductDescription()) + "</Description>" +
                (StringUtils.isNotBlank(product.getBulletPoint1()) ? ("<BulletPoint>" + GeneralUtils.replaceHtmlSign(product.getBulletPoint1()) + "</BulletPoint>") : "") +
                (StringUtils.isNotBlank(product.getBulletPoint2()) ? ("<BulletPoint>" + GeneralUtils.replaceHtmlSign(product.getBulletPoint2()) + "</BulletPoint>") : "") +
                (StringUtils.isNotBlank(product.getBulletPoint3()) ? ("<BulletPoint>" + GeneralUtils.replaceHtmlSign(product.getBulletPoint3()) + "</BulletPoint>") : "") +
                (StringUtils.isNotBlank(product.getBulletPoint4()) ? ("<BulletPoint>" + GeneralUtils.replaceHtmlSign(product.getBulletPoint4()) + "</BulletPoint>") : "") +
                (StringUtils.isNotBlank(product.getBulletPoint5()) ? ("<BulletPoint>" + GeneralUtils.replaceHtmlSign(product.getBulletPoint5()) + "</BulletPoint>") : "") +
                "<Manufacturer>" + product.getManufacturer() + "</Manufacturer>" +
                (StringUtils.isNotBlank(product.getGenericKeywords1()) ? ("<SearchTerms>" + GeneralUtils.replaceHtmlSign(product.getGenericKeywords1()) + "</SearchTerms>") : "") +
                (StringUtils.isNotBlank(product.getGenericKeywords2()) ? ("<SearchTerms>" + GeneralUtils.replaceHtmlSign(product.getGenericKeywords2()) + "</SearchTerms>") : "") +
                (StringUtils.isNotBlank(product.getGenericKeywords3()) ? ("<SearchTerms>" + GeneralUtils.replaceHtmlSign(product.getGenericKeywords3()) + "</SearchTerms>") : "") +
                (StringUtils.isNotBlank(product.getGenericKeywords4()) ? ("<SearchTerms>" + GeneralUtils.replaceHtmlSign(product.getGenericKeywords4()) + "</SearchTerms>") : "") +
                (StringUtils.isNotBlank(product.getGenericKeywords5()) ? ("<SearchTerms>" + GeneralUtils.replaceHtmlSign(product.getGenericKeywords5()) + "</SearchTerms>") : "") +
                (StringUtils.isNotBlank(product.getItemType()) ? ("<ItemType>" + product.getItemType() + "</ItemType>") : "") +
                "<IsGiftWrapAvailable>false</IsGiftWrapAvailable>" +
                        "<IsGiftMessageAvailable>false</IsGiftMessageAvailable>" +
                (StringUtils.isNotBlank(product.getProductTypeId()) ? ("<RecommendedBrowseNode>" + product.getProductTypeId() + "</RecommendedBrowseNode>") : "") +
                        "</DescriptionData>" + productData +
                "</Product>" +
                "</Message>";


        return text;
    }

    public static String getUploadImageStr(ProductAmzUpload product, String prePath, List<ProductItemVar> vars) {
        String varStr = "";
        int i = 0;
        for (ProductItemVar var : vars) {
            i++;
            varStr += "<Message>" +
                    "<MessageID>" + product.getId() + numFor1000(i) + "</MessageID>" +
                    "<OperationType>Update</OperationType>" +
                    "<ProductImage>" +
                    "<SKU>" + product.getItemSku() + "-" + var.getSku() + "</SKU>" +
                    "<ImageType>Main</ImageType>" +
                    "<ImageLocation>" + prePath + var.getMainPath() + "</ImageLocation>" +
                    "</ProductImage>" +
                    "</Message>";
            if (StringUtils.isNotBlank(var.getAttachPath())) {
                String[] strs = var.getAttachPath().split(",");
                int index = 1;
                for (String str : strs) {
                    if (StringUtils.isNotBlank(str)) {
                        i++;
                        varStr += "<Message>" +
                                "<MessageID>" + i + "</MessageID>" +
                                "<OperationType>Update</OperationType>" +
                                "<ProductImage>" +
                                "<SKU>" + product.getItemSku() + "-" + var.getSku() + "</SKU>" +
                                "<ImageType>PT" + index + "</ImageType>" +
                                "<ImageLocation>" + prePath + str + "</ImageLocation>" +
                                "</ProductImage>" +
                                "</Message>";
                        index++;
                    }
                }

            }
        }
        String text = varStr;

        //System.out.println(text);

        return text;
    }


    public static String addUploadImageStrHead(String str, UploadServiceRequest.ShopReq shop) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>\n" +
                "<DocumentVersion>1.01</DocumentVersion>" +
                "<MerchantIdentifier>" + shop.getMerchantId() + "</MerchantIdentifier>\n" +
                "</Header>\n" +
                "<MessageType>ProductImage</MessageType>" +
                str +
                "</AmazonEnvelope>";
    }

    public static String addUploadRelationsStrHead(String str, UploadServiceRequest.ShopReq shop) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>" +
                "<DocumentVersion>1.01</DocumentVersion>" +
                "<MerchantIdentifier>" + shop.getMerchantId() + "</MerchantIdentifier>\n" +
                "</Header>" +
                "<MessageType>Relationship</MessageType>" + str + "</AmazonEnvelope>";
    }

    public static String getUploadRelationsStr(ProductAmzUpload product, List<ProductItemVar> vars) {
        String varStr = "";
        int i = 1;
        for (ProductItemVar var : vars) {
            i++;
            if (StringUtils.isNotBlank(var.getVariationType())) {
                varStr += "<Relation>" +
                        "<SKU>" + product.getItemSku() + "-" + var.getSku() + "</SKU>" +
                        "<Type>Variation</Type>" +
                        "</Relation>";
            }
        }
        String text =
                "<Message>" +
                        "<MessageID>" + product.getId() + "</MessageID>" +
                        "<OperationType>Update</OperationType>" +
                        "<Relationship>" +
                        "<ParentSKU>" + product.getItemSku() + "-" + product.getAmzSku() + "</ParentSKU>\n" + varStr +
                        "</Relationship>" +
                        "</Message>";

        //System.out.println(text);
        return text;
    }

    public static String addUploadPriceStrHead(String str, UploadServiceRequest.ShopReq shop) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>" +
                "<DocumentVersion>1.01</DocumentVersion>" +
                "<MerchantIdentifier>" + shop.getMerchantId() + "</MerchantIdentifier>\n" +
                "</Header>" +
                "<MessageType>Price</MessageType>" +
                str +
                "</AmazonEnvelope>";
    }

    public static String getUploadPriceStr(ProductAmzUpload product, UploadServiceRequest.ShopReq shop, List<ProductItemVar> vars) {
        String varStr = "";
        int i = 1;
        BigDecimal rate=shop.getExrate();
        if (rate.compareTo(BigDecimal.ONE) > 0) {
            rate = rate.setScale(0, ROUND_DOWN);
        }
        for (ProductItemVar var : vars) {
            i++;
            String saleStr = "";
            if (var.getSalePrice() != null && var.getSalePrice().compareTo(BigDecimal.ZERO) > 0 & var.getSaleStartTime() != null) {
                saleStr = " <Sale>" +
                        " <StartDate>" + GeneralUtils.localToUTC(var.getSaleStartTime()) + "</StartDate>" +
                        " <EndDate>" + GeneralUtils.localToUTC(var.getSaleEndTime()) + "</EndDate>" +
                        " <SalePrice currency='DEFAULT'>" + (var.getSalePrice().divide(rate, 0, ROUND_DOWN)) + ".99</SalePrice>" +
                        " </Sale>";
            }
            varStr += "<Message>" +
                    "<MessageID>" + product.getId() + numFor1000(i) + "</MessageID>" +
                    "<Price>" +
                    "<SKU>" + product.getItemSku() + "-" + var.getSku() + "</SKU>" +
                    "<StandardPrice currency=\"DEFAULT\">" + (var.getPrice().divide(rate, 0, ROUND_DOWN)) + ".99</StandardPrice>" +
                    saleStr +
                    "</Price>" +
                    "</Message>";
        }
        String text = varStr;

        return text;
    }

    private static String numFor1000(int num) {
        if (num < 10) {
            return "00" + num;
        } else if (num < 100) {
            return "0" + num;
        } else {
            return num + "";
        }
    }


}
