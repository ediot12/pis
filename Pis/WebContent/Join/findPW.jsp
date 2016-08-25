<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="../style.css" rel="stylesheet" type="text/css">
<script>
function checkpass1_2(){
	
var userinput = eval("document.userinput");	

if(userinput.passwd.value != userinput.passwd2.value){
	document.getElementById("change").innerHTML=
	"<b><font color=red size=2pt align='center'>비밀번호 확인다름</font></b>"
		return false;
}else{
	document.getElementById("change").innerHTML=
	"<b><font color=blue size=2pt align='center'>비밀번호 확인일치</font></b>"
		return false;}
		
	return true;
}
</script> 
</head>
<body>
<center>
<br>
<!-- <div class="title">
</div> -->
<c:if test="${empty pwd}">	
<script>
alert("입력하신 정보가 맞지 않습니다.");
history.go(-1);
</script>
	</c:if>
	 <div class="title">
	 <b>비밀번호 변경</b>
	 </div>
	<c:if test="${!empty pwd}">
	 <center>
	<form action="/Pis/Join/updatePWD.do" name="userinput" method="post" >
		<table border="1" cellpadding="0" cellspacing="0" class="shorttable">
			<tr>
				<td class="color">비밀번호</td>
				<td><input type="password" name="passwd" size="15" maxlength="10">
				</td>
			</tr>  
			<tr>
				<td class="color">비밀번호확인</td>
				<td><input type="password" name="passwd2" size="15" maxlength="10"
				onblur="checkpass1_2(this.form)"></td>
			</tr>  
				<tr>
				<td class="color">일치여부</td><td id="change" width="50"></td>
			</tr>
		</table>
		</center>
		<input type="hidden" name="id"  value="${id}">
		 <center><input type="submit" value="변경" class="button"></center>
	</form>
</c:if>
</body>
</html>