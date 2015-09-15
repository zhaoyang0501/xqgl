<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.worker.js"></script>
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
								<h3>物业人员管理</h3>
							</div>
							<div class="box well form-inline">
								<span>姓名：</span>
								<input type="text" id="_name" >
								<a onclick="$.adminWorker.initSearchDataTable()"
									class="btn btn-info" data-loading-text="正在加载..."><i class="icon-search"></i>查询</a>
							</div>
							<div class="widget-container">
								
									<a class="btn btn-success" style="float: right; margin: 5px;" onclick="$.adminWorker.showWorkerAddModal()"><i class="icon-plus"></i>新增</a>
								<table class="responsive table table-striped table-bordered"
									id="dt_table_view">
									<thead>
										<tr>
											<th >id</th>
											<th >用户名</th>
											<th >密码</th>
											<th >头像</th>
											<th >姓名</th>
											<th >入职日期</th>
											<th >性别</th>
											<th >电话</th>
											<th >职位</th>
											<th >个人简介</th>
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
		<div class="modal-body" style="min-height: 500px;">
			<div class="row-fluid">
				<div class="span12">
					<div class="form-container grid-form form-background left-align form-horizontal">
						 <form id='_form' action="${pageContext.request.contextPath}/admin/worker/save"   enctype="multipart/form-data" method="post">
							<input type="hidden" id="worker.id" placeholder="">
							<div class="control-group">
								<label for="title" class="control-label">用户名：</label>
								<div class="controls">
									<input type="text" name="worker.name" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">密码：</label>
								<div class="controls">
									<input type="text" name="worker.password" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">姓名：</label>
								<div class="controls">
									<input type="text" name="worker.name" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">性别：</label>
								<div class="controls">
									<select name='worker.sex'>
										<option value="男">男</option>
										<option value="男">男</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">电话：</label>
								<div class="controls">
									<input type="text" name=worker.tel placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">照片：</label>
								<div class="controls">
									<input type="file" name=imgPath placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">入职日期：</label>
								<div class="controls">
									<div class="input-append date">
										<input name="worker.joinDate" type="text" value="" readonly="readonly" style="width:80px;">
										<span class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">职业：</label>
								<div class="controls">
									<input type="text" name=worker.type placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">个人简介：</label>
								<div class="controls">
									<input type="text" name=worker.remark placeholder="">
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button class='' type="submit">提交 </button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		
	</div>
</body>
</html>