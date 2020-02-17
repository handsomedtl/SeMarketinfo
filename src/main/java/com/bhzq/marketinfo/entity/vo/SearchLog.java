package com.bhzq.marketinfo.entity.vo;


import java.sql.Timestamp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SearchLog {
	/**
	 * 日志分类
	 */
	private Integer type;
	/**
	 * 分为四个级别：<br/>
	 * 调试、正常、警告、错误
	 */
	private Integer level;
	
	private Timestamp  startTime;
	private Timestamp  endTime;
	
	private String sortColumn;
	private String sortOrder;
}
