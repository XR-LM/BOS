package com.itheima.bos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.domain.Role;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IUserService;
import com.itheima.bos.utils.MD5Utils;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	/**
	 * 用户登录
	 */
	@Autowired
	private IUserDao userDao;
	
	public User login(User user) {
		// TODO Auto-generated method stub
		String password = MD5Utils.md5(user.getPassword());
		User user1=userDao.findUserByUsername(user.getUsername(),password);
		return user1;
	}
	/**
	 * 修改用户密码
	 */
	@Override
	public String editPassword(String id, String password) {
		// TODO Auto-generated method stub
		//加密密码
		String pass = MD5Utils.md5(password);
		if(userDao.executeUpdate("user.editpassword",pass,id)==true)
			return "1";
		return "0";
		
	}
	@Override
	public void save(User user, String[] roleIds) {
		// TODO Auto-generated method stub
		String password=user.getPassword();
		password=MD5Utils.md5(password);
		user.setPassword(password);
		userDao.save(user);
		if(roleIds!=null&&roleIds.length>0){
			for (String string : roleIds) {
				//手动构造游离对象
				Role role= new Role(string);
				//用户对象关联角色对象
				user.getRoles().add(role);
			}
		}
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		userDao.PageQuery(pageBean);
	}

}
