<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
<script src="js/jquery.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script>
<script>
	$(document).ready(function() {

		//粒子背景特效
		$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});

		$("#button").click(function login() {
			//alert('点击了');
			var username=$("#username").val();
			var password=$("#password").val();
			if(username!=''&&password!='')
			{

				$.post("UserController/login.html", {
					username:$("#username").val(),
					password:$("#password").val()
					
				},
				function(re) {
					if(re=='success')
					{
						//alert('用户验证成功');
						 location.href = "helloworld.html";
					}else if(re=='error')
					{
						//alert('用户验证失败');

						$("#error").text("用户名或密码输入错误");
					}
					else
					{
						alert('服务器错误');
					}
					
					
				},
				"text");
			}
			else
		   {
				$("#error").text("用户名或密码不能为空噢噢噢噢22");
		   }
			

		});
		
		
		
	});
</script>
</head>
<body>
<dl class="admin_login">
		<dt>
			<strong>站点后台管理系统</strong> <em>Management System</em>

		</dt>
		<span style="color: red;" id="error"></span>
		<dd class="user_icon">
			<input type="text" placeholder="账号" class="login_txtbx" id="username" />
		</dd>
		<span style="color: red;"> </span>
		<dd class="pwd_icon">
			<input type="password" placeholder="密码" class="login_txtbx"
				id="password" />
		</dd>
		<dd>
			<button id="button" value="立即登陆" class="submit_btn" type="button">立即登陆</button>

		</dd>
		<dd>
			<p></p>

		</dd>
	</dl>
</body>
</html>
