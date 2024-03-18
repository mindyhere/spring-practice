<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function showPostcode(){
	new daum.Postcode({
		oncomplete : function(data){
			let fullAddr="";
			let extraAddr="";
			if(data.userSelectedType==="R"){
				fullAddr=data.roadAddress;
			}else{
				fullAddr=data.jibunAddress;
			}
			if(data.userSelectedType==="R"){
				if(data.bname!==""){
					extraAddr+=data.bname;
				}
				if(data.buildingName!==""){
					extraAddr+=(extraAddr!==""? ", "+data.buildingName : data.buildingName);
				}
				fullAddr += (extraAddr !==""? " ("+extraAddr+")" : "");
			}
			document.getElementById("post_code").value=data.zonecode;
			document.getElementById("address1").value=fullAddr;
			document.getElementById("address2").focus();
		}
	}).open();
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp"%>
<form name="form1" method="post">
우편번호 : <input name="zipcode" id="post_code" readonly>
<input type="button" onclick="showPostcode()" value="우편번호찾기">
<br>
주소: <input name="address1" id="address1" size="60">
상세주소: <input name="address2" id="address2"> 
</form>
</body>
</html>