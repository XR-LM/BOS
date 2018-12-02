package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.service.IDecidedzoneService;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.Customer;
import com.itheima.crm.ICustomerService;
/**
 * 区域管理
 * @author XR
 *
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	@Autowired
	private ICustomerService customerService;
	private String[] subareaid;

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	@Autowired
	private IDecidedzoneService decidedzoneService;
	/**
	 * 添加定区
	 */
	public String add(){
		decidedzoneService.save(model,subareaid);
		return "list";
	}
	/**
	 * 分页查询
	 */
	public String pageQuery(){
		decidedzoneService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
		
		return NONE;
	}
	/**
	 * 远程调用crm服务，获取未关联到定区的客户
	 */
	public String findNoAssCustomer(){
		List<Customer> list = customerService.findNoAssCustomer();
		this.java2Json(list, new String[]{});
		return NONE;
	}
	/**
	 * 远程调用crm服务，获取关联到定区的客户
	 */
	public String findAssCustomer(){
		String id=model.getId();
		List<Customer> list = customerService.findAssCustomer(id);
		this.java2Json(list, new String[]{});
		return NONE;
	}
	//属性驱动，接收页面提交的多个客户id
	private List<Integer> customerIds;
	
	public List<Integer> getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	/**
	 * 远程调用crm服务，将客户关联到定区
	 */
	public String assCustomer(){
		System.out.println(model.getId()+" "+customerIds);
		customerService.assigncustomerstodecidedzone(model.getId(), customerIds);
		return "list";
	}
}
