<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
<script>
function begin(){
	document.myform.passwd.focus();
}

function checkIt(){
	if(!document.myform.passwd.value){
		alert("비밀번호를 입력하지 않으셨습니다.");
		document.myform.passwd.focus();
		return false;
	}
} 
</script>
</head>

<BODY onload="begin()">
<center>
<div class="title">
<b>회원탈퇴</b>
</div>
<form name="myform" action="/Pis/Join/deletePro.do" method="post" onSubmit="return checkIt()">
<table border="1" cellspacing="0" cellpadding="0" class="shorttable">
	<TR height="30">
		<TD width="110"  align=center class=colorblack><br>비밀번호를 입력해주세요.<br><br>
			<INPUT type=password name="passwd" size="15" maxlength="12"><br><br>
		</TD>
	</tr>
</TABLE>
<div class="colorblack">
	탈퇴하시겠습니까?<br><br>
	<INPUT type=submit value="예" class=button>
	<input type="button" value="아니요" onclick="javascript:window.location='/Pis/layout/main.do'" class=button>
</div>
</form>
</BODY>
</HTML>