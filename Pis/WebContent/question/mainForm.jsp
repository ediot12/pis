<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>
<!DOCTYPE html >
<html>
<head>
<title></title>
<style>
	.target{border-bottom: 1px solid #a5a5a5}
	.box-button{
	text-align: right;
    padding-top: 20px;
	}
	.box-gray{
	background: #555c67;
    padding: 4px 7px;
    border: 0px;
    color: #ffffff !important;
    cursor: pointer;
	}

</style>


<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<center>
		<table width="700" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left"><b>1:1문의 내역</b></td>
			</tr>
		</table>

		
			<table border=1 width="700" cellpadding="0" cellspacing="0">
				<tr height="30">
					<td class="target" align="center" width="50">번호</td>
					<td class="target" align="center" width="150">문의유형</td>
					<td class="target" align="center" width="350">제 목</td>
					<td class="target" align="center" width="100">답변여부</td>
					<td class="target" align="center" width="100">작성일</td>
				</tr>
				<c:if test="${count != 0 }">
				 <c:forEach var="article" items="${articleList}">
					<tr height="30" align="center">
						<td>${article.num }</td>
						<td>${article.kind }</td>		
						<td><a href="/Pis/question/questionContent.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td>${article.checked}</td>
						<td>${date.format(article.regdt)}</td>
				</c:forEach> 
			</table>
		</c:if>
		<br>
		<table width="700" class="box-button">
			<tr>
				<td align="right"><a href="/Pis/question/writeForm.do" class="box-gray">문의 하기</a></td>
			</tr>
		</table>

</body>
</html>