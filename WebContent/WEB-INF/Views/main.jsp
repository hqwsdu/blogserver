<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>后台管理系统</title>
		<meta name="author" content="DeathGhost" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<!--[if lt IE 9]>
<script src="js/html5.js"></script>"${pageContext.request.contextPath}/js/bootstrap-collapse.js"
<![endif]-->
		<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.mCustomScrollbar.concat.min.js"></script>
		<script>
		$(document).ready(function() {
			$("#quit").click(function login() {
								
				
				location.href = "quit.html";
				
				
				

			});

		});

			(function($) {
				$(window).load(function() {
					$("a[rel='load-content']").click(function(e) {
						e.preventDefault();
						var url = $(this).attr("href");
						$.get(url, function(data) {
							$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
							//scroll-to appended content 
							$(".content").mCustomScrollbar("scrollTo", "h2:last");
						});
					});
					$(".content").delegate("a[href='top']", "click", function(e) {
						e.preventDefault();
						$(".content").mCustomScrollbar("scrollTo", $(this).attr("href"));
					});
				});
			})(jQuery);

			function iFrameHeight() {
				var ifm = document.getElementById("iframepage");
				var subWeb = document.frames ? document.frames["iframepage"].document :
					ifm.contentDocument;
				if (ifm != null && subWeb != null) {
					ifm.height = subWeb.body.scrollHeight;
				}
			}

			function dyniframesize(down) {
				var Sys = {};
				var ua = navigator.userAgent.toLowerCase();
				var s;
				(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1]:
					(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
					(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
					(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
					(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
				var pTar = null;
				if (document.getElementById) {
					pTar = document.getElementById(down);
				} else {
					eval('pTar=' + down + ';');
				}
				pTar.style.display = "block";
				if (Sys.ie) {
					if (Sys.ie == '9.0') {
						pTar.height = pTar.contentWindow.document.body.offsetHeight + 15 + "px";
						pTar.width = pTar.contentWindow.document.body.scrollWidth + "px";
					} else if (Sys.ie == '8.0') {
						pTar.height = pTar.Document.body.offsetHeight + 15 + "px";
						pTar.width = pTar.Document.body.scrollWidth + "px";
					} else {
						pTar.height = pTar.Document.body.scrollHeight + 25 + "px";
						pTar.width = pTar.Document.body.scrollWidth + "px";
					}
				}
				if (Sys.firefox) {
					pTar.height = pTar.contentDocument.body.offsetHeight + 15 + "px";
					pTar.width = pTar.contentDocument.body.scrollWidth + "px";
				}
				if (Sys.chrome) {
					pTar.height = pTar.contentDocument.body.offsetHeight;
					pTar.width = pTar.contentDocument.body.scrollWidth;
				}
				if (Sys.opera) {
					pTar.height = pTar.contentDocument.body.offsetHeight;
					pTar.width = pTar.contentDocument.body.scrollWidth;
				}
				if (Sys.safari) {
					if (pTar.contentDocument.body.offsetHeight <= '186') {
						pTar.height = pTar.contentDocument.body.offsetHeight + 10;
					} else {
						pTar.height = pTar.contentDocument.body.offsetHeight;
					}
					pTar.width = pTar.contentDocument.body.scrollWidth;
				}
			}
		</script>
	</head>

	<body>
		<!--header-->
		<header>
			<h1><img src="images/admin_logo.png"/></h1>
			<ul class="rt_nav">
				<li><a href="#" class="admin_icon"><%=session.getAttribute("username")%></a></li>
				<li><a  class="quit_icon" id="quit">安全退出</a></li>
			</ul>
		</header>

		<!--aside nav-->
		<aside class="lt_aside_nav content mCustomScrollbar">
			<h2><a href="main_table.html" target="iframepage">起始页</a></h2>
			<ul>
				<li>
					<dl>
						<dt>管理员信息</dt>

						<dd><a href="add_guanliyuan.html" target="iframepage">添加管理员</a></dd>
						<dd><a href="main_guanliyuan.html" target="iframepage">查看现有管理员</a></dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt>用户信息</dt>
						<dd><a href="findUser.html" target="iframepage">现有用户</a></dd>
						<dd><a href="heiMingDan.html" target="iframepage">用户黑名单</a></dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt>博文管理</dt>
						<dd><a href="findBlog.html" target="iframepage">查看博文</a></dd>
						<dd><a href="checkBlog.html" target="iframepage">待审博文</a></dd>
					</dl>
				</li>
			
				<li>
					<dl>
						<dt>系统信息</dt>
						<dd><a href="loginLog.html" target="iframepage">管理员登录记录</a></dd>
					</dl>
				</li>

				<p class="btm_infor"></p>
				</li>
			</ul>
		</aside>

		<section class="rt_wrap content mCustomScrollbar">

			<div>
				<iframe id="iframepage" border="0" name="iframepage" height="600px" width="100%" src="main_table.html" scrolling="yes"></iframe>
			</div>
		</section>

	</body>

</html>