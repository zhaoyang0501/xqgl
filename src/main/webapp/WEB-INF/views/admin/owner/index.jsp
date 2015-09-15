<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.owner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/falgun/bootbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".date").datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
	        format:'yyyy-mm-dd',
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	});
</script>
</head>
<body>
	<div class="layout">
		<!-- top -->
		<%@ include file="../top.jsp"%>
		<!-- 导航 -->
		<%@ include file="../menu.jsp"%>
		
		<input type="hidden" id="hf_id" />

		<div class="main-wrapper">
			<div class="container-fluid">
				<div class="row-fluid ">
					<div class="span12">
						<div class="content-widgets light-gray">
							<div class="widget-head  bondi-blue" >
								<h3>业主管理</h3>
							</div>
							<div class="box well form-inline">
								<span>业主姓名：</span>
								<input type="text" id="_name" >
								<a onclick="$.adminOwner.initSearchDataTable()"
									class="btn btn-info" data-loading-text="正在加载..."><i class="icon-search"></i>查询</a>
							</div>
							<div class="widget-container">
								
									<a class="btn btn-success" style="float: right; margin: 5px;" onclick="$.adminOwner.showOwnerAddModal()"><i class="icon-plus"></i>新增</a>
								<table class="responsive table table-striped table-bordered"
									id="dt_table_view">
									<thead>
										<tr>
											<th >门牌号码</th>
											<th >姓名</th>
											<th >地址</th>
											<th >性别</th>
											<th >电话</th>
											<th >职业</th>
											<th >房间面积</th>
											<th >人口</th>
											<th >操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../foot.jsp"%>
	</div>

	<!-- 编辑新增弹出框 -->
	<div class="modal hide fade" id="_modal">
		<div class="modal-header blue">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<label id="_modal_header_label"></label>
		</div>
		<div class="modal-body" style="min-height: 400px;">
			<div class="row-fluid">
				<div class="span12">
					<div class="form-container grid-form form-background left-align form-horizontal">
						<form action="" method="get" id=''>
							
							<div class="control-group">
								<label for="title" class="control-label">门牌号：</label>
								<div class="controls">
									<input type="text" id="id" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">姓名：</label>
								<div class="controls">
									<input type="text" id="name" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">地址：</label>
								<div class="controls">
									<input type="text" id="addr" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">性别：</label>
								<div class="controls">
									<select id='sex'>
										<option>男</option>
										<option>女</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">电话：</label>
								<div class="controls">
									<input type="text" id=phone placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">职业：</label>
								<div class="controls">
									<select id='job'>
									<option value="公务员">公务员</option>
									<option value="企业单位">企业单位</option>
									<option value="事业单位">事业单位</option>
									<option value="教育行业">教育行业</option>
									<option value="个人单位">个人单位</option>
									<option value="医疗行业">教师</option>
									</select>
								</div>
							</div>
							
							
							
							
							<div class="control-group">
								<label for="title" class="control-label">房屋面积：</label>
								<div class="controls">
									<input type="text"  id='area' placeholder="">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">家庭人口：</label>
								<div class="controls">
									<input type="text"  id='count' placeholder="">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal-footer center" id="div_footer">
			<a class="btn btn-primary" onclick="$.adminOwner.saveOwner()">保存</a>
			<a href="#" class="btn" data-dismiss="modal" id="closeViewModal">关闭</a>
		</div>
	</div>
</body>
</html>