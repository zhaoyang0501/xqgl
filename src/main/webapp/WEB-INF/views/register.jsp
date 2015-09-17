<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javaScript" type="text/javascript" src="js/jquery.js" ></script>
<script language="javaScript" type="text/javascript" src="js/jquery.validate.js" ></script>
<style type="text/css">
			.error label {
				display: block  !important;
				margin-left: 1em;
				width: auto;
				float: none  !important;
				color: red;
				}
		</style>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/pagination_new.css" />
		<title>小区管理</title>
		<link type="text/css" rel="stylesheet" href="pagination_new.css"  />
		<script type="text/javascript">
			$(document).ready(function(){
				$("#_index").addClass("current");
				
				var formvalidate= $("#registerForm").validate({
					errorLabelContainer: $("#registerForm div.error"),
					ignore:"",
					rules: {
						"owner.id":  "required",
						"owner.username":  "required",
						"owner.name":  "required",
						"owner.area": {number:true},
						"owner.count":  {digits:true},
						"owner.password":  {required:true, minlength: 8}
						},
					messages: {
						"owner.id":"请填写门牌号码",
						"owner.username":"请填写用户名",
						"owner.name":"请填写姓名",
						"owner.area":{ number: "面积必须是数字"},
						"owner.count":{ digits: "人口必须是整数"},
						"owner.password":{ required: "请输入密码",minlength: "密码至少8位"}
						
					}
				});
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
				目前位置：<a href="" >首页</a><span>></span><span>业主注册</span>
			</div>
			<!-- 正文 -->
			
			<div class="readbox">
					<div class="title">
						<ul>
							<li class="current">
								<span><a href="javascript:void(0);">业主注册</a></span>
							</li>
						</ul>
					</div>
					<div class="view register">
						<form action="doregister" id="registerForm" name="registerForm" method="post">
							<div class="error">
</div>
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
								<b>免责条款</b>
							</div>
							<div class="form">
								<ul>
									<li>
										<div class="name">
											&nbsp;
										</div>
										<textarea style="width: 670px;height: 180px;font-size: 13px;" readonly="readonly">　　小区管理服务平台（以下简称“平台”）所有活动均为非赢利性质的，采取自愿参加、风险自担、责任自负的原则；参与者应为年满18周岁、具有完全民事行为能力的自然人，（未满18周岁必须在相关部门学校的组织下、并在其监护人知晓并监督的情况下）自愿接受并遵守活动发布内容中的规则和事项，参加者必须对自己的安全负责，活动中发生任何意外及损害，本平台在任何情况下皆不承担任何法律和经济责任，本平台仅作为信息发布的渠道，特此声明！参加者必须在参加活动之前完全阅读本声明，声明条款自动生效并表明参加者全部接受本声明内容。
免责条款

一、参与者应为年满18周岁、具有完全民事行为能力的自然人，（未满18周岁必须在相关部门学校的组织下、并在其监护人知晓并监督的情况下）自愿接受并遵守活动发布内容中的规则和事项。 

二、活动应遵守国家相关法律、法规及本平台的相关规定，符合积极向上的社会道德风尚，一切因志愿服务活动参与者直接或间接引起的法律责任由参与者自行独自承担。 

三、本平台不从活动组织中收取任何费用或盈利，不对任何个人或机构承担民事赔偿责任或补充民事赔偿责任，亦不担保或提供任何参加人的人身和财产的安全保障。

四、活动参与者已确认自己有充分的身体、心理和物质上的准备而参加活动；对活动中的一切风险及导致的各种后果均自我负责和承担，并承诺在活动中发生的一切有关自己人身、财产和精神的损失均不会向平台追究法律上的责任。

五、平台在发布申请者的相关活动内容和需求后，不排除因受人为或自然因素影响导致的对原计划的变更和取消。平台仅保证在取消活动或变更相关内容前，尽可能通知到报名参与者，并说明原因，但不负责承担由此给参与者造成的人身、财产和精神上的损失。 

六、如发生恶意侵犯他人或其它涉及犯罪的行为，则由受害人依法向相关侵害人追究法律责任。 

　　鉴于活动难免会存在不可预知的危险性，请务必仔细阅读本声明及附带条款，本声明将自动生效并表明参加者已默认接受，如志愿者对本声明条款存在异议或不能接受，请在志愿活动开始前退出活动。活动组织者务必在每次活动前告知参与者仔细阅读本声明。
										</textarea>
									</li>
									<li>
										<div class="name">
											<b class="blank">&nbsp;</b>
										</div>
										同意上述条款内容，并已经认真阅读
									</li>
								</ul>
							</div>
							<input type="submit" class="submit" value="" />
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
