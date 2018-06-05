
package com.crossborder.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HeaderRequest" type="{http://www.example.org/ShipRate/}HeaderRequest" minOccurs="0"/>
 *         &lt;element name="ratesRequestInfo" type="{http://www.example.org/ShipRate/}getRatesByTypeRequestInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "headerRequest",
    "ratesRequestInfo"
})
@XmlRootElement(name = "getRatesByType")
public class GetRatesByType {

    @XmlElement(name = "HeaderRequest")
    protected HeaderRequest headerRequest;
    @XmlElement(required = true)
    protected GetRatesByTypeRequestInfo ratesRequestInfo;

    /**
     * 获取headerRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HeaderRequest }
     *     
     */
    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    /**
     * 设置headerRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderRequest }
     *     
     */
    public void setHeaderRequest(HeaderRequest value) {
        this.headerRequest = value;
    }

    /**
     * 获取ratesRequestInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GetRatesByTypeRequestInfo }
     *     
     */
    public GetRatesByTypeRequestInfo getRatesRequestInfo() {
        return ratesRequestInfo;
    }

    /**
     * 设置ratesRequestInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GetRatesByTypeRequestInfo }
     *     
     */
    public void setRatesRequestInfo(GetRatesByTypeRequestInfo value) {
        this.ratesRequestInfo = value;
    }

}
