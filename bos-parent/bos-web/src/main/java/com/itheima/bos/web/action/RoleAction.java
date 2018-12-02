package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Role;
import com.itheima.bos.service.IRoleService;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	//属性驱动获取权限id
	private String functionIds;
	@Autowired
	private IRoleService roleService;
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	/**
	 * 添加角色
	 * @return
	 */
	public String  add(){
		roleService.save(model,functionIds);
		return "list";
	}
	public String pageQuery(){
		roleService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"functions","users"});
		return NONE;
	}
	public String listajax(){
		List<Role> list = roleService.findAll();
		java2Json(list, new String[]{"functions","users"});
		return NONE;
	}
	
}
