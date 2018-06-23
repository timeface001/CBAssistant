/**
 * BookingFreightResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class BookingFreightResponse  implements java.io.Serializable {
    private EOCBookingFreight bookingFreight;

    private EOCBookingFreightCompleted bookingFreightCompleted;

    public BookingFreightResponse() {
    }

    public BookingFreightResponse(
           EOCBookingFreight bookingFreight,
           EOCBookingFreightCompleted bookingFreightCompleted) {
           this.bookingFreight = bookingFreight;
           this.bookingFreightCompleted = bookingFreightCompleted;
    }


    /**
     * Gets the bookingFreight value for this BookingFreightResponse.
     * 
     * @return bookingFreight
     */
    public EOCBookingFreight getBookingFreight() {
        return bookingFreight;
    }


    /**
     * Sets the bookingFreight value for this BookingFreightResponse.
     * 
     * @param bookingFreight
     */
    public void setBookingFreight(EOCBookingFreight bookingFreight) {
        this.bookingFreight = bookingFreight;
    }


    /**
     * Gets the bookingFreightCompleted value for this BookingFreightResponse.
     * 
     * @return bookingFreightCompleted
     */
    public EOCBookingFreightCompleted getBookingFreightCompleted() {
        return bookingFreightCompleted;
    }


    /**
     * Sets the bookingFreightCompleted value for this BookingFreightResponse.
     * 
     * @param bookingFreightCompleted
     */
    public void setBookingFreightCompleted(EOCBookingFreightCompleted bookingFreightCompleted) {
        this.bookingFreightCompleted = bookingFreightCompleted;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BookingFreightResponse)) return false;
        BookingFreightResponse other = (BookingFreightResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bookingFreight==null && other.getBookingFreight()==null) || 
             (this.bookingFreight!=null &&
              this.bookingFreight.equals(other.getBookingFreight()))) &&
            ((this.bookingFreightCompleted==null && other.getBookingFreightCompleted()==null) || 
             (this.bookingFreightCompleted!=null &&
              this.bookingFreightCompleted.equals(other.getBookingFreightCompleted())));
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
        if (getBookingFreight() != null) {
            _hashCode += getBookingFreight().hashCode();
        }
        if (getBookingFreightCompleted() != null) {
            _hashCode += getBookingFreightCompleted().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BookingFreightResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "BookingFreightResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookingFreight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BookingFreight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCBookingFreight"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookingFreightCompleted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BookingFreightCompleted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCBookingFreightCompleted"));
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
