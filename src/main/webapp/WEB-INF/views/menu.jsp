<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
								<li ><a id="_index" href="index">首页</a></li>
								<li ><a id='_news' href="news">小区新闻</a></li>
								<li ><a  id='_notice' href="notice">小区公告</a></li>
								<li ><a id='_park' href="park">停车位查询</a></li>
								<li ><a id='_worker' href="worker">物业人员</a></li>
								<c:if test="${sessionScope.user!=null }">
									<li ><a id='_center' href="center">个人中心</a></li>
								</c:if>
</ul>