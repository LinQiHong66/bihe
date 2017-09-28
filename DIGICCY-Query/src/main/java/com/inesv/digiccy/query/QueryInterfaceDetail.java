package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.InterfaceAddressDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JimJim on 2016/12/16 0016.
 */
@Component
public class QueryInterfaceDetail {

    @Autowired
    QueryRunner queryRunner;

    public List<InterfaceAddressDto> queryDetailByUser(String userNo) throws Exception{
        List<InterfaceAddressDto> detailDtoList = new ArrayList<>();
        String sql = "SELECT t2.api_no AS attr1 , t1.user_no AS user_no , t1.address_no AS address_no , t1.state AS state , t1.sign AS sign , t1.date AS date , t2.name AS attr2 " + 
        		" FROM t_inesv_interface_detail t1 , t_inesv_interface t2 WHERE user_no = ? AND t1.address_no = t2.id";
            detailDtoList = queryRunner.query(sql,new BeanListHandler<>(InterfaceAddressDto.class),userNo);
        return detailDtoList;
    }
    
    public List<InterfaceAddressDto> queryDetailByUserAndAddressNo(String userNo,String addressNo) throws Exception{
        List<InterfaceAddressDto> detailDtoList = new ArrayList<>();
        String sql = "SELECT * FROM t_inesv_interface_detail WHERE user_no = ? AND address_no = ?";
        Object params[] = {userNo,addressNo};   
            detailDtoList = queryRunner.query(sql,new BeanListHandler<>(InterfaceAddressDto.class),params);
        return detailDtoList;
    }
    
    public List<InterfaceAddressDto> queryDetailByUserAndState(String userNo,String state) throws Exception{
        List<InterfaceAddressDto> detailDtoList = new ArrayList<>();
        String sql = "SELECT * FROM t_inesv_interface_detail WHERE user_no = ? AND state = ?";
        Object params[] = {userNo,state};    
            detailDtoList = queryRunner.query(sql,new BeanListHandler<>(InterfaceAddressDto.class),params);
        return detailDtoList;
    }
    
    public List<InterfaceAddressDto> queryAllDetail() throws Exception{
        List<InterfaceAddressDto> detailDtoList = new ArrayList<>();
            String sql = "  SELECT t1.id AS id, t1.user_no AS user_no, t2.name AS address_no, t1.state AS state, t1.sign AS sign, t1.date AS date FROM t_inesv_interface_detail t1 , t_inesv_interface t2 WHERE t1.address_no = t2.id ";
            detailDtoList = queryRunner.query(sql,new BeanListHandler<>(InterfaceAddressDto.class));
        return detailDtoList;
    }
    
    public InterfaceAddressDto queryAllDetailByUserAndApiNo(Integer userNo, String apiNo, Integer state) throws Exception{
        InterfaceAddressDto dto = new InterfaceAddressDto();
            String sql = "SELECT * FROM t_inesv_interface_detail WHERE user_no = ? AND api_no = ? AND state = ?";
        Object params[] = {userNo,apiNo,state};  
            dto = queryRunner.query(sql,new BeanHandler<>(InterfaceAddressDto.class),params);
        return dto;
    }
    
    public InterfaceAddressDto queryAllDetailBySign(Integer userNo,String apiNo,String sign,Integer state) throws Exception{
        InterfaceAddressDto dto = new InterfaceAddressDto();
            String sql = "SELECT * FROM t_inesv_interface_detail WHERE user_no = ? AND address_no = ? AND sign = ? AND state = ?";
        Object params[] = {userNo,apiNo,sign,state};  
            dto = queryRunner.query(sql,new BeanHandler<>(InterfaceAddressDto.class),params);
        return dto;
    }
    
    public InterfaceAddressDto queryAllDetailBySign(String sign) throws Exception{
        InterfaceAddressDto dto = new InterfaceAddressDto();
            String sql = "SELECT * FROM t_inesv_interface_detail WHERE sign = ? ";
        Object params[] = {sign};  
            dto = queryRunner.query(sql,new BeanHandler<>(InterfaceAddressDto.class),params);
        return dto;
    }
    
    public InterfaceAddressDto queryDetailBySign(String sign){
        InterfaceAddressDto dto = new InterfaceAddressDto();
            String sql = "SELECT * FROM t_inesv_interface_detail WHERE sign = ?";
        Object params[] = {sign};  
        try {
        	dto = queryRunner.query(sql,new BeanHandler<>(InterfaceAddressDto.class),params);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return dto;
    }

}
