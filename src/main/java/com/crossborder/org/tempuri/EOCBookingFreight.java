/**
 * EOCBookingFreight.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class EOCBookingFreight  implements java.io.Serializable {
    private String equickWBNo;

    private java.math.BigDecimal WHTLength;

    private java.math.BigDecimal WHTWidth;

    private java.math.BigDecimal WHTHeight;

    private java.math.BigDecimal WHTVOLWHT;

    private java.math.BigDecimal WHTFactWHT;

    private java.math.BigDecimal bookingFeeWHT;

    private java.math.BigDecimal bookingFee;

    private java.math.BigDecimal otherFreight;

    private String otherFreightCause;

    private java.math.BigDecimal totFreight;

    public EOCBookingFreight() {
    }

    public EOCBookingFreight(
           String equickWBNo,
           java.math.BigDecimal WHTLength,
           java.math.BigDecimal WHTWidth,
           java.math.BigDecimal WHTHeight,
           java.math.BigDecimal WHTVOLWHT,
           java.math.BigDecimal WHTFactWHT,
           java.math.BigDecimal bookingFeeWHT,
           java.math.BigDecimal bookingFee,
           java.math.BigDecimal otherFreight,
           String otherFreightCause,
           java.math.BigDecimal totFreight) {
           this.equickWBNo = equickWBNo;
           this.WHTLength = WHTLength;
           this.WHTWidth = WHTWidth;
           this.WHTHeight = WHTHeight;
           this.WHTVOLWHT = WHTVOLWHT;
           this.WHTFactWHT = WHTFactWHT;
           this.bookingFeeWHT = bookingFeeWHT;
           this.bookingFee = bookingFee;
           this.otherFreight = otherFreight;
           this.otherFreightCause = otherFreightCause;
           this.totFreight = totFreight;
    }


    /**
     * Gets the equickWBNo value for this EOCBookingFreight.
     * 
     * @return equickWBNo
     */
    public String getEquickWBNo() {
        return equickWBNo;
    }


    /**
     * Sets the equickWBNo value for this EOCBookingFreight.
     * 
     * @param equickWBNo
     */
    public void setEquickWBNo(String equickWBNo) {
        this.equickWBNo = equickWBNo;
    }


    /**
     * Gets the WHTLength value for this EOCBookingFreight.
     * 
     * @return WHTLength
     */
    public java.math.BigDecimal getWHTLength() {
        return WHTLength;
    }


    /**
     * Sets the WHTLength value for this EOCBookingFreight.
     * 
     * @param WHTLength
     */
    public void setWHTLength(java.math.BigDecimal WHTLength) {
        this.WHTLength = WHTLength;
    }


    /**
     * Gets the WHTWidth value for this EOCBookingFreight.
     * 
     * @return WHTWidth
     */
    public java.math.BigDecimal getWHTWidth() {
        return WHTWidth;
    }


    /**
     * Sets the WHTWidth value for this EOCBookingFreight.
     * 
     * @param WHTWidth
     */
    public void setWHTWidth(java.math.BigDecimal WHTWidth) {
        this.WHTWidth = WHTWidth;
    }


    /**
     * Gets the WHTHeight value for this EOCBookingFreight.
     * 
     * @return WHTHeight
     */
    public java.math.BigDecimal getWHTHeight() {
        return WHTHeight;
    }


    /**
     * Sets the WHTHeight value for this EOCBookingFreight.
     * 
     * @param WHTHeight
     */
    public void setWHTHeight(java.math.BigDecimal WHTHeight) {
        this.WHTHeight = WHTHeight;
    }


    /**
     * Gets the WHTVOLWHT value for this EOCBookingFreight.
     * 
     * @return WHTVOLWHT
     */
    public java.math.BigDecimal getWHTVOLWHT() {
        return WHTVOLWHT;
    }


    /**
     * Sets the WHTVOLWHT value for this EOCBookingFreight.
     * 
     * @param WHTVOLWHT
     */
    public void setWHTVOLWHT(java.math.BigDecimal WHTVOLWHT) {
        this.WHTVOLWHT = WHTVOLWHT;
    }


    /**
     * Gets the WHTFactWHT value for this EOCBookingFreight.
     * 
     * @return WHTFactWHT
     */
    public java.math.BigDecimal getWHTFactWHT() {
        return WHTFactWHT;
    }


    /**
     * Sets the WHTFactWHT value for this EOCBookingFreight.
     * 
     * @param WHTFactWHT
     */
    public void setWHTFactWHT(java.math.BigDecimal WHTFactWHT) {
        this.WHTFactWHT = WHTFactWHT;
    }


    /**
     * Gets the bookingFeeWHT value for this EOCBookingFreight.
     * 
     * @return bookingFeeWHT
     */
    public java.math.BigDecimal getBookingFeeWHT() {
        return bookingFeeWHT;
    }


    /**
     * Sets the bookingFeeWHT value for this EOCBookingFreight.
     * 
     * @param bookingFeeWHT
     */
    public void setBookingFeeWHT(java.math.BigDecimal bookingFeeWHT) {
        this.bookingFeeWHT = bookingFeeWHT;
    }


    /**
     * Gets the bookingFee value for this EOCBookingFreight.
     * 
     * @return bookingFee
     */
    public java.math.BigDecimal getBookingFee() {
        return bookingFee;
    }


    /**
     * Sets the bookingFee value for this EOCBookingFreight.
     * 
     * @param bookingFee
     */
    public void setBookingFee(java.math.BigDecimal bookingFee) {
        this.bookingFee = bookingFee;
    }


    /**
     * Gets the otherFreight value for this EOCBookingFreight.
     * 
     * @return otherFreight
     */
    public java.math.BigDecimal getOtherFreight() {
        return otherFreight;
    }


    /**
     * Sets the otherFreight value for this EOCBookingFreight.
     * 
     * @param otherFreight
     */
    public void setOtherFreight(java.math.BigDecimal otherFreight) {
        this.otherFreight = otherFreight;
    }


    /**
     * Gets the otherFreightCause value for this EOCBookingFreight.
     * 
     * @return otherFreightCause
     */
    public String getOtherFreightCause() {
        return otherFreightCause;
    }


    /**
     * Sets the otherFreightCause value for this EOCBookingFreight.
     * 
     * @param otherFreightCause
     */
    public void setOtherFreightCause(String otherFreightCause) {
        this.otherFreightCause = otherFreightCause;
    }


    /**
     * Gets the totFreight value for this EOCBookingFreight.
     * 
     * @return totFreight
     */
    public java.math.BigDecimal getTotFreight() {
        return totFreight;
    }


    /**
     * Sets the totFreight value for this EOCBookingFreight.
     * 
     * @param totFreight
     */
    public void setTotFreight(java.math.BigDecimal totFreight) {
        this.totFreight = totFreight;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof EOCBookingFreight)) return false;
        EOCBookingFreight other = (EOCBookingFreight) obj;
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
              this.equickWBNo.equals(other.getEquickWBNo()))) &&
            ((this.WHTLength==null && other.getWHTLength()==null) || 
             (this.WHTLength!=null &&
              this.WHTLength.equals(other.getWHTLength()))) &&
            ((this.WHTWidth==null && other.getWHTWidth()==null) || 
             (this.WHTWidth!=null &&
              this.WHTWidth.equals(other.getWHTWidth()))) &&
            ((this.WHTHeight==null && other.getWHTHeight()==null) || 
             (this.WHTHeight!=null &&
              this.WHTHeight.equals(other.getWHTHeight()))) &&
            ((this.WHTVOLWHT==null && other.getWHTVOLWHT()==null) || 
             (this.WHTVOLWHT!=null &&
              this.WHTVOLWHT.equals(other.getWHTVOLWHT()))) &&
            ((this.WHTFactWHT==null && other.getWHTFactWHT()==null) || 
             (this.WHTFactWHT!=null &&
              this.WHTFactWHT.equals(other.getWHTFactWHT()))) &&
            ((this.bookingFeeWHT==null && other.getBookingFeeWHT()==null) || 
             (this.bookingFeeWHT!=null &&
              this.bookingFeeWHT.equals(other.getBookingFeeWHT()))) &&
            ((this.bookingFee==null && other.getBookingFee()==null) || 
             (this.bookingFee!=null &&
              this.bookingFee.equals(other.getBookingFee()))) &&
            ((this.otherFreight==null && other.getOtherFreight()==null) || 
             (this.otherFreight!=null &&
              this.otherFreight.equals(other.getOtherFreight()))) &&
            ((this.otherFreightCause==null && other.getOtherFreightCause()==null) || 
             (this.otherFreightCause!=null &&
              this.otherFreightCause.equals(other.getOtherFreightCause()))) &&
            ((this.totFreight==null && other.getTotFreight()==null) || 
             (this.totFreight!=null &&
              this.totFreight.equals(other.getTotFreight())));
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
        if (getWHTLength() != null) {
            _hashCode += getWHTLength().hashCode();
        }
        if (getWHTWidth() != null) {
            _hashCode += getWHTWidth().hashCode();
        }
        if (getWHTHeight() != null) {
            _hashCode += getWHTHeight().hashCode();
        }
        if (getWHTVOLWHT() != null) {
            _hashCode += getWHTVOLWHT().hashCode();
        }
        if (getWHTFactWHT() != null) {
            _hashCode += getWHTFactWHT().hashCode();
        }
        if (getBookingFeeWHT() != null) {
            _hashCode += getBookingFeeWHT().hashCode();
        }
        if (getBookingFee() != null) {
            _hashCode += getBookingFee().hashCode();
        }
        if (getOtherFreight() != null) {
            _hashCode += getOtherFreight().hashCode();
        }
        if (getOtherFreightCause() != null) {
            _hashCode += getOtherFreightCause().hashCode();
        }
        if (getTotFreight() != null) {
            _hashCode += getTotFreight().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EOCBookingFreight.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "EOCBookingFreight"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("equickWBNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EquickWBNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WHTLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "WHTLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WHTWidth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "WHTWidth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WHTHeight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "WHTHeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WHTVOLWHT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "WHTVOLWHT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WHTFactWHT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "WHTFactWHT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookingFeeWHT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BookingFeeWHT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookingFee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BookingFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otherFreight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OtherFreight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otherFreightCause");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OtherFreightCause"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totFreight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TotFreight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
