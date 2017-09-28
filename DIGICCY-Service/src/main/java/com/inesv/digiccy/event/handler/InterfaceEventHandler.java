package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.InterfaceAddressDto;
import com.inesv.digiccy.dto.InterfaceDto;
import com.inesv.digiccy.event.InterfaceEvent;
import com.inesv.digiccy.persistence.inesvaddress.InterfaceOperation;

public class InterfaceEventHandler {
	
	@Autowired
	InterfaceOperation interfaceOperation;
	
	@EventHandler
	public void handler(InterfaceEvent event) throws Exception{
		InterfaceDto interfaces = new InterfaceDto();
		interfaces.setId(event.getId());
		interfaces.setApi_no(event.getApi_no());
		interfaces.setName(event.getName());
		interfaces.setState(event.getState());
		interfaces.setDate(event.getDate());
		interfaces.setRemark(event.getRemark());
		interfaces.setAttr1(event.getAttr1());
		interfaces.setAttr2(event.getAttr2());
        String operation = event.getOperation();
		switch (operation) {
		case "insert":
			interfaceOperation.addInterface(interfaces);
			break;
		case "edit":
			interfaceOperation.updateById(interfaces);
			break;
		case "editState":
			interfaceOperation.updateState(interfaces);
			break;
		case "delete":
			interfaceOperation.delete(interfaces);
			break;
		default:
			break;
		}
	}

}
