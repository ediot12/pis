<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>
<!DOCTYPE> 
<html>
<head>
<title>포인트 사용 내역</title>
</head>
<body>
<center>
<br>
	<div class="title">
	<b>포인트 사용내역</b>
	</div>
<c:if test="${count==0}">
	<table width="750" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td align="center">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table>
</c:if>
<c:if test="${count> 0}">
	<table border="1" width="750" cellpadding="0" cellspacing="0" align="center" class="table">
	<tr>
		<td width="50" class="color">번호</td>
		<td width="250" class="color">주차장명</td>
		<td width="200" class="color">내역</td>
		<td width="100" class="color">충전포인트</td>
		<td width="100" class="color">사용포인트</td> 
		<td width="200" class="color">사용일</td>
	</tr>
		<c:forEach var="article" items="${articleList}" >
			<tr height="30" align="center">
				<td class="colorblack">
				<c:out value="${number }"/>
				<c:set var="number" value="${number-1 }"/>
				</td>
				<td class="colorblack">${article.parking_name }</td>
				<td class="colorblack">${article.info }</td>
				<td class="colorblack">${article.point }</td>
				<td class="colorblack">${article.use_point }</td>
				<td class="colorblack">${article.reg_date }</td>
			</tr>
		</c:forEach>
	</table><br>
</c:if>
	</div>
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
			<a href="/Pis/pointcharge/pointlist.do?pageNum=${startPage - 10 }"><input type="button" value="<<이전"  class="button"></a>
		</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/Pis/pointcharge/pointlist.do?pageNum=${i}"><input type="button" value="${i}" class="button"></a>
			</c:forEach>
			
			<c:if test="${endPage < pageCount}">
				<a href="/Pis/pointcharge/pointlist.do?pageNum=${startPage + 10}"><input type="button" value="다음>>" class="button"></a>
			</c:if>
		</c:if>


</body>
</html>