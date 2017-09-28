package com.inesv.digiccy.controller;

import com.inesv.digiccy.validata.InterfaceValidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Controller
@RequestMapping("/api")
public class InterfaceController {

    @Autowired
    InterfaceValidata InterfaceValidata;
    
    /**
     * 取得API接口
     * @return
     */
    @RequestMapping(value = "/getAllApi", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllApi(){
    	 return InterfaceValidata.getAllApi();
    }
    
    /**
     * 取得指定API接口
     * @return
     */
    @RequestMapping(value = "/getApi", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getApi(Integer id){
    	 return InterfaceValidata.getApi(id);
    }

}
