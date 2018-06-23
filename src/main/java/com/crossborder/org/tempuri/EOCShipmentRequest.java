/**
 * EOCShipmentRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class EOCShipmentRequest  implements java.io.Serializable {
    private Authentication authentication;

    private EOCBookingShipment bookingShipment;

    public EOCShipmentRequest() {
    }

    public EOCShipmentRequest(
           Authentication authentication,
           EOCBookingShipment bookingShipment) {
           this.authentication = authentication;
           this.bookingShipment = bookingShipment;
    }


    /**
     * Gets the authentication value for this EOCShipmentRequest.
     * 
     * @return authentication
     */
    public Authentication getAuthentication() {
        return authentication;
    }


    /**
     * Sets the authentication value for this EOCShipmentRequest.
     * 
     * @param authentication
     */
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }


    /**
     * Gets the bookingShipment value for this EOCShipmentRequest.
     * 
     * @return bookingShipment
     */
    public EOCBookingShipment getBookingShipment() {
        return bookingShipment;
    }


    /**
     * Sets the bookingShipment value for this EOCShipmentRequest.
     * 
     * @param bookingShipment
     */
    public void setBookingShipment(EOCBookingShipment bookingShipment) {
        this.bookingShipment = bookingShipment;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof EOCShipmentRequest)) return false;
        EOCShipmentRequest other = (EOCShipmentRequest) obj;
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
            ((this.bookingShipment==null && other.getBookingShipment()==null) || 
             (this.bookingShipment!=null &&
              this.bookingShipment.equals(other.getBookingShipment())));
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
        if (getBookingShipment() != null) {
            _hashCode += getBookingShipment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EOCShipmentRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCShipmentRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authentication");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Authentication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Authentication"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookingShipment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BookingShipment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCBookingShipment"));
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
