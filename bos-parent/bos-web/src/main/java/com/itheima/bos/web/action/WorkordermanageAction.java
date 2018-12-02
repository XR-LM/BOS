package com.itheima.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Workordermanage;
import com.itheima.bos.service.IWorkordermanageService;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	/**
	 * 快速录入工单信息
	 * @return
	 */
	@Autowired
	private IWorkordermanageService workordermanageService;
	public String add() throws IOException{
		String flag="1";
		try {
			workordermanageService.save(model);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag="0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		
		return NONE;
		
	}
}
