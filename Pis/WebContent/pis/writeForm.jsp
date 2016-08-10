<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>게시판 공지사항</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>
<body>
	<center>
		<b>글쓰기</b> <br>
		<form method="post" name="writeform" action="/project/pis/writePro.do"
			onsubmit="return writeSave()">
			<input type="hidden" name="num" value="${num}">
			<table width="400" border="1" cellspacing="0" cellpadding="0"
				 align="center">
				<tr>
					<td align="right" colspan="2">
					<a href="/project/pis/list.do"> 글목록</a></td>
				</tr>
				<tr>
					<td width="70" align="center">이 름</td>
					<td width="330" ><input type="text" size="10" maxlength="10"
						name="writer"></td>
				</tr>
				<tr>
					<td width="70" align="center">제 목</td>
					<td width="330" ><input type="text" size="30" maxlength="20"
						name="subject"></td>
				</tr>

				<tr>
					<td width="70"  align="center">내 용</td>
					<td width="330" ><textarea name="content" rows="13" cols="40"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan=2  align="center">
					<input type="submit" value="글 등록"> 
						<input type="reset" value="수정하기"> 
						<input type="button" value="목록보기" OnClick="window.location='/project/pis/list.do'"></td>
				</tr>
			</table>
		</form>
</body>
</html>