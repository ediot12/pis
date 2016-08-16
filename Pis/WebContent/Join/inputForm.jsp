<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="inputForm.js"></script>
</head>
<body bgcolor="${team}">
<form method="post" name="userinput" action="/Pis/Join/inputPro.do" onsubmit="return checkId()" enctype="multipart/form-data">
<table width="700" border="1" cellspacing="0" cellpadding="3" align="center">
<tr>
<td colspan="2" height="39" align="center" bgcolor="${bodyback_c}">
<font size="+1"><b>회원가입</b></font>
</td>
</tr>
<tr>
<td width="200">아이디</td>
<td width="400">
<input type="text" id="ID" name="id" size="10" maxlength="12" onblur="id_nohan(this.form)">
<input type="button" name="confirm_id" value="ID중복확인" OnClick="openConfirmid(this.form)">
<input type="hidden" name="confirm_ok"  value="no">
</td>
</tr>
<tr>
<td width="200">비밀번호</td>
<td width="400"><input type="password" name="passwd" size="15" maxlength="12"></td>
</tr>
<tr>
<td width="200">비밀번호 확인</td>
<td width="400"><input type="password" name="passwd2" size="15" maxlength="12"></td>
</tr>
<tr>
<td width="200">이 름</td>
<td width="400"> <input type="text" name="name" size="15" maxlength="15" onblur="id_noNumber(this.form)"></td>
</tr>
<tr>
<td width="200">전화번호</td>
<td width="400"> <input type="text" name="phone" size="15" maxlength="11"></tr>
<tr>
<td width="200">우편번호</td>
<td width="400"><input type="text" name="zipcode" size="7" readonly>
<input type="button" value="우편번호찾기" onClick="zipCheck()"></td>
</tr>
<tr>
<td width="200">주소</td>
<td width="400">
<input type="text" name="address" size="60">
</td>
</tr>
<tr>
<td width="200">E-Mail</td>
<td width="400">
<input type="text" name="email" size="30" maxlength="40">
<input type="button" value="인증받기" onClick="mail(this.form)">
<input type="hidden" name="certify"> 
</td>

</tr>
<tr>
<td width="200">거주자여부</td>
<td width="400">
<input type="radio" name="resident" value="1" checked="checked">거주자
<input type="radio" name="resident" value="2" >비거주자
</td>
</tr>
<tr>
<td width="200">거주자자료첨부</td>
<td width="400">
<input type="file" name="upload">
</td>
</tr>
<tr>
<td colspan="2" align="center" bgcolor="${bodyback_c}">
<input type="button" value="취소" onclick="document.location.href='/Pis/Join/main.do?pageNum=${pageNum}'">
<input type="submit" name="confirm" value="등록"></td>
</tr>
</table>
</form>
</body>
</html>