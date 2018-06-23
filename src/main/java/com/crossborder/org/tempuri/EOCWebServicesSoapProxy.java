package com.crossborder.org.tempuri;

public class EOCWebServicesSoapProxy implements EOCWebServicesSoap {
  private String _endpoint = null;
  private EOCWebServicesSoap eOCWebServicesSoap = null;
  
  public EOCWebServicesSoapProxy() {
    _initEOCWebServicesSoapProxy();
  }
  
  public EOCWebServicesSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initEOCWebServicesSoapProxy();
  }
  
  private void _initEOCWebServicesSoapProxy() {
    try {
      eOCWebServicesSoap = (new EOCWebServicesLocator()).getEOCWebServicesSoap();
      if (eOCWebServicesSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)eOCWebServicesSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)eOCWebServicesSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (eOCWebServicesSoap != null)
      ((javax.xml.rpc.Stub)eOCWebServicesSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public EOCWebServicesSoap getEOCWebServicesSoap() {
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap;
  }
  
  public BookingQuickTypeDataSetResponseBookingQuickTypeDataSetResult bookingQuickTypeDataSet() throws java.rmi.RemoteException{
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap.bookingQuickTypeDataSet();
  }
  
  public BookingFreightResponse requestBookingFreight(EOCBookingFreightRequest EOCBookingFreight) throws java.rmi.RemoteException{
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap.requestBookingFreight(EOCBookingFreight);
  }
  
  public EOCQuickLabelResponse requestEOCQuickLabel(EOCQuickLabelRequest EOCQuickLabel) throws java.rmi.RemoteException{
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap.requestEOCQuickLabel(EOCQuickLabel);
  }
  
  public EOCShipmentResponse requestEOCShipment(EOCShipmentRequest EOCShipment) throws java.rmi.RemoteException{
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap.requestEOCShipment(EOCShipment);
  }
  
  public TrackTraceDataSetResponseTrackTraceDataSetResult trackTraceDataSet(String equickWBNo) throws java.rmi.RemoteException{
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap.trackTraceDataSet(equickWBNo);
  }
  
  public String trackTraceDestinationLabelNo(String equickWBNo) throws java.rmi.RemoteException{
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap.trackTraceDestinationLabelNo(equickWBNo);
  }
  
  public TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult trackTraceLabelDataSet(String[] equickWBNoArray) throws java.rmi.RemoteException{
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap.trackTraceLabelDataSet(equickWBNoArray);
  }
  
  public String trackTraceLabelNo(String equickWBNo) throws java.rmi.RemoteException{
    if (eOCWebServicesSoap == null)
      _initEOCWebServicesSoapProxy();
    return eOCWebServicesSoap.trackTraceLabelNo(equickWBNo);
  }
  
  
}