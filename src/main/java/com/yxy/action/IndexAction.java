package com.yxy.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yxy.entity.News;
import com.yxy.entity.Owner;
import com.yxy.service.NewsService;
import com.yxy.service.OwnerService;

@ParentPackage("struts-default")  
@Namespace("/")
public class IndexAction extends ActionSupport implements SessionAware{
	private Map<String,Object> session;
	private List<News> newss;
	private String tip;
	private Owner owner;
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private  OwnerService ownerService;
	
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/index.jsp") })
	public String index() throws Exception {
		newss=newsService.findTop3();
		return SUCCESS;
	}
	@Action(value = "apply", results = { @Result(name = "success", location = "/WEB-INF/views/apply.jsp") })
	public String apply() throws Exception {
		return SUCCESS;
	}
	@Action(value = "login", results = { @Result(name = "success", location = "/WEB-INF/views/login.jsp") })
	public String login() throws Exception {
		return SUCCESS;
	}
	 @Action(value = "loginout", results = { @Result(name = "success", location = "/WEB-INF/views/index.jsp") })
     public String loginout(){
	 	ActionContext.getContext().getSession().remove("user");
	 	ActionContext.getContext().getSession().clear();
		newss=newsService.findTop3();
	 	tip="成功退出登陆";
	 	return SUCCESS;
     }
	 @Action(value = "dologin", 
	    		results = { @Result(name = "success" , location = "/WEB-INF/views/index.jsp") ,
	    					@Result(name = "login", location = "/WEB-INF/views/index.jsp") })  
	    public String dologin() throws Exception { 
	    	Owner bean=ownerService.login(owner.getUsername(),owner.getPassword() );
	    	if(bean!=null){
	    		session.put("user",bean );
	    		ActionContext.getContext().getSession().put("user", bean);
	    		this.tip="登录成功!";
	    		newss=newsService.findTop3();
	            return SUCCESS; 
	    	}
	    	else{
	    		ActionContext.getContext().getSession().clear();
	    		this.tip="登陆失败 不存在此用户名或密码!";
	    		newss=newsService.findTop3();
	    		return LOGIN;
	    	}
	    } 
	
	@Action(value = "register", results = { @Result(name = "success", location = "/WEB-INF/views/register.jsp") })
	public String register() throws Exception {
		return SUCCESS;
	}
	@Action(value = "doregister", results = { @Result(name = "success", location = "/WEB-INF/views/index.jsp") })
	public String doregister() throws Exception {
		newss=newsService.findTop3();
		ownerService.save(owner);
		tip="注册成功！";
		return SUCCESS;
	}
	
	public List<News> getNewss() {
		return newss;
	}
	public void setNewss(List<News> newss) {
		this.newss = newss;
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	
	
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
