<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<link href="../style.css" rel="stylesheet" type="text/css">
<script src="inputForm.js"></script>
</head>
<body>
    <center>
	<br>
	<div class="title">
	<b>회원가입</b>
	</div>
<form method="post" name="userinput" action="/Pis/Join/inputPro.do" onsubmit="return checkId()" enctype="multipart/form-data">
<table border="1" cellspacing="0" cellpadding="0" align="center" class="inputtable">
<tr>
<td width="30%" class="color">아이디</td>
<td width="70%" class="colorblack">
<input type="text" id="ID" name="id" size="10" maxlength="12" onblur="id_nohan(this.form)">
<input type="button" name="confirm_id" value="ID중복확인" OnClick="openConfirmid(this.form)" class="button">
<input type="hidden" name="confirm_ok"  value="y">
</td>
</tr>
<tr>
<td class="color">비밀번호</td>
<td class="colorblack"><input type="password" name="passwd" size="15" maxlength="12"></td>
</tr>
<tr>
<td class="color">비밀번호 확인</td>
<td class="colorblack"><input type="password" name="passwd2" size="15" maxlength="12"></td>
</tr>
<tr>
<td class="color">이 름</td>
<td class="colorblack"> <input type="text" name="name" size="15" maxlength="15" onblur="id_noNumber(this.form)"></td>
</tr>
<tr> 
<td class="color">전화번호</td> 
<td class="colorblack"> <input type="text" name="phone" size="15" maxlength="11"> '-'없이 입력해주세요</tr>
<tr>
<td class="color">우편번호</td>
<td class="colorblack"><input type="text" name="zipcode" size="7" readonly>
<input type="button" value="우편번호찾기" onClick="zipCheck()" class="button"></td>
</tr>
<tr>
<td class="color">주소</td>
<td class="colorblack">
<input type="text" name="address" size="60">
</td>
</tr>
<tr>
<td class="color">E-Mail</td>
<td class="colorblack">
<input type="text" name="email" size="30" maxlength="40">
<input type="button" value="인증받기" onClick="mail(this.form)" class="button">
<input type="hidden" name="certify" value="n"> 
</td>
</tr>
<tr>
<td class="color">거주자여부</td>
<td class="colorblack">
<input type="radio" name="resident" value="1" onClick="this.form.upload.disabled=false">거주자
<input type="radio" name="resident" value="2" onClick="this.form.upload.disabled=true">비거주자
</td>
</tr>
<tr>
<td class="color">거주자자료첨부</td>
<td class="colorblack">
<input type="file" name="upload" disabled>
</td>
</tr>
</table>
<input type="submit" name="confirm" value="등 록" class="button">
<input type="button" value="취 소" onclick="document.location.href='/Pis/layout/main.do'" class="button">
</form>
</body>
</html>