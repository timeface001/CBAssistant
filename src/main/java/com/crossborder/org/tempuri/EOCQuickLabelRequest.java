/**
 * EOCQuickLabelRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class EOCQuickLabelRequest  implements java.io.Serializable {
    private Authentication authentication;

    private String quickLabelType;

    private String equickWBNo;

    public EOCQuickLabelRequest() {
    }

    public EOCQuickLabelRequest(
           Authentication authentication,
           String quickLabelType,
           String equickWBNo) {
           this.authentication = authentication;
           this.quickLabelType = quickLabelType;
           this.equickWBNo = equickWBNo;
    }


    /**
     * Gets the authentication value for this EOCQuickLabelRequest.
     * 
     * @return authentication
     */
    public Authentication getAuthentication() {
        return authentication;
    }


    /**
     * Sets the authentication value for this EOCQuickLabelRequest.
     * 
     * @param authentication
     */
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }


    /**
     * Gets the quickLabelType value for this EOCQuickLabelRequest.
     * 
     * @return quickLabelType
     */
    public String getQuickLabelType() {
        return quickLabelType;
    }


    /**
     * Sets the quickLabelType value for this EOCQuickLabelRequest.
     * 
     * @param quickLabelType
     */
    public void setQuickLabelType(String quickLabelType) {
        this.quickLabelType = quickLabelType;
    }


    /**
     * Gets the equickWBNo value for this EOCQuickLabelRequest.
     * 
     * @return equickWBNo
     */
    public String getEquickWBNo() {
        return equickWBNo;
    }


    /**
     * Sets the equickWBNo value for this EOCQuickLabelRequest.
     * 
     * @param equickWBNo
     */
    public void setEquickWBNo(String equickWBNo) {
        this.equickWBNo = equickWBNo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof EOCQuickLabelRequest)) return false;
        EOCQuickLabelRequest other = (EOCQuickLabelRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authentication==null && other.getAuthentication()==null) || 
             (this.authentication!=null &&
              this.authentication.equals(other.getAuthentication()))) &&
            ((this.quickLabelType==null && other.getQuickLabelType()==null) || 
             (this.quickLabelType!=null &&
              this.quickLabelType.equals(other.getQuickLabelType()))) &&
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
        if (getAuthentication() != null) {
            _hashCode += getAuthentication().hashCode();
        }
        if (getQuickLabelType() != null) {
            _hashCode += getQuickLabelType().hashCode();
        }
        if (getEquickWBNo() != null) {
            _hashCode += getEquickWBNo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EOCQuickLabelRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCQuickLabelRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authentication");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Authentication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Authentication"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quickLabelType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "QuickLabelType"));
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
