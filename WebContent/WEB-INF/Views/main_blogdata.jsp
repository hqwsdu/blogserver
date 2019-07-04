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
.zxx_text_overflow{width:10em; white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;}



</style>

<script>
function function_name() {
	$(".zxx_text_overflow").each(function() {
		var maxwidth = 10;
		if ($(this).text().length > maxwidth) {
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() + '...');
		}
	});
}


$.post("PageController/findPageCount.html", {
	name : 'blog'

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
						var page=n;
						$.post(
										"BlogController/findAllBlog.html",
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
																		+ value.blog_id
																		+ '</td><td class="center"style="width:100px;">'
																		+ value.blog_date
																		+ '</td><td style="width:120px;" class="center">'
																		+ value.bloguser_nickname
																		+ '</td><td class="center" hidden>'
																		+ JSON.stringify(value.list)
																		+ '</td><td class="center">'
																		+ value.blogstate
																		+ '</td><td class="center" hidden>'
																		+  value.blog_content
																		+ '</td><td class="center">'
																		+ value.blogtype
																		+ '</td><td style="width:100px;" class="zxx_text_overflow">'+ value.blog_content+'</td><td class="center"><a  class="btn">查看博文</a>  </td></tr>';

																str1 += str;

															});
									

											$("#table1").append(str1);
											function_name();
											
											$("a.btn").click( function() {
												var bloguser_id=$(this).parent().parent().find("td").eq(0).text();
												var s=$(this).parent().parent().find("td").eq(3).text();
												var user_name=$(this).parent().parent().find("td").eq(2).text();
												var content=$(this).parent().parent().find("td").eq(5).text();
												var date=$(this).parent().parent().find("td").eq(1).text();
												
												$("#username").text("用户名："+user_name);
												$("#date").text("发表日期："+date);
												$("#content").text(content);

												
												var str='';
												$.each(JSON.parse(s), function(i,value) {
													var s="<img src='"+value.file_address+"' style='width: 80px;margin: 3px;height: 80px;' />";

												
													str+=s;
												});
												$("#div1").append(str);
									
												 $("#s_bg").fadeIn(500);
												
												});

                                               
												$("#btnno").click(function  () {
													$("#s_bg").fadeOut()
													$("#div1").empty();
													
												})
												

										}, "text");}
					
					
					$(document).tooltip();
					


						
				
</script>

</head>
<body>
	<div class="rt_content">
		<ul class="admin_tab">
			<li><a class="active">系统博客信息</a></li>

		</ul>

		<section>
			<table class="table" id="table1">
				<tr>
					<th>博客ID</th>
					<th>发表日期</th>
					<th>用户昵称</th>
					<th>博客状态</th>
					<th>博客类型</th>
					<th>博文内容</th>
					<th class="center">详细操作</th>
				</tr>
			</table>
			<div class="content" style="margin-bottom: 100px">

				<div class="demo">
					<div id="demo2"></div>
				</div>

			</div>

		</section>
		<section class="pop_bg" id="s_bg">
			<div class="pop_cont">
				<!--title-->
				<h3>博客信息</h3>
				<!--content-->
				<div class="pop_cont_input">
					<ul>
						<li>
							<span id="username">用户名:来年我为你摇桃花</span>
							
						</li>
						<li>
							<span id="date">发表时间:2019.5.16</span>
							
						</li>
						<li>
						<span class="ttl" >博文内容</span>
							
						</li>
					
						<li>
						<textarea id="content" class="textarea" style="height:50px;width:350px;"disabled="true "></textarea>
						</li>
						<li>
							<div id="div1" style="float: left; width: 370px;">
								
							</div>
							<!--<table>
							
							
						</li>
						
					</ul>
				</div>
				<!--以pop_cont_text分界-->
				<div class="pop_cont_text">
					管理员无权修改博文信息
				</div>
				<!--bottom:operate->button-->
				<div class="btm_btn">
										<input type="button" value="关闭" class="input_btn falseBtn" id="btnno"/>
				

			</div>
		</section>
		
	</div>
</body>
</html>
