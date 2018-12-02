package com.itheima.bos.web.interceptor;

import com.itheima.bos.domain.User;
import com.itheima.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 登录拦截校验
 * @author XR
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//获取session中的User对象
		User  user = BOSUtils.getUserSession();
		if(user==null){
			return "login";
		}
		return invocation.invoke();
	}

}
