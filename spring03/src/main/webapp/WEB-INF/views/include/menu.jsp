<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="/">Home</a>&nbsp;&nbsp;|
<a href="/upload/input.do">업로드테스트</a>&nbsp;&nbsp;|
<a href="/shop/product/list.do">상품목록</a>&nbsp;&nbsp;|
<c:if test="${sessioScope.admin_userid != null }">
	<a href="/shop/product/write.do">상품등록</a>&nbsp;&nbsp;|
</c:if>
<a href="/shop/cart/list.do">장바구니</a>&nbsp;&nbsp;|
<a href="/pdf/list.do">PDF</a>&nbsp;&nbsp;|
<a href="/chart/chart1.do">구글차트(json)</a>&nbsp;&nbsp;|
<a href="/chart/chart2.do">구글차트(db)</a>&nbsp;&nbsp;|
<a href="/jchart/chart1.do">JFreeChart(png)</a>&nbsp;&nbsp;|
<a href="/jchart/chart2.do">JFreeChart(pdf)</a>&nbsp;&nbsp;|
<a href="/email/write.do">이메일발송</a>&nbsp;&nbsp;|
<a href="/member/address.do">도로명주소</a>&nbsp;&nbsp;|
<a href="/upload/ajax_form">파일업로드</a>&nbsp;&nbsp;|
<a href="/board/list.do">게시판</a>&nbsp;&nbsp;|

<div style="text-align: right;">
	<c:choose>
		<c:when test="${sessionScope.userid==null }">
			<a href="/member/login.do">로그인</a>&nbsp;&nbsp;|
			<a href="/admin/login.do">관리자 로그인</a>
		</c:when>
		<c:otherwise>
			${sessionScope.name } 님이 로그인중입니다.
			<a href="/member/logout.do">로그아웃</a>&nbsp;&nbsp;|
			<a href="/admin/logout.do">관리자 로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>

<hr>