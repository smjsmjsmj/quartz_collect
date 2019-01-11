package com.mj.quartz.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobDemo{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		String fileName="txt_"+(UUID.randomUUID().toString())+".txt";
		String strPath = "E:\\TextDir\\"+fileName;  
		File file = new File(strPath);  
		if(!file.exists()){  
		    try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
	}

	public void runDemo(){
		String fileName="txt_"+(UUID.randomUUID().toString())+".txt";
		String strPath = "E:\\TextDir\\"+fileName;  
		File file = new File(strPath);  
		if(!file.exists()){  
		    try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
}
