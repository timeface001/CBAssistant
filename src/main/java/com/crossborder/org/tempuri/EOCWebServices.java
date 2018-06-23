/**
 * EOCWebServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public interface EOCWebServices extends javax.xml.rpc.Service {
    public String getEOCWebServicesSoapAddress();

    public EOCWebServicesSoap getEOCWebServicesSoap() throws javax.xml.rpc.ServiceException;

    public EOCWebServicesSoap getEOCWebServicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
