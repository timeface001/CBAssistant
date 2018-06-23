/**
 * RequestBookingFreightResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class RequestBookingFreightResponse  implements java.io.Serializable {
    private BookingFreightResponse requestBookingFreightResult;

    public RequestBookingFreightResponse() {
    }

    public RequestBookingFreightResponse(
           BookingFreightResponse requestBookingFreightResult) {
           this.requestBookingFreightResult = requestBookingFreightResult;
    }


    /**
     * Gets the requestBookingFreightResult value for this RequestBookingFreightResponse.
     * 
     * @return requestBookingFreightResult
     */
    public BookingFreightResponse getRequestBookingFreightResult() {
        return requestBookingFreightResult;
    }


    /**
     * Sets the requestBookingFreightResult value for this RequestBookingFreightResponse.
     * 
     * @param requestBookingFreightResult
     */
    public void setRequestBookingFreightResult(BookingFreightResponse requestBookingFreightResult) {
        this.requestBookingFreightResult = requestBookingFreightResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RequestBookingFreightResponse)) return false;
        RequestBookingFreightResponse other = (RequestBookingFreightResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestBookingFreightResult==null && other.getRequestBookingFreightResult()==null) || 
             (this.requestBookingFreightResult!=null &&
              this.requestBookingFreightResult.equals(other.getRequestBookingFreightResult())));
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
        if (getRequestBookingFreightResult() != null) {
            _hashCode += getRequestBookingFreightResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestBookingFreightResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">RequestBookingFreightResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestBookingFreightResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RequestBookingFreightResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "BookingFreightResponse"));
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
