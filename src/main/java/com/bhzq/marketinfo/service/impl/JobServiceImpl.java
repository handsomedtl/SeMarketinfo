package com.bhzq.marketinfo.service.impl;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhzq.marketinfo.entity.domain.JobAndTrigger;
import com.bhzq.marketinfo.entity.form.JobForm;
import com.bhzq.marketinfo.mapper.JobMapper;
import com.bhzq.marketinfo.service.JobService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Job Service
 * </p>
 *
 * @package: com.xkcoding.task.quartz.service.impl
 * @description: Job Service
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 13:25
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@Slf4j
public class JobServiceImpl implements JobService {
    private final Scheduler scheduler;
    private final JobMapper jobMapper;

    @Autowired
    public JobServiceImpl(Scheduler scheduler, JobMapper jobMapper) {
        this.scheduler = scheduler;
        this.jobMapper = jobMapper;
    }

    /**
     * 添加并启动定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @return {@link JobDetail}
     * @throws Exception 异常
     */
    @Override
    public void addJob(JobForm form) throws Exception {
        

    }

    /**
     * 删除定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void deleteJob(JobForm form){
        
    }

    /**
     * 暂停定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void pauseJob(JobForm form) {
    }

    /**
     * 恢复定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void resumeJob(JobForm form){
    }

    /**
     * 重新配置定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    @Override
    public void cronJob(JobForm form) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(form.getTaskName(), form.getCommand());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 根据Cron表达式构建一个Trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("【定时任务】更新失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }
    }

    /**
     * 查询定时任务列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return 定时任务列表
     */
    @Override
    public PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<JobAndTrigger> list = jobMapper.list();
        return new PageInfo<>(list);
    }

	@Override
	public List<String> taskGroupList() {
		
		return jobMapper.taskGroupList();
	}

	@Override
	public int getTaskNameCount(String taskName) {
		
		return jobMapper.taskNameCount(taskName);
	}
}
