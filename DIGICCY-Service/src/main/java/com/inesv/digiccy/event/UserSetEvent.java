package com.inesv.digiccy.event;

import java.util.Date;

/**
 * 
 * @author Administrator
 *
 */
public class UserSetEvent {

	private int id;
	private int opertion_number;
	private int opertion_time;
	private Date opertion_uptime;
	private String opertion_name;
	private String opertion_ip;
	private String attr1;
	private String operation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOpertion_number() {
		return opertion_number;
	}

	public void setOpertion_number(int opertion_number) {
		this.opertion_number = opertion_number;
	}

	public int getOpertion_time() {
		return opertion_time;
	}

	public void setOpertion_time(int opertion_time) {
		this.opertion_time = opertion_time;
	}

	public Date getOpertion_uptime() {
		return opertion_uptime;
	}

	public void setOpertion_uptime(Date opertion_uptime) {
		this.opertion_uptime = opertion_uptime;
	}

	public String getOpertion_name() {
		return opertion_name;
	}

	public void setOpertion_name(String opertion_name) {
		this.opertion_name = opertion_name;
	}

	public String getOpertion_ip() {
		return opertion_ip;
	}

	public void setOpertion_ip(String opertion_ip) {
		this.opertion_ip = opertion_ip;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public UserSetEvent() {

	}

	public UserSetEvent(int id, int opertion_number, int opertion_time, Date opertion_uptime, String opertion_name,
			String opertion_ip, String attr1, String operation) {
		this.id = id;
		this.opertion_number = opertion_number;
		this.opertion_time = opertion_time;
		this.opertion_uptime = opertion_uptime;
		this.opertion_name = opertion_name;
		this.opertion_ip = opertion_ip;
		this.attr1 = attr1;
		this.operation = operation;
	}

}
