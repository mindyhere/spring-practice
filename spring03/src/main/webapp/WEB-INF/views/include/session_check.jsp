<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 관리자 전용페이지, 세션체크 : 인터셉터를 사용하지 않고 세선존재 여부를 체크할 경우 -->
<c:if test="${sessionScope.admin_userid == null }">
	<script>
		alert("로그인 후 사용하세요.");
		location.href="/admin/login.do";
	</script>
</c:if>
