package com.itheima.bos.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IRegionDao;
import com.itheima.bos.dao.IStaffDao;
import com.itheima.bos.domain.Region;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IRegionService;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	@Autowired
	private IRegionDao regionDao;

	@Override
	public void saveBatch(List<Region> list) {
		// TODO Auto-generated method stub
		for (Region region : list) {
			regionDao.saveOrUpdate(region);
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		regionDao.PageQuery(pageBean);
	}

	@Override
	public List<Region> findListByQ(String q) {
		// TODO Auto-generated method stub
		return regionDao.findListByQ(q);
	}

	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return regionDao.findAll();
	}

}
