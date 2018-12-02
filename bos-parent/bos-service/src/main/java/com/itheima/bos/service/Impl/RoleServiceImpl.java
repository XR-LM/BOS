package com.itheima.bos.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IRoleDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.Role;
import com.itheima.bos.service.IRoleService;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;
	@Override
	/**
	 * 保存角色信息
	 */
	public void save(Role model, String functionIds) {
		// TODO Auto-generated method stub
		roleDao.save(model);
		if(StringUtils.isNotBlank(functionIds)){
			String[] fIds = functionIds.split(",");
			for(String functionId:fIds){
				Function function = new Function(functionId);
				model.getFunctions().add(function);
			}
		}
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		roleDao.PageQuery(pageBean);
	}
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

}
