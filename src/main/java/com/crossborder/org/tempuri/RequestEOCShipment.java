/**
 * RequestEOCShipment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class RequestEOCShipment  implements java.io.Serializable {
    private EOCShipmentRequest EOCShipment;

    public RequestEOCShipment() {
    }

    public RequestEOCShipment(
           EOCShipmentRequest EOCShipment) {
           this.EOCShipment = EOCShipment;
    }


    /**
     * Gets the EOCShipment value for this RequestEOCShipment.
     * 
     * @return EOCShipment
     */
    public EOCShipmentRequest getEOCShipment() {
        return EOCShipment;
    }


    /**
     * Sets the EOCShipment value for this RequestEOCShipment.
     * 
     * @param EOCShipment
     */
    public void setEOCShipment(EOCShipmentRequest EOCShipment) {
        this.EOCShipment = EOCShipment;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RequestEOCShipment)) return false;
        RequestEOCShipment other = (RequestEOCShipment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.EOCShipment==null && other.getEOCShipment()==null) || 
             (this.EOCShipment!=null &&
              this.EOCShipment.equals(other.getEOCShipment())));
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
        if (getEOCShipment() != null) {
            _hashCode += getEOCShipment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestEOCShipment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">RequestEOCShipment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EOCShipment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EOCShipment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCShipmentRequest"));
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
