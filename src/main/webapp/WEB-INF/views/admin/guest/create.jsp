<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.guest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/falgun/bootbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if("${tip}" != null && "${tip}" != ""){
			noty({"text":"${tip}","layout":"top","type":"success","timeout":"2000"});
		}
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
								<h3>访客管理管理</h3>
							</div>
							
							<div class="widget-container">
								
									<form action="${pageContext.request.contextPath}/admin/guest/save" method="post" id=''>
							<div class="control-group">
								<label for="title" class="control-label">访客姓名：</label>
								<div class="controls">
									<input type="text" name="guest.name" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">车牌号码：</label>
								<div class="controls">
									<input type="text" name="guest.carno" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">访客来自：</label>
								<div class="controls">
									<input type="text" name="guest.comefrom" placeholder="">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">被访问业主：</label>
								<div class="controls">
									
									<select name="guest.owner.id">
									<c:forEach items="${owners }" var="bean">
											<option value="${bean.id}"> ${bean.id}的${bean.name }</option>
									</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">电话：</label>
								<div class="controls">
									<input type="text" name=guest.phone placeholder="">
								</div>
							</div>
								<div class="modal-footer center" id="div_footer">
									<button type="submit" class='btn btn-primary'>登记</button>
								</div>
						</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../foot.jsp"%>
	</div>
</body>
</html>