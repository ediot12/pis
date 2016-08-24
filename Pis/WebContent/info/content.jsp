<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title></title>
<!-- css적용 -->
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center>
	<br>    
	<div class="title">
		<b>주차장 제보</b>
	</div>
	<br>
			<!-- 테이블 생성  사이즈 및 테투리 여백 -->
			<table width="800" height="200"border="1" cellpadding="0" cellspacing="0" 
			      align="center" style="table-layout:fixed">

				<!-- 작성자 id  -->
				<tr>
					<td width="150" height="40" class="color"><b>작성자</b></td>
					<td colspan="3" align="left">&nbsp;${article.writer}</td>
				</tr>
				
				<!-- 제목 -->
				<tr>
					<td height="40" width="70" class="color"><b>제목</b></td>
					<td colspan="3" align="left">&nbsp;${article.subject}</td>
				</tr>
				<!-- 우편번호 -->
				<tr>
					<td height="40" width="70" class="color"><b>우편번호</b></td>
					<td colspan="3" align="left">&nbsp;${article.zipcode}</td>
				</tr>
				<!-- 주소 -->
				<tr>
					<td height="40" width="70" class="color"><b>주소</b></td>
					<td colspan="3" align="left">&nbsp;${article.address}</td>
				</tr>
				
				<!-- db 에 파일이 있을경우 파일과 글 내용  출력-->
				<tr height="450">
					<td  width="70" class="color"><b>내용</b></td>
					<c:if test="${!article.bfile.equals('null')}">
						<td width="300" colspan="3" style="word-wrap:break-word"><pre style="white-space: pre-wrap;">
						<img src="../filesave/${article.bfile}" width="250px" height="250px" >
						<br><b><h3>${article.content}</h3></b></pre>
					</td>
					</c:if>
					
					<!-- db에 저장된 파일이 없을시 즉 null 값일 경우 글내용만 출력 -->
					<c:if test="${article.bfile.equals('null')}">
						<td width="300" colspan="3" style="word-wrap:break-word"><pre style="white-space: pre-wrap;">
					<b><h3>${article.content}</h3></b></pre>
					</c:if>
				</tr>
	
			
			</table>
			<br>
			<!-- 메인 페이지로 이동할 글목록 버튼  -->
			<input type="button"
					value="글 목록" class="button"
					onclick="document.location.href='mainForm.do?pageNum=${pageNum}'">
		</body>
</html>