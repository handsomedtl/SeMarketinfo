package com.bhzq.marketinfo.service;

import java.util.List;

import com.bhzq.marketinfo.entity.domain.JobAndTrigger;
import com.bhzq.marketinfo.entity.domain.LogEntity;
import com.bhzq.marketinfo.entity.form.JobForm;
import com.bhzq.marketinfo.entity.vo.SearchLog;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * Job Service
 * </p>
 *
 * @description: Job Service
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 13:24
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
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
    
   
}
