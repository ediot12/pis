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
					<tr>
					<!-- 글번호 -->
						<td><c:out value="${number}" />
						<c:set var="number" value="${number -1}" /></td>
						<!-- 문의 유형 -->
						<td class="colorblack">${article.kind }</td>
						 <!-- 제목클릭시 이동할 페이지 설정 -->		
						<td class="colorblack"><a href="/Pis/question/questionContent.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<!-- 답변 여부 -->
						<td class="colorblack">${article.checked}</td>
						<!-- 날짜  -->
						<td class="colorblack">${date.format(article.regdt)}</td>
				</c:forEach>
				</c:if> 
			</table>
			<!-- 페이지 번호 설정 -->
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
		<a href="/Pis/qeustion/mainForm.do?pageNum=${startPage-10 }">[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="/Pis/qeustion/mainForm.do?pageNum=${i}"><input type="button" value="${i}"  class="button"></a>
	</c:forEach>
	
	<c:if test="${endPage<pageCount }">
		<a href="/Pis/qeustion/mainForm.do?pageNum=${startPage+10 }">[다음]</a>
	</c:if>
</c:if>

</body>
</html>