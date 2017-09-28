package com.inesv.digiccy.api.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CoinDto;
import com.inesv.digiccy.dto.InterfaceAddressDto;
import com.inesv.digiccy.dto.InterfaceDto;
import com.inesv.digiccy.dto.KDealDetailDto;
import com.inesv.digiccy.dto.UserInfoDto;
import com.inesv.digiccy.query.QueryDealDetailInfo;
import com.inesv.digiccy.query.QueryInterface;
import com.inesv.digiccy.query.QueryInterfaceDetail;
import com.inesv.digiccy.query.QuerySubCore;
import com.inesv.digiccy.query.coin.QueryCoin;
import com.inesv.digiccy.util.MD5;
import com.inesv.digiccy.validata.BuyEntrustDepthValidata;
import com.inesv.digiccy.validata.EntrustDealValidate;
import com.inesv.digiccy.validata.TradeValidata;
import com.inesv.digiccy.validata.UserBalanceValidate;
import com.inesv.digiccy.validata.CoinValidate;
 
@Controller
@RequestMapping("/API")
public class ApiController {
	
	@Autowired
	QueryInterface queryInterface;
	
	@Autowired
	QueryCoin queryCoin;
	
	@Autowired
    QuerySubCore querySubCore;
	
	@Autowired
	CoinValidate coinValidate;
	
	@Autowired
	QueryInterfaceDetail queryInterfaceDetail;
	
	@Autowired
	QueryDealDetailInfo queryDealDetailInfo;
	
	@Autowired
	TradeValidata tradeValidata;
	
	@Autowired
	UserBalanceValidate balanceValidate;
	
	@Autowired
	BuyEntrustDepthValidata entrustValidata;
	
	@Autowired
	EntrustDealValidate entrustDealValidate;
	
