/**
 * RequestBookingFreight.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class RequestBookingFreight  implements java.io.Serializable {
    private EOCBookingFreightRequest EOCBookingFreight;

    public RequestBookingFreight() {
    }

    public RequestBookingFreight(
           EOCBookingFreightRequest EOCBookingFreight) {
           this.EOCBookingFreight = EOCBookingFreight;
    }


    /**
     * Gets the EOCBookingFreight value for this RequestBookingFreight.
     * 
     * @return EOCBookingFreight
     */
    public EOCBookingFreightRequest getEOCBookingFreight() {
        return EOCBookingFreight;
    }


    /**
     * Sets the EOCBookingFreight value for this RequestBookingFreight.
     * 
     * @param EOCBookingFreight
     */
    public void setEOCBookingFreight(EOCBookingFreightRequest EOCBookingFreight) {
        this.EOCBookingFreight = EOCBookingFreight;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RequestBookingFreight)) return false;
        RequestBookingFreight other = (RequestBookingFreight) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.EOCBookingFreight==null && other.getEOCBookingFreight()==null) || 
             (this.EOCBookingFreight!=null &&
              this.EOCBookingFreight.equals(other.getEOCBookingFreight())));
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
        if (getEOCBookingFreight() != null) {
            _hashCode += getEOCBookingFreight().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestBookingFreight.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">RequestBookingFreight"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EOCBookingFreight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EOCBookingFreight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCBookingFreightRequest"));
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
