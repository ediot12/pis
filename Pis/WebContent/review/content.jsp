<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<b>사용후기</b>
	</div>
	<br>
	
			<table width="800" height="200"border="1" cellpadding="0" cellspacing="0" 
			      align="center" style="table-layout:fixed">

				
				<tr>
					<td width="150" height="40" class="color"><b>작성자</b></td>
					<td colspan="3" align="center">${article.writer}</td>
				</tr>

				<tr>
					<td height="40" width="70" class="color"><b>제목</b></td>
					<td colspan="3" align="center">${article.subject}</td>
				</tr>
				
				<!-- db 에 파일이 있을경우 파일과 글 내용  출력-->
				<tr height="350">
					<td width="70" class="color"><b>내용</b></td>
					<c:if test="${!article.bfile.equals('null')}">
						<td width="300" colspan="3" style="word-wrap:break-word"><pre style="white-space: pre-wrap;">
						<div><img src="../filesave/${article.bfile}" width="250px" height="250px" ></div>
						<h3>${article.content}</h3></pre>
					</td>
					</c:if>
					<!-- db에 저장된 파일이 없을시 즉 null 값일 경우 글내용만 출력 -->
					<c:if test="${article.bfile.equals('null')}">
						<td width="300" colspan="3" style="word-wrap:break-word"> <pre style="white-space: pre-wrap;">
					<p><b><h3>${article.content}</h3></b></p></pre>
					</c:if>
				</tr>
	
			
			</table>
			<br>
					<input class="button" type="button" value="글수정" onclick="document.location.href='updateForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="button" type="button" value="글목록" onclick="document.location.href='mainForm.do?pageNum=${pageNum}'">
		
	
</body>
</html>