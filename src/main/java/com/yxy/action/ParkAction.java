package com.yxy.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.yxy.entity.Park;
import com.yxy.entity.Worker;
import com.yxy.service.ParkService;

@Namespace("/")
@ParentPackage("json-default") 
public class ParkAction extends PageAction {
	private List<Park> parks;
	
	

	private String area="东A区";
	
	@Autowired
	private ParkService parkService;
	
	@Action(value = "park", results = { @Result(name = "success", location = "/WEB-INF/views/park.jsp") })
	public String park() {
		parks = parkService.findByArea(area);
		return SUCCESS;
	}

	public List<Park> getParks() {
		return parks;
	}

	public void setParks(List<Park> parks) {
		this.parks = parks;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
