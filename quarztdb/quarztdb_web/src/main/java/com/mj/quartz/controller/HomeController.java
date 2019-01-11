package com.mj.quartz.controller;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mj.quartz.common.JobDemo;
import com.mj.quartz.service.IQuartzService;
import com.sun.tools.javac.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private IQuartzService quartzService;
	
	@Autowired
	@SuppressWarnings("all")
	private Scheduler quartzScheduler;

	@RequestMapping("")
	public String Index() {
		// IQuartzService.removeJob("job2", "job1","tri1", "tri1");
//		
//		String fileName="plugin.test4";
//		Class class1=Class.forName(fileName);
//		
//		addJob("jobDemo", "mj", "tri1", "tri1",JobDemo.class, "0/10 * * * * ?");
		
		return "home/index";
	}
	
	@RequestMapping("/stop")
	public void Stop() throws SchedulerException{
		
		java.util.List<String> listName=quartzScheduler.getJobGroupNames();
		boolean flag=quartzScheduler.checkExists(JobKey.jobKey("testjob4","mj"));
		flag=flag;
		
		StdSchedulerFactory schedulerFactory = new StdSchedulerFactory(); 
		Scheduler scheduler = schedulerFactory.getScheduler(); 
		TriggerState state = scheduler.getTriggerState(TriggerKey.triggerKey("tri4", "mj"));
		
		quartzService.pauseJob("testjob4","mj");
	}
	

	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class cls,
			String cron) {
		try {

			// 获取调度器
			Scheduler sched = quartzScheduler;
			// 创建一项作业
			 JobDetail job = JobBuilder.newJob(cls)
			 .withIdentity(jobName, jobGroupName).build();
			// 创建一个触发器
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
			// 告诉调度器使用该触发器来安排作业
			sched.scheduleJob(job, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
