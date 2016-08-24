<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<center>
	<br>
		<div class="title">
			<b>사용후기</b>
				<a href="/Pis/review/writeForm.do">
				<input type="button" value="글작성" class="button"></a>
				</div>
				
		
			<table class="table" border=1 width="700" cellpadding="0" cellspacing="0">
				<tr height="30">
					<td class="color" align="center" width="10%">번 호</td>
					<td class="color" align="center" width="20%">평가</td>
					<td class="color" align="center" width="40%">제 목</td>
					<td class="color" align="center" width="20%">작성자</td>
					<td class="color" align="center" width="20%">작성일</td>
				</tr>
				<c:forEach var="article" items="${articleList}" >
					<tr>
						<td><c:out value="${number}" />
						<c:set var="number" value="${number -1}" /></td>
						
						<!-- c:if 태그로 글 작성시 서버로 전송된 값 비교하여 맞는 조건문 실행 -->
						<td class="colorblack">
								<c:if test="${article.score == 1 }">
									<img src="/Pis/review/img/rating02.png">
								</c:if> <c:if test="${article.score == 2 }">
									<img src="/Pis/review/img/rating04.png">
								</c:if> <c:if test="${article.score == 3 }">
									<img src="/Pis/review/img/rating06.png">
								</c:if> <c:if test="${article.score == 4 }">
									<img src="/Pis/review/img/rating08.png">
								</c:if> <c:if test="${article.score == 5 }">
									<img src="/Pis/review/img/rating10.png">
								</c:if>
							</td>
						<td class="colorblack"><a href="/Pis/review/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td class="colorblack">${article.writer}</td>
						<td class="colorblack">${sd.format(article.regdt)}</td> 	
					</tr>
				</c:forEach>
			</table>
			<c:if test="${count > 0 }">
	<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0 : 1) }"/>
	<c:set var="pageBlock" value="${10 }"/>
	<fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true"/>
	<c:set var="startPage" value="${result*10+1 }"/>
	<c:set var="endPage" value="${startPage+pageBlock-1 }"/>

	<c:if test="${endPage>pageCount }">
		<c:set var="endPage" value="${pageCount }"/>
	</c:if>
	
	<c:if test="${startPage>10 }" >
		<a href="/Pis/review/mainForm.do?pageNum=${startPage-10 }">
		<input type="button" value="<<이전"  class="button"></a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="/Pis/review/mainForm.do?pageNum=${i}"><input type="button" value="${i}"  class="button"></a>
	</c:forEach>
	
	<c:if test="${endPage<pageCount }">
		<a href="/Pis/review/mainForm.do?pageNum=${startPage+10 }">[다음]</a>
	</c:if>
</c:if>
</body>
</html>