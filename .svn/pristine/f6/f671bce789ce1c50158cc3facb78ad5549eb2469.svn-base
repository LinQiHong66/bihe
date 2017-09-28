package com.inesv.digiccy.back.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.validata.UserSetValidata;

@Controller
@RequestMapping("/userset")
public class UserSetController {

	@Autowired
	UserSetValidata usersetvalidata;

	@RequestMapping(value = "userset", method = RequestMethod.GET)
	public String getLoginLimit() {

		return "/param/loginlimit";
	}

	@RequestMapping(value = "modifyuserset", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyUserset(int id, int limittime, int time, String username, String ip) {
		return usersetvalidata.modifyUserSetInfo(id, limittime, time, new Date(), ip, username);
	}

	@RequestMapping(value = "getUserSet", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserSet() {
		return usersetvalidata.getUserSet();
	}

}
