/**
 * EOCWebServicesSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crossborder.org.tempuri;

public interface EOCWebServicesSoap extends java.rmi.Remote {
    public BookingQuickTypeDataSetResponseBookingQuickTypeDataSetResult bookingQuickTypeDataSet() throws java.rmi.RemoteException;
    public BookingFreightResponse requestBookingFreight(EOCBookingFreightRequest EOCBookingFreight) throws java.rmi.RemoteException;
    public EOCQuickLabelResponse requestEOCQuickLabel(EOCQuickLabelRequest EOCQuickLabel) throws java.rmi.RemoteException;
    public EOCShipmentResponse requestEOCShipment(EOCShipmentRequest EOCShipment) throws java.rmi.RemoteException;
    public TrackTraceDataSetResponseTrackTraceDataSetResult trackTraceDataSet(String equickWBNo) throws java.rmi.RemoteException;
    public String trackTraceDestinationLabelNo(String equickWBNo) throws java.rmi.RemoteException;
    public TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult trackTraceLabelDataSet(String[] equickWBNoArray) throws java.rmi.RemoteException;
    public String trackTraceLabelNo(String equickWBNo) throws java.rmi.RemoteException;
}
