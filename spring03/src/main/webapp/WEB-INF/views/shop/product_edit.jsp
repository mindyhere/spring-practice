<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- ckeditor 실습 -->
<script src="/resources/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
function product_delete(){
	/*if(confirm("삭제하시겠습니까?")){
	document.form1.action="/shop/product/delete.do";
	document.form1.submit();
	}*/
	
	// 자바스크립트 코드 난독화
	function _0x3329(_0x3fd9c1,_0x5634b6){var _0x315261=_0x3152();return _0x3329=function(_0x33295d,_0xde6250){_0x33295d=_0x33295d-0x7b;var _0x1a20ff=_0x315261[_0x33295d];return _0x1a20ff;},_0x3329(_0x3fd9c1,_0x5634b6);}var _0x3f7747=_0x3329;(function(_0xf1c723,_0xe3a826){var _0x10599c=_0x3329,_0x376a7a=_0xf1c723();while(!![]){try{var _0x542bb3=-parseInt(_0x10599c(0x86))/0x1*(parseInt(_0x10599c(0x85))/0x2)+parseInt(_0x10599c(0x7c))/0x3*(-parseInt(_0x10599c(0x81))/0x4)+parseInt(_0x10599c(0x8b))/0x5*(-parseInt(_0x10599c(0x7b))/0x6)+parseInt(_0x10599c(0x83))/0x7+parseInt(_0x10599c(0x7d))/0x8*(parseInt(_0x10599c(0x89))/0x9)+-parseInt(_0x10599c(0x8a))/0xa+parseInt(_0x10599c(0x87))/0xb*(parseInt(_0x10599c(0x7f))/0xc);if(_0x542bb3===_0xe3a826)break;else _0x376a7a['push'](_0x376a7a['shift']());}catch(_0xd8359b){_0x376a7a['push'](_0x376a7a['shift']());}}}(_0x3152,0xa3ec4));function _0x3152(){var _0x154e35=['348PsuvQj','4053LHarSS','451990orIUAJ','submit','171LFNosf','12387650eYjhdp','24740abavaF','78OGLnrG','354WreiSb','418808hsjHVz','form1','504FyLEdP','/shop/product/delete.do','23656nMtZYt','action','4599994BHXqLX','삭제하시겠습니까?'];_0x3152=function(){return _0x154e35;};return _0x3152();}confirm(_0x3f7747(0x84))&&(document[_0x3f7747(0x7e)][_0x3f7747(0x82)]=_0x3f7747(0x80),document['form1'][_0x3f7747(0x88)]());
}

function product_update(){
	const product_name=document.form1.product_name.value;
	const price=document.form1.price.value;
	const description=document.form1.description.value;
	
	if(product_name==""){
		alert("상품명을 입력하세요.");
		document.form1.product_name.focus();
		return;
	}
	if(price==""){
		alert("가격을 입력하세요.");
		document.form1.price.focus();
		return;
	}
	/* if(description==""){
		alert("상품설명을 입력하세요.");
		document.form1.description.focus();
		return;
	} */
	document.form1.action="/shop/product/update.do";
	document.form1.submit();
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<form name="form1" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>상품명</td>
			<td><input name="product_name" value="${dto.product_name }"></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><input name="price" value="${dto.price }"></td>
		</tr>
		<tr>
			<td>상품설명</td>
			<td><textarea rows="5" cols="60" name="description" id="description">${dto.description }</textarea></td>
			<!--textarea를 스마트에디터로 변경 -->
			<script>
			// CKEDITOR.replace("description"); // 태그의 id
			
			// 이미지 업로드기능 추가
				CKEDITOR.replace("description", {
					filebrowserUploadUrl : "/imageUpload.do"
				});
			</script>
		</tr>
		<tr>
			<td>상품이미지</td>
			<td>
				<image src="/resources/images/${dto.filename }" width="300px" height="300px">
				<br>
				<input type="file" name="file1">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="hidden" name="product_code" value="${dto.product_code }">
				<input type="button" value="수정" onclick="product_update()">
				<input type="button" value="삭제" onclick="product_delete()">
				<input type="button" value="목록" onclick="location.href='/shop/product/list.do'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>