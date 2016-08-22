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
	<table  width="750" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td align="ㅣeft"><h1>포인트 사용내역</h1></td>			
		</tr>
	</table>
<c:if test="${count==0}">
	<table width="750" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td align="center">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table>
</c:if>
<c:if test="${count!=0}">
	<table border="1" width="750" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td align="center" width="50">번호</td>
		<td align="center" width="200">주차장명</td>
		<td align="center" width="200">내역</td>
		<td align="center" width="100">충전포인트</td>
		<td align="center" width="100">사용포인트</td>
		<td align="center" width="200">사용일</td>
	</tr>
		<c:forEach var="article" items="${articleList}" >
			<tr height="30" align="center">
				<td>${article.num }</td>
				<td>${article.parking_name }</td>
				<td>${article.info }</td>
				<td>${article.point }</td>
				<td>${article.use_point }</td>
				<td>${article.reg_date }</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${count > 0 }">
	<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0 : 1) }"/>
	<c:set var="pageBlock" value="${10 }"/>
	<fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true"/>
	<c:set var="startPage" value="${result*10+1 }"/>
	<c:set var="endPage" value="${startPage+pageBlock-1 }"/>

	<c:if test="${endPage>pageCount }">
		<c:set var="endPage" value="${pageCount }"/>
	</c:if>
	<div align="center">
	<c:if test="${startPage>10 }">
		<a href="/Pis/pointcharge/pointlist.do?pageNum=${startPage-10 }">[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="/Pis/pointcharge/pointlist.do?pageNum=${i}">[${i}]</a>
	</c:forEach>
	
	<c:if test="${endPage<pageCount }">
		<a href="/Pis/pointcharge/pointlist?pageNum=${startPage+10 }">[다음]</a>
	</c:if>
	</div>
</c:if>

</body>
</html>