<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf" %>

<!DOCTYPE html>
<html>
<head>
<title>회원탈퇴</title>
<link href="style.css" rel="stylesheet" type="text/css">
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
<BODY onload="begin()" bgcolor="${bodyback_c}">
<form name="myform" action="/Pis/Join/deletePro.do" method="post" onSubmit="return checkIt()">
<TABLE cellSpacing=1 cellPadding=1 width="260" border=1 align="center">

<TR height="30">
<TD colspan="2" align="left" bgcolor="${title_c}">
<font size="+1"><b>회원탈퇴</b></font></TD></TR>

<TR height="30">
<TD width="110" bgcolor="${value_c}" align=center>비밀번호를 입력해주세요.</TD>
</TR>
<tr>
<TD width="150" align=center>
<INPUT type=password name="passwd" size="15" maxlength="12"></TD></tr>
<TR height="30">
<TD width="110" bgcolor="${value_c}" align=center>탈퇴하시겠습니까?</TD>
</TR>
<TR height="30">
<TD colspan="2" align="middle" bgcolor="${value_c}">
<INPUT type=submit value="예">
<input type="button" value="아니요" onclick="javascript:window.location='/Pis/Join/main.do'"></TD></TR>
</TABLE>
</form>
</BODY>
</HTML>