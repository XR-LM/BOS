package com.itheima.bos.web.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	/**
	 * 添加取派员
	 */
	@Autowired
	private IStaffService staffService;
	
	public String add(){
		staffService.save(model);
		return "list";
	}
	//使用属性驱动注入分页信息
	private int page;
	private int rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	/*
	 * 查询指派员
	 */
	public String pageQuery() throws IOException{
		//创建离线查询对象
		DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Staff.class);
		//实例化pagebean对象
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		pageBean.setDetachedCriteria(detachedCriteria);
		//调用Service查询数据
		staffService.pageQuery(pageBean);
		//将查询到的数据保存到JSON中
		//使用json-lib将PageBean对象转为json，通过输出流写回页面中
		//JSONObject---将单一对象转为json
		//JSONArray----将数组或者集合对象转为json
		JsonConfig config =new JsonConfig();
		config.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
		String json = JSONObject.fromObject(pageBean,config).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);

		return NONE;
		
	}
	private String ids;
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * 批量删除数据
	 * @return
	 */
	@RequiresPermissions("staff-delete")//执行这个方法，需要当前用户具有staff-delete权限
	public String deleteBatch(){
		staffService.deleteBatch(ids);
		return "list";

	}
	/**
	 * 修改数据
	 * @return
	 */
	public String edit(){
		//显查询数据库，根据id查询原始数据
		Staff staff = staffService.findById(model.getId());
		//使用页面提交的数据进行覆盖
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staffService.update(staff);
		return "list";
	}
	/**
	 * ajax回传数据
	 * 
	 */
	public String listajax(){
		//查询所有未被删除的取派员
		List<Staff> list = staffService.findListNotDelete();
		this.java2Json(list, new String[]{"decidedzones"});
		return NONE;
		
	}
}
