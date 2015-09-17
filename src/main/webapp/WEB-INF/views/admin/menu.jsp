<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="leftbar leftbar-close clearfix">
	<div class="admin-info clearfix">
		<div class="admin-thumb">
			<i class="icon-user"></i>
		</div>
		<div class="admin-meta">
			<ul>
				<li class="admin-username" style="margin-top: 10px;">欢迎你 ${sessionScope.worker!=null?sessionScope.worker.name:"超级管理员" }</li>
				<li><a href="${pageContext.request.contextPath}/admin/loginout">
				<i class="icon-lock"></i> 退出</a></li>
			</ul>
		</div>
	</div>

	<div class="left-nav clearfix">
		<div class="left-primary-nav">
			<ul id="myTab">
				<li  class="active"><a href="#dailyreport" class="icon-calendar" data-original-title="日报"></a></li>
			</ul>
		</div>
		<div class="responsive-leftbar">
			<i class="icon-list"></i>
		</div>
		<div class="left-secondary-nav tab-content" >
			<div class="tab-pane active dailyreport" id="dailyreport">
				<ul id="nav" class="accordion-nav" >
					<li><a href="${pageContext.request.contextPath}/admin/guest/create"><i class="icon-upload"></i> 访客登记 </a></li>
					<c:if test="${sessionScope.worker==null}">
						<li><a href="${pageContext.request.contextPath}/admin/guest/index"><i class="icon-zoom-in"></i> 访客查询</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/owner/index"><i class="icon-zoom-in"></i> 业主管理</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/worker/index"><i class="icon-zoom-in"></i> 物业人员管理</a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath}/admin/news/index"><i class="icon-zoom-in"></i> 新闻公告管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/news/create"><i class="icon-zoom-in"></i> 新闻公告发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/park/index"><i class="icon-zoom-in"></i> 停车位管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/cost/index"><i class="icon-zoom-in"></i> 收费管理</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>