<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<link href="../../style.css" rel="stylesheet" type="text/css">
</head>
 
<body> 
<center>
	<br>
	<div class="title">
	<b>주차장 제보관리</b>
	</div>
<br>
<form>
<table border="1" cellspacing="0" cellpadding="0" align="center" class="table"> 
  <tr>
 	<td align="center" width="15%" class="color">작성자</td>
 	<td align="center" width="75%" class="colorblack">${idb.writer}</td>
  </tr>
  <tr>
  	<td align="center" class="color">제 목</td>
  	<td align="center" class="colorblack">${idb.subject}</td>
  </tr>
  <tr>
  	<td align="center" class="color">우편번호</td>
  	<td align="center" class="colorblack">${idb.zipcode}</td>
  </tr>
  <tr>
    <td align="center" class="color">주 소</td>
  	<td align="center" class="colorblack">${idb.address}</td>
  </tr>
  <!-- db 에 파일이 있을경우 파일과 글 내용  출력-->
				<tr height="350">
					<td width="70" class="color"><b>내용</b></td>
					<c:if test="${!idb.bfile.equals('null')}">
						<td class="colorblack" width="300" colspan="3" style="word-wrap:break-word"><pre style="white-space: pre-wrap;">
						<div><img src="../../filesave/${idb.bfile}" width="250px" height="250px" ></div>
						<h3>${idb.content}</h3></pre>
					</td>
					</c:if>
					<!-- db에 저장된 파일이 없을시 즉 null 값일 경우 글내용만 출력 -->
					<c:if test="${idb.bfile.equals('null')}">
						<td class="colorblack" width="300" colspan="3" style="word-wrap:break-word"> <pre style="white-space: pre-wrap;">
					<p><b><h3>${idb.content}</h3></b></p></pre>
					</c:if>
				</tr>
</table>
		<input type="button" value="글목록" class="button"
       onclick="document.location.href='/Pis/admin/board/info.do?pageNum=${pageNum}'">
</form>     
</body>
</html>    