	/**
	 * 市场深度图
	 */
	@RequestMapping(value = "/depth",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> Depth(String sign, Integer coin_no) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InterfaceAddressDto detailDto = queryInterfaceDetail.queryDetailBySign(sign);
			if(detailDto == null) {
				map.put("code", 200);
				map.put("desc", "签名错误无效");
				return map;
			}
			if(detailDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "用户未申请市场深度图API或申请未通过");
				return map;
			}
			InterfaceDto interfaceDto = queryInterface.queryById(Integer.valueOf(detailDto.getAddress_no()));
			interfaceDto = queryInterface.queryByApi_no(interfaceDto.getApi_no());
			if (interfaceDto == null) {
				map.put("code", 200);
				map.put("desc", "API接口不存在，具体情况请查看看官网API，抱歉！");
				return map;
			}
			if (interfaceDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "API接口暂未启动，具体情况看官网通知，抱歉！");
				return map;
			}
			if (interfaceDto.getAttr1()==null || !interfaceDto.getAttr1().equals("depth")) {
				map.put("code", 200);
				map.put("desc", "请求接口名称有误，请联系管理人员！");
				return map;
			}
			map = entrustValidata.queryEntrustByEntrustCoinOrderBy(coin_no);
		} catch (Exception e) {
			map.put("code", 200);
			map.put("desc", "查询市场深度图失败！");
		}
		return map;
	}
	
	/**
	 * 市场行情图
	 */
	@RequestMapping(value = "/market",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> Market(String sign, String coin_no, String market_type) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InterfaceAddressDto detailDto = queryInterfaceDetail.queryDetailBySign(sign);
			if(detailDto == null) {
				map.put("code", 200);
				map.put("desc", "签名错误无效！");
				return map;
			}
			if(detailDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "用户未申请市场行情图API或申请未通过");
				return map;
			}
			InterfaceDto interfaceDto = queryInterface.queryById(Integer.valueOf(detailDto.getAddress_no()));
			interfaceDto = queryInterface.queryByApi_no(interfaceDto.getApi_no());
			if (interfaceDto == null) {
				map.put("code", 200);
				map.put("desc", "API接口不存在，具体情况请查看看官网API，抱歉！");
				return map;
			}
			if (interfaceDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "API接口暂未启动，具体情况看官网通知，抱歉！");
				return map;
			}
			if (interfaceDto.getAttr1()==null || !interfaceDto.getAttr1().equals("market")) {
				map.put("code", 200);
				map.put("desc", "请求接口名称有误，请联系管理人员！");
				return map;
			}
			if(Integer.valueOf(coin_no) == 0) {	//全部市场行情
				Map<String,Object> resultMap = new HashMap<String,Object>();
				List<CoinDto> coinList = queryCoin.queryAllCoin(1);
				for(CoinDto coin : coinList) {
					List<KDealDetailDto> list = queryDealDetailInfo.queryDealDetailInfoByType(coin.getCoin_no().toString(),market_type);
					Object[][] kDealDetailDtoList = new Object[list.size()][6];
			        for(int i=0;i<list.size();i++){
			        	for(int j=0;j<6;j++){
			        		if(j==0){
			        			kDealDetailDtoList[i][j] = Long.valueOf(list.get(i).getEnd_date_num() + "000");
			        		}if(j==1){
			        			kDealDetailDtoList[i][j] = list.get(i).getBegin_price();
			        		}if(j==2){
			        			kDealDetailDtoList[i][j] = list.get(i).getMax_price();
			        		}if(j==3){
			        			kDealDetailDtoList[i][j] = list.get(i).getMin_price();
			        		}if(j==4){
			        			kDealDetailDtoList[i][j] = list.get(i).getEnd_price();
			        		}if(j==5){
			        			kDealDetailDtoList[i][j] = Double.valueOf(list.get(i).getDeal_num());
			        		}
			        	}
			        }
			        resultMap.put(coin.getCoin_core(), kDealDetailDtoList);
				}
				map.put("data", resultMap);
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
				if(resultMap != null) {
					map.put("data", resultMap);
					map.put("code", ResponseCode.SUCCESS);
					map.put("desc", ResponseCode.SUCCESS_DESC);
				}else {
					map.put("code", 200);
		        	map.put("desc", "查询市场行情图失败！");
		        	return map;
				}
			}else {	//单独市场行情
				List<KDealDetailDto> list = queryDealDetailInfo.queryDealDetailInfoByType(coin_no,market_type);
				Object[][] kDealDetailDtoList = new Object[list.size()][6];
		        for(int i=0;i<list.size();i++){
		        	for(int j=0;j<6;j++){
		        		if(j==0){
		        			kDealDetailDtoList[i][j] = Long.valueOf(list.get(i).getEnd_date_num() + "000");
		        		}if(j==1){
		        			kDealDetailDtoList[i][j] = list.get(i).getBegin_price();
		        		}if(j==2){
		        			kDealDetailDtoList[i][j] = list.get(i).getMax_price();
		        		}if(j==3){
		        			kDealDetailDtoList[i][j] = list.get(i).getMin_price();
		        		}if(j==4){
		        			kDealDetailDtoList[i][j] = list.get(i).getEnd_price();
		        		}if(j==5){
		        			kDealDetailDtoList[i][j] = Double.valueOf(list.get(i).getDeal_num());
		        		}
		        	}
		        }
		        map.put("data", kDealDetailDtoList);
	        	map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
		        if(list != null) {
		        	map.put("data", kDealDetailDtoList);
		        	map.put("code", ResponseCode.SUCCESS);
					map.put("desc", ResponseCode.SUCCESS_DESC);
		        }else {
		        	map.put("code", 200);
		        	map.put("desc", "查询市场行情图失败！");
		        	return map;
		        }
			}
		} catch (Exception e) {
			map.put("code", 200);
			map.put("desc", "查询市场行情图失败！");
		}
		return map;
	}
	
	/**
	 * 个人资产
	 */
	@RequestMapping(value = "/balance",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> Balance(Integer user_no,String sign,String deal_pwd) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InterfaceAddressDto detailDto = queryInterfaceDetail.queryDetailBySign(sign);
			if(detailDto == null) {
				map.put("code", 200);
				map.put("desc", "签名错误无效！");
				return map;
			}
			if(detailDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "用户未申请市场行情图API或申请未通过");
				return map;
			}
			if(user_no != detailDto.getUser_no()) {
				map.put("code", 200);
				map.put("desc", "抱歉，" + sign + "签名只能查看用户编号为：" + detailDto.getUser_no() + "的个人资产！");
				return map;
			}
			InterfaceDto interfaceDto = queryInterface.queryById(Integer.valueOf(detailDto.getAddress_no()));
			interfaceDto = queryInterface.queryByApi_no(interfaceDto.getApi_no());
			if (interfaceDto == null) {
				map.put("code", 200);
				map.put("desc", "API接口不存在，具体情况请查看看官网API，抱歉！");
				return map;
			}
			if (interfaceDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "API接口暂未启动，具体情况看官网通知，抱歉！");
				return map;
			}
			if (interfaceDto.getAttr1()==null || !interfaceDto.getAttr1().equals("balance")) {
				map.put("code", 200);
				map.put("desc", "请求接口名称有误，请联系管理人员！");
				return map;
			}
			UserInfoDto uid = querySubCore.getUserInfo(user_no);
	        if(uid == null){
	            map.put("code",200);
	            map.put("desc",user_no + "用户不存在！");
	            return map;
	        }
	        if(!new MD5().getMD5(deal_pwd).equals(uid.getDeal_pwd())){
	            map.put("code",200);
	            map.put("desc","交易密码错误！");
	            return map;
	        }
			map = coinValidate.validateBalanceInfo(user_no);
		} catch (Exception e) {
			map.put("code", 200);
			map.put("desc", "查询个人资产失败！");
		}
		return map;
	}
	
	/**
	 * 委托记录
	 */
	@RequestMapping(value = "/entrust",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> Entrust(Integer user_no,String sign,String deal_pwd) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InterfaceAddressDto detailDto = queryInterfaceDetail.queryDetailBySign(sign);
			if(detailDto == null) {
				map.put("code", 200);
				map.put("desc", "签名错误无效！");
				return map;
			}
			if(detailDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "用户未申请市场行情图API或申请未通过");
				return map;
			}
			if(user_no != detailDto.getUser_no()) {
				map.put("code", 200);
				map.put("desc", "抱歉，" + sign + "签名只能查看用户编号为：" + detailDto.getUser_no() + "的个人委托记录！");
				return map;
			}
			InterfaceDto interfaceDto = queryInterface.queryById(Integer.valueOf(detailDto.getAddress_no()));
			interfaceDto = queryInterface.queryByApi_no(interfaceDto.getApi_no());
			if (interfaceDto == null) {
				map.put("code", 200);
				map.put("desc", "API接口不存在，具体情况请查看看官网API，抱歉！");
				return map;
			}
			if (interfaceDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "API接口暂未启动，具体情况看官网通知，抱歉！");
				return map;
			}
			if (interfaceDto.getAttr1()==null || !interfaceDto.getAttr1().equals("entrust")) {
				map.put("code", 200);
				map.put("desc", "请求接口名称有误，请联系管理人员！");
				return map;
			}
			UserInfoDto uid = querySubCore.getUserInfo(user_no);
	        if(uid == null){
	            map.put("code",200);
	            map.put("desc",user_no + "用户不存在！");
	            return map;
	        }
	        if(!new MD5().getMD5(deal_pwd).equals(uid.getDeal_pwd())){
	            map.put("code",200);
	            map.put("desc","交易密码错误！");
	            return map;
	        }
	        map = entrustDealValidate.validataEntrustByUserNo(user_no);
		} catch (Exception e) {
			map.put("code", 200);
			map.put("desc", "查询个人委托记录失败！");
		}
		return map;
	}
	
	/**
	 * 撤销委托记录
	 */
	@RequestMapping(value = "/cancel",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> Cancel(Integer user_no,String sign,Long entrust_no,String deal_pwd) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InterfaceAddressDto detailDto = queryInterfaceDetail.queryDetailBySign(sign);
			if(detailDto == null) {
				map.put("code", 200);
				map.put("desc", "签名错误无效！");
				return map;
			}
			if(detailDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "用户未申请市场行情图API或申请未通过");
				return map;
			}
			if(user_no != detailDto.getUser_no()) {
				map.put("code", 200);
				map.put("desc", "抱歉，" + sign + "签名只能撤销用户编号为：" + detailDto.getUser_no() + "的个人委托！");
				return map;
			}
			InterfaceDto interfaceDto = queryInterface.queryById(Integer.valueOf(detailDto.getAddress_no()));
			interfaceDto = queryInterface.queryByApi_no(interfaceDto.getApi_no());
			if (interfaceDto == null) {
				map.put("code", 200);
				map.put("desc", "API接口不存在，具体情况请查看看官网API，抱歉！");
				return map;
			}
			if (interfaceDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "API接口暂未启动，具体情况看官网通知，抱歉！");
				return map;
			}
			if (interfaceDto.getAttr1()==null || !interfaceDto.getAttr1().equals("cancel")) {
				map.put("code", 200);
				map.put("desc", "请求接口名称有误，请联系管理人员！");
				return map;
			}
			UserInfoDto uid = querySubCore.getUserInfo(user_no);
	        if(uid == null){
	            map.put("code",200);
	            map.put("desc",user_no + "用户不存在！");
	            return map;
	        }
	        if(!new MD5().getMD5(deal_pwd).equals(uid.getDeal_pwd())){
	            map.put("code",200);
	            map.put("desc","交易密码错误！");
	            return map;
	        }
	        map = tradeValidata.validateDelEntrust(entrust_no, user_no);
		} catch (Exception e) {
			map.put("code",200);
            map.put("desc","撤销委托失败！");
		}
		return map;
	}
	
	/**
	 * 挂单操作
	 */
	@ResponseBody
	@RequestMapping(value = "/trade",method = RequestMethod.POST)
	public Map<String,Object> Trade(Integer user_no, String sign, String trade_num, String trade_price, 
			String coin_type, String trade_type, String deal_pwd) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(Double.valueOf(trade_price) <= 0) {
				map.put("code", 200);
				map.put("desc", "委托价格异常！");
				return map;
			}
			if(Double.valueOf(trade_num) <= 0.01) {
				map.put("code", 200);
				map.put("desc", "委托数量不能低于0.01");
				return map;
			}
			if(!trade_type.equals("sell") && !trade_type.equals("buy")) {
				map.put("code", 200);
				map.put("desc", "委托类型异常！");
				return map;
			}
			InterfaceAddressDto detailDto = queryInterfaceDetail.queryDetailBySign(sign);
			if(detailDto == null) {
				map.put("code", 200);
				map.put("desc", "签名错误无效！");
				return map;
			}
			if(detailDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "用户未申请市场行情图API或申请未通过");
				return map;
			}
			if(user_no != detailDto.getUser_no()) {
				map.put("code", 200);
				map.put("desc", "抱歉，" + sign + "签名只能挂用户编号：" + detailDto.getUser_no() + "的委托！");
				return map;
			}
			InterfaceDto interfaceDto = queryInterface.queryById(Integer.valueOf(detailDto.getAddress_no()));
			interfaceDto = queryInterface.queryByApi_no(interfaceDto.getApi_no());
			if (interfaceDto == null) {
				map.put("code", 200);
				map.put("desc", "API接口不存在，具体情况请查看看官网API，抱歉！");
				return map;
			}
			if (interfaceDto.getState() != 1) {
				map.put("code", 200);
				map.put("desc", "API接口暂未启动，具体情况看官网通知，抱歉！");
				return map;
			}
			if (interfaceDto.getAttr1()==null || !interfaceDto.getAttr1().equals("trade")) {
				map.put("code", 200);
				map.put("desc", "请求接口名称有误，请联系管理人员！");
				return map;
			}
			UserInfoDto uid = querySubCore.getUserInfo(user_no);
	        if(uid == null){
	            map.put("code",200);
	            map.put("desc",user_no + "用户不存在！");
	            return map;
	        }
	        if(!new MD5().getMD5(deal_pwd).equals(uid.getDeal_pwd())){
	            map.put("code",200);
	            map.put("desc","交易密码错误！");
	            return map;
	        }
	        map = tradeValidata.validateTradeCoinActual(user_no.toString(), new BigDecimal(trade_num),
	        		new BigDecimal(trade_price), new BigDecimal(0), deal_pwd, coin_type, trade_type);
		} catch (Exception e) {
			map.put("code",200);
            map.put("desc","委托挂单操作失败！");
		}
		return map;
	}
	
}
