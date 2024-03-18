<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>이메일 보내기</h2>
<form method="post" action="/email/send.do">
발신자 이름: <input name="senderName"><br>
발신자 이메일주소: <input name="senderMail"><br>
수신자 이메일주소: <input name="receiveMail"><br>
제목:	 <input name="subject"><br>
내용: <textarea rows="5" cols="80" name="message"></textarea><br>
<input type="submit" value="전송">
</form>
<!-- 메일 전송 결과 확인 메세지 출력 -->
<span style="color:red">${message }</span>
</body>
</html>