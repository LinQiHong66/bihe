package com.inesv.digiccy.persistence.command;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.CommandRedDto;
import com.inesv.digiccy.dto.CrowdFundingDto;

import java.sql.SQLException;

/**
 * Created by JimJim on 2017/06/06 0009.
 */
@Component
public class CommandRedOperation {
	
	private static Logger logger = LoggerFactory.getLogger(CommandRedOperation.class);

	@Autowired
	QueryRunner queryRunner;
	
	/*
	 * 新增口令红包信息
	 */
	public void insertCommandRed(CommandRedDto commandRedDto) {
		String sql = "INSERT INTO t_command_red (command_no,command_name,command_prize_type,command_name_price,command_number,command_remark,state,date) VALUES (?,?,?,?,?,?,?,?)";
		Object params[] = { commandRedDto.getCommand_no(),
				commandRedDto.getCommand_name(),
				commandRedDto.getCommand_prize_type(),
				commandRedDto.getCommand_name_price(),
				commandRedDto.getCommand_number(),
				commandRedDto.getCommand_remark(),
				commandRedDto.getState(),
				commandRedDto.getDate()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("新增口令红包失败");
		}
	}
	
	/*
	 * 删除口令红包信息
	 */
	public void deleteCommandRed(CommandRedDto commandRedDto) {
		String sql = "UPDATE t_command_red set state=2 WHERE command_no=?";
		Object params[] = { commandRedDto.getCommand_no()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("删除口令红包失败");
		}
	}
	
	/*
	 * 修改口令红包信息
	 */
	public void updateCommandRed(CommandRedDto commandRedDto) {
		String sql = "UPDATE t_command_red SET command_name=?,command_prize_type=?,command_name_price=?,command_remark=? WHERE command_no = ?";
		Object params[] = { commandRedDto.getCommand_name(),
				commandRedDto.getCommand_prize_type(),
				commandRedDto.getCommand_name_price(),
				commandRedDto.getCommand_remark(), 
				commandRedDto.getCommand_no() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("修改口令红包失败");
		}
	}
	
	/*
	 * 修改口令红包信息
	 */
	public void updateCommandRedState(CommandRedDto commandRedDto) {
		String sql = "UPDATE t_command_red SET state=? WHERE id = ?";
		Object params[] = { commandRedDto.getState(),
				commandRedDto.getId()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("修改口令红包失败");
		}
	}

}
