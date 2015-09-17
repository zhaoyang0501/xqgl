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
public class NewsAction extends PageAction {
	
	private Integer page=1;
	private Long id;
	private News news;
	private List<News> newss;
	@Autowired
	private NewsService newsService;
	
	@Action(value = "detail", results = { @Result(name = "success", location = "/WEB-INF/views/detail.jsp") })
	public String detail() {
		news=newsService.find(id);
		return SUCCESS;
	}
	
	@Action(value = "news", results = { @Result(name = "success", location = "/WEB-INF/views/news.jsp") })
	public String news() {
		Page<News> list = newsService.findAll(page, 15,1);
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
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
}
