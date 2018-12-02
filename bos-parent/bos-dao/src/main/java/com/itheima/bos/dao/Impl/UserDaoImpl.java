package com.itheima.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.dao.base.Impl.BaseDaoImlp;
import com.itheima.bos.domain.User;
@Repository
public class UserDaoImpl extends BaseDaoImlp<User> implements IUserDao {
	/**
	 * 根据用户名密码查询 用户
	 */
	@Override
	public User findUserByUsername(String username, String password) {
		// TODO Auto-generated method stub
		String hqlString="FROM User u where u.username= ? AND u.password =?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hqlString, username,password);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	/**
	 * 根据用户名查询用户
	 */
	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		String hqlString="FROM User u where u.username= ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hqlString, username);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}
