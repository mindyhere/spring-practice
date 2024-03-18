<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="https://www.google.com/jsapi"></script>
<script>
//차트 라이브러리 로딩
google.load("visualization", "1", {"packages":["corechart"]});

// 라이브러리 로딩이 완료되면 실행할 함수지정
google.setOnLoadCallback(drawChart);

function drawChart(){
	var jsonData=$.ajax({
		url: "/chart/cart_money_list.do",
		dataType: "json",
		async: false
	}).responseText;
	
	console.log(jsonData);
	
	// 데이터 테이블
	var data=new google.visualization.DataTable(jsonData);
	// 차트생성
	var chart=new google.visualization.PieChart(document.getElementById("chart_div"));
	//차트그리기
	chart.draw (data, {
		title:"차트예제",
		width:600,
		height:400
	});
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<div id = "chart_div"></div>
<button type="button" id="btn" onclick="drawChart()">refresh</button>
</body>
</html>