package com.inesv.digiccy.validata;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.inesv.digiccy.api.command.UserVoucherCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.UserVoucherDto;
import com.inesv.digiccy.query.QueryUserInfo;
import com.inesv.digiccy.query.QueryUserVoucher;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * 
 * @author Liukeling
 *
 */
@Component
public class UserVoucherValidate {

	private static Logger logger = LoggerFactory.getLogger(StaticParamValidata.class);

	@Autowired
	CommandGateway commandGateway;

	@Autowired
	QueryUserVoucher queryUserVoucher;

	// 开始认证身份
	/**
	 * 
	 * @param cardId  证件号码
	 * @param type    证件类型
	 * @param imgUrl1  图片1
	 * @param imgUrl2  图片2
	 * @param imgUrl3  图片3
	 * @param userNo  用户编号
	 * @param realName  真实姓名
	 * @param myvoucherType  自定义证件
	 * @return
	 */
	public Map<String, Object> startVoucher(String cardId, int type, String imgUrl1, String imgUrl2, String imgUrl3,
			int userNo, String realName, String myvoucherType) {
		HashMap<String, Object> map = new HashMap<>();
		
		//验证用户名和身份证号是否一致
//		if(type == 1){
//			Map<String, Object> m = validateCardId(realName, cardId);
//			if(!m.get("code").equals(ResponseCode.SUCCESS)){
//				map.put("msg", "姓名和证件号不符");
//				map.put("code", ResponseCode.FAIL);
//				map.put("desc", ResponseCode.FAIL_DESC);
//				return map;
//			}
//		}
		
		UserVoucherCommand command = new UserVoucherCommand();
		command.setCardId(cardId);
		command.setCardType(type);
		command.setImgUrl1(imgUrl1);
		command.setImgUrl2(imgUrl2);
		command.setImgUrl3(imgUrl3);
		command.setUserNo(userNo);
		command.setRealName(realName);
		command.setMyvoucherType(myvoucherType);
		UserVoucherDto dto = queryUserVoucher.findByUserNo(userNo);
		String operation = "";
		if (dto != null) {
			int state = dto.getState();
			switch (state) {
			case 1:
				operation = "系统正在验证，请耐心等候";
				break;
			case 2:
				operation = "系统正在验证，请耐心等候";
				break;
			case 3:
				operation = "startinsert";
				break;
			case 4:
				operation = "您已认证成功，无须重复申请";
				break;
			default:
				operation = "startupdate";
				break;
			}
		} else {
			operation = "startinsert";
		}

		command.setOperating(operation);
		if ("startinsert".equals(operation) || "startupdate".equals(operation)) {
			try {
				commandGateway.send(command);
				map.put("msg", "成功申请，请耐心等候");
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
			} catch (Exception e) {
				map.put("msg", "申请失败");
				map.put("code", ResponseCode.FAIL);
				map.put("desc", ResponseCode.FAIL_DESC);
			}
		}else{
			map.put("msg", operation);
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
}
