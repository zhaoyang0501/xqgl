package com.yxy.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.yxy.entity.Worker;
import com.yxy.service.WorkerService;

@Namespace("/")
@ParentPackage("json-default") 
public class WorkerAction extends PageAction {
	private List<Worker> workers;
	@Autowired
	private WorkerService workerService;

	@Action(value = "worker", results = { @Result(name = "success", location = "/WEB-INF/views/worker.jsp") })
	public String notice() {
		workers = workerService.findAll();
		return SUCCESS;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

}
