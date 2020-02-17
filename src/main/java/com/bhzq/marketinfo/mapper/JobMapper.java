package com.bhzq.marketinfo.mapper;

import org.springframework.stereotype.Component;

import com.bhzq.marketinfo.entity.domain.JobAndTrigger;

import java.util.List;

/**
 * <p>
 * Job Mapper
 * </p>
 *
 * @description: Job Mapper
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 15:12
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
public interface JobMapper {
    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    List<JobAndTrigger> list();
    
    List<String> taskGroupList();
    
    int taskNameCount(String taskName);
}
