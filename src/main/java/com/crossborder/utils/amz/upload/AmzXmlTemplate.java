package com.crossborder.utils.amz.upload;

import com.crossborder.entity.ProductAmzUpload;
import com.crossborder.entity.ProductItemVar;
import com.crossborder.utils.FileUtils;
import com.crossborder.utils.GeneralUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

public class AmzXmlTemplate {



    public static FileInputStream uploadProduct(ProductAmzUpload product, Map<String, Object> shop, String path, ProductItemVar var,boolean isSingle) {

        try {
            return new FileInputStream(FileUtils.byte2File(getUploadProductStr(product, shop, var, isSingle).getBytes(), path, product.getId() + "product_fee.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }


    private static String getByProductType(ProductAmzUpload product) {
        String str = "<ProductType>SportingGoods</ProductType>";

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


    public static FileInputStream uploadInventory(ProductAmzUpload product, Map<String, Object> shop, String path, List<ProductItemVar> vars) {


        try {
            return new FileInputStream(FileUtils.byte2File(getUploadInventoryStr(product, vars).getBytes(), path, product.getId() + "product_inventory.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static FileInputStream uploadPrice(ProductAmzUpload product, Map<String, Object> shop, String path, List<ProductItemVar> vars) {


        try {
            return new FileInputStream(FileUtils.byte2File(getUploadPriceStr(product, shop, vars).getBytes(), path, product.getId() + "product_price.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static String getPriceStr(BigDecimal rate, BigDecimal price, String code) {

        if (CountryCodeEnum.US.equal(code)) {
            return "<StandardPrice currency='USD'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.GB.equal(code)) {
            return "<StandardPrice currency='GBP'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.CA.equal(code)) {
            return "<StandardPrice currency='CAD'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.DE.equal(code)) {
            return "<StandardPrice currency='EUR'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.IT.equal(code)) {
            return "<StandardPrice currency='EUR'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.FR.equal(code)) {
            return "<StandardPrice currency='EUR'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.MX.equal(code)) {
            return "<StandardPrice currency='MXN'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.ES.equal(code)) {
            return "<StandardPrice currency='EUR'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.AU.equal(code)) {
            return "<StandardPrice currency='AUD'>" + GeneralUtils.mutiHalfTwo(price.divide(rate, 2, ROUND_HALF_DOWN)) + "</StandardPrice>";
        }

        if (CountryCodeEnum.CN.equal(code)) {
            return "<StandardPrice currency='RMB'>" + price + "</StandardPrice>";
        }
        return "";
    }

    public static FileInputStream uploadRelationShop(ProductAmzUpload product, Map<String, Object> shop, String path, List<ProductItemVar> vars) {


        try {
            return new FileInputStream(FileUtils.byte2File(getUploadRelationsStr(product, vars).getBytes(), path, product.getId() + "product_relationship.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static FileInputStream uploadImage(ProductAmzUpload product, Map<String, Object> shop, String path, List<ProductItemVar> vars, String prePath) {


        try {
            return new FileInputStream(FileUtils.byte2File(getUploadImageStr(product, shop, prePath, vars).getBytes(), path, product.getId() + "product_imgs.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String addUploadInventoryStrHead(String str, Map<String, Object> shop) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>\n" +
                "<DocumentVersion>1.01</DocumentVersion>\n" +
                "<MerchantIdentifier>" + shop.get("MERCHANT_ID") + "</MerchantIdentifier>\n" +
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

    public static String addUploadProductStrHead(String str, Map<String, Object> shop) {

        return "<?xml version=\"1.0\" ?>" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>" +
                "<DocumentVersion>1.01</DocumentVersion>" +
                "<MerchantIdentifier>" + shop.get("MERCHANT_ID") + "</MerchantIdentifier>\n" +
                "</Header>" +
                "<MessageType>Product</MessageType>\n" +
                "<PurgeAndReplace>false</PurgeAndReplace>" + str + "</AmazonEnvelope>";
    }

    public static String getUploadProductStr(ProductAmzUpload product, Map<String, Object> shop, ProductItemVar var, boolean isSingle) {
        boolean isParent = StringUtils.isBlank(var.getVariationType());
        String text =
                "<Message>" +
                "<MessageID>1</MessageID>" +
                "<OperationType>Update</OperationType>" +
                "<Product>" +
                "<SKU>" + product.getItemSku() + "-" + var.getSku() + "</SKU>" +
                (isParent && !isSingle ? "" : ("<StandardProductID><Type>" + product.getExternalProductIdType() + "</Type><Value>" + product.getExternalProductId() + "</Value></StandardProductID>")) +
                "<ItemPackageQuantity>" + (var.getQuantity() == null ? "0" : var.getQuantity()) + "</ItemPackageQuantity>" +
                /*"<LaunchDate>" + GeneralUtils.formatDate(new Date(), "yyyy-MM-dd'T'hh:mm:ss") + "</LaunchDate>" +*/
                "<DescriptionData>" +
                "<Title>" + product.getItemName() + "</Title>" +
                "<Brand>" + product.getBrandName() + "</Brand> " +
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
                "<IsGiftMessageAvailable>false</IsGiftMessageAvailable>\n" +
                (StringUtils.isNotBlank(product.getProductTypeId()) ? ("<RecommendedBrowseNode>" + product.getProductTypeId() + "</RecommendedBrowseNode>") : "") +
                "</DescriptionData>" +
                "<ProductData>" +
                "<Sports>" + getByProductType(product) +
                (isSingle ? "" : ("<VariationData>" +
                        "<Parentage>" + (isParent ? "parent" : "child") + "</Parentage>\n" +
                        (isParent ? variationTheme(product.getVariationTheme()) : (variationData(var))) +
                        "</VariationData>")) +

                "</Sports>" +
                "</ProductData>" +
                "</Product>" +
                "</Message>";

        //System.out.println(text);

        return text;
    }

    public static String getUploadImageStr(ProductAmzUpload product, Map<String, Object> shop, String prePath, List<ProductItemVar> vars) {
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


    public static String addUploadImageStrHead(String str, Map<String, Object> shop) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>\n" +
                "<DocumentVersion>1.01</DocumentVersion>" +
                "<MerchantIdentifier>" + shop.get("MERCHANT_ID") + "</MerchantIdentifier>\n" +
                "</Header>\n" +
                "<MessageType>ProductImage</MessageType>" +
                str +
                "</AmazonEnvelope>";
    }

    public static String addUploadRelationsStrHead(String str, Map<String, Object> shop) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>" +
                "<DocumentVersion>1.01</DocumentVersion>" +
                "<MerchantIdentifier>" + shop.get("MERCHANT_ID") + "</MerchantIdentifier>\n" +
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

    public static String addUploadPriceStrHead(String str, Map<String, Object> shop) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>" +
                "<DocumentVersion>1.01</DocumentVersion>" +
                "<MerchantIdentifier>" + shop.get("MERCHANT_ID") + "</MerchantIdentifier>\n" +
                "</Header>" +
                "<MessageType>Price</MessageType>" +
                str +
                "</AmazonEnvelope>";
    }

    public static String getUploadPriceStr(ProductAmzUpload product, Map<String, Object> shop, List<ProductItemVar> vars) {
        String varStr = "";
        int i = 1;
        for (ProductItemVar var : vars) {
            i++;
            String saleStr = "";
            if (var.getSalePrice() != null && var.getSalePrice().compareTo(BigDecimal.ZERO) > 0) {
                saleStr = " <Sale>" +
                        " <StartDate>" + GeneralUtils.localToUTC(var.getSaleStartTime()) + "</StartDate>" +
                        " <EndDate>" + GeneralUtils.localToUTC(var.getSaleEndTime()) + "</EndDate>" +
                        " <SalePrice currency='USD'>" + GeneralUtils.formatTwo(var.getSalePrice().divide(new BigDecimal(shop.get("EXRATE").toString()), 2, ROUND_HALF_DOWN)) + "</SalePrice>" +
                        " </Sale>";
            }
            varStr += "<Message>" +
                    "<MessageID>" + product.getId() + numFor1000(i) + "</MessageID>" +
                    "<Price>" +
                    "<SKU>" + product.getItemSku() + "-" + var.getSku() + "</SKU>" +
                    getPriceStr(new BigDecimal(shop.get("EXRATE").toString()), var.getPrice(), shop.get("COUNTRY_CODE").toString()) +
                    saleStr +
                    "</Price>" +
                    "</Message>";
        }
        String text = varStr;

        //System.out.println(text);
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
