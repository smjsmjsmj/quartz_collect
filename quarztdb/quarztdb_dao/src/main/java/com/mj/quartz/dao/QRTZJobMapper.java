package com.mj.quartz.dao;

import java.util.List;

import com.mj.quartz.model.QRTZJob;

public interface QRTZJobMapper {
   
	int insert(QRTZJob record);

    QRTZJob selectByPrimaryKey(Long id);

    int updateByPrimaryKey(QRTZJob record);
    
    List<QRTZJob> selectJobs();
    
}