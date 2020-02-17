package com.bhzq.marketinfo.entity.domain;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class NewsInfo extends BaseInfo implements Serializable{

	private static final long serialVersionUID = 4085279153853517544L;
	
	    
    /**
     * 资讯标题
     */
    private String title;           
    
    
    /**
     * 资讯时间
     */
    private Time time;
}