package com.pzy.action.admin;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.action.PageAction;
import com.pzy.entity.Guest;
import com.pzy.entity.Owner;
import com.pzy.service.GuestService;
import com.pzy.service.OwnerService;

@Namespace("/admin/guest")
@ParentPackage("json-default") 
public class GuestAction extends PageAction {
	private String name;
	private Long id;
	private Guest guest;
	private List<Guest> guests;
	private List<Owner> owners;
	private String tip;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private GuestService guestService;
	@Autowired
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/guest/index.jsp") })
	public String index() {
		return SUCCESS;
	}
	
	
	@Action(value = "create", results = { @Result(name = "success", location = "/WEB-INF/views/admin/guest/create.jsp") })
	public String create() {
		owners=ownerService.findAll();
		return SUCCESS;
	}
	
	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Guest> list = guestService.findAll(pageNumber, pageSize,name);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String delete() {
		try {
			guestService.delete(id);
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
		getResultMap().put("object", guestService.find(id));
		getResultMap().put("state", "success");
		getResultMap().put("msg", "操作成功");
		return SUCCESS;
	}

	@Action(value = "update", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String update() {
		Guest bean = guestService.find(guest.getId());
		bean.setCarno(guest.getCarno());
		bean.setComefrom(guest.getComefrom());
		bean.setName(guest.getName());
		bean.setPhone(guest.getPhone());
		guestService.save(bean);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "修改成功");
		return SUCCESS;
	}
	@Action(value = "save", results = { @Result(name = "success", location = "/WEB-INF/views/admin/guest/create.jsp") })
	public String save() {
		guest.setCreateDate(new Date());
		guestService.save(guest);
		tip="登记成功";
		owners=ownerService.findAll();
		return SUCCESS;
	}
	
	public String getTip() {
		return tip;
	}


	public void setTip(String tip) {
		this.tip = tip;
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
	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	public List<Owner> getOwners() {
		return owners;
	}


	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
}
