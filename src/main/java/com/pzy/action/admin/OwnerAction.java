package com.pzy.action.admin;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.action.PageAction;
import com.pzy.entity.Grades;
import com.pzy.entity.Owner;
import com.pzy.service.GradesService;
import com.pzy.service.OwnerService;

@Namespace("/admin/owner")
@ParentPackage("json-default") 
public class OwnerAction extends PageAction {
	private String name;
	private String id;
	private Owner owner;
	private List<Owner> owners;
	private List<Grades> gradess;
	@Autowired
	private GradesService gradesService;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/owner/index.jsp") })
	public String index() {
		gradess=gradesService.findAllApproved();
		return SUCCESS;
	}

	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Owner> list = ownerService.findAll(pageNumber, pageSize,name);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String delete() {
		try {
			ownerService.delete(id);
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
		getResultMap().put("object", ownerService.find(id));
		getResultMap().put("state", "success");
		getResultMap().put("msg", "操作成功");
		return SUCCESS;
	}

	@Action(value = "update", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String update() {
		Owner bean = ownerService.find(owner.getId());
		BeanUtils.copyProperties(owner, bean);
		ownerService.save(bean);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "修改成功");
		return SUCCESS;
	}
	@Action(value = "save", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String save() {
		ownerService.save(owner);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
	public List<Grades> getGradess() {
		return gradess;
	}

	public void setGradess(List<Grades> grades) {
		this.gradess = grades;
	}


}
