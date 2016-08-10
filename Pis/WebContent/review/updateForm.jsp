<%@ page  contentType="text/html; charset=UTF-8"%>
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
			action="/project/review/updatePro.do?pageNum=${pageNum}"
			onsubmit="return writeSave()">
			<table width="600" border="1" cellspacing="0" cellpadding="0"
			 align="center">
				<tr>
					<td width="70" align="center">이 름</td>
					<td align="left" width="330">
					<input type="text" size="20" maxlength="10" name="writer" value="${article.writer}">
					<input type="hidden" name="num" value="${article.num}"></td>
				</tr>
				<tr>
					<td width="70" align="center">제 목</td>
					<td align="left" width="330"><input type="text" size="20"
						maxlength="50" name="subject" value="${article.subject}"></td>
				</tr>
				
				<tr>
				<td width="70" align="center">평가하기</td>
					<td width="330">
					<select name="score">
					<option value="">선택</option>
					<option value="1">★</option>
					<option value="2">★★</option>
					<option value="3">★★★</option>
					<option value="4">★★★★</option>
					<option value="5">★★★★★</option>
					</select>평가 : ${article.score}</td>
				<tr>

				<tr>
					<td width="70" align="center">내 용</td>
					<td align="left" width="330"><textarea name="content"
							rows="12" cols="70">${article.content}</textarea></td>
				</tr>

				<tr>
					<td colspan=2 align="center">
					<input type="submit" value="글수정">
						 <input type="reset" value="다시작성">
						 <input type="button" value="목록보기" onclick="document.location.href='mainForm.do?pageNum=${pageNum}'">
					</td>
				</tr>
			</table>
		</form>
</body>
</html>