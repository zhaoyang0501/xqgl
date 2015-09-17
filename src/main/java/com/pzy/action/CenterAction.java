package com.pzy.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.pzy.entity.Cost;
import com.pzy.entity.Guest;
import com.pzy.entity.Owner;
import com.pzy.service.CostService;
import com.pzy.service.GuestService;

@Namespace("/")
@ParentPackage("json-default") 
public class CenterAction extends PageAction {
	
	private List<Guest> guests;
	
	private List<Cost> costs;
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private CostService costService;
	@Action(value = "center", results = { @Result(name = "success", location = "/WEB-INF/views/center.jsp") })
	public String center() {
		Owner owner=(Owner)ActionContext.getContext().getSession().get("user");
		guests=this.guestService.findByOwner(owner);
		costs=costService.findByOwner(owner);
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
}
