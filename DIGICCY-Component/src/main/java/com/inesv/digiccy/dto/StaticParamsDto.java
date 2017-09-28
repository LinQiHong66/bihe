package com.inesv.digiccy.dto;

import java.math.BigDecimal;

/**
 * Created by JimJim on 2016/12/14 0014.
 */
public class StaticParamsDto {

    private Integer id;

    private String param;

    private BigDecimal value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

	public StaticParamsDto(Integer id, String param, BigDecimal value) {
		this.id = id;
		this.param = param;
		this.value = value;
	}

	public StaticParamsDto() {
	}
    
    
}
