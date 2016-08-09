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
		<b>글내용 보기</b> <br>
		<form>
			<table width="650" height="200"border="1" cellpadding="0" cellspacing="0" 
			      align="center" style="table-layout:fixed">

				<tr>
					<td align="center" width="150"><b>글번호</b></td>
					<td align="center" width="150">${article.num}</td>
					<td align="center" width="150"><b>조회수</b></td>
					<td align="center" width="150">${article.readcount}</td>
				</tr>

				<tr>
					<td align="center" width="70"><b>작성자</b></td>
					<td align="center" width="150">${article.writer}</td>
					<td align="center" width="150"><b>작성일</b></td>
					<td align="center" width="150">${article.regdt}</td>
				</tr>

				<tr>
					<td align="center" width="70"><b>제목</b></td>
					<td colspan="3" align="center">${article.subject}</td>
				</tr>

				<tr>
					<td align="center" width="70"><b>내용</b></td>
					<td width="300" colspan="3" style="word-wrap:break-word"><pre style="white-space: pre-wrap;"><b>${article.content}</b></pre>
					</td>
				</tr>

				<tr>
					<td colspan="4" align="right">
					<input type="button" value="글수정" onclick="document.location.href='updateForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="글삭제" onclick="document.location.href='deleteForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="글목록" onclick="document.location.href='mainForm.do?pageNum=${pageNum}'">
					</td>
				</tr>
			</table>

		</form>
	
</body>
</html>