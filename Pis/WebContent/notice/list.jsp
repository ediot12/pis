<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html >
<html>
<head>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body> 
	
		<br>
		 
			<b><h1>공지사항</h1> </b>
		<center>
		<c:if test="${count!=0}">

			<table class="table" border=1 width="700" cellpadding="0" cellspacing="0"
				align="center">
				<tr height="30">
					<td class="color" align="center" width="10%">번 호</td>
					<td class="color" align="center" width="40%">제 목</td>
					<td class="color" align="center" width="20%">작성일</td>
					<td class="color" align="center" width="10%">조회수</td>
				</tr>
				<c:forEach var="article" items="${articleList}" >
					<tr>
						<td class="colorblack"><c:out value="${number}" />
						<c:set var="number" value="${number -1}" /></td>
						<td class="colorblack"><a href="/Pis/notice/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td class="colorblack">${sd.format(article.regdt)}</td>
						<td class="colorblack">${article.readcount }</td> 	
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
	
	<c:if test="${startPage>10 }">
		<a href="/Pis/notice/list.do?pageNum=${startPage-10 }">[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="/Pis/notice/list.do?pageNum=${i}"><input type="button" value="${i}"  class="button"></a>
	</c:forEach>
	
	<c:if test="${endPage<pageCount }">
		<a href="/Pis/notice/list.do?pageNum=${startPage+10 }">[다음]</a>
	</c:if>
</c:if>
<br>
<form>
<select name="searchn">
<option value="0">제목</option>
</select>

<input type="text" name="search" size="15" maxlength="50" /> 
<input class="button" type="submit" value="검색" />
</form>	
</br>
</body>
</html>