package com.inesv.digiccy.validata;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.api.command.InterfaceCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InterfaceAddressDto;
import com.inesv.digiccy.dto.InterfaceDto;
import com.inesv.digiccy.persistence.inesvaddress.InterfaceOperation;
import com.inesv.digiccy.query.QueryInterface;


@Component
public class InterfaceValidata {
	
	@Autowired
	QueryInterface queryInterface;
	
	@Autowired
	InterfaceOperation interfaceOperation;
	
	@Autowired
	CommandGateway commandGateway;
	
	public Map<String,Object> getAllApi(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<InterfaceDto> list = queryInterface.queryAllInterface();
		if(list != null && list.size() != 0){
			map.put("data", list);
			map.put("total", list.size());
			map.put("code", ResponseCode.SUCCESS);
	        map.put("desc", ResponseCode.SUCCESS_DESC);			
		}else{
			map.put("code", ResponseCode.FAIL);
			map.put("code", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	public Map<String,Object> getApi(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		InterfaceDto dto= queryInterface.queryById(id);
		if(dto != null){
			map.put("data", dto);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		}else{
			map.put("code", ResponseCode.FAIL);
			map.put("code", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	public Map<String,Object> updateState(Integer id,Integer state){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InterfaceCommand command = new InterfaceCommand(id, "", "", state, new Date(), "", "", "", "editState");			
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("code", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	public Map<String,Object> delete(Integer id){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InterfaceCommand command = new InterfaceCommand(id, "", "", 2, new Date(), "", "", "", "delete");			
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("code", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	public Map<String,Object> addApi(String name,Integer state,String remark,String apiName){
		Map<String,Object> map = new HashMap<String, Object>();
		Long timeInMillis = System.currentTimeMillis();
		int radomInt = new Random().nextInt(899) + 100;
		String apiNo = timeInMillis.toString() + String.valueOf(radomInt);
		System.out.println("*******************" + apiNo);
		try {
			InterfaceCommand command = new InterfaceCommand(0, apiNo, name, state, new Date(), remark, apiName, "", "insert");	
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	public Map<String,Object> updateApi(Integer id,String name,Integer state,String remark,String apiName){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InterfaceCommand command = new InterfaceCommand(id, "", name, state, new Date(), remark, apiName, "", "edit");			
			commandGateway.send(command);
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
			map.put("code", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
}
