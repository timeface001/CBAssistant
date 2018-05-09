package com.crossborder.utils;

import com.crossborder.entity.ProductAmzUpload;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Map;

public class AmzXmlTemplate {


    public static FileInputStream uploadProduct(ProductAmzUpload product, Map<String, Object> shop) {

        String text = "<?xml version=\"1.0\" ?>\n" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "<Header>\n" +
                "<DocumentVersion>1.01</DocumentVersion>\n" +
                "<MerchantIdentifier>M_SELLER_" + shop.get("MERCHANT_ID") + "</MerchantIdentifier>\n" +
                "</Header>\n" +
                "<MessageType>Product</MessageType>\n" +
                "<PurgeAndReplace>false</PurgeAndReplace>\n" +
                "<Message>\n" +
                "<MessageID>1</MessageID>\n" +
                "<OperationType>Update</OperationType>\n" +
                "<Product>\n" +
                "<SKU>" + product.getItemSku() + "-" + product.getAmzSku() + "</SKU>\n" +
                "<StandardProductID>" + product.getExternalProductId() + "</StandardProductID>\n" +
                "<ItemPackageQuantity>" + product.getItemPackageQuantity() + "</ItemPackageQuantity>\n" +
                "<LaunchDate>" + new Date() + "</LaunchDate>\n" +
                "<DescriptionData>\n" +
                "<Title>" + product.getItemName() + "</Title>\n" +
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
                " <RecommendedBrowseNode></RecommendedBrowseNode>\n" +
                " <RecommendedBrowseNode></RecommendedBrowseNode>\n" +
                "</DescriptionData>\n" +
                "<ProductData>\n" +
                "<Home>\n" +
                "<Parentage>variation-parent</Parentage>\n" +
                "<VariationData>\n" +
                "<VariationTheme>" + product.getVariationTheme() + "</VariationTheme>\n" +
                "</VariationData>\n" +
                /*"<Material>cotton</Material>\n" +
                "<ThreadCount>500</ThreadCount>\n" +*/
                "</Home>\n" +
                "</ProductData>\n" +
                "</Product>\n" +
                "</Message>\n" +
                "<Message>\n" +
                "</AmazonEnvelope>";

        FileUtils.byte2File(text.getBytes(), "/Users/fengsong/Downloads/", "rule_chain.txt");

        return null;

    }
}
