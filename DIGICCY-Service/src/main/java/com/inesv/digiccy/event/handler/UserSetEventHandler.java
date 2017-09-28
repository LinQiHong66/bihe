package com.inesv.digiccy.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.UserSet;
import com.inesv.digiccy.event.UserSetEvent;
import com.inesv.digiccy.persistence.userset.HandleUserSet;

public class UserSetEventHandler {
	@Autowired
	HandleUserSet handleuserset;

	@EventHandler
	public void handler(UserSetEvent usersetevent) {
		UserSet userset = new UserSet();
		userset.setId(usersetevent.getId());
		userset.setOpertion_time(usersetevent.getOpertion_time());
		userset.setOpertion_number(usersetevent.getOpertion_number());
		userset.setOpertion_name(usersetevent.getOpertion_name());
		userset.setOpertion_ip(usersetevent.getOpertion_ip());
		userset.setOpertion_uptime(usersetevent.getOpertion_uptime());
		String operation = usersetevent.getOperation();
		System.out.println("operation:" + operation);
		switch (operation) {
		// 新增登录用户信息
		case "insertUserSet":
			handleuserset.insertUserSet(userset);
			break;
		// 修改登录用信息
		case "updateUserSet":
			handleuserset.updateUserSet(userset);
			break;
		// 删除登录信息
		case "deleteUserSet":
			handleuserset.deleteUserSet(userset);
			break;
		}

	}

}
