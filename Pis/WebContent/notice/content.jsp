<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.jsp"%>
<!DOCTYPE html >
<html>
<head>

<style>
.box-button {
	padding-top: 20px;
	padding-bottom: 10px;
}

.box-gray {
	background: #555c67;
	padding: 4px 7px;
	border: 0px;
	color: #ffffff !important;
	cursor: pointer;
}
</style>
<title>글 상세보기</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<center>
		<table width="500" class="box-button">
			<tr>
				<td align="left"><b>공지사항</b></td>
		</table>




		<table width="500" border="1" cellspacing="0" cellpadding="0"
			align="center" style="table-layout: fixed">
			<tr height="30">
				<td align="center" width="125">제목</td>
				<td  width="375" align="center" colspan="3">
					${article.subject}</td>
			</tr>

			<tr>
				<td  width="375" align="left" height="150" colspan="4"
					style="word-wrap: break-word"><pre
						style="white-space: pre-wrap;">
						<b>${article.content}</b>
					</pre></td>
			</tr>

			<tr height="30">
				<td colspan="4" align="right"><input type="button"
					value="돌아가기" class="box-gray"
					onclick="document.location.href='list.do?pageNum=${pageNum}'">
				</td>
			</tr>
		</table>

		</form>
	</center>
</body>
</html>
