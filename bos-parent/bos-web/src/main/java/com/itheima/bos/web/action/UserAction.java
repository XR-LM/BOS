package com.itheima.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.User;
import com.itheima.bos.service.IUserService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.MD5Utils;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.Customer;
import com.itheima.crm.ICustomerService;
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private String checkcode;
	@Autowired
	private IUserService userService;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	/**
	 * 用户登录
	 */
	public String login(){
		//校验验证码是否正确
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(!StringUtils.isNotEmpty(model.getUsername())||!StringUtils.isNotEmpty(model.getPassword())){
			this.addActionError("用户名或者密码为空!");
	    	return LOGIN;
		}
	    if(StringUtils.isNoneBlank(checkcode)&&checkcode.equalsIgnoreCase(code)){
	    	//使用shiro框架提供的方式进行身份认证
	    	Subject subject = SecurityUtils.getSubject();//获得当前登录用户对象，现在状态为“未认证”
	    	//用户名密码令牌
	    	AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
	    	try {
				subject.login(token);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				this.addActionError("用户名或者密码错误!");
				return LOGIN;
			}
	    	User user=(User)subject.getPrincipal();
    		//登陆成功，将user对象保存到session域中，跳转到首页
    		ServletActionContext.getRequest().getSession().setAttribute("LoginUser", user);
    		ServletActionContext.getResponse().addCookie(new Cookie("namecookie", user.getUsername()));
    		return "home";	
		  }else{
	    	this.addActionError("输入的验证码错误!");
	    	return LOGIN;
		 }
	}
	/**
	 * 用户注销
	 */
	public String logout(){
		//直接让session时效即可
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	/**
	 * 修改密码
	 * @throws IOException 
	 */
	public String editPassword() throws IOException{
		//获取User对象
		String flag="";
		User user = BOSUtils.getUserSession();
		System.out.println(model.getPassword());
		flag=userService.editPassword(user.getId(),model.getPassword());
		//调用service方法更新用户密码
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	/**
	 *添加 用户
	 */
	//属性驱动，接受多个角色id
	private String[] roleIds;
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	public String add(){
		userService.save(model,roleIds);
		return "list";
	}
	public String pageQuery(){
		userService.pageQuery(pageBean);
		java2Json(pageBean,new String[]{"noticebills","roles"});
		return NONE;
	}
}
