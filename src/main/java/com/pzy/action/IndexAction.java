package com.pzy.action;

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
import com.pzy.entity.News;
import com.pzy.service.NewsService;

@ParentPackage("struts-default")  
@Namespace("/")
public class IndexAction extends ActionSupport implements SessionAware{
	private Map<String,Object> session;
	private List<News> newss;
	private String tip;
	@Autowired
	private NewsService newsService;
	
	
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/index.jsp") })
	public String index() throws Exception {
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
	 	ActionContext.getContext().getSession().remove("grades");
	 	ActionContext.getContext().getSession().clear();
		newss=newsService.findTop3();
	 	tip="成功退出登陆";
	 	return SUCCESS;
     }
	 @Action(value = "dologin", 
	    		results = { @Result(name = "success" , location = "grades.jsp") ,
	    					@Result(name = "login", location = "/WEB-INF/views/login.jsp") })  
	    public String dologin() throws Exception { 
	    	/*User loginuser=userService.login(user.getId(), user.getPassword());
	    	if(loginuser!=null){
	    		session.put("user",loginuser );
	    		session.put("grades",loginuser.getGrades() );
	    		users=userService.findByNews((Grades) ServletActionContext.getRequest().getSession().getAttribute("grades"));
	            return SUCCESS; 
	    	}
	    	else{
	    		ActionContext.getContext().getSession().clear();
	    		this.tip="登陆失败 不存在此用户名或密码!";
	    		return LOGIN;
	    	}*/
		 return SUCCESS; 
	    } 
	
	@Action(value = "register", results = { @Result(name = "success", location = "/WEB-INF/views/register.jsp") })
	public String register() throws Exception {
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
}
