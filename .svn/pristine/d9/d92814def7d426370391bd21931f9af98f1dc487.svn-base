package com.inesv.digiccy.persistence.user;

import com.inesv.digiccy.dto.InesvUserDto;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
@Component
public class AuthenticationUser {

	@Autowired
	QueryRunner queryRunner;

	/**
	 * 实名验证插入照片！
	 * 
	 * @param inesvUserDto
	 */
	public void insert(InesvUserDto inesvUserDto) {
		String sql = "update t_inesv_user set photo = ?,photo_state = ? where user_no = ?";
		Object params[] = { inesvUserDto.getPhoto(), inesvUserDto.getPhoto_state(), inesvUserDto.getUser_no() };
		String sqlProfit = "update t_inesv_rec_profit set real=? where user_no= ? ";
		Object paramsProfit[] = { inesvUserDto.getPhoto_state(), inesvUserDto.getUser_no() };
		try {
			queryRunner.update(sql, params);
			queryRunner.update(sqlProfit, paramsProfit);
		} catch (SQLException e) {
			throw new RuntimeException("sql error");
		}

	}

	/**
	 * 修改登录密码
	 * 
	 * @param inesvUserDto
	 */
	public void updateUser(InesvUserDto inesvUserDto) {
		String upUser = "update t_inesv_user set password = ? where user_no = ?";
		Object params[] = { inesvUserDto.getPassword(), inesvUserDto.getUser_no() };
		try {
			queryRunner.update(upUser, params);
		} catch (SQLException e) {
			throw new RuntimeException("数据异常");
		}
	}

	/**
	 * 修改交易密码
	 * 
	 * @param inesvUserDto
	 */
	public void updatePwd(InesvUserDto inesvUserDto) {
		String upPwd = "update t_inesv_user set deal_pwd = ?, deal_pwdstate=1 where user_no = ?";
		Object params[] = { inesvUserDto.getDeal_pwd(), inesvUserDto.getUser_no() };
		try {
			queryRunner.update(upPwd, params);
		} catch (SQLException e) {
			throw new RuntimeException("数据异常");
		}
	}

	/**
	 * 绑定手机号码
	 * 
	 * @param inesvUserDto
	 */
	public void updatePhone(InesvUserDto inesvUserDto) {
		String upPhone = "update t_inesv_user set phone = ?,phone_state = ?,username=? where user_no = ?";
		Object params[] = { inesvUserDto.getPhone(), inesvUserDto.getPhoto_state(), inesvUserDto.getPhone(),
				inesvUserDto.getUser_no() };
		try {
			queryRunner.update(upPhone, params);
		} catch (SQLException e) {
			throw new RuntimeException("数据异常");
		}
	}

	/**
	 * 绑定支付宝账户
	 * 
	 * @param inesvUserDto
	 */
	public void updateAlipay(InesvUserDto inesvUserDto) {
		String upAlipay = "update t_inesv_user set alipay = ?, alipay_state = ? where user_no = ?";
		Object params[] = { inesvUserDto.getAlipay(), inesvUserDto.getAlipay_state(), inesvUserDto.getUser_no() };
		try {
			queryRunner.update(upAlipay, params);
		} catch (SQLException e) {
			throw new RuntimeException("数据异常");
		}
	}

	/**
	 * 交易密码输入设置
	 * 
	 * @param inesvUserDto
	 */
	public void upDealPwdState(InesvUserDto inesvUserDto) {
		String upPwdState = "update t_inesv_user set deal_pwdstate = ? where user_no = ?";
		Object params[] = { inesvUserDto.getDeal_pwdstate(), inesvUserDto.getUser_no() };
		try {
			queryRunner.update(upPwdState, params);
		} catch (SQLException e) {
			throw new RuntimeException("数据异常");
		}
	}

	/**
	 * 四要素验证通过
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void fourVoucherOk(InesvUserDto inesvUserDto) throws Exception {
		String sql = "update t_inesv_user set phone_state=?, certificate_type=?, certificate_num=?, factor=?, real_name=?  where user_no=?";
		Object[] para = new Object[] { 2, 1, inesvUserDto.getCertificate_num(),
				1, inesvUserDto.getReal_name(),inesvUserDto.getUser_no() };
		//更改用户表
		queryRunner.update(sql, para);
		String sql1 = "delete from t_inesv_user_voucher where userNo=?";
		Object para1 = new Object[] {inesvUserDto.getUser_no()};
		String sql2 = "insert into t_inesv_user_voucher (" + 
				"voucher_cardid," + 
				"voucher_type," + 
				"voucher_state," + 
				"userNo," + 
				"realName" + 
				") values (" + 
				"?,?,?,?,?" + 
				")";
		Object[] para2 = new Object[] {
				inesvUserDto.getCertificate_num(),
				1,4,inesvUserDto.getUser_no(),inesvUserDto.getReal_name()
		};
		//用户实名认证表
		queryRunner.update(sql1, para1);
		queryRunner.update(sql2, para2);
	}

	/**
	 * 设置双重验证密码
	 * 
	 * @param inesvUserDto
	 */
	public void upValidate_pwd(InesvUserDto inesvUserDto) {
		String upValidatePwd = "update t_inesv_user set "
				+ (inesvUserDto.getValidate_pwdstate() == 1 ? "" : "validate_pwd = ?")
				+ ((inesvUserDto.getValidate_pwdstate() != 1 && inesvUserDto.getValidate_pwdstate() != -1) ? "," : "")
				+ (inesvUserDto.getValidate_pwdstate() == -1 ? ""
						: " validate_pwdstate=" + inesvUserDto.getValidate_pwdstate())
				+ " where user_no = ?";
		Object params[];
		if (inesvUserDto.getValidate_pwdstate() != 1) {
			params = new Object[] { inesvUserDto.getValidate_pwd(), inesvUserDto.getUser_no() };
		} else {
			params = new Object[] { inesvUserDto.getUser_no() };
		}
		try {
			queryRunner.update(upValidatePwd, params);
		} catch (SQLException e) {
			throw new RuntimeException("数据异常");
		}
	}
}
