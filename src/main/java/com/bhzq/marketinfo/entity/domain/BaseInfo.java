package com.bhzq.marketinfo.entity.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * <p>
 * 跑马资讯
 * </p>
 *
 * @author: terry.duan
 * @date: Created in 2020-01-21 15:05
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 */
@Data
public class BaseInfo{	
	/**
     * 日志ID
     */
	@JsonIgnore
    private long id;
    /**
     * 指数/股票代码
     */
    private String code;
    
    /**
	 * 类型:1-指数，2-期货，3-资讯
	 */
    @JsonIgnore
	private Integer type;     
    
    /**
     * 产生时间
     */
    @JsonIgnore
    private Timestamp insertTime;
    

    
}