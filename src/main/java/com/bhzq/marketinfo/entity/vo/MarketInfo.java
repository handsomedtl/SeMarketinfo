package com.bhzq.marketinfo.entity.vo;


import java.sql.Timestamp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MarketInfo {
	
	/**
	 * 指数代码
	 */
	private String code;
	
	/**
	 * 类型:1-指数，2-期货，3-资讯
	 */
	private Integer type;
	
	private Timestamp  startTime;
	private Timestamp  endTime;
}
