package com.mj.quartz.common;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.mj.quartz.model.QRTZJob;
 
/**
 * QuartzJob工厂
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {
   
	private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		QRTZJob scheduleJob = (QRTZJob) context.getMergedJobDataMap().get("scheduleJob");
		Class<?> clazz;
		try {
			clazz = Class.forName(scheduleJob.getClassPath());
			Object invokeTest = null;
			try {
				invokeTest = clazz.newInstance();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Method method = ReflectionUtils.findMethod(clazz, scheduleJob.getMethodName());
			try {
				method.invoke(invokeTest);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ReflectionUtils.invokeMethod(method,clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		logger.info("----------定时开始----------");
	}
 
}
