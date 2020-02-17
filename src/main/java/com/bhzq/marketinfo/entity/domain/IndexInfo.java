package com.bhzq.marketinfo.entity.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <p>
 * 日志实体类
 * </p>
 *
 * @description: 日志实体类
 * @author: terry.duan
 * @date: Created in 2020-01-21 15:05
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 */
@Data
public class IndexInfo extends BaseInfo implements Serializable{

	private static final long serialVersionUID = 4078779153853517544L;
	
    /**
     * 指数名称
     */
    private String name;
           
    /**
     * 最新价格
     */
    private Double newPrice;
    
    /**
     * 涨跌幅
     */
    private Double increase;
    
    
    /**
     * 涨跌百分比
     */
    private Double increper;
}