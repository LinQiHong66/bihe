package com.inesv.digiccy.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created by yc on 2016/12/21 0021.
 */
public class InterfaceAddressCommand {

    @TargetAggregateIdentifier
    /**编号*/
    private int id;
    /**用户编号*/
    private long user_no;
    /**接口ID*/
    private String address_no;
    /**是否认证*/
    private int state;
    /**操作日期*/
    private Date date;
    /**密匙*/
    private String sign;
    /**备用字段1*/
    private String attr1;
    /**备用字段2*/
    private String attr2;
    /**操作类型*/
    private String operation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getAddress_no() {
		return address_no;
	}
	public void setAddress_no(String address_no) {
		this.address_no = address_no;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public InterfaceAddressCommand(int id, long user_no, String address_no, int state, Date date, String sign,
			String attr1, String attr2, String operation) {
		this.id = id;
		this.user_no = user_no;
		this.address_no = address_no;
		this.state = state;
		this.date = date;
		this.sign = sign;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.operation = operation;
	}
	
}
