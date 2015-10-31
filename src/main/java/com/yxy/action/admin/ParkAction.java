package com.yxy.action.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.yxy.action.PageAction;
import com.yxy.entity.Park;
import com.yxy.service.ParkService;

@Namespace("/admin/park")
@ParentPackage("json-default") 
public class ParkAction extends PageAction {
	private String name;
	private Long id;
	private Park park;
	private List<Park> parks;
	@Autowired
	private ParkService parkService;
	@Autowired
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/park/index.jsp") })
	public String index() {
		return SUCCESS;
	}

	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Park> list = parkService.findAll(pageNumber, pageSize,name);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String delete() {
		try {
			parkService.delete(id);
			getResultMap().put("state", "success");
			getResultMap().put("msg", "删除成功");
		} catch (Exception e) {
			 getResultMap().put("state", "error");
			 getResultMap().put("msg", "删除失败，外键约束");
		}
        return SUCCESS;
		
	}

	@Action(value = "get", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String get() {
		getResultMap().put("object", parkService.find(id));
		getResultMap().put("state", "success");
		getResultMap().put("msg", "操作成功");
		return SUCCESS;
	}

	@Action(value = "update", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String update() {
		Park bean = parkService.find(park.getId());
		BeanUtils.copyProperties(park, bean);
		parkService.save(bean);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "修改成功");
		return SUCCESS;
	}
	@Action(value = "save", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String save() {
		parkService.save(park);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "保存成功");
		return SUCCESS;
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
	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	public List<Park> getParks() {
		return parks;
	}

	public void setParks(List<Park> parks) {
		this.parks = parks;
	}

}
