package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.InterfaceCommand;
import com.inesv.digiccy.event.InterfaceEvent;


public class InterfaceAggregate extends AbstractAnnotatedAggregateRoot{

	@AggregateIdentifier
	private int id;

	public InterfaceAggregate() {
		
	}
	
	@CommandHandler
	public InterfaceAggregate(InterfaceCommand command) {
		apply(new InterfaceEvent(command.getId(), command.getApi_no(), command.getName(), command.getState(), command.getDate(), command.getRemark(), 
				command.getAttr1(), command.getAttr2(), command.getOperation()));
	}
	
	@EventHandler
	public void on(InterfaceEvent interfaceEvent){
		id = interfaceEvent.getId();
	}
}
