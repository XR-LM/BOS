package com.itheima.bos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IWorkordermanageDao;
import com.itheima.bos.domain.Workordermanage;
import com.itheima.bos.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServieImpl implements IWorkordermanageService{
	@Autowired
	private IWorkordermanageDao workordermanageDao;

	@Override
	public void save(Workordermanage model) {
		// TODO Auto-generated method stub
		workordermanageDao.save(model);
	}
}
