package com.itheima.bos.dao.base.Impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

public class BaseDaoImlp<T> extends HibernateDaoSupport implements IBaseDao<T> {
	//代表某个实体的类型
	private Class<T> entityClass;
	//在父类的构造方法中来动态获得entityClass
	public BaseDaoImlp(){
		//父类类型
		ParameterizedType superclass = (ParameterizedType)this.getClass().getGenericSuperclass();
		//获得父类上的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		//获得entitClass
		entityClass=(Class<T>) actualTypeArguments[0];
	}
	//使用spring工厂注入sessionFactory实例
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		String hql = "From"+" "+entityClass.getSimpleName();
		
		return (List<T>) this.getHibernateTemplate().find(hql);
	}
	@Override
	public boolean executeUpdate(String queryName, Object... objects) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i = 0;
		for (Object object : objects) {
			//为HQL语句中的？赋值
			query.setParameter(i++, object);
		}
		//执行更新
		int j = query.executeUpdate();
		if(j!=0){
			return true;
		}
		return false;
		
	}
	/**
	 * 通过分页查询数据
	 */
	@Override
	public void PageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		//当前页数
		int currentPage=pageBean.getCurrentPage();
		//每页记录数
		int pageSize=pageBean.getPageSize();
		//创建离线查询对象
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		//查询总记录数
		detachedCriteria.setProjection(Projections.rowCount());
		//通过数据库查询
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long count = countList.get(0);
		pageBean.setTotal(count.intValue());
		//查询rows,当前页所需展示的数据集合
		//将查询条件清空
		detachedCriteria.setProjection(null);
		//制定hibernate封装对象的格式
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult = (currentPage-1)*pageSize;
		List list = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, pageSize);
		pageBean.setRows(list);
	}
	@Override
	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	@Override
	public List<T> findbyCriteria(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
