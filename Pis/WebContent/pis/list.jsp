<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>

<!DOCTYPE html >
<html>
<head>
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<center>
		<b>글목록(전체 글:${count})</b>

		<table width="700" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td align="ㅣeft">공지사항</td>
				<td align="right">
				
				<a href="/project/pis/writeForm.do">글쓰기</a>
				</td>
				</tr>
		</table>

		<c:if test="${count==0}">
			<table width="700" border="1" cellpadding="0" cellspacing="0"
				align="center">
				<tr>
					<td align="center">게시판에 저장된 글이 없습니다.</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${count!=0}">

			<table boder=1 width="700" cellpadding="0" cellspacing="0"
				align="center">
				<tr height="30">
					<td align="center" width="50">번 호</td>
					<td align="center" width="250">제 목</td>
					<td align="center" width="100">작성자</td>
					<td align="center" width="150">작성일</td>
					<td align="center" width="50">조회수</td>
				</tr>
				<c:forEach var="article" items="${articleList}" >
					<tr height="30" align="center">
						<!-- <td align="center" width="50"> -->
						
						<%-- <c:out value="${number}" />
						<c:set var="number" value="${number -1}" /></td> --%>
						<td>${article.num }</td>
						<td><a href="/project/pis/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						
						 
						<td>${article.writer }</td>
						<td>${article.regdt }</td>
						<td>${article.readcount }</td> 	
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
		<a href="/project/pis/list.do?pageNum=${startPage-10 }">[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="/project/pis/list.do?pageNum=${i}">[${i}]</a>
	</c:forEach>
	
	<c:if test="${endPage<pageCount }">
		<a href="/project/pis/list.do?pageNum=${startPage+10 }">[다음]</a>
	</c:if>
</c:if>
<br>
<form>
<select name="searchn">
<option value="0">작성자</option>
<option value="1">제목</option>
<option value="2">내용</option>
</select>

<input type="text" name="search" size="15" maxlength="50" /> 
<input type="submit" value="검색" />
</form>	
</br>
</body>
</html>