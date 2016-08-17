<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center>
<br>
<div class="title">
	<b>비밀번호 찾기</b>
</div>
<form action="findPW.do" method="post">
<table border="1" cellspacing="0" cellpadding="0" class="shorttable">
<tr>
<td class="color">아이디</td>
<td><input type="text" name="id" size="15" maxlength="10">
</td>
</tr>
<tr>
<td class="color">이름</td>
<td><input type="text" name="name" size="15" maxlength="10">
</td>
</tr>
<tr>
<td class="color">전화번호</td>
<td><input type="text" name="phone" size="15" maxlength="11"></td>
</tr>
</table>
<input type="submit" name="confirm" value="찾 기" class="button"> 
<input type="button" value="취소" onclick="document.location.href='/Pis/Join/main.do?pageNum=${pageNum}'" class="button"> 
</form>
</body>
</html> 