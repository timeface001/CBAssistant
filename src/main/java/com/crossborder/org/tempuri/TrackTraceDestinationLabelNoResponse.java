/**
 * TrackTraceDestinationLabelNoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class TrackTraceDestinationLabelNoResponse  implements java.io.Serializable {
    private String trackTraceDestinationLabelNoResult;

    public TrackTraceDestinationLabelNoResponse() {
    }

    public TrackTraceDestinationLabelNoResponse(
           String trackTraceDestinationLabelNoResult) {
           this.trackTraceDestinationLabelNoResult = trackTraceDestinationLabelNoResult;
    }


    /**
     * Gets the trackTraceDestinationLabelNoResult value for this TrackTraceDestinationLabelNoResponse.
     * 
     * @return trackTraceDestinationLabelNoResult
     */
    public String getTrackTraceDestinationLabelNoResult() {
        return trackTraceDestinationLabelNoResult;
    }


    /**
     * Sets the trackTraceDestinationLabelNoResult value for this TrackTraceDestinationLabelNoResponse.
     * 
     * @param trackTraceDestinationLabelNoResult
     */
    public void setTrackTraceDestinationLabelNoResult(String trackTraceDestinationLabelNoResult) {
        this.trackTraceDestinationLabelNoResult = trackTraceDestinationLabelNoResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TrackTraceDestinationLabelNoResponse)) return false;
        TrackTraceDestinationLabelNoResponse other = (TrackTraceDestinationLabelNoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.trackTraceDestinationLabelNoResult==null && other.getTrackTraceDestinationLabelNoResult()==null) || 
             (this.trackTraceDestinationLabelNoResult!=null &&
              this.trackTraceDestinationLabelNoResult.equals(other.getTrackTraceDestinationLabelNoResult())));
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
        if (getTrackTraceDestinationLabelNoResult() != null) {
            _hashCode += getTrackTraceDestinationLabelNoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrackTraceDestinationLabelNoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TrackTraceDestinationLabelNoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackTraceDestinationLabelNoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TrackTraceDestinationLabelNoResult"));
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
