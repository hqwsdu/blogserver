<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/fenyestyle.css"
	media="screen" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.3.2.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.paginate.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"
	type="text/javascript"></script>

<style>
.demo {
	width: 480px;
	padding: 10px;
	margin: 10px auto;
	border: 1px solid #fff;
	background-color: #f7f7f7;
	margin-right: 8px;
}
</style>

<script>
$.post("PageController/findPageCount.html", {
	name : 'loginlog'

    }, function(re) {
    	mydata(1);

	$("#demo2").paginate(
			{
				count : re,
				start : 2,
				display : 5,
				border : false,
				text_color : '#fff',
				background_color : '#339966',
				text_hover_color : '#339966',
				background_hover_color : '#fff',
				onChange : function(page) {
					$('._current', '#paginationdemo').removeClass('_current').hide();
					$('#p' + page).addClass('_current').show();
					currentPage=page;
					mydata(page)
				
					
					
				}
			});
           }, "text");



					function mydata(n) {
						$.post(
										"findAllLoginLog.html",
										{
											data : n,

										},
										function(re) {
											var str1 = '';
											$("#table1 tr:not(:first)").remove();
											content = JSON.parse(re);
											$.each(
															JSON.parse(re),
															function(n, value) {
																var str = '<tr><td style="width:100px;" class="center">'
																		+ value.loginlog_id
																		+ '</td><td class="center">'
																		+ value.user_name
																		+ '</td><td  class="center">'
																		+ value.loginlog_date
																		+ '</td></tr>';

																str1 += str;

															});

											$("#table1").append(str1);
											

										}, "text");}
					
					
					$(document).tooltip()
					


						
				
</script>

</head>
<body>
	<div class="rt_content">
		<ul class="admin_tab">
			<li><a class="active">系统登录信息</a></li>

		</ul>

		<section>
			<table class="table" id="table1">
				<tr>
					<th>登录记录序号</th>
					<th>管理员名</th>
					<th>登录时间</th>
					
				</tr>
			</table>
			<div class="content" style="margin-bottom: 100px">

				<div class="demo">
					<div id="demo2"></div>
				</div>

			</div>

		</section>
	
			
	</div>
</body>
</html>
