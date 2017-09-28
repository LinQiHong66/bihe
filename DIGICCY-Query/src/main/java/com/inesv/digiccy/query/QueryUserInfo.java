package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.LoginLogDto;
import com.inesv.digiccy.dto.UserVoucherDto;
import com.inesv.digiccy.dto.auth.AuthRoleDto;
import com.inesv.digiccy.dto.auth.ResourceDto;
import com.inesv.digiccy.query.util.TimeUtil;
import com.inesv.digiccy.util.MD5;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class QueryUserInfo implements UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(QueryUserInfo.class);

	@Autowired
	private QueryRunner queryRunner;

	// public UserDetails loginByUsernamePassWord(String username,String
	// password){
	// String sql = "select * from user where name = ? and password = ?";
	// queryResourceURL();
	// Object params[] = {username,password};
	// User user = null;
	// try {
	// UserDto userdto = (UserDto)queryRunner.query(sql,new
	// BeanHandler<UserDto>(UserDto.class),params);
	// Set<GrantedAuthority> grantedAuths = getGrantedAuthorities(userdto);
	// //封装成spring security的user
	// return new
	// User(userdto.getName(),userdto.getPassword(),true,true,true,true,grantedAuths);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return user;
	// }

	/*
	 * 测试
	 */
	public List<InesvUserDto> getAllUsers() {
		String sql = "SELECT * FROM t_inesv_user";
		List<InesvUserDto> user = null;
		try {
			user = (List<InesvUserDto>) queryRunner.query(sql,
					new BeanListHandler(InesvUserDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<AuthRoleDto> queryUserRole(Long userid) {
		String sql = "select id,name,description from t_inesv_role where id IN (SELECT role_id FROM t_inesv_user_role WHERE user_id=?)";
		Object params[] = { userid };

		List<AuthRoleDto> role = null;
		try {
			role = (List<AuthRoleDto>) queryRunner.query(sql,
					new BeanListHandler(AuthRoleDto.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	/**
	 * 根据用户获取该用户拥有的角色
	 * 
	 * @param user
	 * @return
	 */

	private Set<GrantedAuthority> getGrantedAuthorities(InesvUserDto user) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		List<AuthRoleDto> roles = queryUserRole(user.getId());
		if (roles != null) {
			for (AuthRoleDto role : roles) {
				grantedAuthorities
						.add(new GrantedAuthorityImpl(role.getName()));
			}
		}
		return grantedAuthorities;
	}

	@Override
	public UserDetails loadUserByUsername(String s)
			throws UsernameNotFoundException {
		String sql = "select * from t_inesv_user where username = ?";
		queryResourceURL();
		Object params[] = { s };
		User user = null;
		try {
			InesvUserDto userdto = (InesvUserDto) queryRunner.query(sql,
					new BeanHandler(InesvUserDto.class), params);

			// Set<GrantedAuthority> grantedAuths =
			// getGrantedAuthorities(userdto);
			Set<GrantedAuthority> grantedAuths = getGrantedAuthorities(userdto);
			// 封装成spring security的user
			return new User(userdto.getUsername(), userdto.getPassword(), true,
					true, true, true, grantedAuths);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public InesvUserDto loadUser(String name, String pass)
			throws UsernameNotFoundException {
		pass = new MD5().getMD5(pass);
		System.out.println("pass:"+pass);
		String sql = "select * from t_inesv_user where username = ? and password = ?";
		queryResourceURL();
		Object params[] = { name, pass };
		try {
			InesvUserDto userdto = (InesvUserDto) queryRunner.query(sql,
					new BeanHandler(InesvUserDto.class), params);

			return userdto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<InesvUserDto> getAllUser() {
		String sql = "select * from t_inesv_user";
		List<InesvUserDto> userlist = null;
		try {
			userlist = (List<InesvUserDto>) queryRunner.query(sql,
					new BeanListHandler(InesvUserDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userlist;
	}

	/**
	 * 查询访问url
	 */
	public HashMap<String, Object> queryResourceURL() {
		String sql = "select id,type,value from t_inesv_resource";

		HashMap<String, Object> resource = null;
		try {
			resource = (HashMap<String, Object>) queryRunner.query(sql,
					new MapHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resource;
	}

	/**
	 * 查询所有角色
	 */
	public List<AuthRoleDto> queryRole() {
		String sql = "select id,name,description from t_inesv_role";

		List<AuthRoleDto> roleList = null;
		try {
			roleList = (List<AuthRoleDto>) queryRunner.query(sql,
					new BeanListHandler(AuthRoleDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleList;
	}

	/**
	 * 查询角色对应的url
	 */
	public List<ResourceDto> queryRoleResource(Integer roleid) {
		String sql = "select id,type,value from t_inesv_resource where id in (select resource_id from t_inesv_role_resource where role_id = ?)";
		Object params[] = { roleid };
		List<ResourceDto> roleList = null;
		try {
			roleList = (List<ResourceDto>) queryRunner.query(sql,
					new BeanListHandler(ResourceDto.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleList;
	}

	// 获取所有的验证用户
	public List<UserVoucherDto> getAllVoucher(String filed, String value) {
		String sql = "select voucher_id as id, voucher_cardid as cardId,"
				+ " voucher_type as cardType, voucher_imgurl1 as imgUrl1,"
				+ " voucher_imgurl2 as imgUrl2, voucher_imgurl3 as imgUrl3,"
				+ " voucher_state as state, userNo as userNo,"
				+ " realName as trueName, voucher_mytype as myvoucherType"
				+ " from t_inesv_user_voucher";
		if (filed != null && !"".equals(filed) && value != null
				&& !"".equals(value)) {
			sql += " where " + filed + "=" + value;
		}
		List<UserVoucherDto> vouchers = null;
		try {
			vouchers = queryRunner.query(sql,
					new BeanListHandler<UserVoucherDto>(UserVoucherDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vouchers;
	}

	public InesvUserDto getUserInfoById(Long id) {
		String sql = "select * from t_inesv_user where id = ?";
		Object param[] = { id };
		InesvUserDto userInfo = null;
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(
					InesvUserDto.class), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/**
	 * 查询用户ip
	 * 
	 * @param userNo
	 * @return list
	 */
	public List<LoginLogDto> getLoginLogInfo(Integer userNo) {
		String sql = "select * from t_inesv_login_log where user_no = ? order by date desc";
		Object params[] = { userNo };
		List<LoginLogDto> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<LoginLogDto>(
					LoginLogDto.class), params);
		} catch (SQLException e) {
			log.error("查询用户ip失败");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询用户ip
	 * 
	 * @param userNo
	 * @return list
	 */
	public List<LoginLogDto> getLoginLogInfoLimitTime(Integer userNo,
			String startTime, String endTime) {
		String sql = "select * from t_inesv_login_log where user_no = ? and date between ? and ? order by date desc";
		Object params[] = { userNo, startTime, endTime };
		List<LoginLogDto> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<LoginLogDto>(
					LoginLogDto.class), params);
		} catch (SQLException e) {
			log.error("查询用户ip失败");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询用户在限制时间短的登录次数
	 * 
	 * @param userNo
	 * @param limitTime
	 * @return
	 */
	public List<LoginLogDto> getLoginLogInfoLimitTime(Integer userNo,
			int limitTime) {
		String startTime = TimeUtil.getStartTime(limitTime);
		String endTime = TimeUtil.getCurrentTime();
		return getLoginLogInfoLimitTime(userNo, startTime, endTime);
	}

	/** 查询用户推荐码 */
	public InesvUserDto queryInviteNum(int userNo) {
		InesvUserDto userInfo = null;
		String sql = "select * from t_inesv_user where user_no = ?";
		Object parmas[] = { userNo };
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(
					InesvUserDto.class), parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/**
	 * 判断该用户是否已注册
	 * 
	 * @param phone
	 * @return
	 */
	public InesvUserDto getPhoneIsUnique(String phone) {
		InesvUserDto userInfo = null;
		String sql = "select * from t_inesv_user where username = ?";
		Object parmas[] = { phone };
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(
					InesvUserDto.class), parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/** 查询用户推荐码 */
	public InesvUserDto queryUserByInviteNum(String invite_num) {
		InesvUserDto userInfo = null;
		String sql = "select * from t_inesv_user where invite_num = ?";
		Object parmas[] = { invite_num };
		try {
			userInfo = queryRunner.query(sql, new BeanHandler<>(
					InesvUserDto.class), parmas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/**
	 * 获取我的邀请人
	 * 
	 * @param userNo
	 * @return
	 */
	public List<InesvUserDto> getInesvByUserNo(int userNo) {
		String sql = "select u.id,u.username,u.user_no,u.region,u.real_name,u.mail,u.phone,r.rec_user,r.rec_type,r.auth,r.date from t_inesv_user u,t_inesv_rec r where r.rec_user=u.user_no and r.user_no=?";

		Object parmas[] = { userNo };
		try {
			List<InesvUserDto> list = queryRunner.query(sql,
					new BeanListHandler<InesvUserDto>(InesvUserDto.class),
					parmas);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
