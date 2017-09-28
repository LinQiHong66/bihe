package com.inesv.digiccy.back.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.back.utils.QiniuUploadUtil;
import com.inesv.digiccy.dto.CrowdFundingDto;
import com.inesv.digiccy.util.AbstractAction;
import com.inesv.digiccy.validata.CrowdFundingValidata;
import com.inesv.digiccy.validata.coin.CoinValidata;

/**
 * Created by Administrator on 2016/06/05 0016.
 */
@Controller
@RequestMapping("/crowFundingDetail")
public class CrowdFundingDetailController{
	
	@Autowired
	CrowdFundingValidata crowdFundingValidata;
	
	@RequestMapping(value = "gotoCrowdDetail",method = RequestMethod.GET)
    public String gotoCrowd(){
        return "/crowd/crowdDetail";
    }
	
	/**
     * 得到所有众筹项目的信息
     * @return
     */
    @RequestMapping(value = "getAllCrowdFundingDetail" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllCrowdFundingDetail(){
        Map<String,Object> map = crowdFundingValidata.validataAllCrowdFundingDetailBack();
        return map;
    }
}
