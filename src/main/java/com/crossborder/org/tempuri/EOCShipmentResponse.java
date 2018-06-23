/**
 * EOCShipmentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class EOCShipmentResponse  implements java.io.Serializable {
    private EOCBookingShipment bookingShipment;

    private EOCShipmentCompleted shipmentCompleted;

    public EOCShipmentResponse() {
    }

    public EOCShipmentResponse(
           EOCBookingShipment bookingShipment,
           EOCShipmentCompleted shipmentCompleted) {
           this.bookingShipment = bookingShipment;
           this.shipmentCompleted = shipmentCompleted;
    }


    /**
     * Gets the bookingShipment value for this EOCShipmentResponse.
     * 
     * @return bookingShipment
     */
    public EOCBookingShipment getBookingShipment() {
        return bookingShipment;
    }


    /**
     * Sets the bookingShipment value for this EOCShipmentResponse.
     * 
     * @param bookingShipment
     */
    public void setBookingShipment(EOCBookingShipment bookingShipment) {
        this.bookingShipment = bookingShipment;
    }


    /**
     * Gets the shipmentCompleted value for this EOCShipmentResponse.
     * 
     * @return shipmentCompleted
     */
    public EOCShipmentCompleted getShipmentCompleted() {
        return shipmentCompleted;
    }


    /**
     * Sets the shipmentCompleted value for this EOCShipmentResponse.
     * 
     * @param shipmentCompleted
     */
    public void setShipmentCompleted(EOCShipmentCompleted shipmentCompleted) {
        this.shipmentCompleted = shipmentCompleted;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof EOCShipmentResponse)) return false;
        EOCShipmentResponse other = (EOCShipmentResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bookingShipment==null && other.getBookingShipment()==null) || 
             (this.bookingShipment!=null &&
              this.bookingShipment.equals(other.getBookingShipment()))) &&
            ((this.shipmentCompleted==null && other.getShipmentCompleted()==null) || 
             (this.shipmentCompleted!=null &&
              this.shipmentCompleted.equals(other.getShipmentCompleted())));
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
        if (getBookingShipment() != null) {
            _hashCode += getBookingShipment().hashCode();
        }
        if (getShipmentCompleted() != null) {
            _hashCode += getShipmentCompleted().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EOCShipmentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCShipmentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookingShipment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BookingShipment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCBookingShipment"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentCompleted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ShipmentCompleted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCShipmentCompleted"));
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
