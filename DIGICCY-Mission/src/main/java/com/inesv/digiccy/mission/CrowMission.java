package com.inesv.digiccy.mission;

import com.inesv.digiccy.validata.CrowdFundingValidata;
import com.inesv.digiccy.validata.TradeValidata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CrowMission implements Runnable{
    private final static Logger LOGGER = LoggerFactory.getLogger(CrowMission.class);

    @Autowired
    private CrowdFundingValidata crowdValidata;
    
    /**
     * 瀹氭椂鍣ㄥ鐞嗕紬绛圭姸鎬侊紙姣忓ぉ鍑屾櫒2鐐规墽琛岋級
     */
	@Override
	@Scheduled(cron="0 0 2 * * ?") 
	public void run() {
		crowdValidata.validataEditCrowdState();
		LOGGER.info("**********************瀹氭椂鍣ㄥ鐞嗕紬绛圭姸鎬�**********************************:"+new Date());
	} 
    
}
