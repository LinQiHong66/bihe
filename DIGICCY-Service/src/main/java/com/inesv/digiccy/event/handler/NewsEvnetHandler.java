package com.inesv.digiccy.event.handler;

import com.inesv.digiccy.dto.NewsDto;
import com.inesv.digiccy.dto.RmbRechargeDto;
import com.inesv.digiccy.event.NewsEvnet;
import com.inesv.digiccy.event.RmbRechargeEvnet;
import com.inesv.digiccy.persistence.finance.NewsPersistence;
import com.inesv.digiccy.persistence.finance.RmbRechargePersistence;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class NewsEvnetHandler {

    @Autowired
    private NewsPersistence newsPersistence;

    @EventHandler
    public void handler(NewsEvnet evnet){
        NewsDto newsDto =new NewsDto();
        newsDto.setId(evnet.getId());
        newsDto.setNews_author(evnet.getNews_author());
        newsDto.setNews_content(evnet.getNews_content());
        newsDto.setNews_title(evnet.getNews_title());
        newsDto.setDate(evnet.getDate());
        newsDto.setType(evnet.getType());
        
       
        String operating = evnet.getOperating();
        switch (operating){
            case "insert":
            	newsPersistence.add(newsDto);
                break;
        
            default:
                break;
        }
    }

}
