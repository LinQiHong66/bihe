package com.inesv.digiccy.validata.user;

import com.inesv.digiccy.api.command.InesvPhoneCommand;
import com.inesv.digiccy.api.command.MyrecCommand;
import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.api.command.UserVoucherCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.dto.UserVoucherDto;
import com.inesv.digiccy.query.QueryMyRecInfo;
import com.inesv.digiccy.query.QueryUserInfo;
import com.inesv.digiccy.query.QueryUserNamePhoneInfo;
import com.inesv.digiccy.redis.RedisCodeImpl;
import com.inesv.digiccy.sms.SendMsgUtil;
import com.inesv.digiccy.util.MD5;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
@Component
public class OpUserValidata {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    RedisCodeImpl redisCode;

    @Autowired
    SendMsgUtil sendMsgUtil;

    @Autowired
    QueryUserNamePhoneInfo queryUserNamePhoneInfo;

    @Autowired
    QueryUserInfo queryUser;

    @Autowired
    QueryMyRecInfo queryMyRecInfo;
    
    /*
     * 测试
     */
    public Map<String,Object> validateGetUsers(){
    	Map<String,Object> map=new HashMap<>();
    	List<InesvUserDto> list=queryUser.getAllUsers();
    	if(list==null){
    		map.put("code", ResponseCode.FAIL_BILL_INFO);
    		map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
    	}else{
    		map.put("data", list);
    		map.put("code", ResponseCode.SUCCESS);
    		map.put("desc", ResponseCode.SUCCESS_DESC);
    	}
    	return map;
    }
    //修改状态
    public Map<String, Object> modifyVoucher(int userNo, int state){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<UserVoucherDto> vouchers = queryUser.getAllVoucher("userNo",userNo+"");
    	if(vouchers != null && !vouchers.isEmpty()){
    		UserVoucherDto dto = vouchers.get(0);
    		UserVoucherCommand command = new UserVoucherCommand();
    		command.setUserNo(userNo);
    		command.setState(state);
    		command.setRealName(dto.getTrueName());
    		command.setCardType(dto.getCardType());
    		command.setCardId(dto.getCardId());
    		command.setOperating("modifystate");
    		try{
    			commandGateway.send(command);
    			map.put("code", ResponseCode.SUCCESS);
    			map.put("desc", ResponseCode.SUCCESS_DESC);
    		}catch(Exception e){
    			map.put("code", ResponseCode.FAIL);
    			map.put("desc", ResponseCode.FAIL_DESC);
    		}
    	}else{
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
    	}
    	return map;
    }
    
    
    //根据条件查询
    public Map<String, Object> getAllVoucher(String filed, String value){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<UserVoucherDto> vouchers = queryUser.getAllVoucher(filed, value);
    	if(vouchers != null){
    		map.put("code", ResponseCode.SUCCESS);
    		map.put("desc", ResponseCode.SUCCESS_DESC);
    		map.put("result", vouchers);
    	}else{
    		map.put("code", ResponseCode.FAIL_BILL_INFO);
    		map.put("desc", ResponseCode.FAIL_BILL_INFO_DESC);
    		map.put("result", "none");
    	}
    	return map;
    }
    
