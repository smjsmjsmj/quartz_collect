package com.quartz.test;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

public class TestJob {

	public void testMethod() throws SchedulerException {
		// 获取SchedulerFactory实例
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		// 获取Scheduler实例
		Scheduler sched = schedFact.getScheduler();

		// 获取JobDetail实例
		JobDetail job = newJob(HelloJob.class).withIdentity("testJob", "testGroup").build();

		/**
		 * simpleSchedule:开始时间、结束时间、重复次数以及重复执行的时间间隔。
		 */
		// Trigger trigger = newTrigger().withIdentity("testTrigger",
		// "testGroup")
		// .startNow()
		// .withSchedule(simpleSchedule()
		// .withIntervalInSeconds(2)
		// .repeatForever()).build();
		
		/**
		 * 基于Cron表达式的触发器CronTrigger:
		 * 基于日历的概念，而不是对具体间隔的行为
		 * example:'每个星期五的中午','每个工作日上午9:30'
		 * note:
		 * 		Cron表达式:秒 分 时 天 月 周 年(可选项)  参考：http://cron.qqe2.com/
		 */
		// 获取Trigger 实例
		Trigger trigger = newTrigger().withIdentity("testTrigger", "testGroup")
				.startNow()
				.withSchedule(cronSchedule("0/5 * * * * ? "))
				.build();
		
		
		
		System.out.println(sched.getSchedulerName());

		sched.scheduleJob(job, trigger);

		// 启动
		sched.start();

	}

	public static void main(String[] args) throws SchedulerException {
		TestJob test = new TestJob();
		test.testMethod();
	}
}
