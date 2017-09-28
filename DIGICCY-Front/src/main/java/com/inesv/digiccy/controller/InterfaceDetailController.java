package com.inesv.digiccy.controller;

import com.inesv.digiccy.validata.InterfaceDetailValidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Controller
@RequestMapping("/apiDetail")
public class InterfaceDetailController {

    @Autowired
    InterfaceDetailValidata InterfaceDetailValidata;
    
    /**
     * 申请API接口
     * @return
     */
    @RequestMapping(value = "/addApiDetail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addApiDetail(Long userNo,String addressNo) throws Exception{
    	 return InterfaceDetailValidata.addApiDetail(userNo,addressNo);
    }
    
    /**
     * 取得指定用户API接口
     * @return
     */
    @RequestMapping(value = "/getApiDetail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getApiDetail(String userNo){
    	 return InterfaceDetailValidata.getApiDetailByUserNo(userNo);
    }
    
    /**
     * 根据状态，取得指定用户申请的API接口
     * @return
     */
    @RequestMapping(value = "/getApiDetailByState", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getApiDetailByState(String userNo,String state){
    	 return InterfaceDetailValidata.getApiDetailByState(userNo,state);
    }

}
