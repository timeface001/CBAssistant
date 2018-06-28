package com.crossborder.utils;

import com.crossborder.org.tempuri.*;
import org.jdom.JDOMException;

import javax.xml.soap.Node;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EOCWebServicesWS {
    static EOCWebServicesLocator loca;
    static String urlAdd = "http://eoc.equick.cn/OpenClientWebServices/EOCWebServices.asmx?wsdl";

    public EOCWebServicesWS() {
        loca = new EOCWebServicesLocator();
    }

    public String TrackTraceLabelNo(String EquickWBNo) {
        String result = "";
        try {
            java.net.URL url = new java.net.URL(urlAdd);
            EOCWebServicesSoapStub eocWSSS = new EOCWebServicesSoapStub(url, loca);
            result = eocWSSS.trackTraceLabelNo(EquickWBNo);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult TrackTraceLabelDataSet(String[] EquickWBNoArray) {
        TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult result = new TrackTraceLabelDataSetResponseTrackTraceLabelDataSetResult();
        try {
            java.net.URL url = new java.net.URL(urlAdd);
            EOCWebServicesSoapStub eocWSSS = new EOCWebServicesSoapStub(url, loca);
            result = eocWSSS.trackTraceLabelDataSet(EquickWBNoArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String TrackTraceDestinationLabelNo(String EquickWBNo) {
        String result = "";
        try {
            java.net.URL url = new java.net.URL(urlAdd);
            EOCWebServicesSoapStub eocWSSS = new EOCWebServicesSoapStub(url, loca);
            result = eocWSSS.trackTraceDestinationLabelNo(EquickWBNo);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static BookingQuickTypeDataSetResponseBookingQuickTypeDataSetResult BookingQuickTypeDataSet() {
        BookingQuickTypeDataSetResponseBookingQuickTypeDataSetResult result = new BookingQuickTypeDataSetResponseBookingQuickTypeDataSetResult();
        try {
            java.net.URL url = new java.net.URL(urlAdd);
            EOCWebServicesSoapStub eocWSSS = new EOCWebServicesSoapStub(url, loca);
            result = eocWSSS.bookingQuickTypeDataSet();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public TrackTraceDataSetResponseTrackTraceDataSetResult TrackTraceDataSet(String EquickWBNo) {
        TrackTraceDataSetResponseTrackTraceDataSetResult result = new TrackTraceDataSetResponseTrackTraceDataSetResult();
        try {
            java.net.URL url = new java.net.URL(urlAdd);
            EOCWebServicesSoapStub eocWSSS = new EOCWebServicesSoapStub(url, loca);
            result = eocWSSS.trackTraceDataSet(EquickWBNo);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static EOCShipmentResponse RequestEOCShipment(EOCShipmentRequest EOCShipment) {
        EOCShipmentResponse result = new EOCShipmentResponse();
        try {
            java.net.URL url = new java.net.URL(urlAdd);
            EOCWebServicesSoapStub eocWSSS = new EOCWebServicesSoapStub(url, loca);
            result = eocWSSS.requestEOCShipment(EOCShipment);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public BookingFreightResponse RequestBookingFreight(EOCBookingFreightRequest EOCBookingFreight) {
        BookingFreightResponse result = new BookingFreightResponse();
        try {
            java.net.URL url = new java.net.URL(urlAdd);
            EOCWebServicesSoapStub eocWSSS = new EOCWebServicesSoapStub(url, loca);
            result = eocWSSS.requestBookingFreight(EOCBookingFreight);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public EOCQuickLabelResponse RequestEOCQuickLabel(EOCQuickLabelRequest EOCQuickLabel) {
        EOCQuickLabelResponse result = new EOCQuickLabelResponse();
        try {
            java.net.URL url = new java.net.URL(urlAdd);
            EOCWebServicesSoapStub eocWSSS = new EOCWebServicesSoapStub(url, loca);
            result = eocWSSS.requestEOCQuickLabel(EOCQuickLabel);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws JDOMException, IOException {
        BookingQuickTypeDataSetResponseBookingQuickTypeDataSetResult result = BookingQuickTypeDataSet();
        List<Map<String, String>> types = new ArrayList<>();
        for (Object obj : result.get_any()) {
            Node nd = (Node) obj;
            for (int m = 0; m < nd.getChildNodes().getLength(); m++) {
                Node child = (Node) nd.getChildNodes().item(m);
                if ("NewDataSet".equals(child.getNodeName())) {
                    for (int n = 0; n < child.getChildNodes().getLength(); n++) {
                        Node childTypes = (Node) child.getChildNodes().item(n);
                        if ("QuickType".equals(childTypes.getNodeName())) {
                            Map<String, String> type = new HashMap<>();
                            for (int i = 0; i < childTypes.getChildNodes().getLength(); i++) {
                                Node childType = (Node) childTypes.getChildNodes().item(i);
                                if ("EOCQuickTypeName".equals(childType.getNodeName())) {
                                    String name = childType.getValue();
                                    type.put("name", name);
                                    System.out.println(name);
                                }
                                types.add(type);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(types.size());
    }
}
