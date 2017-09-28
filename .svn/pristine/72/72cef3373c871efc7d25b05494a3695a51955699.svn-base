package com.inesv.digiccy.aggregate;

import com.inesv.digiccy.api.command.InterfaceAddressCommand;
import com.inesv.digiccy.event.InterfaceAddressEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
public class InterfaceAddressAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private int id;

    public InterfaceAddressAggregate() {}

    @CommandHandler
    public InterfaceAddressAggregate(InterfaceAddressCommand command){
        apply(new InterfaceAddressEvent(command.getId(),command.getUser_no(),command.getAddress_no(),command.getState(),command.getDate(),command.getSign(),
        		command.getAttr1(),command.getAttr2(),command.getOperation()));
    }

    @EventHandler
    public void on(InterfaceAddressEvent event){
        id = event.getId();
    }

}
