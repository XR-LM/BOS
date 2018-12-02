package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 表现层通用实现
 * @author XR
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	protected PageBean pageBean = new PageBean();
	//创建离线查询对象
	DetachedCriteria detachedCriteria=null;
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	/**
	 * 将指定Java对象转为json，并响应到客户端页面
	 * @param o
	 * @param exclueds
	 */
	public void java2Json(Object o ,String[] exclueds){
		JsonConfig jsonConfig = new JsonConfig();
		//指定哪些属性不需要转json
		jsonConfig.setExcludes(exclueds);
		String json = JSONObject.fromObject(o,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将集合转为json，并响应到客户端页面
	 * @param o
	 * @param exclueds
	 */
	public void java2Json(List o ,String[] exclueds){
		JsonConfig jsonConfig = new JsonConfig();
		//指定哪些属性不需要转json
		jsonConfig.setExcludes(exclueds);
		String json = JSONArray.fromObject(o,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//模型对象
	protected T model;
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	public BaseAction() {
		//获得父类参数类型
		ParameterizedType genericSuperclass=(ParameterizedType)this.getClass().getGenericSuperclass();
		//获取参数数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> entiyClass = (Class<T>) actualTypeArguments[0];
		detachedCriteria = DetachedCriteria.forClass(entiyClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		//通过反射创建对象
		try {
			model=entiyClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
