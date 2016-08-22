<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>
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
			<b>1:1문의</b>
				<a href="/Pis/question/writeForm.do">
				<input type="button" value="문의하기" class="button"></a>
			</div>
					<table class="table" border=1 width="700" cellpadding="0" cellspacing="0">
				<tr height="30">
					<td class="color" align="center" width="10%">번호</td>
					<td class="color" align="center" width="20%">문의유형</td>
					<td class="color" align="center" width="40%">제 목</td>
					<td class="color" align="center" width="20%">답변여부</td>
					<td class="color" align="center" width="20%">작성일</td>
				</tr>
				<c:if test="${count != 0 }">
				 <c:forEach var="article" items="${articleList}">
					<tr height="30" align="center">
						<td class="colorblack">${article.num }</td>
						<td class="colorblack">${article.kind }</td>		
						<td class="colorblack"><a href="/Pis/question/questionContent.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td class="colorblack">${article.checked}</td>
						<td class="colorblack">${date.format(article.regdt)}</td>
				</c:forEach> 
			</table>
		</c:if>

</body>
</html>