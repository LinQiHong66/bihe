package com.inesv.digiccy.sms;

import com.alibaba.fastjson.JSON;
import com.inesv.digiccy.redis.RedisCodeImpl;
import com.inesv.digiccy.util.SmsDemo;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Component
public class SendMsgUtil implements ApplicationContextAware {
	/**
	 * Logger for this class
	 */
    private  final Logger LOGGER = LoggerFactory.getLogger(SendMsgUtil.class);
	
    @Autowired
	RedisCodeImpl redisCodeImpl;

	
	public Map<String,Object> sendMsg(String mobile, int type, boolean hasContent) {
		Map<String, Object> result = new HashMap<String, Object>();

		Integer code=0;
		if (!redisCodeImpl.getSms(mobile, type).equals(0)) {
			code = redisCodeImpl.getSms(mobile, type);
		} else {
			code = getRandomCode();
		}
		try {
			SmsDemo.sendMessage(mobile, code.toString(), hasContent);
			// 存入缓存
			redisCodeImpl.setSms(mobile, type, code);
			result.put("code",code);
			System.out.println(redisCodeImpl.getSms(mobile, type));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code",500);
			return result;
		}
	}

	// 获取验证码
	public int getRandomCode() {

		return (int) Math.abs(new Random().nextInt(899999) + 100000);
	}

	/**
	 * 
	 * @Title: getSmsTemplateCode
	 * @Description:验证类型获取不同的content
	 * @param type
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws @date
	 *             2016年8月18日 下午12:12:35
	 */
	public String getSmsTemplateCode(int type) {
		String sysTemplate = "";
		switch (type) {
		case 1:// 修改密码
			sysTemplate = SmsConstant.sms_update;
			break;
		case 2:// 支付
			sysTemplate = SmsConstant.sms_payment;
			break;
		default:// 默认

			break;
		}
		return sysTemplate;
	}

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    // TODO Auto-generated method stub
    
  }

}
