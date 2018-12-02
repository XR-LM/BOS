package com.itheima.bos.dao.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.IRegionDao;
import com.itheima.bos.dao.IStaffDao;
import com.itheima.bos.dao.ISubareaDao;
import com.itheima.bos.dao.base.Impl.BaseDaoImlp;
import com.itheima.bos.domain.Region;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.domain.Subarea;
@Repository
public class SubareaDaoImpl extends BaseDaoImlp<Subarea> implements ISubareaDao {

	@Override
	public List<Object> findSubByPro() {
		// TODO Auto-generated method stub
		String hql = "SELECT r.province ,count(*) FROM Subarea s LEFT OUTER JOIN s.region r Group BY r.province";
		return (List<Object>) this.getHibernateTemplate().find(hql);
	}
}
