package com.pzy.action;

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

import com.pzy.entity.Grades;
import com.pzy.entity.Notice;
import com.pzy.entity.Photo;
import com.pzy.entity.User;
import com.pzy.service.NoticeService;
import com.pzy.service.PhotoService;

@Namespace("/")
@ParentPackage("json-default") 
public class PhotoAction extends PageAction {
	private List<Photo> photos;
	private Photo photo;
	private String tip;
	private File imgPath;  
	private String imgPathContentType;  
	private String imgPathFileName;  
	@Autowired
	private PhotoService photoService;
	public String execute() throws Exception {
		Grades grades = (Grades) ServletActionContext.getRequest().getSession().getAttribute("grades");
		photos=photoService.findByGrades(grades);
		return SUCCESS;
	}
	@Action(value = "deletePhoto", results = { @Result(name = "success", location = "/WEB-INF/views/photo.jsp") })
	public String deletePhoto() throws Exception {
		photoService.delete(photo.getId());
		Grades grades = (Grades) ServletActionContext.getRequest().getSession().getAttribute("grades");
		photos=photoService.findByGrades(grades);
		 tip="删除成功";
		return SUCCESS;
	}
	@Action(value = "uploadPhoto", results = { @Result(name = "success", location = "/WEB-INF/views/upload_photo.jsp") })
	public String uploadPhoto() throws Exception {
		return SUCCESS;
	}
	@Action(value = "doUploadPhoto", results = { @Result(name = "success", location = "/WEB-INF/views/photo.jsp") })
	public String doUploadPhoto() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		Grades grades = (Grades) ServletActionContext.getRequest().getSession().getAttribute("grades");
		photo.setCreateDate(new Date());
		photo.setUser(user);	  
		photo.setGrades(grades);
		photo.setFilePath(this.imgPathFileName);
		photoService.save(photo);
		/**文件上传逻辑*/
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		System.out.println(realpath);
		File saveImg = new File(new File(realpath), this.imgPathFileName);
         try {
			FileUtils.copyFile(imgPath, saveImg);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
         tip="上传成功";
         photos=photoService.findByGrades(grades);
		return SUCCESS;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
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
	public void setImgPathContentType(String imgPathContentType) {
		this.imgPathContentType = imgPathContentType;
	}
	public String getImgPathFileName() {
		return imgPathFileName;
	}
	public void setImgPathFileName(String imgPathFileName) {
		this.imgPathFileName = imgPathFileName;
	}
}
