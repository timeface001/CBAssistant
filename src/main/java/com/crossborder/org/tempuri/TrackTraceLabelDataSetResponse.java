/**
 * TrackTraceLabelDataSetResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class TrackTraceLabelDataSetResponse  implements java.io.Serializable {
    private TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult trackTraceLabelDataSetResult;

    public TrackTraceLabelDataSetResponse() {
    }

    public TrackTraceLabelDataSetResponse(
           TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult trackTraceLabelDataSetResult) {
           this.trackTraceLabelDataSetResult = trackTraceLabelDataSetResult;
    }


    /**
     * Gets the trackTraceLabelDataSetResult value for this TrackTraceLabelDataSetResponse.
     * 
     * @return trackTraceLabelDataSetResult
     */
    public TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult getTrackTraceLabelDataSetResult() {
        return trackTraceLabelDataSetResult;
    }


    /**
     * Sets the trackTraceLabelDataSetResult value for this TrackTraceLabelDataSetResponse.
     * 
     * @param trackTraceLabelDataSetResult
     */
    public void setTrackTraceLabelDataSetResult(TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult trackTraceLabelDataSetResult) {
        this.trackTraceLabelDataSetResult = trackTraceLabelDataSetResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TrackTraceLabelDataSetResponse)) return false;
        TrackTraceLabelDataSetResponse other = (TrackTraceLabelDataSetResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.trackTraceLabelDataSetResult==null && other.getTrackTraceLabelDataSetResult()==null) || 
             (this.trackTraceLabelDataSetResult!=null &&
              this.trackTraceLabelDataSetResult.equals(other.getTrackTraceLabelDataSetResult())));
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
        if (getTrackTraceLabelDataSetResult() != null) {
            _hashCode += getTrackTraceLabelDataSetResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrackTraceLabelDataSetResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TrackTraceLabelDataSetResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackTraceLabelDataSetResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TrackTraceLabelDataSetResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>TrackTraceLabelDataSetResponse>TrackTraceLabelDataSetResult"));
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
