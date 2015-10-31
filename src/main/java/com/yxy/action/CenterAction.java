package com.yxy.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.yxy.entity.Cost;
import com.yxy.entity.Guest;
import com.yxy.entity.Owner;
import com.yxy.service.CostService;
import com.yxy.service.GuestService;
import com.yxy.service.OwnerService;

@Namespace("/")
@ParentPackage("json-default") 
public class CenterAction extends PageAction {
	
	private List<Guest> guests;
	
	private List<Cost> costs;
	
	private String tip;

	private Owner owner;
	@Autowired
	private GuestService guestService;
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private CostService costService;
	@Action(value = "center", results = { @Result(name = "success", location = "/WEB-INF/views/center.jsp") })
	public String center() {
		Owner owner=(Owner)ActionContext.getContext().getSession().get("user");
		guests=this.guestService.findByOwner(owner);
		costs=costService.findByOwner(owner);
		return SUCCESS;
	}
	
	@Action(value = "docenter", results = { @Result(name = "success", location = "/WEB-INF/views/center.jsp") })
	public String doregister() throws Exception {
		ownerService.save(owner);
		ActionContext.getContext().getSession().put("user", owner);
		guests=this.guestService.findByOwner((Owner)ActionContext.getContext().getSession().get("user"));
		costs=costService.findByOwner(owner);
		tip="用户信息修改成功！";
		return SUCCESS;
	}
	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}
	public List<Cost> getCosts() {
		return costs;
	}
	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
}
