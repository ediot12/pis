<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>
<!DOCTYPE html >
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
			<table border=1 width="800" cellpadding="0" cellspacing="0" class="table">
				<tr>
					<td class="color" align="center" width="5%">번호</td>
					<td class="color" align="center" width="20%">제 목</td>
					<td class="color" align="center" width="30%">주 소</td>
					<td class="color" align="center" width="10%">작성자</td>
					<td class="color" align="center" width="10%">작성일</td>
				</tr>
				 <c:forEach var="article" items="${articleList}">
					<tr height="30" align="center">
						<td class="colorblack"><c:out value="${number}" />
					<c:set var="number" value="${number - 1}" /></td> 
						<td class="colorblack"><a href="/Pis/admin/board/infoContent.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td class="colorblack">${article.address}</td>
						<td class="colorblack">${article.writer }</td>
						<td class="colorblack">${date.format(article.regdt)}</td>
				</c:forEach> 
			</table>
				<c:if test="${count > 0}">
			<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
			<c:set var="pageBlock" value="${10}"/>
			<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
			<c:set var="startPage" value="${result * 10 + 1}" />
			<c:set var="endPage" value="${startPage + pageBlock-1}"/>
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}"/>
			</c:if>
				         
			<c:if test="${startPage > 10}">
				<a href="/Pis/admin/board/info.do?pageNum=${startPage - 10 }"><input type="button" value="<<이전"  class="button"></a>
			</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/Pis/admin/board/info.do?pageNum=${i}"><input type="button" value="${i}" class="button"></a>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<a href="/Pis/admin/board/info.do?pageNum=${startPage + 10}"><input type="button" value="다음>>" class="button"></a>
				</c:if>
			</c:if>
</body>
</html>