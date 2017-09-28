package com.inesv.digiccy.validata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.ApiDepthDto;
import com.inesv.digiccy.query.QueryEntrustInfo;

@Component
public class BuyEntrustDepthValidata {
	
	@Autowired
	QueryEntrustInfo queryEntrustInfo;
	
	public Map<String,Object> queryEntrustByEntrustCoinOrderBy(Integer entrust_coin){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<ApiDepthDto> buy_list = queryEntrustInfo.queryBuyEntrustByEntrustCoin(entrust_coin);
			List<ApiDepthDto> sell_list = queryEntrustInfo.querySellEntrustByEntrustCoin(entrust_coin);
			if(buy_list.size() != 0 || sell_list.size() != 0){
				map.put("code", ResponseCode.SUCCESS);
				map.put("desc", ResponseCode.SUCCESS_DESC);
				map.put("buy_list", buy_list);//深度图买的记录
				map.put("sell_list", sell_list);//深度图卖的记录
			}else{
				map.put("code", 200);
				map.put("desc", ResponseCode.FAIL_BUY_ENTRUST_INFO_CODE_DESC);
			}
		} catch (Exception e) {
			map.put("code", 200);
			map.put("desc", ResponseCode.FAIL_BUY_ENTRUST_INFO_CODE_DESC);
		}
		return map;
	}
}
