package com.inesv.digiccy.query;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inesv.digiccy.dto.InsevSetInfoDto;

public class QuerySetInfo {
	private static Logger log = LoggerFactory.getLogger(QuerySetInfo.class);

	@Autowired
	private QueryRunner queryRunner;

	/*public List<InsevSetInfoDto> getSetInfo() {
		String sql = "SELECT * FROM t_inesv_userset";
		List<InsevSetInfoDto> user = null;
		try {
			user = (List<InsevSetInfoDto>) queryRunner.query(sql,new BeanListHandler(InsevSetInfoDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}*/
}
