
package com.crossborder.entity;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.14
 * 2015-06-08T18:02:43.296+08:00
 * Generated source version: 2.7.14
 * 
 */
 
public class EIS_EISAPI_Server{

    protected EIS_EISAPI_Server() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new EISImpl();
        String address = "http://api.sfcservice.com/ishipsvc/eis-web-service";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception {
        new EIS_EISAPI_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
