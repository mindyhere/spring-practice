<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(function(){
	list();
	$("#btnWrite").click(function(){
		insert(); // 메모저장
	});
});

function list(){
	// 백그라운드로 서버 호출
	$.ajax({
		url: "/memo/list.do",
		success: function(txt){ // 콜백함수
			$("#div1").html(txt); // div에 메모리스트 출력
		}
	});
}

function insert(){
	const writer=$("#writer").val();
	const memo=$("#memo").val();
	const params={"writer": writer, "memo":memo};
	
	// 백그라운드로 서버 호출
	$.ajax({
		type: "post",
		data: params,
		url: "/memo/insert.do",
		success: function(){ // 백그라운드 처리가 완료되면
			list(); //리스트 갱신
			$("#writer").val("");
			$("#memo").val("");
			// 입력필드 초기화
		}
	});
}

function view(num){
	$(location).attr("href", "/memo/view.do?_id="+num);
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>한줄메모장</h2>
이름: <input type="text" id="writer">
메모: <input type="text" id="memo" size="50">
<input type="button" id="btnWrite" value="확인">
<div id="div1"></div>
</body>
</html>