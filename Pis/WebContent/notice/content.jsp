<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.text.SimpleDateFormat" %>

<!DOCTYPE html >
<html>
<head>
<title>글 상세보기</title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center>
	<br> 
	<div class="title">
		<b>공지사항</b>
	</div>
	<br>
		<table width="900" border="1" cellspacing="0" cellpadding="0"
			align="center" style="table-layout: fixed">
			<tr height="30">
				<td align="center" width="125" class="color">제목</td>
				<td  width="375" align="center" colspan="3">
					${article.subject}</td>
			</tr>

			<tr height="450">
				<td  width="375" align="left" height="150" colspan="4"
					style="word-wrap: break-word"><pre style="white-space: pre-wrap;">
						<b>${article.content}</b>
					</pre></td>
			</tr>

		</table>
		<br>
					<input type="button"
					value="돌아가기" class="button"
					onclick="document.location.href='list.do?pageNum=${pageNum}'">
		
	
</body>
</html>
