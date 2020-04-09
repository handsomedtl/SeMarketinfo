package com.bhzq.marketinfo.entity.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="initparam")
public class Initparam {
	/**
	 * 实时统计：每段时间间隔
	 */
	private int timespan;
	
	/**
	 * 实时统计：保留时间段数
	 */
	private int timecount;
	
	/**
	 * 行情数据保存天数
	 */
	private int datecount;
	
	/**
	 * 日志保留天数
	 */
	private int logReserved;
}
