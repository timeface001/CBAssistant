/**
 * EOCShipmentCompleted.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class EOCShipmentCompleted  implements java.io.Serializable {
    private int returnValue;

    private String returnMessage;

    private String equickWBNo;

    public EOCShipmentCompleted() {
    }

    public EOCShipmentCompleted(
           int returnValue,
           String returnMessage,
           String equickWBNo) {
           this.returnValue = returnValue;
           this.returnMessage = returnMessage;
           this.equickWBNo = equickWBNo;
    }


    /**
     * Gets the returnValue value for this EOCShipmentCompleted.
     * 
     * @return returnValue
     */
    public int getReturnValue() {
        return returnValue;
    }


    /**
     * Sets the returnValue value for this EOCShipmentCompleted.
     * 
     * @param returnValue
     */
    public void setReturnValue(int returnValue) {
        this.returnValue = returnValue;
    }


    /**
     * Gets the returnMessage value for this EOCShipmentCompleted.
     * 
     * @return returnMessage
     */
    public String getReturnMessage() {
        return returnMessage;
    }


    /**
     * Sets the returnMessage value for this EOCShipmentCompleted.
     * 
     * @param returnMessage
     */
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }


    /**
     * Gets the equickWBNo value for this EOCShipmentCompleted.
     * 
     * @return equickWBNo
     */
    public String getEquickWBNo() {
        return equickWBNo;
    }


    /**
     * Sets the equickWBNo value for this EOCShipmentCompleted.
     * 
     * @param equickWBNo
     */
    public void setEquickWBNo(String equickWBNo) {
        this.equickWBNo = equickWBNo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof EOCShipmentCompleted)) return false;
        EOCShipmentCompleted other = (EOCShipmentCompleted) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnValue == other.getReturnValue() &&
            ((this.returnMessage==null && other.getReturnMessage()==null) || 
             (this.returnMessage!=null &&
              this.returnMessage.equals(other.getReturnMessage()))) &&
            ((this.equickWBNo==null && other.getEquickWBNo()==null) || 
             (this.equickWBNo!=null &&
              this.equickWBNo.equals(other.getEquickWBNo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getReturnValue();
        if (getReturnMessage() != null) {
            _hashCode += getReturnMessage().hashCode();
        }
        if (getEquickWBNo() != null) {
            _hashCode += getEquickWBNo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EOCShipmentCompleted.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCShipmentCompleted"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ReturnValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ReturnMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("equickWBNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EquickWBNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
