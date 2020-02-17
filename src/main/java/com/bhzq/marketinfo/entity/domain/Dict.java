package com.bhzq.marketinfo.entity.domain;

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
public class Dict{	
	/**
     * 日志ID
     */
	@JsonIgnore
    private long id;
    private String name;
    /**
     * 主类型：期货、指数、个股、资讯
     */
    private String type;
    private String typeName;
}