package com.pzy.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.pzy.entity.*;
import com.pzy.service.UserService;

@Namespace("/")
@ParentPackage("json-default") 
public class CenterAction extends PageAction {
	private User user;
	private String tip;
	private File imgPath;  
	private String imgPathContentType;  
	private String imgPathFileName;  
	@Autowired
	private UserService userService;
	/***
	 * 
	 * @return
	 */
	@Action(value = "doCenter", results = { @Result(name = "success", location = "center.jsp") })
	public String doCenter(){
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload/head");
		System.out.println(realpath);
		if(imgPath!=null){
			user.setHeadimg(imgPathFileName);
			File saveImg = new File(new File(realpath), this.imgPathFileName);
	         try {
				FileUtils.copyFile(imgPath, saveImg);
			} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
			}
		}else{
			user.setHeadimg("images/web/man.png");
		}
		
     
		user.setGrades((Grades)ActionContext.getContext().getSession().get("grades"));
		userService.save(user);
		ActionContext.getContext().getSession().put("user", user);
		tip="用户信息修改成功";
		return SUCCESS;
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
	public File getImgPath() {
		return imgPath;
	}
	public void setImgPath(File imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgPathContentType() {
		return imgPathContentType;
	}
	public void setImgPathContentType(String imgPathContentType) {
		this.imgPathContentType = imgPathContentType;
	}
	public String getImgPathFileName() {
		return imgPathFileName;
	}
	public void setImgPathFileName(String imgPathFileName) {
		this.imgPathFileName = imgPathFileName;
	}
}
