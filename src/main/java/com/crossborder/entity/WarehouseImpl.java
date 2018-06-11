
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.crossborder.entity;

import com.crossborder.service.Warehouse;

import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 2.7.14
 * 2015-06-08T18:02:43.203+08:00
 * Generated source version: 2.7.14
 * 
 */

@WebService(
                      serviceName = "Warehouse",
                      portName = "WarehouseSOAP",
                      targetNamespace = "http://www.example.org/ShipRate/",
                      wsdlLocation = "http://www.sfcservice.com/ishipsvc/web-service?wsdl",
                      endpointInterface = "com.crossborder.service.Warehouse")
                      
public class WarehouseImpl implements Warehouse {

    private static final Logger LOG = Logger.getLogger(WarehouseImpl.class.getName());

    /* (non-Javadoc)
     * @see org.example.shiprate.Warehouse#getInventory(org.example.shiprate.GetInventoryRequest  parameters )*
     */
    public GetInventoryResponse getInventory(GetInventoryRequest parameters) {
        LOG.info("Executing operation getInventory");
        System.out.println(parameters);
        try {
            GetInventoryResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.example.shiprate.Warehouse#getWarehouseShipType(org.example.shiprate.GetWarehouseShipTypeRequest  parameters )*
     */
    public GetWarehouseShipTypeResponse getWarehouseShipType(GetWarehouseShipTypeRequest parameters) {
        LOG.info("Executing operation getWarehouseShipType");
        System.out.println(parameters);
        try {
            GetWarehouseShipTypeResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.example.shiprate.Warehouse#getWarehouse(org.example.shiprate.GetWarehouseRequestType  parameters )*
     */
    public Warehouses getWarehouse(GetWarehouseRequestType parameters) {
        LOG.info("Executing operation getWarehouse");
        System.out.println(parameters);
        try {
            Warehouses _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.example.shiprate.Warehouse#addWarehouseOrder(org.example.shiprate.AddWarehouseOrderRequest  parameters )*
     */
    public AddWarehouseOrderResponse addWarehouseOrder(AddWarehouseOrderRequest parameters) {
        LOG.info("Executing operation addWarehouseOrder");
        System.out.println(parameters);
        try {
            AddWarehouseOrderResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}