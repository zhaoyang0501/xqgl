package com.pzy.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 所下载文件相关的的几个属性:文件格式 contentType, 
 * 获取文件名的方法inputName,
 * 文件内容（显示的）属性contentDisposition, 
 * 限定下载文件 缓冲区的值bufferSize
 * */
@Namespace("/")
@Results({ @Result(name = "success", type = "stream", params = { "contentType",
		"application/octet-stream;charset=ISO8859-1", "inputName",
		"inputStream", "contentDisposition",
		"attachment;filename=\"${fileName}\"",  "bufferSize", "4096" }) })
public class DownloadAction  extends ActionSupport {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -8423083786146592523L;
    
    /**
     * 下载文件的参数名
     */
    private String fileName;
    
    private static Set<String> allowFix=new HashSet<String>();
    static{
    	allowFix.add("JPG");
    	allowFix.add("GIF");
    	allowFix.add("PNG");
    }
    
    /**
     * fdos
     * @param fileName todo
     * @return todo
     */
    private boolean allowedFix(String fileName){
    	String fix = fileName.substring(fileName.lastIndexOf(".")+1);
    	return allowFix.contains(fix.toUpperCase());
    }

    public String getFileName() throws UnsupportedEncodingException {
    	//解决下载文件的中文乱码问题
    	fileName=new String(fileName.getBytes(),"ISO-8859-1"); 
		return fileName;
	}


	public void setFileName(String fileName) throws UnsupportedEncodingException {
		this.fileName = fileName;
	}
	


	/**
     * 获得inputstream
     * @return inputStream
     * @throws Exception e
     */
    public InputStream getInputStream() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath("/");
        return  new FileInputStream(path+"upload/"+fileName);
    }
    
    
    /* 
     * 使用struts2注解方式进行下载，
     * 其中contentType,inputName,contentDisposition,bufferSize分别代表下载文件类型、指定被下载文件的入口输入流、指定下载文件的文件名、指定下载文件时的缓冲大小
     * 其中params={key1,value1,key2,value2..}，下面注解里面的inputName的value值代表getInputStream()方法（潜规则为去掉get前缀、首字母小写的字符串即为inputStream）
     * 如果想在注解里面添加字符串，或者想调用方法并获得返回值，那么需要使用\"${方法名字这里依然遵守潜规则}\"这种形式，比如在"attachment;filename=gdc.exe"，可以将gdc.exe替换为\"${方法名}\"通过调用该方法获得下载文件名字
     * application/octet-stream表示可以下载任何文件
     */
    @Action(results = { @Result(name = "success", type = "stream", params = {
            "contentType", "application/octet-stream;charset=utf-8", "inputName",
            "inputStream", "contentDisposition", "attachment;filename=\"${fileName}\"", 
            "bufferSize", "4096" }) })
    @Override
    public String execute() throws Exception {
        if(!allowedFix(fileName)){
        	return "downloadfail";
        }
        return SUCCESS;
    }

}

