package com.pzy.action.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.action.PageAction;
import com.pzy.entity.Worker;
import com.pzy.entity.Owner;
import com.pzy.service.WorkerService;
import com.pzy.service.OwnerService;

@Namespace("/admin/worker")
@ParentPackage("json-default") 
public class WorkerAction extends PageAction {
	private String name;
	private Long id;
	private Worker worker;
	private List<Worker> workers;
	private List<Owner> owners;
	private String tip;
	
	
	/*这三个参数用于文件上传**/
	private File imgPath;
	private String imgPathContentType;
	private String imgPathFileName;
	

	public void setImgPathContentType(String imgPathContentType) {
		this.imgPathContentType = imgPathContentType;
	}


	public String getImgPathFileName() {
		return imgPathFileName;
	}


	public void setImgPathFileName(String imgPathFileName) {
		this.imgPathFileName = imgPathFileName;
	}


	public OwnerService getOwnerService() {
		return ownerService;
	}


	public void setOwnerService(OwnerService ownerService) {
		this.ownerService = ownerService;
	}


	public WorkerService getWorkerService() {
		return workerService;
	}


	public void setWorkerService(WorkerService workerService) {
		this.workerService = workerService;
	}


	@Autowired
	private OwnerService ownerService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/worker/index.jsp") })
	public String index() {
		return SUCCESS;
	}
	
	
	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Worker> list = workerService.findAll(pageNumber, pageSize,name);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String delete() {
		try {
			workerService.delete(id);
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
		getResultMap().put("object", workerService.find(id));
		getResultMap().put("state", "success");
		getResultMap().put("msg", "操作成功");
		return SUCCESS;
	}

	@Action(value = "update", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String update() {
		Worker bean = workerService.find(worker.getId());
		/*bean.setCarno(worker.getCarno());
		bean.setComefrom(worker.getComefrom());
		bean.setName(worker.getName());
		bean.setPhone(worker.getPhone());*/
		workerService.save(bean);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "修改成功");
		return SUCCESS;
	}
	@Action(value = "save", results = { @Result(name = "success", location = "/WEB-INF/views/admin/worker/index.jsp") })
	public String save() {
			worker.setHeadimg(this.imgPathFileName);
			/** 文件上传逻辑 */
			String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
			File saveImg = new File(new File(realpath), this.imgPathFileName);
			try {
				FileUtils.copyFile(imgPath, saveImg);
			} catch (IOException e) {
				e.printStackTrace();
				this.tip="参数错误或者没有选择文件";
				return SUCCESS;
			}
		workerService.save(worker);
		this.tip="保存成功";
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
	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	public List<Owner> getOwners() {
		return owners;
	}


	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
	
	public File getImgPath() {
		return imgPath;
	}


	public void setImgPath(File imgPath) {
		this.imgPath = imgPath;
	}


	public String getImgPathContentType() {
		return imgPathContentType;
	}

}
