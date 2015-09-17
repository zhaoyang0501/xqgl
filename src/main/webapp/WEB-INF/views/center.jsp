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
				$("#_center").addClass("current");
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
				目前位置：<a href="" >首页</a><span>></span><span>业主中心</span>
			</div>
			<!-- 正文 -->
			
			<div class="readbox">
					<div class="title">
						<ul>
							<li class="current">
								<span><a href="javascript:void(0);">业主中心</a></span>
							</li>
						</ul>
					</div>
					<div class="view register">
						<form action="doregister" id="registerForm" name="registerForm" method="post">
							<div class="class">
								<b>用户信息</b>
							</div>
							<div class="form">
								<ul>
									
									<li>
										<div class="name">
											<b>门牌号码：</b>
										</div>
										<input class="size1" type="text" id="owner.id" name="owner.id"/>
										<div class="notice">
											例如 1-110
										</div>
									</li>
									
									<li>
										<div class="name">
											<b>用户名：</b>
										</div>
										<input class="size1" type="text" id="username" name="owner.username"/>
									</li>
									
									<li>
										<div class="name">
											<b>姓名：</b>
										</div>
										<input class="size1" type="text" id="name" name="owner.name"/>
									</li>
									
									<li>
										<div class="name">
											<b>登录密码：</b>
										</div>
										<input class="size1" type="password" id="password" name="owner.password" autocomplete="off" style="font-family: Tahoma;"/>
										<div class="notice">
											密码长度为6－20个字符，字母区分大小写。密码不可以过于简单，可使用英文字母及数字以及特殊字符组合。
										</div>
									</li>
									
									<li>
										<div class="name">
											<b>性别：</b>
										</div>
										<input type="radio" name="owner.sex" checked="checked" value="1"/>
										&nbsp;男&nbsp;&nbsp;
										<input type="radio" name="owner.sex" value="2"/>
										&nbsp;女
									</li>
									<li>
										<div class="name">
											<b class="blank">职业分类：</b>
										</div> <select class="size4" id="job" name="owner.job"
									style="font-family: SimSun; font-size: 12px;"><option
											value="">----------------请选择----------------</option>
										<option value="公务员">公务员</option>
										<option value="教师">教师</option>
										<option value="学生">学生</option>
										<option value="军人">军人</option>
										<option value="工人">工人</option>
										<option value="农民">农民</option>
										<option value="医务人员">医务人员</option>
										<option value="科技人员">科技人员</option>
										<option value="企事业单位行政/管理人员">企事业单位行政/管理人员</option>
										<option value="退休人员">退休人员</option>
										<option value="其它">其它</option></select>
								</li>
									<li>
										<div class="name">
											<b>手机：</b>
										</div>
										<input class="size1" type="text" id="phone" name="owner.phone"/>
										<div class="notice">
											例如：13912345678。可用于找回您的密码、接收活动短信等。
										</div>
									</li>
									<li>
										<div class="name">
											<b>详细地址：</b>
										</div>
										<input class="size1" type="text" id="addr" name="owner.addr"/>
										
									</li>
									<li>
										<div class="name">
											<b>房屋面积：</b>
										</div>
										<input class="size1" type="text" id="area" name="owner.area"/>
									</li>
									
									<li>
										<div class="name">
											<b>家庭人口：</b>
										</div>
										<input class="size1" type="text" id="count" name="owner.count"/>
										
									</li>
								</ul>
							</div>
							<div class="class">
								<b>我的访客记录</b>
							</div>
							<div class="form">
								<table cellpadding="0" cellspacing="0" style="margin-top: 20px;margin-bottom: 20px;width: 100%">
									<thead>
										<tr style="background-color: #FBE6E5;">
											<th height="30" class="td_style1 td_style2 td_style3">访客姓名</th>
											<th  class="td_style1 td_style2">到访时间</th>
											<th  class="td_style1 td_style2">来自</th>
											<th  class="td_style1 td_style2">类别</th>
											<th class="td_style1 td_style2">电话</th>
											<th class="td_style1 td_style2">车牌</th>
										</tr>
									</thead>
									<tbody id="activity_list">
									<c:forEach items="${guests}" var="bean">
									<tr>
										  <td height="30" class="td_style1 td_style3">${bean.name }</td>
										  <td align="center" class="td_style1">${bean.createDate }</td>
										  <td align="center" class="td_style1">${bean.comefrom }</td>
										  <td align="center" class="td_style1">${bean.type }</td>
										  <td align="center" class="td_style1">${bean.phone }</td>
										  <td align="center" class="td_style1">${bean.carno }</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="class">
								<b>我的缴费</b>
							</div>
							<div class="form">
								<table cellpadding="0" cellspacing="0" style="margin-top: 20px;margin-bottom: 20px;width: 100%">
									<thead>
										<tr style="background-color: #FBE6E5;">
											<th height="30" class="td_style1 td_style2 td_style3">流水单据号</th>
											<th  class="td_style1 td_style2">单据生成时间</th>
											<th  class="td_style1 td_style2">单价</th>
											<th  class="td_style1 td_style2">总价</th>
											<th class="td_style1 td_style2">年份</th>
											<th class="td_style1 td_style2">状态</th>
										</tr>
									</thead>
									<tbody id="activity_list">
									<c:forEach items="${costs}" var="bean">
									<tr>
										  <td height="30" class="td_style1 td_style3">${bean.id }</td>
										  <td align="center" class="td_style1">${bean.createDate }</td>
										  <td align="center" class="td_style1">${bean.price }</td>
										  <td align="center" class="td_style1">${bean.cash }</td>
										  <td align="center" class="td_style1">${bean.year }</td>
										  <td align="center" class="td_style1">${bean.state }</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</form>
					</div>
				</div>
			<!-- 正文 -->
			
		</div>
		<div class="bot"></div>
	</div>
	<%@include file="./foot.jsp" %>
	</body>
</html>
