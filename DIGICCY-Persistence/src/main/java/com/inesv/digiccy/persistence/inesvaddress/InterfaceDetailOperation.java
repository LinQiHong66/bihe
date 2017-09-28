package com.inesv.digiccy.persistence.inesvaddress;

import com.inesv.digiccy.dto.InterfaceAddressDto;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Component
public class InterfaceDetailOperation {

    private static Logger logger = LoggerFactory.getLogger(InterfaceDetailOperation.class);

    @Autowired
    QueryRunner queryRunner;

    /**
     * 新增用户API接口
     * @param address
     */
    public void insertAddress(InterfaceAddressDto address) throws Exception{
    	String sql = "INSERT INTO t_inesv_interface_detail (user_no,address_no,state,date) VALUES (?,?,?,?)";
        Object params[] = {address.getUser_no(),
        		address.getAddress_no(),
        		address.getState(),
        		address.getDate()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("新增API错误");
            e.printStackTrace();
        }
    }

    /**
     * 修改用户API接口
     * @param address
     */
    public void updateAddress(InterfaceAddressDto address){
        String sql = "UPDATE t_inesv_interface_detail SET state=?, sign=?  WHERE id = ?";
        Object params[] = {address.getState(),address.getSign(),address.getId()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            logger.error("修改API错误");
            e.printStackTrace();
        }
    }

}
