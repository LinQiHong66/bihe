package com.inesv.digiccy.persistence.inesvaddress;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.InterfaceDto;

@Component
public class InterfaceOperation {

	private static Logger logger = LoggerFactory.getLogger(InterfaceOperation.class);
	
	@Autowired
	QueryRunner queryRunner;
	
	/**
	 * 新增API接口
	 * */
	public void addInterface(InterfaceDto dto) throws Exception{
		String sql = "INSERT INTO t_inesv_interface (api_no,name,state,date,remark,attr1) VALUES (?,?,?,?,?,?)";
        Object params[] = {dto.getApi_no(),
        		dto.getName(),
        		dto.getState(),
        		dto.getDate(),
        		dto.getRemark(),
        		dto.getAttr1()};
            queryRunner.update(sql,params);
	}
	
	/**
	 * 修改API接口
	 * */
	public void updateById(InterfaceDto dto) throws Exception{
		String sql = "update t_inesv_interface set name = ?, state = ?, remark = ?, attr1 = ? where id = ?";
        Object params[] = {dto.getName(),
        		dto.getState(),
        		dto.getRemark(),
        		dto.getAttr1(),
        		dto.getId()
        		};
            queryRunner.update(sql,params);
	}
	
	
	/**
	 * 修改API状态
	 * */
	public void updateState(InterfaceDto dto) throws Exception{
		String sql = "update t_inesv_address set state = ? where id = ?";
		Object params[] = {dto.getState(),dto.getId()};
			queryRunner.update(sql,params);
	}
	
	/**
	 * 删除API接口
	 * */
	public void delete(InterfaceDto dto) throws Exception{
		String sql = "update t_inesv_interface set state = 2 where id = ?";
		Object params[] = {dto.getId()};
			queryRunner.update(sql,params);
	}

}
