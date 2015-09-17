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
		<title>小区管理</title>
		<link type="text/css" rel="stylesheet" href="pagination_new.css"  />
		<script type="text/javascript">
			$(document).ready(function(){
				$("#_index").addClass("current");
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
			<!-- 正文 -->
			<div class="content">
					<div class="area_left">
				    	
						
						<!-- 工作动态 -->
						<div class="working">
							<div class="title">
								<div class="bt"></div>
							</div>
							<div class="list">
								<ul>
								<c:forEach items="${newss}" var="bean">
								<li><a
									href="detail?id=${bean.id}"
									target="_blank">${bean.title}</a><span>${bean.createDate}</span></li>
								</c:forEach>
								
							</ul>
							</div>
						</div>
						
						
					</div>
					<div class="area_right">
						
<div class="box">
	<div id="enter" class="title">
		<span class="current" id="zyzLogin">业主登录</span>
		
	</div>
	<div class="inner">
		<div class="login" id="zyzLoginDiv">
		
		<c:if test="${sessionScope.user==null}">
			 <form action="dologin" method="post">
			<ul>
				<li style="margin-top:10px; _margin-top:13px; line-height: 35px;_line-height: 33px;">
					<span>登录账号：</span>
					<input type="text" autocomplete="off" name="owner.username">
				</li>
				<li style="margin-top:5px; line-height: 35px;_line-height: 33px;">
					<span>登录密码：</span>
					<input type="password" autocomplete="off" style="font-family: Tahoma;" name="owner.password">
				</li>
				
				<li style="margin-top:5px; line-height: 35px;_line-height: 33px;">
					<input class="submit" type="submit" value="" >
					<br>
				</li>
				<li style="margin-top:10px;margin-left: 15px; line-height: 35px;_line-height: 35px;">
					<a href="register"><img src="images/reg.gif"></a>
				</li>
			</ul>
			</form> 
			</c:if>
			<c:if test="${sessionScope.user!=null }">
			<ul>
				<li style="margin-top:10px; _margin-top:13px; line-height: 35px;_line-height: 33px;">
					<span>欢迎您：来自${sessionScope.user.id}的${sessionScope.user.name}</span>
				</li>
				
				<li style="margin-top:10px;margin-left: 15px; line-height: 35px;_line-height: 35px;">
					<a href="loginout"><img src="images/bbb.gif"></a>
				</li>
			</ul>
			</c:if>
		</div>
	</div>
</div>
					</div>
				<div class="friendly">
					<div class="inner">
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
							
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
						<a href="#" target="_blank">
							<img src="images/aaaa.gif" title="中国文明网" alt="中国文明网"
							width="123" height="48"/>
						</a>
					</div>
				</div>
			</div>
			
		</div>
		<div class="bot"></div>
	</div>
	<%@include file="./foot.jsp" %>
	</body>
</html>
