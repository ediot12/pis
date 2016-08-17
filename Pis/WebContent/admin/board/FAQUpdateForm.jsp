<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<link href="../../style.css" rel="stylesheet" type="text/css">
</head>

<body> 
	<center>
	<br>
	<div class="title"> 
		<b>자주묻는질문</b>
	</div>
	<form action="/Pis/admin/board/FAQUpdatePro.do?pageNum=${pageNum}" method="post" onsubmit="return writeSave()">
	<input type="hidden" name="num" value="${fdb.num}">
	<table class="table" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="90" class="color">문의유형</td>
			<td width="100">
			<select name="kind">
				<option value="회원정보">회원정보</option>
				<option value="예약">에약</option>
				<option value="취소">취소</option>
				<option value="환불">환불</option>
			</select>
			<td width="90" height="30" class="color">제목</td>
			<td width="400" height="30" align="left"><input type="text" size="70" name="subject" value="${fdb.subject}" ></td>
		</tr>
		<tr>
			<td class="color">글내용
			<td colspan="3"><textarea rows="35" cols="141" class="textarea" name="content" >${fdb.content}</textarea></td>
		</tr>
	</table>
	<input type="button" value="목록보기" OnClick="window.location='/Pis/admin/board/FAQ.do'" class="button">
	<input type="submit" value="수정하기" class="button"> 
	</form>     
</body>
</html>    