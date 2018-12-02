package com.itheima.bos.dao;

import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {
	//根据用户名和密码查询用户
	User findUserByUsername(String username, String password);

	User findUserByUsername(String username);

}
