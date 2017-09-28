package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.InterfaceDetailValidata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by JimJim on 2016/12/2 0002.
 */
@Controller
@RequestMapping("/apiDetail")
public class InterfaceDetailController {

    private static Logger logger = LoggerFactory.getLogger(InterfaceDetailController.class);

    @Autowired
    InterfaceDetailValidata interfaceDetailValidata;

    @RequestMapping(value = "gotoApiDetail",method = RequestMethod.GET)
    public String gotoCount(){
        return "/api/apiDetail";
    }

    /*
     * 取得所有API申请
     */
    @RequestMapping(value = "getApiDetail",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getApiDetail(){
        Map<String,Object> map = interfaceDetailValidata.getAllApiDetail();
        return map;
    }
    
    /*
     * 修改申请API状态
     */
    @RequestMapping(value = "updateApiDetailState",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateApiDetailState(Integer id){
    	Map<String,Object> map = interfaceDetailValidata.editApiDetail(1, id);
        return map;
    }

}

