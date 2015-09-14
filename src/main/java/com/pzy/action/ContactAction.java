package com.pzy.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.pzy.entity.Grades;
import com.pzy.entity.User;
import com.pzy.service.UserService;

@Namespace("/")
@ParentPackage("json-default") 
public class ContactAction extends PageAction {
	private List<User> users;
	private User user;
	private String tip;
	@Autowired
	private UserService userService;
	public String execute() throws Exception {
		Grades grades = (Grades) ServletActionContext.getRequest().getSession().getAttribute("grades");
		users=userService.findByNews(grades);
		return SUCCESS;
	}
	@Action(value = "registerUser", results = { @Result(name = "success", location = "/WEB-INF/views/register_user.jsp") })
	public String registerUser() throws Exception {
		return SUCCESS;
	}
	@Action(value = "doRegisterUser", results = { @Result(name = "success", location = "/WEB-INF/views/contact.jsp") })
	public String doRegisterUser() throws Exception {
		Grades grades = (Grades) ServletActionContext.getRequest().getSession().getAttribute("grades");
		user.setGrades(grades);
		user.setCreateDate(new Date());
		userService.save(user);
		tip="注册成功！";
		users=userService.findByGrades(grades);
		return SUCCESS;
	}
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
