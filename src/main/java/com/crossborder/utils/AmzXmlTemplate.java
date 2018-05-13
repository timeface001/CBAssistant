package com.crossborder.utils;

import com.crossborder.entity.ProductAmzUpload;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;

public class AmzXmlTemplate {


    public static FileInputStream uploadProduct(ProductAmzUpload product, Map<String, Object> shop, String path) {





        String text = "<?xml version=\"1.0\" ?>\n" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>\n" +
                "<DocumentVersion>1.01</DocumentVersion>\n" +
                "<MerchantIdentifier>" + shop.get("MERCHANT_ID") + "</MerchantIdentifier>\n" +
                "</Header>\n" +
                "<MessageType>Product</MessageType>\n" +
                "<PurgeAndReplace>false</PurgeAndReplace>\n" +
                "<Message>\n" +
                "<MessageID>1</MessageID>\n" +
                "<OperationType>Update</OperationType>\n" +
                "<Product>\n" +
                "<SKU>" + product.getItemSku() + "-" + product.getAmzSku() + "</SKU>" +
                "<StandardProductID>" + product.getExternalProductId() + "</StandardProductID>" +
                "<ItemPackageQuantity>" + product.getItemPackageQuantity() + "</ItemPackageQuantity>\n" +
                "<LaunchDate>" + GeneralUtils.formatDate(new Date(), "yyyy-MM-dd'T'hh:mm:ss") + "</LaunchDate>\n" +
                "<DescriptionData>" +
                "<Title>" + product.getItemName() + "</Title>" +
                "<Brand>" + product.getBrandName() + "</Brand> <Description>" + product.getProductDescription() + "</Description>\n" +
                "<BulletPoint>" + product.getBulletPoint1() + "</BulletPoint>" +
                "<BulletPoint>" + product.getBulletPoint2() + "</BulletPoint>" +
                "<BulletPoint>" + product.getBulletPoint3() + "</BulletPoint>" +
                "<BulletPoint>" + product.getBulletPoint4() + "</BulletPoint>" +
                "<BulletPoint>" + product.getBulletPoint5() + "</BulletPoint>" +
                "<Manufacturer>" + product.getManufacturer() + "</Manufacturer>\n" +
                "<SearchTerms>" + product.getGenericKeywords1() + "</SearchTerms>" +
                "<SearchTerms>" + product.getGenericKeywords2() + "</SearchTerms>" +
                "<SearchTerms>" + product.getGenericKeywords3() + "</SearchTerms>" +
                "<SearchTerms>" + product.getGenericKeywords4() + "</SearchTerms>" +
                "<SearchTerms>" + product.getGenericKeywords5() + "</SearchTerms>" +
                //"<ItemType>flat-sheets</ItemType>" +
                "<IsGiftWrapAvailable>false</IsGiftWrapAvailable>\n" +
                "<IsGiftMessageAvailable>false</IsGiftMessageAvailable>\n" +
                "</DescriptionData>\n" +
                "<ProductData>\n" +
                "<Home>\n" +
                "<Parentage>variation-parent</Parentage>\n" +
                "<VariationData>" +
                (StringUtils.isBlank(product.getVariationTheme()) ? "" : ("<VariationTheme>" + product.getVariationTheme() + "</VariationTheme>")) +
                "</VariationData>\n" +
                /*"<Material>cotton</Material>\n" +
                "<ThreadCount>500</ThreadCount>\n" +*/
                "</Home>\n" +
                "</ProductData>\n" +
                "</Product>\n" +
                "</Message>\n" +
                "</AmazonEnvelope>";

        System.out.println(text);


        try {
            return new FileInputStream(FileUtils.byte2File(text.getBytes(), path, product.getId() + "product_fee.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }
}
