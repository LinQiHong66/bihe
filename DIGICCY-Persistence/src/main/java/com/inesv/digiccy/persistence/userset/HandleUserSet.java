package com.inesv.digiccy.persistence.userset;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.UserSet;

/**
 * 
 * @author Administrator 操作登录用户信息 增/删/改/
 */
@Component
public class HandleUserSet {
	@Autowired
	QueryRunner queryRunner;

	/**
	 * 
	 * @param userset
	 */
	/**
	 * 添加登录用户的用户信息
	 * 
	 * @param operationtime
	 *            登录时间
	 * @param operationnumbe
	 *            操作次数
	 * @param operationname
	 *            操作人姓名
	 * @param state
	 *            状态
	 * @param loginip
	 *            登录Ip
	 */
	public void insertUserSet(UserSet userset) {
		String sql = "INSERT INTO t_inesv_userset(opertion_time,opertion_number,opertion_name,opertion_ip,opertion_uptime) VALUES (?,?,?,?,?)";
		Object params[] = { userset.getOpertion_time(), userset.getOpertion_number(), userset.getOpertion_name(),
				userset.getOpertion_ip(), userset.getOpertion_uptime() };
		System.out.println("sql:" + sql);
		try {
			queryRunner.update(sql, params);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改登录用户信息
	 * 
	 * @param operationtime
	 *            登录时间
	 * @param operationnumbe
	 *            操作次数
	 * @param operationname
	 *            操作人姓名
	 * @param state
	 *            状态
	 * @param loginip
	 *            登录Ip
	 * @param id
	 *            根据ID修改登录用户信息
	 */
	public void updateUserSet(UserSet userset) {
		String sql = "UPDATE t_inesv_userset SET opertion_time = ?,opertion_number = ?,opertion_name = ?,opertion_ip = ?,opertion_uptime = ? WHERE id =?";
		Object params[] = { userset.getOpertion_time(), userset.getOpertion_number(), userset.getOpertion_name(),
				userset.getOpertion_ip(), userset.getOpertion_uptime(), userset.getId() };
		System.out.println("sql:" + sql);
		System.out.println("userset.getId():"+userset.getId());
		try {
			queryRunner.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除用户登录信息
	 * 
	 * @param id
	 */
	public void deleteUserSet(UserSet userset) {
		String sql = "DELETE FROM t_inesv_userset WHERE id=?";
		Object params[] = { userset.getId() };
		try {
			queryRunner.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
