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
	name : 'blacklist'

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
										"BlackListController/findBalckListUi.html",
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
																		+ value.blacklist_id
																		+ '</td><td class="center"style="width:100px;">'
																		+ value.bloguser_nickname
																		+ '</td><td style="width:120px;" class="center">'
																		+ value.bloguser_phone
																		+ '</td><td class="center" style="width:150px;" class="center">'
																		+ value.blacklist_date
																		+ '</td><td class="center" style="width:100px;" class="center">'
																		+ value.blacklist_cause
																		+ '</td><td class="center">'
																		+ value.user_name
																		+ '</td><td class="center"><a title='+ value.bloguser_data+'>用户详情</a></td><td class="center"><a  class="btn">移出黑名单</a></td></tr>';

																str1 += str;

															});

											$("#table1").append(str1);
											$("a.btn").click( function() {
												var blackList_id=$(this).parent().parent().find("td").eq(0).text();
												var bloguser=$(this).parent().parent().find("td").eq(1).text();
											
												$("#s_bg").fadeIn(500);
												$("#s_span").text("用户名:"+bloguser);
												$("#btnno").click(function  () {
													$("#s_bg").fadeOut()
													
												})
												$("#btnyes").click( function() {
													$("#s_bg").fadeOut(500);
													$.post("BlackListController/deleteBlackList.html", {
														id_1:blackList_id
														
														
													},
													function(re) {
														if(re=='success')
														{
															mydata(n);
														
														}else if(re=='error')
														{
															alert("error");
														}
														else
														{
															alert('服务器错误')
														}
														
														
													},
													"text");
													});
												
												
												});
											


										}, "text");}
					
					
					$(document).tooltip()
					


						
				
</script>

</head>
<body>
	<div class="rt_content">
		<ul class="admin_tab">
			<li><a class="active">用户黑名单信息</a></li>

		</ul>

		<section>
			<table class="table" id="table1">
				<tr>
					<th>黑名单序号</th>
					<th>用户昵称</th>
					<th>用户电话</th>
					<th>拉黑日期</th>
					<th>拉黑原因</th>
					<th>管理员名</th>
					<th>用户详情</th>
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
				<h3>是否将下面的用户移出黑名单</h3>
				<!--content-->
				<div class="pop_cont_input">
					<ul>
						<li>
							<span id="s_span"></span>
							
						</li>
					
			
					</ul>
				</div>
				<div class="btm_btn">
					<input type="button" value="确定" class="input_btn trueBtn" id="btnyes"/>
					<input type="button" value="取消" class="input_btn falseBtn" id="btnno" />
				</div>
			</div>
		</section>
		
		<!--结束：以下内容则可删除，仅为素材引用参考-->
	</div>
</body>
</html>
