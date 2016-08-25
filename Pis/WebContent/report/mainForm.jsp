<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>

<!DOCTYPE html >
<html>
<head>
<title>불편 신고 게시판</title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head> 
<body>
<center> 
		<br>
		<div class="title">
			<b>불편 신고 관리 </b>
				<a href="/Pis/report/writeForm.do">
				<input type="button" value="글 작성" class="button">
				</a>
			</div>
			<table class="table" border=1 cellpadding="0" cellspacing="0">
				<tr height="30">
					<td class="color" align="center" width="10%">번호</td>
					<td class="color" align="center" width="20%">불편유형</td>
					<td class="color" align="center" width="40%">제 목</td>
					<td class="color" align="center" width="20%">작성자</td>
					<td class="color" align="center" width="20%">작성일</td>
				</tr>
				<c:if test="${count != 0 }">
				 <c:forEach var="article" items="${articleList}">
					<tr>
						<td class="colorblack"><c:out value="${number}" />
						<c:set var="number" value="${number -1}" /></td>
						<!-- 불편 유형 -->
						<td class="colorblack">${article.type }</td>
						<td class="colorblack"><a href="/Pis/report/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td class="colorblack">${article.writer }</td>
						<td class="colorblack">${sd.format(article.regdt)}</td>
				</c:forEach> 
				</c:if>
			</table>
		
		<br>
		
		<c:if test="${count > 0 }">
			<c:set var="pageCount"
				value="${count/pageSize+(count%pageSize==0 ? 0 : 1) }" />
			<c:set var="pageBlock" value="${10 }" />
			<fmt:parseNumber var="result" value="${currentPage/10 }"
				integerOnly="true" />
			<c:set var="startPage" value="${result*10+1 }" />
			<c:set var="endPage" value="${startPage+pageBlock-1 }" />

			<c:if test="${endPage>pageCount }">
				<c:set var="endPage" value="${pageCount }" />
			</c:if>

			<c:if test="${startPage>10 }">
				<a href="/Pis/report/mainForm.do?pageNum=${startPage-10 }">[이전]</a>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/Pis/report/mainForm.do?pageNum=${i}"><input type="button" value="${i}"  class="button"></a>
			</c:forEach>

			<c:if test="${endPage<pageCount }">
				<a href="/Pis/report/mainForm.do?pageNum=${startPage+10 }">[다음]</a>
			</c:if>
		</c:if>
</body>
</html>