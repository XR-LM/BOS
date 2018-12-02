package com.itheima.bos.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IStaffDao;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
	@Autowired
	private IStaffDao staffDao;
	@Override
	public void save(Staff staff) {
		// TODO Auto-generated method stub
		staffDao.save(staff);
	}
	//查询取派员信息
	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		staffDao.PageQuery(pageBean);
	}
	//批量删除
	@Override
	public void deleteBatch(String ids) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(ids)){
			String[] staffIds = ids.split(",");
			for (String id : staffIds) {
				staffDao.executeUpdate("staff.delete", id);
			}
		}
	}
	@Override
	public Staff findById(String id) {
		// TODO Auto-generated method stub
		Staff staff = staffDao.findById(id);
		return staff;
	}
	@Override
	public void update(Staff staff) {
		// TODO Auto-generated method stub
		staffDao.update(staff);
	}
	@Override
	public List<Staff> findListNotDelete() {
		// TODO Auto-generated method stub
		//设置离线查询对象模板
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		//添加查询条件
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		return staffDao.findbyCriteria(detachedCriteria);
	}	
}
