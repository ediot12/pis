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
  <tr height="450px">
    <td align="center" class="color">글내용</td>
    <td align="left" colspan="3" class="colorblack" style="word-wrap:break-word">
    <img src="../../filesave/${idb.bfile}" width="300px" height="300px">
    	<pre style="white-space: pre-wrap;">
    	<br>${idb.content}</pre></td>
  </tr>
</table>
		<input type="button" value="글목록" class="button"
       onclick="document.location.href='/Pis/admin/board/info.do?pageNum=${pageNum}'">
</form>     
</body>
</html>    