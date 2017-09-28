package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.AddressDto;
import com.inesv.digiccy.dto.InterfaceAddressDto;
import com.inesv.digiccy.event.AddressEvent;
import com.inesv.digiccy.event.InterfaceAddressEvent;
import com.inesv.digiccy.persistence.address.AddressOperation;
import com.inesv.digiccy.persistence.inesvaddress.InterfaceDetailOperation;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
public class InterfaceAddressEventHandler {

    @Autowired
    InterfaceDetailOperation interfaceDetailOperation;

    @EventHandler
    public void handler(InterfaceAddressEvent event) throws Exception {
    	InterfaceAddressDto address = new InterfaceAddressDto();
        address.setId(event.getId());
        address.setUser_no(event.getUser_no());
        address.setAddress_no(event.getAddress_no());
        address.setState(event.getState());
        address.setDate(event.getDate());
        address.setSign(event.getSign());
        address.setAttr1(event.getAttr1());
        address.setAttr2(event.getAttr2());
        String operation = event.getOperation();
        switch (operation){
            case "insert":
            	interfaceDetailOperation.insertAddress(address);
                break;
            case "updateState":
            	interfaceDetailOperation.updateAddress(address);
                break;
            default:
                break;
        }
    }

}
