package com.inesv.digiccy.event;

import java.util.Date;

public class InterfaceEvent {
	
	private int id;
	
	private String api_no;
	
	private String name;
	
	private int state;
	
	private Date date;
	
	private String remark;
	
	private String attr1;
	
	private String attr2;
	
	private String operation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApi_no() {
		return api_no;
	}

	public void setApi_no(String api_no) {
		this.api_no = api_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public InterfaceEvent(int id, String api_no, String name, int state, Date date, String remark, String attr1,
			String attr2, String operation) {
		this.id = id;
		this.api_no = api_no;
		this.name = name;
		this.state = state;
		this.date = date;
		this.remark = remark;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.operation = operation;
	}

}
