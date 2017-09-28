package com.inesv.digiccy.validata;

import com.inesv.digiccy.api.command.CrowdFundingCommand;
import com.inesv.digiccy.api.command.RegUserCommand;
import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.dto.CrowdFundingDetailsDto;
import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.dto.NoticeDto;
import com.inesv.digiccy.query.QueryCrowdFundingInfo;
import com.inesv.digiccy.util.MD5;

import org.apache.commons.collections.map.HashedMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/9 0009.
 */
@Component
public class CrowdFundingValidata {
	
	@Autowired 
	QueryCrowdFundingInfo queryCrowdFundingInfo;
	
	@Autowired
    private CommandGateway commandGateway;
	
	/**
	 * 增加众筹项目
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataAddCrowdFunding(String icoName, String photoName, String photoRemarkName, Integer icoTarget, String icoPriceType, BigDecimal icoPrice, String icoRemark, String icoExplain, Date endDate){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			String icoNo=df.format(new Date()) + ((int)(Math.random()*(8999))+1000);
			BigDecimal icoSumPrice = icoPrice.multiply(new BigDecimal(icoTarget));
			CrowdFundingCommand command = new CrowdFundingCommand(0L,icoNo,icoName,photoName,icoTarget,0,0d,icoPriceType,icoPrice,icoSumPrice,icoRemark,icoExplain,0,new Date(),endDate,photoRemarkName,"","insertCrowdFunding");
	        commandGateway.send(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 修改众筹项目
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataEditCrowdFunding(String icoNo, String icoName, String photoName, String photoRemarkName, Integer icoTarget, String icoPriceType, BigDecimal icoPrice, String icoRemark, String icoExplain,Date endDate){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BigDecimal icoSumPrice = icoPrice.multiply(new BigDecimal(icoTarget));
			CrowdFundingCommand command = new CrowdFundingCommand(0L,icoNo,icoName,photoName,icoTarget,0,0d,icoPriceType,icoPrice,icoSumPrice,icoRemark,icoExplain,0,new Date(),endDate,photoRemarkName,"","updateCrowdFundingBack");
	        commandGateway.sendAndWait(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 删除众筹项目
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public Map<String, Object> validataDeleteCrowdFunding(String icoNo){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CrowdFundingCommand command = new CrowdFundingCommand(0L,icoNo,"","",0,0,0d,"0",new BigDecimal("0"),new BigDecimal("0"),"","",0,new Date(),new Date(),"","","deleteCrowdFunding");
	        commandGateway.sendAndWait(command);
	        map.put("code", ResponseCode.SUCCESS);
	        map.put("desc",ResponseCode.SUCCESS_DESC);
		} catch (Exception e) {
			e.printStackTrace();
            map.put("code",ResponseCode.FAIL);
            map.put("desc",ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFunding(String pageSize,String lineSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrowdFundingDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFunding(pageSize,lineSize);
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingList.size());
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFundingBack() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrowdFundingDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFundingBack();
		System.out.println("********************" + crowdFundingList.size());
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingList.size());
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 取得指定众筹项目
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> validataCrowdFundingInfo(String icoNo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		CrowdFundingDto crowdFunding = queryCrowdFundingInfo.queryCrowdFundingInfo(icoNo);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = format.parse(format.format(crowdFunding.getEnd_date())); 
		crowdFunding.setAttr1(String.valueOf(date.getTime()));
		if (crowdFunding != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", crowdFunding);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 取得指定众筹项目
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> validataCrowdFundingInfoBack(String icoNo){
		Map<String, Object> map = new HashMap<String, Object>();
		CrowdFundingDto crowdFunding = queryCrowdFundingInfo.queryCrowdFundingInfo(icoNo);
		if (crowdFunding != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("data", crowdFunding);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	
	/**
	 * 取得所有众筹项目
	 * @return
	 */
	public Map<String, Object> validataAllCrowdFundingDetailBack() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrowdFundingDetailsDto> crowdFundingList = queryCrowdFundingInfo.queryAllCrowdFundingDetailBack();
		if (crowdFundingList != null) {
			map.put("code", ResponseCode.SUCCESS);
			map.put("desc", ResponseCode.SUCCESS_DESC);
			map.put("total", crowdFundingList.size());
			map.put("data", crowdFundingList);
		} else {
			map.put("code", ResponseCode.FAIL);
			map.put("desc", ResponseCode.FAIL_DESC);
		}
		return map;
	}
	/**
	 * 定时器修改众筹项目状态
	 * @return
	 */
	public void validataEditCrowdState(){
		CrowdFundingCommand command = new CrowdFundingCommand(0L,"","","",0,0,0d,"",new BigDecimal(0),new BigDecimal(0),"","",0,new Date(),new Date(),"","","updateCrowdFundingState");
	    commandGateway.sendAndWait(command);
	}
}