    /**
     * 查询所有用户
     * @return
     */
    public Map<String , Object> validataGetAllUser(){
        Map<String ,Object> map = new HashMap<>();
        List<InesvUserDto> list = queryUser.getAllUser();
        if(list == null){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", list);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }

    public Map<String , Object> validataGetUserInfoById(Long id){
        Map<String ,Object> map = new HashMap<>();
        InesvUserDto info = queryUser.getUserInfoById(id);
        if(info == null){
            map.put("code", ResponseCode.FAIL_BILL_INFO);
            map.put("desc",ResponseCode.FAIL_BILL_INFO_DESC);
        }else {
            map.put("data", info);
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc", ResponseCode.SUCCESS_DESC);
        }
        return map;
    }


    /**
     *新增用户
     */
    public Map<String , Object> validataRegUser(String phone,String password, String invite_num){
        Map<String , Object> map = new HashMap<>();
        try {
        	if(phone.equals("") || password.equals("")  ){
        		map.put("code",ResponseCode.REG_FAIL_NULL);
                map.put("desc",ResponseCode.REG_FAIL_NULL_DESC);
                return map;
        	}
            RegUserCommand command = new RegUserCommand(0,phone,0,new MD5().getMD5(password),null,null,null,null,null,phone,0,creatRecCode().toUpperCase(),new Date(),"insert");
            commandGateway.sendAndWait(command);
            RegUserCommand upcommand = new RegUserCommand(0, "", 0, "", "", "", "", "", "", phone, 0, "", new Date(), "updateId");
            commandGateway.send(upcommand);
            System.out.println("invitenum:::::::::::::::"+invite_num);
           if(!"".equals(invite_num) && invite_num != null){
                UserInfoDto userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(invite_num);//根据邀请码查询出此邀请码的用户信息
                if(userInfoDto != null){
                    int userNo = userInfoDto.getUser_no(); //获取推荐码用户的编号
                    UserInfoDto ReguserInfo = queryMyRecInfo.queryUserInfoByUserName(phone);//查询出注册用户的用户编号
                    int ReguserNo = ReguserInfo.getUser_no();//获取注册用户编号
                    MyrecCommand command1 = new MyrecCommand(2121L,userNo,ReguserNo,1,0,new Date(),"inserRecUser");
                    commandGateway.send(command1);
                }
            }
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }
        return map;

    }
    
    /** 生成推荐码 */
	public String creatRecCode() {
		String result = getCode();
		
		UserInfoDto userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(result);
//		if(userInfoDto != null){
//			result = getCode();
//		}
		boolean ok = userInfoDto != null;
		while(ok){
			result = getCode();
			userInfoDto = queryMyRecInfo.queryUserInfoByInvitNum(result);
			ok = userInfoDto != null;
		}
		
		return result;
	}
	public String getCode(){

		String result = "";
		for (int i = 0; i < 7; i++) {
			int intVal = (int) (Math.random() * 26 + 97);
			result = result + (char) intVal;
		}
		return result;
	}
    /**
     * 忘记用户密码
     */
    public Map<String,Object> validataUpdatePwd(String username,String password){
        Map<String,Object> result = new HashMap<>();
        List<InesvUserDto> list = queryUserNamePhoneInfo.getUserNamePhoneInfo(username);
        List<InesvUserDto> dtos = queryUserNamePhoneInfo.getUserNamePhoneInfo(username);
        if(dtos.size() > 0){
        if(list.isEmpty()){
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        	result.put("result", "修改失败");
        }else {
        	InesvUserDto user=list.get(0);
        	Long id=user.getId();
            RegUserCommand command = new RegUserCommand(new Integer(id.intValue()),user.getUsername(),user.getUser_no(),new MD5().getMD5(password),user.getRegion(),user.getReal_name(),user.getCertificate_num(),user.getDeal_pwd(),user.getMail(),user.getPhone(),user.getState(),user.getInvite_num(),user.getDate(),"update");
            commandGateway.sendAndWait(command);
            result.put("code", ResponseCode.SUCCESS);
            result.put("desc",ResponseCode.SUCCESS_DESC);
        }
        }else{
        	result.put("result", "不存在该号码");
            result.put("code",ResponseCode.FAIL);
            result.put("desc",ResponseCode.FAIL_DESC);
        }
        return result;
    }

    /**
     * 校验短信验证码
     */
    public Map<String,Object> validataCompare(String mobile,String code) {
        Map<String, Object> map = new HashMap<>();
        int smsNum=redisCode.getSms(mobile, 1);//获取缓存里里面的验证码
        //int smsNum=725582;
        // 通用 手机号码验证
        if (code.equals(smsNum+"")) { 
        	redisCode.delete(mobile, 1);//清除缓存
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);

        }else{
            map.put("code", ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /**
     * 发送短信
     *
     * @param mobile
     * @param type
     * @return
     */
    public Map<String , Object> validataSend(String mobile, int type) {
        Map<String , Object> map = new HashMap<>();
        map = sendMsgUtil.sendMsg(mobile,type,true);
        int code= Integer.parseInt(String.valueOf(map.get("code")));
        InesvPhoneCommand command = new InesvPhoneCommand(0,null,mobile,1,code,"insert");
        commandGateway.sendAndWait(command);
        if (!map.get("code").equals(500) ) {
            map.put("code", ResponseCode.SUCCESS);
            map.put("desc",ResponseCode.SUCCESS_DESC);
            map.put("validataCode", code);
        }else{
            map.put("code", ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
        }
        return map;
    }

    /**
     * 判断该用户是否已注册
     * @param phone
     * @return
     */
	public Map<String, Object> phoneIsUnique(String phone) {
		Map<String,Object> result = new HashMap<>();
		InesvUserDto user= queryUser.getPhoneIsUnique(phone);
		if(user!=null){//不为说明该手机号已经存在了（已有该手机号对应的用户）
			result.put("code", ResponseCode.REG_FAIL_NOT_UNIQUE);
			result.put("desc",ResponseCode.REG_FAIL_NOT_UNIQUE_DESC);
		}else{
			result.put("code", ResponseCode.SUCCESS);
			result.put("desc",ResponseCode.SUCCESS_DESC);
		}
		return result;
	}


}
