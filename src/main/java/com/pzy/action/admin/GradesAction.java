package com.pzy.action.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.action.PageAction;
import com.pzy.entity.Grades;
import com.pzy.service.GradesService;

@Namespace("/admin/grades")
@ParentPackage("json-default") 
public class GradesAction extends PageAction {
	private String name;
	private Long id;
	private Grades grades;
	private List<Grades> gradess;
	@Autowired
	private GradesService gradesService;
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/grades/index.jsp") })
	public String index() {
		return SUCCESS;
	}

	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Grades> list = gradesService.findAll(pageNumber, pageSize,name);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}

	@Action(value = "approve", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String approve() {
		Grades bean = gradesService.find(id);
		bean.setState("已审核");
		gradesService.save(bean);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "审批成功");
		return SUCCESS;
	}

	
	public Grades getGrades() {
		return grades;
	}

	public void setGrades(Grades grades) {
		this.grades = grades;
	}

	public List<Grades> getGradess() {
		return gradess;
	}

	public void setGradess(List<Grades> gradess) {
		this.gradess = gradess;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
