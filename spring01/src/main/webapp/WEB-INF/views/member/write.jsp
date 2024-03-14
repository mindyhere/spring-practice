<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
</head>

<body>
<h2>회원등록폼</h2>
<form method="post" action="/member/insert.do">
	<table border="1" width="700px">
		<tr>
			<td>아이디</td>
			<td><input name="userid"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input name="name"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input name="email"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="확인"> 
			</td>
		</tr>
	</table>
</form>
</body>
</html>