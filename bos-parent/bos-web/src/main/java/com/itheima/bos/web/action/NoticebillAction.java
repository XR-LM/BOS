package com.itheima.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Noticebill;
import com.itheima.bos.service.INoticebillService;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.Customer;
import com.itheima.crm.ICustomerService;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	//注入crm客户端代理对象
	@Autowired
	private ICustomerService customerService;
	//注入service对象
	@Autowired
	private INoticebillService noticebillService;
	/*
	 * 远程调用crm服务，根据手机号查询客户信息
	 */
	public String findCustomerByTel(){
		String telephone=model.getTelephone();
		Customer customer=customerService.findCustomerByTel(telephone);
		this.java2Json(customer, new String[]{});
		return NONE;
	}
	/*
	 * 保存业务通知单，并尝试自动分单
	 */
	public String add(){
		noticebillService.save(model);
		return "noticebill_add";
	}
}
