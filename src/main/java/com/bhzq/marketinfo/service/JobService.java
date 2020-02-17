package com.bhzq.marketinfo.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.bhzq.marketinfo.entity.domain.JobAndTrigger;
import com.bhzq.marketinfo.entity.form.JobForm;
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
public interface JobService {
    /**
     * 添加并启动定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    void addJob(JobForm form) throws Exception;

    /**
     * 删除定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void deleteJob(JobForm form) ;

    /**
     * 暂停定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void pauseJob(JobForm form);

    /**
     * 恢复定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void resumeJob(JobForm form);

    /**
     * 重新配置定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    void cronJob(JobForm form) throws Exception;

    /**
     * 查询定时任务列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return 定时任务列表
     */
    PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize);
    
    /**
     * 查询分组列表
     */
    List<String> taskGroupList();
    
    /**
     * 查询任务名称列表
     */
    int getTaskNameCount(String taskName);
}
