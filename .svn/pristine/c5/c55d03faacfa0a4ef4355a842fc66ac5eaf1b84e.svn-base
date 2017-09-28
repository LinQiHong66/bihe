package com.inesv.digiccy.validata;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.UserSetCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.UserSet;
import com.inesv.digiccy.query.QueryUserSet;

/**
 * 
 * @author Administrator
 *
 */
@Component
public class UserSetValidata {
	@Autowired
	CommandGateway commandGateway;
	@Autowired
	QueryUserSet queryUserSet;

	public Map<String, Object> modifyUserSetInfo(int id, int limittime, int time, Date date, String ip, String name) {
		List<UserSet> userSets = getUserSets();
		UserSetCommand command = new UserSetCommand();
		if (userSets != null && userSets.size() > 0) {
			command.setOperation("updateUserSet");
			command.setId(id);
		} else {
			command.setOperation("insertUserSet");
		}
		command.setOpertion_time(limittime);
		command.setOpertion_number(time);
		command.setOpertion_uptime(date);
		command.setOpertion_ip(ip);
		command.setOpertion_name(name);

		HashMap<String, Object> map = new HashMap<>();
		try {
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

	public List<UserSet> getUserSets() {
		List<UserSet> param = queryUserSet.findUserSet();
		return param;
	}

	public Map<String, Object> getUserSet() {
		HashMap<String, Object> map = new HashMap<>();
		List<UserSet> userSets = queryUserSet.findUserSet();
		if (userSets != null && userSets.size() > 0) {
			map.put("result", userSets.get(0));
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} else {
			map.put("result", "none");
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}

}
