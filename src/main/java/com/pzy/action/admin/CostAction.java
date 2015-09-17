package com.pzy.action.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.action.PageAction;
import com.pzy.entity.Cost;
import com.pzy.entity.Owner;
import com.pzy.service.CostService;
import com.pzy.service.OwnerService;

@Namespace("/admin/cost")
@ParentPackage("json-default") 
public class CostAction extends PageAction {
	private String name;
	private Long id;
	private Cost cost;
	private List<Owner> owners;

	private List<Cost> costs;
	@Autowired
	private CostService costService;
	@Autowired
	private OwnerService ownerService;
	
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/cost/index.jsp") })
	public String index() {
		owners=ownerService.findAll();
		return SUCCESS;
	}

	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Cost> list = costService.findAll(pageNumber, pageSize,name);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String delete() {
		try {
			costService.delete(id);
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
		getResultMap().put("object", costService.find(id));
		getResultMap().put("state", "success");
		getResultMap().put("msg", "操作成功");
		return SUCCESS;
	}

	@Action(value = "update", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String update() {
		Cost bean = costService.find(cost.getId());
		BeanUtils.copyProperties(cost, bean);
		costService.save(bean);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "修改成功");
		return SUCCESS;
	}
	@Action(value = "save", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String save() {
		costService.save(cost);
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
	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}
	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
}
