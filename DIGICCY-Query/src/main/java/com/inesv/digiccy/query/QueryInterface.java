package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.InterfaceDto;
import com.inesv.digiccy.query.coin.QueryCoin;


@Component
public class QueryInterface {
	
	private static Logger looger =  LoggerFactory.getLogger(QueryInterface.class);
	
	@Autowired
	private QueryRunner queryRunner;
	
	public List<InterfaceDto> queryAllInterface(){
		String sql = "select * from t_inesv_interface where state != 2";
		List<InterfaceDto> list = new ArrayList<InterfaceDto>();
		try {
			list = queryRunner.query(sql, new BeanListHandler<InterfaceDto>(InterfaceDto.class));
		} catch (SQLException e) {
			looger.error("查询接口失败");
			e.printStackTrace();
		}
		return list;
	}
	
	public InterfaceDto queryById(Integer id) {
		String sql = "select * from t_inesv_interface where id = ? and state != 2";
		InterfaceDto dto = new InterfaceDto();
		try {
			dto = queryRunner.query(sql, new BeanHandler<InterfaceDto>(InterfaceDto.class),id);
		} catch (SQLException e) {
			looger.error("根据接口名查询失败");
			e.printStackTrace();
		}
		return dto;
	}
	
	public InterfaceDto queryByApi_no(String apiNo) {
		String sql = "select * from t_inesv_interface where api_no = ? and state != 2";
		InterfaceDto dto = new InterfaceDto();
		try {
			dto = queryRunner.query(sql, new BeanHandler<InterfaceDto>(InterfaceDto.class),apiNo);
		} catch (SQLException e) {
			looger.error("根据接口名查询失败");
			e.printStackTrace();
		}
		return dto;
	}
	
}
