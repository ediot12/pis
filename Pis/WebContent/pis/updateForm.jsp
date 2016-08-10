<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>
<body>
	<center>
		<b>글수정</b> <br>
		<form method="post" name="writeform"
			action="/project/pis/updatePro.do?pageNum=${pageNum}"
			onsubmit="return writeSave()">
			<table width="400" border="1" cellspacing="0" cellpadding="0"
			 align="center">
				<tr>
					<td width="70" align="center">이 름</td>
					<td align="left" width="330"><input type="text" size="10"
						maxlength="10" name="writer" value="${article.writer}">
						<input type="hidden" name="num" value="${article.num}"></td>
				</tr>
				<tr>
					<td width="70" align="center">제 목</td>
					<td align="left" width="330"><input type="text" size="40"
						maxlength="50" name="subject" value="${article.subject}"></td>
				</tr>

				<tr>
					<td width="70" align="center">내 용</td>
					<td align="left" width="330"><textarea name="content"
							rows="13" cols="40">${article.content}</textarea></td>
				</tr>

				<tr>
					<td colspan=2 align="center">
					<input type="submit" value="글수정">
						 <input type="reset" value="다시작성">
						 <input type="button" value="목록보기" onclick="document.location.href='list.do?pageNum=${pageNum}'">
					</td>
				</tr>
			</table>
		</form>
</body>
</html>
