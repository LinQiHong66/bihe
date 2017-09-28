package com.inesv.digiccy.mission;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.validata.FicRechargeValidate;

import java.sql.SQLException;
import java.util.Date;

@Component
public class RechargeCoinMission implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(RechargeCoinMission.class);

    @Autowired
    private FicRechargeValidate ficRechargeValidate;

     /**
     * 定时处理虚拟币充值
     */
	@Override
	/*@Scheduled(cron="0 0/1 * * * ?") （每分钟执行一次）  */  
	@Scheduled(cron="0 0/1 * * * ?")
	public void run() {
		try {
			ficRechargeValidate.validateRechargeCoinNew();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!定时器处理虚拟币充值成功!!!!!!!!!!!!!!!!!!!!" + new Date());
        LOGGER.info("**********************定时器处理虚拟币充值**********************************:" + new Date());
	}
}
