package com.inesv.digiccy.persistence.crowd;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.CrowdFundingDto;

import java.sql.SQLException;

/**
 * Created by JimJim on 2017/06/06 0009.
 */
@Component
public class CrowdFundingOperation {
	
	private static Logger logger = LoggerFactory.getLogger(CrowdFundingOperation.class);

	@Autowired
	QueryRunner queryRunner;
	
	/*
	 * 新增众筹项目信息
	 */
	public void insertCrowdFunding(CrowdFundingDto crowdFundingDto) throws SQLException {
		String sql = "INSERT INTO t_crowdfunding (ico_no,ico_name,ico_photo,ico_target,ico_current,ico_status,ico_price_type,ico_price,ico_sum_price,ico_remark,ico_explain,ico_state,begin_date,end_date,attr1) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object params[] = { crowdFundingDto.getIco_no(),
				crowdFundingDto.getIco_name(),
				crowdFundingDto.getIco_photo(),
				crowdFundingDto.getIco_target(),
				crowdFundingDto.getIco_current(),
				crowdFundingDto.getIco_status(),
				crowdFundingDto.getIco_price_type(),
				crowdFundingDto.getIco_price(),
				crowdFundingDto.getIco_sum_price(),
				crowdFundingDto.getIco_remark(),
				crowdFundingDto.getIco_explain(),
				crowdFundingDto.getIco_state(),
				crowdFundingDto.getBegin_date(),
				crowdFundingDto.getEnd_date(),
				crowdFundingDto.getAttr1()};
			queryRunner.update(sql, params);
		/*try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("新增众筹项目失败");
		}*/
	}
	
	/*
	 * 删除众筹信息
	 */
	public void deleteCrowdFunding(CrowdFundingDto crowdFundingDto) {
		String sql = "UPDATE t_crowdfunding set ico_state=2 WHERE ico_no=?";
		Object params[] = { crowdFundingDto.getIco_no()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("删除众筹项目失败");
		}
	}
	
	/*
	 * 修改众筹信息
	 */
	public void updateCrowdFundingBack(CrowdFundingDto crowdFundingDto) throws SQLException {
		String sql = "UPDATE t_crowdfunding SET ico_name=?,ico_photo=?,ico_target=?,ico_price_type=?,ico_price=?,ico_remark = ?,ico_explain = ?,end_date=?,attr1=? WHERE ico_no = ?";
		Object params[] = { crowdFundingDto.getIco_name(),
				crowdFundingDto.getIco_photo(),
				crowdFundingDto.getIco_target(),
				crowdFundingDto.getIco_price_type(),
				crowdFundingDto.getIco_price(), 
				crowdFundingDto.getIco_remark(), 
				crowdFundingDto.getIco_explain(),
				crowdFundingDto.getEnd_date(),
				crowdFundingDto.getAttr1(),
				crowdFundingDto.getIco_no()
				};
		queryRunner.update(sql, params);
		/*try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("修改众筹项目失败");
		}*/
	}

	/*
	 * 修改众筹信息
	 */
	public void updateCrowdFundingFront(CrowdFundingDto crowdFundingDto) throws SQLException {
		String sql = "UPDATE t_crowdfunding SET ico_current=? , ico_status=? , ico_state = ? WHERE ico_no = ?";
		Object params[] = { crowdFundingDto.getIco_current(),
				crowdFundingDto.getIco_status(),
				crowdFundingDto.getIco_state(), 
				crowdFundingDto.getIco_no() };
		queryRunner.update(sql, params);
		/*try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("修改众筹项目失败");
		}*/
	}
	
	/*
	 * 修改众筹状态
	 */
	public void updateCrowdFundingState() throws SQLException {
		String sql = "UPDATE t_crowdfunding SET ico_state = 1 WHERE DATEDIFF(NOW(),end_date)>0 AND ico_state!=2";
		queryRunner.update(sql);
		/*try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("修改众筹项目失败");
		}*/
	}
	
}
