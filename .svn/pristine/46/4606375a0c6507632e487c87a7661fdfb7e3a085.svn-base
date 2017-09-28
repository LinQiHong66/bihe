package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InesvBankInfo;
import com.inesv.digiccy.dto.InesvUserDto;
import com.inesv.digiccy.dto.RmbWithdrawDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
@Component
public class QueryRmbWithdrawInfo {

    @Autowired
    QueryRunner queryRunner;


    /**
     *根据用户查询出用户的提现信息
     *
     */
    public List<RmbWithdrawDto> queryWithdrawInfo(int userNo){
        List<RmbWithdrawDto> list = new ArrayList<>();
        String sql = "select * from t_inesv_rmb_withdraw where user_no = ?  ORDER BY DATE desc";
        Object params[]= {userNo};
        try {
            list = (List<RmbWithdrawDto>) queryRunner.query(sql,new BeanListHandler(RmbWithdrawDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<RmbWithdrawDto> queryWithdrawInfo(String userName, String state, String startDate, String endDate){
        List<RmbWithdrawDto> list = new ArrayList<>();
        String sql = "select w.*,u.username as attr1, " +
                "CONCAT(b.province,b.city,b.bank,b.branch,'(',b.`name`,'-',b.bank_num,')') as attr2 " +
                "from t_inesv_rmb_withdraw w " +
                "join t_inesv_user u on w.user_no = u.user_no " +
                "join t_inesv_bankinfo b on b.id = w.bank";
        ArrayList<Object> paramArr = new ArrayList<>();
        if(userName != null && !"".equals(userName) && !"-1".equals(userName)){
        	String str = sql.contains("where")?" and u.user_no=?":" where u.user_no=?";
        	sql += str;
        	paramArr.add(userName);
        }
        if(state != null && !"".equals(state) && !"-1".equals(state)){
        	String str = sql.contains("where")?" and w.state=?":" where w.state=?";
        	sql += str;
        	paramArr.add(state);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
        	String str = sql.contains("where")?" and w.date between ? and ?":" where w.date between ? and ?";
        	sql += str;
        	paramArr.add(startDate);
        	paramArr.add(endDate);
        }
        try {
            list = (List<RmbWithdrawDto>) queryRunner.query(sql,new BeanListHandler(RmbWithdrawDto.class), paramArr.toArray(new Object[]{}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**查询出用户的交易密码*/
    public List<InesvUserDto> queryUserDealpwd(int userNo){
        List<InesvUserDto> list = new ArrayList();
        String sql = "select deal_pwd from t_inesv_user where user_no = ?";
        Object parmas[] = {userNo};
        try {
            list = (List<InesvUserDto>) queryRunner.query(sql,new BeanListHandler(InesvUserDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**查询出用户的银行地址*/
    public List<InesvBankInfo> queryBankInfo(int userNo){
        List<InesvBankInfo> list = new ArrayList();
        String sql = "select * from t_inesv_bankinfo where user_no = ? and delstate != 1";
        Object parmas[] = {userNo};
        try {
            list = (List<InesvBankInfo>)queryRunner.query(sql,new BeanListHandler(InesvBankInfo.class),parmas);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return list;
    }








}
