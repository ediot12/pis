<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="../../style.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<center>
	<br>
	<div class="title">
		<b>공지사항</b>
	</div>
	<form action="/Pis/admin/board/noticewritePro.do" method="post">
	<input type="hidden" name="num" value="${num}">
	<table class="table" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="200" height="30" class="color">제목</td>
			<td width="500" height="30" align="left"><input type="text" size="100" name="subject" value="[공지]"></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="35" cols="160" class="textarea" name="content"></textarea></td>
		</tr>
	</table>
	<input type="button" value="목록보기" OnClick="window.location='/Pis/admin/board/noticeForm.do'" class="button">
	<input type="reset" value="다시작성" class="button">
	<input type="submit" value="글쓰기" class="button"> 
 	
  	
	</form>
</body>
</html>