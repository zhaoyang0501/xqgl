<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.park.js"></script>
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
								<a onclick="$.adminPark.initSearchDataTable()"
									class="btn btn-info" data-loading-text="正在加载..."><i class="icon-search"></i>查询</a>
							</div>
							<div class="widget-container">
								
									<a class="btn btn-success" style="float: right; margin: 5px;" onclick="$.adminPark.showParkAddModal()"><i class="icon-plus"></i>新增</a>
								<table class="responsive table table-striped table-bordered"
									id="dt_table_view">
									<thead>
										<tr>
											<th >车位编号</th>
											<th >状态</th>
											<th >区位</th>
											<th >启用日期</th>
											<th >到期日期</th>
											<th >当前所属车牌号码</th>
											<th >备注</th>
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
						<input type="hidden" id="id" value="" >
							<div class="control-group">
								<label for="title" class="control-label">区位：</label>
								<div class="controls">
									<select id='area'>
										<option value="东A区">东A区</option>
										<option value="东B区">东B区</option>
										<option value="东C区">东C区</option>
										<option value="东D区">东D区</option>
										
										<option value="西A区">西A区</option>
										<option value="西B区">西B区</option>
										<option value="西C区">西C区</option>
										<option value="西D区">西D区</option>
										
										<option value="南A区">南A区</option>
										<option value="南B区">南B区</option>
										<option value="南C区">南C区</option>
										<option value="南D区">南D区</option>
									</select>
								</div>
							</div>
							
							
							<div class="control-group">
								<label for="title" class="control-label">启用日期：</label>
								<div class="controls">
									<div class="input-append date">
										<input id="startDate" type="text" value="" readonly="readonly" style="width:80px;">
										<span class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">到期日期：</label>
								<div class="controls">
									<div class="input-append date">
										<input id="endDate" type="text" value="" readonly="readonly" style="width:80px;">
										<span class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">状态：</label>
								<div class="controls">
									<select id='state'>
									<option value="正常">使用中</option>
									<option value="空闲">空闲</option>
									</select>
								</div>
							</div>
							
							
							
							
							<div class="control-group">
								<label for="title" class="control-label">车牌号码：</label>
								<div class="controls">
									<input type="text" id="carno" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">备注：</label>
								<div class="controls">
									<input type="text" id="remark" placeholder="">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal-footer center" id="div_footer">
			<a class="btn btn-primary" onclick="$.adminPark.savePark()">保存</a>
			<a href="#" class="btn" data-dismiss="modal" id="closeViewModal">关闭</a>
		</div>
	</div>
</body>
</html>