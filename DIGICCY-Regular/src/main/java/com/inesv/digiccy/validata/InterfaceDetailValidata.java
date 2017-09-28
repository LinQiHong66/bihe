package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.InterfaceAddressCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InterfaceAddressDto;
import com.inesv.digiccy.query.QueryInterfaceDetail;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Component
public class InterfaceDetailValidata {

	@Autowired
	QueryInterfaceDetail queryInterfaceDetail;

	@Autowired
	CommandGateway commandGateway;

	public Map<String, Object> getApiDetailByUserNo(String userNo) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<InterfaceAddressDto> list = queryInterfaceDetail.queryDetailByUser(userNo);
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
	        map.put("desc", ResponseCode.SUCCESS_DESC);	
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
	        map.put("desc", ResponseCode.FAIL_DESC);	
		}
		return map;
	}
	
	public Map<String, Object> getApiDetailByState(String userNo,String state) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<InterfaceAddressDto> list = queryInterfaceDetail.queryDetailByUserAndState(userNo,state);
			map.put("data", list);
			map.put("code", ResponseCode.SUCCESS);
	        map.put("desc", ResponseCode.SUCCESS_DESC);	
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
	        map.put("desc", ResponseCode.FAIL_DESC);	
		}
		return map;
	}
	
	public Map<String, Object> getAllApiDetail() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<InterfaceAddressDto> list = queryInterfaceDetail.queryAllDetail();
			map.put("data", list);
			map.put("total", list.size());
			map.put("code", ResponseCode.SUCCESS);
	        map.put("desc", ResponseCode.SUCCESS_DESC);	
		} catch (Exception e) {
			map.put("code", ResponseCode.FAIL);
	        map.put("desc", ResponseCode.FAIL_DESC);	
		}
		return map;
	}

	public Map<String, Object> addApiDetail(Long userNo, String addressNo) throws Exception{
		Map<String, Object> map = new HashMap<>();
		List<InterfaceAddressDto> list = queryInterfaceDetail.queryDetailByUserAndAddressNo(userNo.toString(),addressNo);
		if(list.size() >= 1) {
			map.put("code", ResponseCode.FAIL);
	        map.put("desc", "该用户已申请过该API接口！");	
	        return map;
		}
		try {
			InterfaceAddressCommand command = new InterfaceAddressCommand(0,userNo,addressNo,0,new Date(),"","","","insert");
	        commandGateway.send(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
	        map.put("desc", ResponseCode.FAIL_DESC);	
		}
		return map;
	}
	
	public Map<String, Object> editApiDetail(Integer state,Integer id) {
		Map<String, Object> map = new HashMap<>();
		String sign = creatRecCode().toUpperCase();
		try {
			InterfaceAddressCommand command = new InterfaceAddressCommand(id,0,"",state,new Date(),sign,"","","updateState");
	        commandGateway.send(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", ResponseCode.FAIL);
	        map.put("desc", ResponseCode.FAIL_DESC);	
		}
		return map;
	}

	/** 生成sign*/
	public String creatRecCode() {
		String sign = getCode();
		InterfaceAddressDto dto = queryInterfaceDetail.queryDetailBySign(sign);
		boolean ok = dto != null;
		while(ok){
			sign = getCode();
			dto = queryInterfaceDetail.queryDetailBySign(sign);
			ok = dto != null;
		}
		return sign;
	}
	
	public String getCode(){
		String sign = "";
		for (int i = 0; i < 12; i++) {
			int intVal = (int) (Math.random() * 26 + 97);
			sign = sign + (char) intVal;
		}
		return sign;
	}

}
