<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호찾기</title>
	<script>
function checkpass(){
	var userinput = eval("document.userinput");	
	if(!userinput.passwd.value){
		alert("비밀번호를 입력하세요");
		return false;
	}return true;
}

function checkpass1_2(){
var userinput = eval("document.userinput");	

if(!userinput.passwd.value){
	alert("비밀번호를 입력하세요");
	return false;
}

if(userinput.passwd.value != userinput.passwd2.value){
	alert("비밀번호를 동일하게 입력하세요");
	return false;
}

if(userinput.passwd.value != userinput.passwd2.value){
document.getElementById("upup").innerHTML="<b><font color=red size=2pt align='center'>비밀번호 확인다름</font></b>"
	return false;
}else{
document.getElementById("upup").innerHTML="<b><font color=blue size=2pt align='center'>비밀번호 확인일치</font></b>"
	return false;}
	return true;
}
</script>
</head>
<body bgcolor="${bodyback_c}">
<c:if test="${empty pwd}">	
<script>
alert("입력하신 정보가 맞지 않습니다.");
history.go(-1);
</script>
	</c:if>
	<c:if test="${!empty pwd}">
	<form action="/Pis/Join/updatePWD.do" name="userinput" method="post" >
		<table border="1">
			<tr>
				<td colspan="2">비밀번호 변경</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd" size="15" maxlength="10"
				onblur="checkpass()"></td>
			</tr>  
			<tr>
				<td>비밀번호확인</td>
				<td><input type="password" name="passwd2" size="15" maxlength="10"
				onblur="checkpass1_2()"></td>
			</tr>  
				<tr>
				<td>일치여부</td><td id="upup" width="50"></td>
			</tr>
			<tr align="right">
				<td colspan="2">
				<input type="hidden" name="id"  value="${id}">
				<input type="submit" value="변경"></td>
			</tr>
		</table>
	</form>
</c:if>
</body>
</html>