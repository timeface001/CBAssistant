package com.crossborder.entity;

import com.crossborder.service.ShipRate;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 提供国际小包费率查询web服务
 *
 * This class was generated by Apache CXF 2.7.14
 * 2015-06-08T18:02:43.343+08:00
 * Generated source version: 2.7.14
 * 
 */
@WebServiceClient(name = "ShipRate", 
                  wsdlLocation = "http://www.sfcservice.com/ishipsvc/web-service?wsdl",
                  targetNamespace = "http://www.example.org/ShipRate/") 
public class ShipRate_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.example.org/ShipRate/", "ShipRate");
    public final static QName ShipRateSOAP = new QName("http://www.example.org/ShipRate/", "ShipRateSOAP");
    static {
        URL url = null;
        try {
            url = new URL("http://www.sfcservice.com/ishipsvc/web-service?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ShipRate_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://www.sfcservice.com/ishipsvc/web-service?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ShipRate_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ShipRate_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ShipRate_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ShipRate_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ShipRate_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ShipRate_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 费率查询服务
     *
     * @return
     *     returns ShipRate
     */
    @WebEndpoint(name = "ShipRateSOAP")
    public ShipRate getShipRateSOAP() {
        return super.getPort(ShipRateSOAP, ShipRate.class);
    }

    /**
     * 费率查询服务
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ShipRate
     */
    @WebEndpoint(name = "ShipRateSOAP")
    public ShipRate getShipRateSOAP(WebServiceFeature... features) {
        return super.getPort(ShipRateSOAP, ShipRate.class, features);
    }

}
