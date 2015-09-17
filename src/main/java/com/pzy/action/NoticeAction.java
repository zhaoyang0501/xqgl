package com.pzy.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.entity.News;
import com.pzy.service.NewsService;

@Namespace("/")
@ParentPackage("json-default") 
public class NoticeAction extends PageAction {
	
	private Integer page=1;
	
	private List<News> newss;
	@Autowired
	private NewsService newsService;

	@Action(value = "notice", results = { @Result(name = "success", location = "/WEB-INF/views/notice.jsp") })
	public String notice() {
		Page<News> list = newsService.findAll(page, 15,2);
		newss=list.getContent();
		return SUCCESS;
	}
	
	

	public Integer getPage() {
		return page;
	}



	public void setPage(Integer page) {
		this.page = page;
	}



	public List<News> getNewss() {
		return newss;
	}

	public void setNewss(List<News> newss) {
		this.newss = newss;
	}
}
