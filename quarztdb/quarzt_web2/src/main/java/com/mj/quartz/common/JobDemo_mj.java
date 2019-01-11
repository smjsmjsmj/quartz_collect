package com.mj.quartz.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class JobDemo_mj {

	public void runJob(){
		String fileName="txt_mj_01_"+(UUID.randomUUID().toString())+".txt";
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
