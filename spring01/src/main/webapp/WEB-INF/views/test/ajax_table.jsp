<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#button1").click(function(){
		num=$("#num").val();
		param={"num":num};
		$.ajax({
			type: "post",
			url: "ajax_table_result.do",
			data:param,
			success: function(txt){
				$("#result").html(txt);
			}
		});
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
	<h2>구구단 계산</h2>
	단을 입력하세요: <input type="number" id="num" value="3">
	<input type="button" id="button1" value="확인">
	<div id="result"></div> 
</body>
</html>