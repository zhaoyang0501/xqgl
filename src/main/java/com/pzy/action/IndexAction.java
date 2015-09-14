package com.pzy.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pzy.entity.Grades;
import com.pzy.entity.News;
import com.pzy.entity.Notice;
import com.pzy.entity.User;
import com.pzy.service.GradesService;
import com.pzy.service.NewsService;
import com.pzy.service.NoticeService;
import com.pzy.service.UserService;

@ParentPackage("struts-default")  
@Namespace("/")
public class IndexAction extends ActionSupport implements SessionAware{
	private Map<String,Object> session;
	private Grades grades;
	private List<News> newss;
	private List<Grades> gradess;
	private List<User> users;
	private User user;
	private String tip;
	@Autowired
	private GradesService gradesService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private UserService userService;
	
	
	public String execute() throws Exception {
		newss=newsService.findTop3();
		gradess=gradesService.findTop4();
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
		gradess=gradesService.findTop4();
	 	tip="成功退出登陆";
	 	return SUCCESS;
     }
	@Action(value = "grades", results = { @Result(name = "success", location = "/WEB-INF/views/grades.jsp") })
	public String grades() throws Exception {
		Grades grades = (Grades) ServletActionContext.getRequest().getSession().getAttribute("grades");
		this.grades=grades;
		users=userService.findByNews(grades);
		return SUCCESS;
	}
	 @Action(value = "dologin", 
	    		results = { @Result(name = "success" , location = "grades.jsp") ,
	    					@Result(name = "login", location = "/WEB-INF/views/login.jsp") })  
	    public String dologin() throws Exception { 
	    	User loginuser=userService.login(user.getId(), user.getPassword());
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
	    	}
	    		 
	    } 
	
	@Action(value = "register", results = { @Result(name = "success", location = "/WEB-INF/views/register.jsp") })
	public String register() throws Exception {
		return SUCCESS;
	}
	@Action(value = "goapply", results = { @Result(name = "success", location = "/WEB-INF/views/index.jsp") })
	public String goapply() throws Exception {
		grades.setCreateDate(new Date());
		grades.setState("未审核");
		gradesService.save(grades);
		newss=newsService.findTop3();
		gradess=gradesService.findTop4();
		tip="成功提交申请,请等待管理员审核";
		return SUCCESS;
	}
	
	public Grades getGrades() {
		return grades;
	}
	public void setGrades(Grades grades) {
		this.grades = grades;
	}
	public List<News> getNewss() {
		return newss;
	}
	public void setNewss(List<News> newss) {
		this.newss = newss;
	}
	public List<Grades> getGradess() {
		return gradess;
	}
	public void setGradess(List<Grades> gradess) {
		this.gradess = gradess;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
