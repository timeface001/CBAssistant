package com.crossborder.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * anonymous complex type的 Java 类。
 * 
 * <p>
 * 以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HeaderRequest" type="{http://www.example.org/ShipRate/}HeaderRequest"/>
 *         &lt;element name="updateOrderInfo" type="{http://www.example.org/ShipRate/}addOrderRequestInfoArray"/>
 *         &lt;element name="orderCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "headerRequest", "updateOrderInfo",
		"orderCode" })
@XmlRootElement(name = "updateOrder")
public class UpdateOrder {

	@XmlElement(name = "HeaderRequest", required = true)
	protected HeaderRequest headerRequest;
	@XmlElement(required = true)
	protected AddOrderRequestInfoArray updateOrderInfo;
	@XmlElement(required = true)
	protected String orderCode;

	/**
	 * 获取headerRequest属性的值。
	 * 
	 * @return possible object is {@link HeaderRequest }
	 * 
	 */
	public HeaderRequest getHeaderRequest() {
		return headerRequest;
	}

	/**
	 * 设置headerRequest属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link HeaderRequest }
	 * 
	 */
	public void setHeaderRequest(HeaderRequest value) {
		this.headerRequest = value;
	}

	/**
	 * 获取updateOrderInfo属性的值。
	 * 
	 * @return possible object is {@link AddOrderRequestInfoArray }
	 * 
	 */
	public AddOrderRequestInfoArray getUpdateOrderInfo() {
		return updateOrderInfo;
	}

	/**
	 * 设置updateOrderInfo属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link AddOrderRequestInfoArray }
	 * 
	 */
	public void setUpdateOrderInfo(AddOrderRequestInfoArray value) {
		this.updateOrderInfo = value;
	}

	/**
	 * 获取orderCode属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 设置orderCode属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderCode(String value) {
		this.orderCode = value;
	}

}
