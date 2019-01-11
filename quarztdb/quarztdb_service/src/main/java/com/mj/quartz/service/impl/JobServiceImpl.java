package com.mj.quartz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.quartz.dao.QRTZJobMapper;
import com.mj.quartz.model.QRTZJob;
import com.mj.quartz.service.IJobService;

@Service
public class JobServiceImpl implements IJobService{

	@Autowired
	private QRTZJobMapper jobMapper;
	
	@Override
	public int insert(QRTZJob record) {
		return jobMapper.insert(record);
	}

	@Override
	public QRTZJob selectByPrimaryKey(Long id) {
		return jobMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(QRTZJob record) {
		return jobMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<QRTZJob> selectJobs() {
		return jobMapper.selectJobs();
	}

}
