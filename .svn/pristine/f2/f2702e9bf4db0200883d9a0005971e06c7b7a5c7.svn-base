package com.inesv.digiccy.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.inesv.digiccy.api.command.UserSetCommand;
import com.inesv.digiccy.event.UserSetEvent;

/**
 * @author Administrator
 *
 */
public class UserSetAggergate extends AbstractAnnotatedAggregateRoot {
	@AggregateIdentifier
	private int id;

	public UserSetAggergate() {

	}

	@CommandHandler
	public UserSetAggergate(UserSetCommand command) {
		
		apply(new UserSetEvent(command.getId(), command.getOpertion_number(), command.getOpertion_time(),
				command.getOpertion_uptime(), command.getOpertion_name(), command.getOpertion_ip(), command.getAttr1(),
				command.getOperation()));
	}

	@EventHandler
	public void on(UserSetEvent usersetEvent) {
		this.id = usersetEvent.getId();

	}
}
