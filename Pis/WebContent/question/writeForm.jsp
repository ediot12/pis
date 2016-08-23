<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center>
	<br>
	<div class="title">
		<b>1:1문의하기</b>
	</div>
	<br>
		<form method="post" name="writeform"
			action="/Pis/question/writePro.do" onsubmit="return writeSave()">
			<input type="hidden" name="num" value="${num}">
			<table class="table" border="1" cellspacing="0" cellpadding="0"
				align="center" style="table-layout:fixed">
				<tr>
					<td width="70" class="color"><b>작성자</b></td>
					<td width="100">${memId }</td>
						<td width="70" class="color"><b>문의 종류</b></td>
						<td width="100" colspan="3">
					<select name="kind">
						<option value="회원정보">회원정보</option>
						<option value="예약">예약</option>
						<option value="취소">취소</option>
						<option value="환불">환불</option>
						<option value="기타">기타</option>
					</select></td>
					</tr>
				
				<tr>
					<td width="70" class="color"><b>제 목</b></td>
					<td width="330" colspan="5"align="left"><input type="text" size="45" maxlength="20"
						name="subject"></td>
				</tr>
					
				<tr>
					<td width="70" class="color"><b>내 용</b></td>
					<td width="100" colspan="5"><textarea name="content" rows="14" cols="133"></textarea>
					</td>
				</tr>
				
			</table>
			<input type="button" value="취소하기" class="button" OnClick="window.location='/Pis/question/mainForm.do'">
			<input type="submit" value="문의하기" class="button">
		</form>
</body>
</html>