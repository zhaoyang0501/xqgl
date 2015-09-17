<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javaScript" type="text/javascript" src="js/jquery.js" ></script>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/pagination_new.css" />
		<title>小区管理11</title>
		<link type="text/css" rel="stylesheet" href="pagination_new.css"  />
		<script type="text/javascript">
			$(document).ready(function(){
				if("${tip}" != null && "${tip}" != ""){
					alert("${tip}");
				}
			});
		</script>
</head>
<body>
	<div id="top">
		<div class="banner">
	        <div class="logo">
	        </div>
		</div>
	</div>

	<div id="container">
		<div class="mid">
			<!-- 菜单 -->
			<div class="nav">
				<div class="left"></div>
				<div class="mid">
					<%@include file="./menu.jsp" %>
				</div>
				<div class="right"></div>
			</div>
			<!-- 面包屑 -->
			<div class="address">
				目前位置：<a href="" >首页</a><span>></span><span>小区新闻</span>
			</div>
			<!-- 正文 -->
			<div class="readbox">
				<div class="title"><span>小区新闻</span></div>
				<div class="view">
					<div class="list" id="dataList">
						<ul>
						<c:forEach items="${newss}" var="bean">
						<li><a href="detail/id=${bean.id}" target="_blank">${bean.title}</a><span>${bean.createDate}</span></li>
						</c:forEach>
						</ul>
					</div>
					<div class="clear"></div>
					<div id="pageNavigation"
						style="height: 50px; display: block; padding-top: 15px; margin: auto;">
						<div class="pagination">
								<a href="./news?page=${page>0?page:1}" class="current prev">下一页</a>
								<a href="./news?page=${page+1 }" class="next">下一页</a>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<div class="bot"></div>
	</div>
	<%@include file="./foot.jsp" %>
	</body>
</html>
