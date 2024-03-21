<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
<h2>상품목록</h2>
<form method="post">
상품명: <input type="text" name="product_name" value="${product_name }">
<input type="submit" value="조회"> 
</form>
<br>
<button type="button" onclick="location.href='write'">상품등록</button>
<hr>
등록된 상품수 : ${fn:length(list)} <!-- 데이터 수 -->
<table width="100%">
	<tr>
		<c:forEach var="row" items="${list }" varStatus="vs">
		<!-- 			개별			   리스트			루프상태변수 -->
			<td style="width:20%; word-break: break-word; vertical-align: bottom;">
				<c:choose>
					<c:when test="${row.filename != '-'}">
						<img src="/resources/images/${row.filename }" widht="100px" height="100px"><br>
					</c:when>
					<c:otherwise>
						[상품이미지 미등록]<br>
					</c:otherwise>
				</c:choose>
				<a href="/detail/${row.product_code }">
					상품명: ${row.product_name }<br>
				</a>
					가격: <fmt:formatNumber value="${row.price }" pattern="#,###"/>원
			</td>
			<c:if test="${vs.count mod 5 == 0 }"> <!-- 5개씩 줄바꿈 처리 -->
				<tr></tr>
			</c:if>
		</c:forEach>
	</tr>
</table>
</body>
</html>