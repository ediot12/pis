<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
<title>포인트 사용 내역</title>
</head>
<body>
<!-- <h1>포인트 사용내역</h1> -->
<c:if test="${count == 0 }">
<table width="750" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
			포인트 사용 내역이 없습니다.
		</td>
	</tr>
</table>
</c:if>

<c:if test="${count > 0 }">
<table border="1" width="750" cellpadding="0" cellspacing="0" align="center">
	<p><h1>포인트 사용내역</h1></p>
	<tr>
		<td align="center" width="50">번호</td>
		<td align="center" width="200">주차장명</td>
		<td align="center" width="200">내역</td>
		<td align="center" width="100">충전포인트</td>
		<td align="center" width="100">사용포인트</td>
		<td align="center" width="100">사용일</td>
	</tr>
	<c:forEach var="article" items="${articleList}">
	<tr height="30">
		<td align="center" width="50">
	<c:out value="${number }"/>
	<c:set var="number" value="${number-1 }"/>
		</td>
		<td width="250">
	<c:if test="${article.re_level >0 }">
		<img src="images/level.gif" width="${5 * article.re_level }" height="16">
		<img src="images/re.gif">
	</c:if>
	<c:if test="${article.re_level == 0 }">
		<img src="images/level.gif" width="${5 * article.re_level }" height="16">
	</c:if>
		<a href="/JunJSP/MVC/content.do?num=${article.num }&pageNum=${currentPage}">
		${article.subject }</a>
	<c:if test="${article.readcount >= 20 }">
		<img src="images/hot.gif" border="0" height="16">
	</c:if>
		</td>
		<td align="center" width="100">
			<a href="mailto:${article.email}">${article.writer}</a>
		</td>
    	<td align="center"  width="150">${article.reg_date}
		</td>
   		<td align="center"  width="50">${article.readcount}</td>
    	<td align="center" width="100" >${article.ip}</td>
  	</tr>
  	</c:forEach>
</table>
	</c:if>

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
        <a href="/JunJSP/MVC/list.do?pageNum=${startPage - 10 }">[이전]</a>
   </c:if>

   <c:forEach var="i" begin="${startPage}" end="${endPage}">
       <a href="/JunJSP/MVC/list.do?pageNum=${i}">[${i}]</a>
   </c:forEach>

   <c:if test="${endPage < pageCount}">
        <a href="/JunJSP/MVC/list.do?pageNum=${startPage + 10}">[다음]</a>
   </c:if>
</c:if>

</center>
</body>
</html>