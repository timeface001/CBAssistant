
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.crossborder.entity;

import com.crossborder.service.EIS;

import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 2.7.14
 * 2015-06-08T18:02:43.187+08:00
 * Generated source version: 2.7.14
 */

@WebService(
        serviceName = "EIS",
        portName = "EIS-API",
        targetNamespace = "http://www.example.org/ShipRate/",
        wsdlLocation = "http://www.sfcservice.com/ishipsvc/web-service?wsdl",
        endpointInterface = "com.crossborder.service.EIS")

public class EISImpl implements EIS {

    private static final Logger LOG = Logger.getLogger(EISImpl.class.getName());

    /* (non-Javadoc)
     * @see EIS#addEISOrder(AddEISOrderReqiest  parameters )*
     */
    public AddEISOrderResponse addEISOrder(AddEISOrderReqiest parameters) {
        LOG.info("Executing operation addEISOrder");
        System.out.println(parameters);
        try {
            AddEISOrderResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
