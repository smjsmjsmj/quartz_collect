package com.mj.quartz;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
 
public class ExampleJob {
	
	private int timeout;
 
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行ExampleJob的定时任务");
	}
	
	public int getTimeout() {
		return timeout;
	}
 
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
 
}

