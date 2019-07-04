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
	name : 'bloguser'

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
										"BlogUserController/findPageBlogUser.html",
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
																		+ value.bloguser_id
																		+ '</td><td class="center"style="width:100px;">'
																		+ value.bloguser_nickname
																		+ '</td><td style="width:120px;" class="center">'
																		+ value.bloguser_phone
																		+ '</td><td class="center" style="width:100px;" class="center">'
																		+ value.bloguser_date
																		+ '</td><td class="center">'
																		+ value.bloguser_address
																		+ '</td><td class="center">'
																		+ value.bloguser_state
																		+ '</td><td class="center"><a title='+ value.bloguser_data+'>用户简介</a></td><td class="center"><a  class="btn">加入黑名单</a> <a>查看博文</a>  </td></tr>';

																str1 += str;

															});

											$("#table1").append(str1);
											$("a.btn").click( function() {
												var bloguser_id=$(this).parent().parent().find("td").eq(0).text();
												
												var name=$(this).parent().parent().find("td").eq(1).text();
												var user_id='<%=(String)session.getAttribute("user_id")%>';
								
		
										
											
												$("#s_bg").fadeIn(500);
												$("#s_span").text("用户名："+name);
												$("#btnno").click(function  () {
													$("#s_bg").fadeOut()
													
												})
												$("#btnyes").click( function() {
													var xinxi={"bloguer_id":bloguser_id,"user_id":user_id,"blacklist_cause":$("#data_cause").val()};
													
													
													$("#s_bg").fadeOut(500);
													$.post("BlackListController/addBlackList.html", {
														data:JSON.stringify(xinxi)
														
														
													},
													function(re) {
														$("#data_cause").val('');
														$("#ser_bg").fadeIn(2000);
														if(re=='success')
														{
															
															
															
															$("#s_span2").text("拉黑成功");
															mydata(page);
															
															
														}
														else
														{
															
															$("#s_span2").text("加入黑名单失败");
														}
														
														$("#ser_bg").fadeOut(1000);
														
														
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
			<li><a class="active">博客用户信息</a></li>

		</ul>

		<section>
			<table class="table" id="table1">
				<tr>
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>用户电话</th>
					<th>注册时间</th>
					<th>用户地址</th>
					<th>用户状态</th>
					<th>额外信息</th>
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
				<h3>是否拉黑下面的用户</h3>
				<!--content-->
				<div class="pop_cont_input">
					<ul>
						<li>
							<span id="s_span"></span>
							
						</li>
						<li> <textarea id="data_cause" placeholder="输入拉黑该用户的原因" class="textarea"
					style="width: 500px; height: 100px;"></textarea></li>
					
			
					</ul>
				</div>
				<div class="btm_btn">
					<input type="button" value="确定" class="input_btn trueBtn" id="btnyes"/>
					<input type="button" value="取消" class="input_btn falseBtn" id="btnno" />
				</div>
			</div>
		</section>
		<section class="pop_bg" id="ser_bg">
			<div class="pop_cont">
				<!--title-->
				<h3>服务器消息</h3>
				<!--content-->
				<div class="pop_cont_input">
					<ul>
						<li>
							<span id="s_span2"></span>
							
						</li>
					
			
					</ul>
				</div>
			</div>
		</section>
		
		<!--结束：以下内容则可删除，仅为素材引用参考-->
	</div>
</body>
</html>
