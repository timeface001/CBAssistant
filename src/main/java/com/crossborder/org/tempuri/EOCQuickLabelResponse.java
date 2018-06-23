/**
 * EOCQuickLabelResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class EOCQuickLabelResponse  implements java.io.Serializable {
    private EOCQuickLabel quickLabel;

    private EOCQuickLabelCompleted quickLabelCompleted;

    public EOCQuickLabelResponse() {
    }

    public EOCQuickLabelResponse(
           EOCQuickLabel quickLabel,
           EOCQuickLabelCompleted quickLabelCompleted) {
           this.quickLabel = quickLabel;
           this.quickLabelCompleted = quickLabelCompleted;
    }


    /**
     * Gets the quickLabel value for this EOCQuickLabelResponse.
     * 
     * @return quickLabel
     */
    public EOCQuickLabel getQuickLabel() {
        return quickLabel;
    }


    /**
     * Sets the quickLabel value for this EOCQuickLabelResponse.
     * 
     * @param quickLabel
     */
    public void setQuickLabel(EOCQuickLabel quickLabel) {
        this.quickLabel = quickLabel;
    }


    /**
     * Gets the quickLabelCompleted value for this EOCQuickLabelResponse.
     * 
     * @return quickLabelCompleted
     */
    public EOCQuickLabelCompleted getQuickLabelCompleted() {
        return quickLabelCompleted;
    }


    /**
     * Sets the quickLabelCompleted value for this EOCQuickLabelResponse.
     * 
     * @param quickLabelCompleted
     */
    public void setQuickLabelCompleted(EOCQuickLabelCompleted quickLabelCompleted) {
        this.quickLabelCompleted = quickLabelCompleted;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof EOCQuickLabelResponse)) return false;
        EOCQuickLabelResponse other = (EOCQuickLabelResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.quickLabel==null && other.getQuickLabel()==null) || 
             (this.quickLabel!=null &&
              this.quickLabel.equals(other.getQuickLabel()))) &&
            ((this.quickLabelCompleted==null && other.getQuickLabelCompleted()==null) || 
             (this.quickLabelCompleted!=null &&
              this.quickLabelCompleted.equals(other.getQuickLabelCompleted())));
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
        if (getQuickLabel() != null) {
            _hashCode += getQuickLabel().hashCode();
        }
        if (getQuickLabelCompleted() != null) {
            _hashCode += getQuickLabelCompleted().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EOCQuickLabelResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCQuickLabelResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quickLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "QuickLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCQuickLabel"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quickLabelCompleted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "QuickLabelCompleted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCQuickLabelCompleted"));
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
