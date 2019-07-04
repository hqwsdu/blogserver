<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
	<div id="container" style="height: 100%"></div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/css/echarts.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.3.2.js"></script>
		
	<script type="text/javascript">
	$.post("findTable.html", {
		
		
		
	},
	function(re) {
		
				var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		var da = JSON.parse(re);
		var ydata = [ '管理员数量', '博客用户数量', '系统博文数量', '待审查博文数', '推送消息数量' ]
		option = null;
		option = {
			title : {
				text : '当前服务器状态'
			},
			tooltip : {},
			legend : {
				data : [ '数量' ]

			},
			xAxis : {
				data : ydata

			},
			yAxis : {},
			series : [ {
				name : '数量',
				type : 'bar',
				data : da,
				itemStyle : {
					normal : {
						color : "#339966"
					}
				},
			} ],

			textStyle : {
				color : "fff"
			}
		};
		;
		if (option && typeof option === "object") {
			var startTime = +new Date();
			myChart.setOption(option, true);
			var endTime = +new Date();
			var updateTime = endTime - startTime;
			console.log("Time used:", updateTime);
		}
		
	},
	"text");
		
	</script>
</body>
</html>