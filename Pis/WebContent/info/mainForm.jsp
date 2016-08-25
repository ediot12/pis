<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>


<!DOCTYPE html >
<html>
<head>
<!-- css 적용 -->
<link href="../style.css" rel="stylesheet" type="text/css">
<title></title>
</head>
<body>
	<center> 
		<br>
		<div class="title">
			<b>주차장 제보</b>
				<a href="/Pis/info/writeForm.do"> <!-- 제보하기 버튼 누르면 이동 페이지 링크 -->
				<input type="button" value="제보하기" class="button"></a>
			</div>
		<table class="table" border=1 width="800" cellpadding="0"
			cellspacing="0">
			<!-- 테이블 헤더 부분 -->
			<tr height="30">
				<td class="color" align="center" width="50">번호</td>
				<td class="color" align="center" width="200">제 목</td>
				<td class="color" align="center" width="300">주 소</td>
				<td class="color" align="center" width="100">작성자</td>
				<td class="color" align="center" width="100">작성일</td>
			</tr>
			<!-- 입력한 글이 있다면 for문 돌림 -->
			<c:if test="${count != 0 }">
				<c:forEach var="article" items="${articleList}">
					<tr>
					<!-- 해당 글번호 -->
						<td class="colorblack"><c:out value="${number}" />
						<c:set var="number" value="${number -1}" /></td>
						<!-- 제목누를시 이동할 페이지 링크 .... 이동하면서 글번호와 페이지 번호  -->
						<td class="colorblack"><a
							href="/Pis/info/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<!-- db에 입력된 주소 -->
						<td class="colorblack">${article.address}</td>
						<!-- db에 입력된 작성자 -->
						<td class="colorblack">${article.writer }</td>
						<!-- db에 입력된 날짜 -->
						<td class="colorblack">${sd.format(article.regdt)}</td>
				</c:forEach>
				</c:if>
		</table>
		
		<!-- 한페이지 번호 설정 -->
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
		<a href="/Pis/info/mainForm.do?pageNum=${startPage-10 }">[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="/Pis/info/mainForm.do?pageNum=${i}"><input type="button" value="${i}"  class="button"></a>
	</c:forEach>
	
	<c:if test="${endPage<pageCount }">
		<a href="/Pis/info/mainForm.do?pageNum=${startPage+10 }">[다음]</a>
	</c:if>
</c:if>
</body>
</html>