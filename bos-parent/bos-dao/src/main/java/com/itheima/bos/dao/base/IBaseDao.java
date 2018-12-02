package com.itheima.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

public interface IBaseDao<T> {
	//保存用户
	public void save(T entity);
	//删除用户
	public void delete(T entity);
	//更新用户
	public void update(T entity);
	//按照ID查询用户
	public T findById(Serializable id);
	//查询所有用户
	public List<T> findAll();
	//查询所有用户
	public List<T> findbyCriteria(DetachedCriteria detachedCriteria);
	//更新
	public boolean executeUpdate(String queryName, Object... objects);
	//按照分页查询数据
	public void PageQuery(PageBean pageBean);
	//保存或者更新
	void saveOrUpdate(T entity);
	
}
