/**
 * TrackTraceDestinationLabelNo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class TrackTraceDestinationLabelNo  implements java.io.Serializable {
    private String equickWBNo;

    public TrackTraceDestinationLabelNo() {
    }

    public TrackTraceDestinationLabelNo(
           String equickWBNo) {
           this.equickWBNo = equickWBNo;
    }


    /**
     * Gets the equickWBNo value for this TrackTraceDestinationLabelNo.
     * 
     * @return equickWBNo
     */
    public String getEquickWBNo() {
        return equickWBNo;
    }


    /**
     * Sets the equickWBNo value for this TrackTraceDestinationLabelNo.
     * 
     * @param equickWBNo
     */
    public void setEquickWBNo(String equickWBNo) {
        this.equickWBNo = equickWBNo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TrackTraceDestinationLabelNo)) return false;
        TrackTraceDestinationLabelNo other = (TrackTraceDestinationLabelNo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
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
        if (getEquickWBNo() != null) {
            _hashCode += getEquickWBNo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrackTraceDestinationLabelNo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TrackTraceDestinationLabelNo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
