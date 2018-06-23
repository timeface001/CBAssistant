/**
 * EOCWebServicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public class EOCWebServicesLocator extends org.apache.axis.client.Service implements EOCWebServices {

    public EOCWebServicesLocator() {
    }


    public EOCWebServicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EOCWebServicesLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EOCWebServicesSoap
    private String EOCWebServicesSoap_address = "http://eoc.equick.cn/OpenClientWebServices/EOCWebServices.asmx";

    public String getEOCWebServicesSoapAddress() {
        return EOCWebServicesSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private String EOCWebServicesSoapWSDDServiceName = "EOCWebServicesSoap";

    public String getEOCWebServicesSoapWSDDServiceName() {
        return EOCWebServicesSoapWSDDServiceName;
    }

    public void setEOCWebServicesSoapWSDDServiceName(String name) {
        EOCWebServicesSoapWSDDServiceName = name;
    }

    public EOCWebServicesSoap getEOCWebServicesSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EOCWebServicesSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEOCWebServicesSoap(endpoint);
    }

    public EOCWebServicesSoap getEOCWebServicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            EOCWebServicesSoapStub _stub = new EOCWebServicesSoapStub(portAddress, this);
            _stub.setPortName(getEOCWebServicesSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEOCWebServicesSoapEndpointAddress(String address) {
        EOCWebServicesSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (EOCWebServicesSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                EOCWebServicesSoapStub _stub = new EOCWebServicesSoapStub(new java.net.URL(EOCWebServicesSoap_address), this);
                _stub.setPortName(getEOCWebServicesSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("EOCWebServicesSoap".equals(inputPortName)) {
            return getEOCWebServicesSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "EOCWebServices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "EOCWebServicesSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("EOCWebServicesSoap".equals(portName)) {
            setEOCWebServicesSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
