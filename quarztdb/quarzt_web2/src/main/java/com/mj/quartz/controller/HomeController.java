package com.mj.quartz.controller;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mj.quartz.common.QuartzJobFactory;
import com.mj.quartz.model.QRTZJob;
import com.mj.quartz.service.IJobService;
import com.mj.quartz.service.IQuartzService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private IJobService jobService;
	
	
	@Autowired
	private IQuartzService quartzService;
	

	@RequestMapping("")
	@Transactional
	public String index(Model model) throws SchedulerException {
		
		List<QRTZJob> joblist=jobService.selectJobs();
		model.addAttribute("joblist",joblist);
		return "home/index";
	}

	@RequestMapping("/add")
	public String add(){
		return "home/add";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(QRTZJob job){
		int res=jobService.insert(job);
		quartzService.addJob(QuartzJobFactory.class,
				job);
		if(job.getJobStatus().equals("on")){
			quartzService.resumeJob(job.getJobName(), job.getJobGroup());
		}else{
			quartzService.pauseJob(job.getJobName(), job.getJobGroup());
		}
		return "success";
	}
	
	@RequestMapping("/changeJobStatus")
	public void changeJobStatus(int status,long jobId){
		
		QRTZJob job=jobService.selectByPrimaryKey(jobId);
		if(status==1){
			quartzService.resumeJob(job.getJobName(), job.getJobGroup());
			job.setJobStatus("on");
			jobService.updateByPrimaryKey(job);
		}else{
			quartzService.pauseJob(job.getJobName(), job.getJobGroup());
		}
	}
	
	
	
	
	
	
}
