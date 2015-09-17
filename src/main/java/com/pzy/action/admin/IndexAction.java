package com.pzy.action.admin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pzy.entity.Worker;
import com.pzy.service.WorkerService;

@Namespace("/admin")
public class IndexAction  extends ActionSupport{
	private String userName;
	private String  password;
	private String tip;
    
	@Autowired
	private WorkerService workService;
	
	public void setPassword(String password) {
		this.password = password;
	}
	@Action(value = "/adminindex", results = { @Result(name = "success", location = "/WEB-INF/views/admin/index.jsp") })
     public String index(){
          return SUCCESS;
     }
     @Action(value = "login", results = { @Result(name = "success", location = "/WEB-INF/views/admin/login.jsp") })
     public String login(){
          return SUCCESS;
     }
     @Action(value = "loginout", results = { @Result(name = "success", location = "/WEB-INF/views/admin/login.jsp") })
     public String loginout(){
    	 	ActionContext.getContext().getSession().remove("adminuser");
    	 	ActionContext.getContext().getSession().remove("worker");
    	 	ActionContext.getContext().getSession().clear();
    	 	return SUCCESS;
     }
     @Action(value = "gologin", results = { @Result(name = "success", location = "/WEB-INF/views/admin/index.jsp"),@Result(name = "input", location = "/WEB-INF/views/admin/login.jsp") })
     public String gologin(){
    	 if("admin".equals(this.userName)&&"123456".equals(this.password)){
    		 ActionContext.getContext().getSession().put("adminuser", "admin");
    		 return SUCCESS;
    	 }
    			
    	 else{
    		 Worker worker= workService.login(userName, password);
    		 if(worker!=null){
    	    		ActionContext.getContext().getSession().put("worker",worker);
    	            return SUCCESS; 
    	    	}
    	    	else{
    	    		ActionContext.getContext().getSession().clear();
    	    		this.tip="登陆失败 不存在此用户名或密码!";
    	    		return LOGIN;
    	    	}
    	 } 
         
     }
   
 	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
    public String getUserName() {
 		return userName;
 	}
 	public void setUserName(String userName) {
 		this.userName = userName;
 	}
 	public String getPassword() {
 		return password;
 	}
}

