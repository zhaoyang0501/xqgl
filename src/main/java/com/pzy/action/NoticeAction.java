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
import com.pzy.entity.Notice;
import com.pzy.entity.User;
import com.pzy.service.NoticeService;

@Namespace("/")
@ParentPackage("json-default") 
public class NoticeAction extends PageAction {
	private List<Notice> notices;
	private Notice notice;
	private String tip;
	@Autowired
	private NoticeService noticeService;
	public String execute() throws Exception {
		Grades grades = (Grades) ServletActionContext.getRequest().getSession().getAttribute("grades");
		notices=noticeService.findByGrades(grades);
		return SUCCESS;
	}
	@Action(value = "createNotice", results = { @Result(name = "success", location = "/WEB-INF/views/create_notice.jsp") })
	public String createNotice() throws Exception {
		return SUCCESS;
	}
	@Action(value = "doCreateNotice", results = { @Result(name = "success", location = "/WEB-INF/views/notice.jsp") })
	public String doCreateNotice() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		Grades grades = (Grades) ServletActionContext.getRequest().getSession().getAttribute("grades");
		notice.setCreateDate(new Date());
		notice.setGrades(grades);
		notice.setUser(user);
		noticeService.save(notice);
		this.setTip("公告发布成功！");
		notices=noticeService.findByGrades(grades);
		return SUCCESS;
	}
	public List<Notice> getNotices() {
		return notices;
	}
	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
}
