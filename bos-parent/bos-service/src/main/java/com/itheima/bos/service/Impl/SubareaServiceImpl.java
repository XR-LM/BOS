package com.itheima.bos.service.Impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.itheima.bos.dao.ISubareaDao;
import com.itheima.bos.domain.Subarea;

import com.itheima.bos.service.ISubareaService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	@Autowired
	private ISubareaDao subareaDao;

	@Override
	public void save(Subarea model) {
		// TODO Auto-generated method stub
		subareaDao.save(model);
	}

	@Override
	public void pageQery(PageBean pageBean) {
		// TODO Auto-generated method stub
		subareaDao.PageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		// TODO Auto-generated method stub
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findListNotAssiontion() {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findbyCriteria(detachedCriteria);
	}
	/**
	 * 根据定区Id查询关联的分区
	 */
	@Override
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		//添加查询条件
		detachedCriteria.add(Restrictions.eq("decidedzone.id",decidedzoneId));
		return subareaDao.findbyCriteria(detachedCriteria);
	}
	/**
	 * 查询分区数量
	 */
	@Override
	public List<Object> findSubByPro() {
		// TODO Auto-generated method stub
		return subareaDao.findSubByPro();
	}
	
}
