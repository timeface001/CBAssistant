/**
 * RequestEOCQuickLabel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class RequestEOCQuickLabel  implements java.io.Serializable {
    private EOCQuickLabelRequest EOCQuickLabel;

    public RequestEOCQuickLabel() {
    }

    public RequestEOCQuickLabel(
           EOCQuickLabelRequest EOCQuickLabel) {
           this.EOCQuickLabel = EOCQuickLabel;
    }


    /**
     * Gets the EOCQuickLabel value for this RequestEOCQuickLabel.
     * 
     * @return EOCQuickLabel
     */
    public EOCQuickLabelRequest getEOCQuickLabel() {
        return EOCQuickLabel;
    }


    /**
     * Sets the EOCQuickLabel value for this RequestEOCQuickLabel.
     * 
     * @param EOCQuickLabel
     */
    public void setEOCQuickLabel(EOCQuickLabelRequest EOCQuickLabel) {
        this.EOCQuickLabel = EOCQuickLabel;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RequestEOCQuickLabel)) return false;
        RequestEOCQuickLabel other = (RequestEOCQuickLabel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.EOCQuickLabel==null && other.getEOCQuickLabel()==null) || 
             (this.EOCQuickLabel!=null &&
              this.EOCQuickLabel.equals(other.getEOCQuickLabel())));
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
        if (getEOCQuickLabel() != null) {
            _hashCode += getEOCQuickLabel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestEOCQuickLabel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">RequestEOCQuickLabel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EOCQuickLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EOCQuickLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCQuickLabelRequest"));
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
