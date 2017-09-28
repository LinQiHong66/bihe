package com.inesv.digiccy.persistence.crowd;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inesv.digiccy.dto.CrowdFundingDetailsDto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by JimJim on 2016/12/9 0009.
 */
@Component
public class CrowdFundingDetailsOperation {

    @Autowired
    QueryRunner queryRunner;
    
    /*
     * 增加众筹信息
     */
    public void insertDetails(CrowdFundingDetailsDto crowdFundingDetailsDto) throws Exception{
        String sql = "INSERT INTO t_crowdfunding_details (user_id,ico_id,ico_user_number,ico_user_sumprice,date) VALUES (?,?,?,?,?)";
        Object params[] = {crowdFundingDetailsDto.getUser_id(),
        		crowdFundingDetailsDto.getIco_id(),
        		crowdFundingDetailsDto.getIco_user_number(),
        		crowdFundingDetailsDto.getIco_user_sumprice(),
        		crowdFundingDetailsDto.getDate()};
        queryRunner.update(sql,params);
        /*try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
    
    /*
     * 参与众筹
     */
    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void updateCrowdDetailAndBalance(String icoNo, String userNo, Integer icoNumber, String sumPrice, String icoCurrent, 
    		String icoStatus, String total_price, String enable_coin, String icoPriceType) throws Exception{
    	if(Double.valueOf(total_price) < 0 || Double.valueOf(enable_coin) < 0) {
    		int exception= 1/0;	//手动抛出异常
    	}
        String insertDetailSql = "INSERT INTO t_crowdfunding_details (user_id,ico_id,ico_user_number,ico_user_sumprice,date) VALUES (?,?,?,?,?)";
        Object insertDetailParams[] = {userNo,icoNo,icoNumber,sumPrice,new Date()};
        	queryRunner.update(insertDetailSql,insertDetailParams);
        String updateCrowdSql = "UPDATE t_crowdfunding SET ico_current=? , ico_status=? , ico_state = ? WHERE ico_no = ?";
        if(Double.valueOf(icoStatus)==1) {
        	Object updateCrowdParams1[] = { icoCurrent, icoStatus, 1, icoNo };
        		queryRunner.update(updateCrowdSql, updateCrowdParams1);
        }else {
        	Object updateCrowdParams0[] = { icoCurrent, icoStatus, 0, icoNo };
        		queryRunner.update(updateCrowdSql, updateCrowdParams0);
        }
    }

}
