<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(function(){
	$("#btnUpdate").click(function(){
		$("#form1").attr("action", "/memo/update.do");
		$("#form1").submit();
	});
	
	$("#btnDelete").click(function(){
		if(confirm("삭제하시겠습니까?")){
			$("#form1").attr("action", "/memo/delete.do");
			$("#form1").submit();
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모장</h2>
<form id="form1" name="form1" method="post">
	<input type="hidden" name="_id" value="${dto._id }">
	이름: <input type="text" name="writer" value="${dto.writer }"><br>
	메모: <input type="text" name="memo" value="${dto.memo }" size="50"><br>
	<input type="button" id="btnUpdate" value="수정">
	<input type="button" id="btnDelete" value="삭제">
	<input type="button" id="btnList" value="목록" onclick="location.href='/memo/'">
</form>
</body>
</html>