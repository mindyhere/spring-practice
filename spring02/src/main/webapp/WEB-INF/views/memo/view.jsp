<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btnUpdate").click(function(){
		document.form1.action="/memo/update/${dto.idx}";
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("삭제하시겠습니까?")){
			document.form1.action="/memo/delete/${dto.idx}";
			document.form1.submit();
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<form method="post" name="form1">
	<table border="1" width="500px">
		<tr>
			<td>번호</td>
			<td>${dto.idx }</td>
		</tr>
		<tr>
			<td>번호</td>
			<td>${dto.idx }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input name="writer" value="${dto.writer }"></td>
		</tr>
		<tr>
			<td>메모</td>
			<td><input name="memo" value="${dto.memo}" size="50"></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="hidden" name="idx" value="${dto.idx }">
				<button type="button" id="btnUpdate">수정</button>
				<button type="button" id="btnDelete">삭제</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>