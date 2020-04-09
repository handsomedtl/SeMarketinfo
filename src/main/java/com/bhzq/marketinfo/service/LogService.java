package com.bhzq.marketinfo.service;

import java.sql.Timestamp;

import com.bhzq.marketinfo.entity.domain.LogEntity;
import com.bhzq.marketinfo.entity.vo.SearchLog;
import com.github.pagehelper.PageInfo;


public interface LogService {
    
    void addLog(LogEntity logEntity);
   

    /**
     * 查询定时任务列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return 定时任务列表
     */
	PageInfo<LogEntity> list(Integer currentPage, Integer pageSize, SearchLog searchLog);
    
	void delete(Timestamp endTime);
}
