<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>


<!DOCTYPE html >
<html>
<head>
<link href="../style.css" rel="stylesheet" type="text/css">
<title></title>
</head>
<body>
	<center>
		<br>
		<div class="title">
			<b>주차장 제보 </b>
		</div>
		<table class="table" border=1 width="800" cellpadding="0"
			cellspacing="0">
			<tr height="30">
				<td class="color" align="center" width="50">번호</td>
				<td class="color" align="center" width="200">제 목</td>
				<td class="color" align="center" width="300">주 소</td>
				<td class="color" align="center" width="100">작성자</td>
				<td class="color" align="center" width="100">작성일</td>
			</tr>
			<c:if test="${count != 0 }">
				<c:forEach var="article" items="${articleList}">
					<tr height="30" align="center">
						<td class="colorblack">${article.num }</td>
						<td class="colorblack"><a
							href="/Pis/info/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td class="colorblack">${article.address}</td>
						<td class="colorblack">${article.writer }</td>
						<td class="colorblack">${sd.format(article.regdt)}</td>
				</c:forEach>
		</table>
		</c:if>
		<br>
		<table class="table">
			<tr>
				<td align="right"><a href="/Pis/info/writeForm.do"> 
				<input type="button" value="제보하기" class="button"></a></td>
			</tr>
		</table>
</body>
</html>