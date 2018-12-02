package com.itheima.bos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IFunctionDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IFunctionService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class FunctionSerciceImpl implements IFunctionService {
	@Autowired
	private IFunctionDao functionDao;
	@Override
	public List<Function> findAll() {
		// TODO Auto-generated method stub
		
		return functionDao.findAll();
	}
	@Override
	public void save(Function model) {
		// TODO Auto-generated method stub
		Function parentFunction =model.getParentFunction();
		if(parentFunction!=null&&parentFunction.getId().equals("")){
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		functionDao.PageQuery(pageBean);
	}
	@Override
	public List<Function> findMenu() {
		// TODO Auto-generated method stub
		List<Function> list = null;
		User user = BOSUtils.getUserSession();
		if(user.getUsername().equals("admin")){
			list=functionDao.findAllMenu();
		}else{
			list=functionDao.findMenuByUserId(user.getId());
		}
		return list;
		
		
	}

}
