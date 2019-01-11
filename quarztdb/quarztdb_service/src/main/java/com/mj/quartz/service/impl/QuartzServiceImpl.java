package com.mj.quartz.service.impl;

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

import com.mj.quartz.model.QRTZJob;
import com.mj.quartz.service.IQuartzService;

@Service
public class QuartzServiceImpl implements IQuartzService{

	    @Autowired
	    @SuppressWarnings("all")
	    private Scheduler quartzScheduler;

	    @Override
	    public void addJob(Class cls,QRTZJob scheduleJob) {
	        try
	        {
	            // 获取调度器
	            Scheduler sched = quartzScheduler;
	            // 创建一项作业
	            JobDetail job = JobBuilder.newJob(cls)
	                    .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();
	            job.getJobDataMap().put("scheduleJob", scheduleJob);
	            // 创建一个触发器
	            CronTrigger trigger = TriggerBuilder.newTrigger()
	                    .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
	                    .withSchedule(CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression()))
	                    .build();
	            
//	            SimpleTrigger trigger =(SimpleTrigger) TriggerBuilder.newTrigger()
//	            		.withIdentity(jobName,jobGroupName)
//	    				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//	    						.withIntervalInSeconds(scheduleJob.getRepeatInterval()).withRepeatCount(scheduleJob.getRepeatCount()))
//	    				.build();
	            // 告诉调度器使用该触发器来安排作业
	            sched.scheduleJob(job, trigger);
	            // 启动
	            if (!sched.isShutdown())
	            {
	                sched.start();
	            }
	        } catch (Exception e)
	        {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * 修改定时器任务信息
	     */
	    @Override
	    public boolean modifyJob(Class cls,String oldjobName, String oldjobGroup, String oldtriggerName, String oldtriggerGroup, String jobName, String jobGroup,
	                             String triggerName, String triggerGroup, String cron,QRTZJob scheduleJob) {
	        try
	        {
	            Scheduler sched = quartzScheduler;
	            CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey
	                    .triggerKey(oldtriggerName, oldtriggerGroup));
	            if (trigger == null)
	            {
	                return false;
	            }

	            JobKey jobKey = JobKey.jobKey(oldjobName, oldjobGroup);
	            TriggerKey triggerKey = TriggerKey.triggerKey(oldtriggerName,
	                    oldtriggerGroup);

	            JobDetail job = sched.getJobDetail(jobKey);
	            Class jobClass = job.getJobClass();
	            // 停止触发器
	            sched.pauseTrigger(triggerKey);
	            // 移除触发器
	            sched.unscheduleJob(triggerKey);
	            // 删除任务
	            sched.deleteJob(jobKey);

	            addJob(cls,scheduleJob);

	            return true;
	        } catch (Exception e)
	        {
	            throw new RuntimeException(e);
	        }

	    }

	    @Override
	    public boolean modifyJobCron(String triggerName, String triggerGroupName,
	                                 String cronExpression) throws  Exception {

	        boolean flag = false;
	        try
	        {
	            Scheduler sched = quartzScheduler;
	            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName);

	            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
	            if (trigger == null)
	            {
	                flag =  false;
	            }
	            String oldExpression = trigger.getCronExpression();
	            if (!oldExpression.equalsIgnoreCase(cronExpression))
	            {
	                @SuppressWarnings("rawtypes")
					TriggerBuilder builder = TriggerBuilder
	                        .newTrigger()
	                        .withIdentity(triggerName,triggerGroupName);
	                @SuppressWarnings("unchecked")
					CronTrigger newTrigger = (CronTrigger)builder
	                        .startNow()
	                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
	                        .build();

	                // 重启触发器
	                sched.rescheduleJob(triggerKey,newTrigger);
	                flag = true;
	            }
	        } catch (Exception e)
	        {
	            flag =  false;
	            throw e;

	        }finally
	        {
	            return flag;
	        }
	    }

	    @Override
	    public void removeJob(String jobName, String jobGroupName,
	                          String triggerName, String triggerGroupName) {
	        try
	        {
	            Scheduler sched = quartzScheduler;
	            // 停止触发器
	            sched.pauseTrigger(TriggerKey.triggerKey(triggerName,
	                    triggerGroupName));
	            // 移除触发器
	            sched.unscheduleJob(TriggerKey.triggerKey(triggerName,
	                    triggerGroupName));
	            // 删除任务
	            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
	        } catch (Exception e)
	        {
	            throw new RuntimeException(e);
	        }
	    }

	    @Override
	    public void startSchedule() {
	        try
	        {
	            Scheduler sched = quartzScheduler;
	            sched.start();
	        } catch (Exception e)
	        {
	            throw new RuntimeException(e);
	        }
	    }

	    @Override
	    public void shutdownSchedule() {
	        try
	        {
	            Scheduler sched = quartzScheduler;
	            if (!sched.isShutdown())
	            {
	                sched.shutdown();
	            }
	        } catch (Exception e)
	        {
	            throw new RuntimeException(e);
	        }
	    }

	    @Override
	    public void pauseJob(String jobName, String jobGroupName) {
	        try
	        {
	        	JobDetail detail=quartzScheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
	            quartzScheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
	        } catch (SchedulerException e)
	        {
	            e.printStackTrace();
	        }

	    }

	    @Override
	    public void resumeJob(String jobName, String jobGroupName) {
	        try
	        {
	            quartzScheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
	        } catch (SchedulerException e)
	        {
	            e.printStackTrace();
	        }
	    }
}
