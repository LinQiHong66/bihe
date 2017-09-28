package com.inesv.digiccy.back.controller;

import com.inesv.digiccy.validata.NewsValidate;
import com.inesv.digiccy.validata.RmbRechargeValidate;
import com.inesv.digiccy.validata.RmbWithdrawValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JimJim on 2016/12/12 0012.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsValidate newsValidate;

 

    @RequestMapping(value = "pubNews",method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> pubNews(String news_title, String news_content, String news_author, String date, Integer type){
    	Map<String, Object> map=new HashMap<>();
    	map = newsValidate.validatePubNews(news_title, news_content, news_author, type);
        return map;
    }
}
