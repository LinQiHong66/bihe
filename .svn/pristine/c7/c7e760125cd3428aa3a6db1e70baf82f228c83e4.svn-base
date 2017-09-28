package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.InterfaceValidata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/2 0002.
 */
@Controller
@RequestMapping("/api")
public class InterfaceController {

    private static Logger logger = LoggerFactory.getLogger(InterfaceController.class);

    @Autowired
    InterfaceValidata interfaceValidata;

    @RequestMapping(value = "gotoApi",method = RequestMethod.GET)
    public String gotoApi(){
        return "/api/api";
    }
    
    @RequestMapping(value = "gotoAdd",method = RequestMethod.GET)
    public String gotoAdd(){
        return "/api/add";
    }
    
    @RequestMapping(value = "gotoEdit",method = RequestMethod.GET)
    public ModelAndView gotoEdit(Integer id){
    	Map<String,Object> map = new HashMap<>();
		map.put("api", interfaceValidata.getApi(id));
        return new ModelAndView("/api/edit",map);
    }

    /*
     * 取得所有API
     */
    @RequestMapping(value = "getAllApi",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllApi(){
        Map<String,Object> map = interfaceValidata.getAllApi();
        return map;
    }
    
    /*
     * 修改API状态
     */
    @RequestMapping(value = "updateApiState",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateApiState(Integer state,Integer id){
    	Map<String,Object> map = interfaceValidata.updateState(state, id);
        return map;
    }
    
    /*
     * 修改API
     */
    @RequestMapping(value = "updateApi",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateApi(Integer id, String name ,Integer state, String remark,String apiName){
    	Map<String,Object> map = interfaceValidata.updateApi(id,name,state,remark,apiName);
        return map;
    }
    
    /*
     * 新增API
     */
    @RequestMapping(value = "addApi",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addApi(String name, Integer state, String remark,String apiName){
    	Map<String,Object> map = interfaceValidata.addApi(name,state,remark,apiName);
        return map;
    }
    
    /*
     * 删除API
     */
    @RequestMapping(value = "deleteApi",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteApi(Integer id){
    	Map<String,Object> map = interfaceValidata.delete(id);
        return map;
    }

}

