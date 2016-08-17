<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>


<!DOCTYPE html >
<html>
<head>
<style>
	.box-button{
    padding-top: 20px;
    padding-bottom: 20px;
	}
	.box-gray{
	background: #555c67;
    padding: 4px 7px;
    border: 0px;
    color: #ffffff !important;
    cursor: pointer;
	}

</style>
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<center>
		<b>주차장 제보 목록(전체글:${count})</b><br>


		<table width="700" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left"><b>주차장 제보 </b></td>
			</tr>
		</table>

		
			<table border=1 width="800" cellpadding="0" cellspacing="0">
				<tr height="30">
					<td class="target" align="center" width="50">번호</td>
					<td class="target" align="center" width="200">제 목</td>
					<td class="target" align="center" width="300">주 소</td>
					<td class="target" align="center" width="100">작성자</td>
					<td class="target" align="center" width="100">작성일</td>
				</tr>
				<c:if test="${count != 0 }">
				 <c:forEach var="article" items="${articleList}">
					<tr height="30" align="center">
						<td>${article.num }</td>
						<td><a href="/Pis/info/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td>${article.address}</td>
						<td>${article.writer }</td>
						<td>${sd.format(article.regdt)}</td>
				</c:forEach> 
			</table>
		</c:if>
		<br>
		<table width="700" class="box-button">
			<tr>
				<td align="right"><a href="/Pis/info/writeForm.do" class="box-gray">제보하기</a></td>
			</tr>
		</table>
		
</body>
</html>