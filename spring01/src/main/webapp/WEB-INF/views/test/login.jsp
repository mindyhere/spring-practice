<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn_login").click(function(){
		id=$("#id").val();
		pw=$("#pw").val();
		params={"id":id, "pw":pw};
		
		$.ajax({
			type: "post",
			url: "login_result.do",
			data: params,
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
	id : <input type="text" id="id">
	pw : <input type="password" id="pw">
	<input type="button" id="btn_login" value="로그인">
	<div id="result"></div> 
</body>
</html>