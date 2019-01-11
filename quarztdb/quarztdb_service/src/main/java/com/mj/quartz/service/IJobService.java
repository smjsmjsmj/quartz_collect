package com.mj.quartz.service;

import java.util.List;

import com.mj.quartz.model.QRTZJob;

public interface IJobService {

	int insert(QRTZJob record);

    QRTZJob selectByPrimaryKey(Long id);

    int updateByPrimaryKey(QRTZJob record);
    
    List<QRTZJob> selectJobs();
	
}
