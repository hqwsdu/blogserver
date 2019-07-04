<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
	$(document).ready(function() {
		$("#button").click(function login() {
			var username = $("#username").val();
			var password = $("#password").val();
			var data=$("#data").val();
			var pe=$("input[type='radio']:checked").val();
			if(username==''&&password==''&&pe!='普通管理员'&&pe!='超级管理员'&&data=='')
			{
				alert('空表单不能提交')
			}
		
			else if(username=='')
			{
				$("#username_error").text("用户名不能为空");
			}
			else if(password=='')
			{
				$("#password_error").text("密码不能为空")
			}
			else if(pe!='普通管理员'&&pe!='超级管理员')
			{
				$("#pe_error").text("权限不能为空")
				
			}
			else
			{
				var json={"user_name":username,"user_password":password,"user_permission":pe,"user_data":data};
				$("#h_bg").fadeIn();
				$("#text_user").attr("value",username);
				$("#text_pass").attr("value",password);
				$("#text_pe").attr("value",pe);
				$("#text_data").text(data);
				
				var fjson= JSON.stringify(json)
					$(".trueBtn").click(function() {
					$("#h_bg").fadeOut();
					$.post("UserController/addUser.html", {
						fdata: fjson,

					}, function(re) {
						var result;
						if(re=='success')
						{
							result='添加管理员成功';
						}
						else
						{
							result='添加失败';
						}
					
						$("#s_bg").fadeIn(100);
						$("#s_span").text(result);
						$("#s_bg").fadeOut(500);
						

					}, "text");
				});
				//弹出：取消或关闭按钮
				$(".falseBtn").click(function() {
			
					$("#h_bg").fadeOut();
				});
			}
			
			
			
			
			

		});

	});
</script>
<title></title>
</head>
<body>
	<section>
		<ul class="admin_tab">
			<li><a class="active">添加管理员</a></li>

		</ul>
		<ul class="ulColumn2">
			<li><span class="item_name" style="width: 120px;" >管理员用户名：</span>
				<input id="username" type="text" class="textbox textbox_295"
				placeholder="请输入用户名..." />
				<span style="color: red;" id="username_error"></span>
				</li>
			<li><span class="item_name" style="width: 120px;">管理员密码：</span>
				<input id="password" type="password" class="textbox textbox_295"
				placeholder="请输入管理员密码..." />
				<span style="color: red;" id="password_error"></span></li>
			<li><span class="item_name" style="width: 120px;">权限：</span> <label
				class="single_selection"><input type="radio" name="name"  value="超级管理员"/>超级管理员</label>
				<label class="single_selection"><input type="radio"
					name="name"  value="普通管理员"/>普通管理员</label>
					<span style="color: red;" id="pe_error"></li>
			<li><span class="item_name" style="width: 120px;">摘要：</span> <textarea
					id="data" placeholder="管理员具体信息" class="textarea"
					style="width: 500px; height: 100px;"></textarea></li>
			<li><span class="item_name" style="width: 120px;"></span>
				<button id="button" class="link_btn">确认</button></li>
		</ul>
	</section>
	<section class="pop_bg" id="h_bg">
			<div class="pop_cont">
				<!--title-->
				<h3>管理员权限</h3>
				<!--content-->
				<div class="pop_cont_input">
					<ul>
						<li>
							<span>用户名</span>
							<input type="text" placeholder="定义提示语..." id="text_user" class="textbox" disabled="true "/>
						</li>
					
						<li>
							<span class="ttl">密&nbsp;&nbsp;&nbsp;码</span>
							<input type="text" placeholder="定义提示语..." id="text_pass" class="textbox" disabled="true "/>
						</li>
						<li>
							<span class="ttl">权&nbsp;&nbsp;&nbsp;限</span>
							<input type="text" placeholder="定义提示语..." id="text_pe" class="textbox" disabled="true "/>
						</li>
						<li>
							<span class="ttl">详细信息</span>
							<textarea class="textarea" style="height:50px;width:80%;" id="text_data" disabled="true "></textarea>
						</li>
					</ul>
				</div>
				<!--以pop_cont_text分界-->
				<div class="pop_cont_text">
					确认无误请提交
				</div>
				<!--bottom:operate->button-->
				<div class="btm_btn">
					<input type="button" value="提交" class="input_btn trueBtn" />
					<input type="button" value="取消" class="input_btn falseBtn" />
				</div>
			</div>
		</section>
		<section class="pop_bg" id="s_bg">
			<div class="pop_cont">
				<!--title-->
				<h3>服务器消息</h3>
				<!--content-->
				<div class="pop_cont_input">
					<ul>
						<li>
							<span id="s_span"></span>
							
						</li>
					
			
					</ul>
				</div>
			</div>
		</section>
</body>
</html>
