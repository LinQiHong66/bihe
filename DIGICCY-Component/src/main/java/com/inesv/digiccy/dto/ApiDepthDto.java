package com.inesv.digiccy.dto;

import java.math.BigDecimal;

public class ApiDepthDto {
	
	private Integer entrust_coin;
	
	private BigDecimal entrust_price;
	
	private Integer entrust_count;

	public Integer getEntrust_coin() {
		return entrust_coin;
	}

	public void setEntrust_coin(Integer entrust_coin) {
		this.entrust_coin = entrust_coin;
	}

	public BigDecimal getEntrust_price() {
		return entrust_price;
	}

	public void setEntrust_price(BigDecimal entrust_price) {
		this.entrust_price = entrust_price;
	}

	public Integer getEntrust_count() {
		return entrust_count;
	}

	public void setEntrust_count(Integer entrust_count) {
		this.entrust_count = entrust_count;
	}
	
	
	
}
