<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- security 태그 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h2>${message }</h2>

	<!-- 관리자 -->
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="/admin/">관리자페이지</a>
		<br>
	</sec:authorize>
	
	<!-- 게스트 -->
	<sec:authorize access="isAnonymous()">
		<a href="/user/login.do">로그인</a>
	</sec:authorize>

	<!-- 로그인 상태 -->
	<sec:authorize access="isAuthenticated()">
		<a href="/user/logout.do">로그아웃</a>
		<br>
	</sec:authorize>
</body>
</html>