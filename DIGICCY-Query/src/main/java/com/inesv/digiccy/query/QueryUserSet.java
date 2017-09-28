package com.inesv.digiccy.query;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.UserSet;

/**
 * 
 * @author Administrator 查看用户登录状态
 */
@Component
public class QueryUserSet {

	private static Logger logger = LoggerFactory.getLogger(QueryUserSet.class);
	@Autowired
	private QueryRunner queryRunner;

	/**
	 * 查询所用户登录状态
	 * 
	 * @return
	 */
	public List<UserSet> findUserSet() {

		String sql = "SELECT  id,opertion_time,opertion_number,opertion_name,opertion_uptime, opertion_ip FROM t_inesv_userset";
		List<UserSet> listuserSet = null;
		try {
			listuserSet = queryRunner.query(sql, new BeanListHandler<UserSet>(UserSet.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listuserSet;
	}

	/**
	 * 根据Id查询登录用户状态信息
	 * 
	 * @param id
	 * @return
	 */
	public UserSet findUserSetById(int id) {
		String sql = "SELECT  id,opertion_time,opertion_number,opertion_name,opertion_uptime, opertion_ip FROM t_inesv_userset WHERE id=?";
		Object[] params = { id };
		UserSet userset = null;
		try {
			userset = queryRunner.query(sql, new BeanHandler<UserSet>(UserSet.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userset;
	}

	/**
	 * 根据登录用户名查找登录信息
	 * 
	 * @param operationname
	 * @return
	 */
	public UserSet findUserSetByName(String operationname) {
		// String sql="SELECT id,operation_time,operation_numbe,operation_name,state,
		// loginip FROM t_inesv_userset WHERE operation_name=?";
		String sql = "SELECT  id,opertion_time,opertion_number,opertion_name,opertion_uptime, opertion_ip FROM t_inesv_userset";
		UserSet userset = null;
		if (operationname != "") {
			StringBuffer sb = new StringBuffer();
			sb.append(" WHERE operation_name=?");
			Object params[] = { operationname };
			try {
				userset = queryRunner.query(sql, new BeanHandler<UserSet>(UserSet.class), params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				userset = queryRunner.query(sql, new BeanHandler<UserSet>(UserSet.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return userset;
	}

}
