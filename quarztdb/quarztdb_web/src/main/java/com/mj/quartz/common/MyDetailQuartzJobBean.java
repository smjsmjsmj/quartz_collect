package com.mj.quartz.common;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

public class MyDetailQuartzJobBean extends QuartzJobBean {

	protected final Log logger = LogFactory.getLog(getClass());

	private String targetObject;

	private String targetMethod;

	private ApplicationContext ctx;

	protected void executeInternal(JobExecutionContext context)

			throws JobExecutionException {

		try {

			logger.info("execute [" + targetObject + "] at once>>>>>>");

			Object otargetObject = ctx.getBean(targetObject);

			Method m = null;

			try {

				m = otargetObject.getClass().getMethod(targetMethod,

						new Class[] {});

				m.invoke(otargetObject, new Object[] {});

			} catch (SecurityException e) {

			} catch (NoSuchMethodException e) {

			}

		} catch (Exception e) {

			throw new JobExecutionException(e);

		}

	}

	public void setApplicationContext(ApplicationContext applicationContext) {

		this.ctx = applicationContext;

	}

	public void setTargetObject(String targetObject) {

		this.targetObject = targetObject;

	}

	public void setTargetMethod(String targetMethod) {

		this.targetMethod = targetMethod;

	}

}