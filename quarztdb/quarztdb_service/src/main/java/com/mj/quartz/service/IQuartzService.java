package com.mj.quartz.service;

import com.mj.quartz.model.QRTZJob;

public interface IQuartzService {
    
	void addJob(Class cls,QRTZJob scheduleJob);

	boolean modifyJob(Class cls,String oldjobName, String oldjobGroup, String oldtriggerName, String oldtriggerGroup,
			String jobName, String jobGroup, String triggerName, String triggerGroup, String cron,QRTZJob scheduleJob);

	boolean modifyJobCron(String triggerName, String triggerGroupName, String cronExpression) throws Exception;

	void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName);

	void startSchedule();

	void shutdownSchedule();

	void pauseJob(String jobName, String jobGroupName);

	void resumeJob(String jobName, String jobGroupName);
}
