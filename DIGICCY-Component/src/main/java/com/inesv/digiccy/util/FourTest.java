package com.inesv.digiccy.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.inesv.digiccy.common.ResponseCode;


public class FourTest {

	public static Map<String, Object> voucher(String bankCard, String idCard, String mobile, String realName) {
		Map<String, Object> map = new HashMap<String, Object>();
		  String host = "http://jisubank4.market.alicloudapi.com";
		    String path = "/bankcardverify4/verify";
		    String method = "GET";
		    String appcode = "0a19fa61d8a241b999de0c8ef8ff3b07";
		    Map<String, String> headers = new HashMap<String, String>();
		    //锟斤拷锟斤拷锟絟eader锟叫的革拷式(锟叫硷拷锟斤拷英锟侥空革拷)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("bankcard", bankCard);
		    querys.put("idcard", idCard);
		    querys.put("mobile", mobile);
		    querys.put("realname", realName);


		    try {
		    	/**
		    	* 锟斤拷要锟斤拷示锟斤拷锟斤拷:
		    	* HttpUtils锟斤拷锟�
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		    	* 锟斤拷锟斤拷
		    	*
		    	* 锟斤拷应锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		    	*/
		    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//		    	System.out.println(response);
		    	//锟斤拷取response锟斤拷bod
		    	String json = EntityUtils.toString(response.getEntity());
		    	JSONObject job = new JSONObject(json);
		    	String sta = job.getString("status");
		    	String msg = job.getString("msg");
//		    	String result = job.getString("result");
		    	System.out.println(json+"----------------");
		    	System.out.println(job.toString());
		    	System.out.println("sta:"+sta+"  msg:"+msg);
		    	map.put("status", sta);
		    	map.put("msg", msg);
		    	if("ok".equals(msg)) {
		    		//验证通过
		    		map.put("code", ResponseCode.SUCCESS);
		    		map.put("desc", ResponseCode.SUCCESS_DESC);
		    	}else {
		    		map.put("code", ResponseCode.FAIL);
		    		map.put("desc", ResponseCode.FAIL_DESC);
		    		if(json.contains("verifymsg")) {
		    			map.put("verifymsg", job.getString("verifymsg"));
		    		}
		    	}
//		    	map.put("result", value)
		    } catch (Exception e) {
		    	e.printStackTrace();
	    		map.put("code", ResponseCode.FAIL);
	    		map.put("desc", ResponseCode.FAIL_DESC);
	    		map.put("msg", "error");
		    }
		    return map;
	}
}
