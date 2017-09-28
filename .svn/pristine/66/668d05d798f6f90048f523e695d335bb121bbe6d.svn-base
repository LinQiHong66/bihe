package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.RmbRechargeDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2016/12/9 0009.
 */
@Component
public class QueryRmbRechargeInfo {

    @Autowired
    QueryRunner queryRunner;

    /**
     *根据用户查询出用户的rmb充值信息
     *
     */
    public List<RmbRechargeDto> qureyRechargeInfo(int userNo){
        List<RmbRechargeDto> list = new ArrayList();
        String sql = "SELECT * FROM t_inesv_rmb_recharge WHERE user_no = ? ORDER BY id DESC";
        Object parmas[] = {userNo};
        try {
            list = (List<RmbRechargeDto>) queryRunner.query(sql,new BeanListHandler(RmbRechargeDto.class),parmas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *根据用户查询出用户的rmb充值信息
     *
     */
    public List<RmbRechargeDto> qureyRechargeInfo(String userName, String state, String startDate, String endDate){
        List<RmbRechargeDto> list = new ArrayList();
        String sql = "select r.*,u.username as attr1 from t_inesv_rmb_recharge r join t_inesv_user u " +
                "on r.user_no = u.user_no";
        ArrayList<Object> paramArr = new ArrayList<>();
        if(userName != null && !"".equals(userName) && !"-1".equals(userName)){
        	String str = sql.contains("where")?" and r.user_no=?":" where r.user_no=?";
        	sql += str;
        	paramArr.add(userName);
        }
        if(state != null && !"".equals(state) && !"-1".equals(state)){
        	String str = sql.contains("where")?" and r.state=?":" where r.state=?";
        	sql += str;
        	paramArr.add(state);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
        	String str = sql.contains("where")?" and r.date between ? and ?":" where r.date between ? and ?";
        	sql += str;
        	paramArr.add(Date.valueOf(startDate));
        	paramArr.add(Date.valueOf(endDate));
        }
        sql += " order by r.date";
        try {
            list = (List<RmbRechargeDto>) queryRunner.query(sql,new BeanListHandler(RmbRechargeDto.class),paramArr.toArray(new Object[]{}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
