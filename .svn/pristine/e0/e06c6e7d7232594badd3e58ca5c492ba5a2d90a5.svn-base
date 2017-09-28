package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.RmbRechargeValidate;
import com.inesv.digiccy.validata.RmbWithdrawValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by JimJim on 2016/12/12 0012.
 */
@Controller
@RequestMapping("/rmb")
public class RmbController {

    @Autowired
    RmbRechargeValidate rmbRechargeValidate;

    @Autowired
    RmbWithdrawValidate rmbWithdrawValidate;

    @RequestMapping(value = "gotoRecharge",method = RequestMethod.GET)
    public String gotoRecharge(){
        return "/rmb/recharge";
    }

    @RequestMapping(value = "gotoWithdraw",method = RequestMethod.GET)
    public String gotoWithdraw(){
        return "/rmb/withdraw";
    }

    @RequestMapping(value = "getRecharge",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRecharge(String userName, String state, String startDate, String endDate){
        Map<String,Object> map = rmbRechargeValidate.validateQueryRecord(userName, state, startDate, endDate);
        return map;
    }

    @RequestMapping(value = "getWithdraw",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getWithdraw(String userName, String state, String startDate, String endDate){
        Map<String,Object> map = rmbWithdrawValidate.validateQueryRecord(userName, state, startDate, endDate);
        return map;
    }


    @RequestMapping(value = "doRecharge",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doRecharge(String recId,String name,String price ){
        Map<String,Object> map = rmbRechargeValidate.confirmToAccount(Long.valueOf(recId), Integer.valueOf(name),new BigDecimal(price));
        return map;
    }

    @RequestMapping(value = "doWithdraw",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doWithdraw(String recId,String name,String price){
        Map<String,Object> map = rmbWithdrawValidate.confirmToAccount(Long.valueOf(recId), Integer.valueOf(name),new BigDecimal(price));
        return map;
    }
    
    @RequestMapping(value="getRechargeExcel")
    public void getRechargeExcel(HttpServletResponse response, String userName, String state, String startDate, String endDate){
    	rmbRechargeValidate.getExcel(response, userName, state, startDate, endDate);
    }

    @RequestMapping(value="getWithdrawExcel")
    public void getWithdraweExcel(HttpServletResponse response, String userName, String state, String startDate, String endDate){
    	rmbWithdrawValidate.getWithdrawExcel(response, userName, state, startDate, endDate);
    }
}
