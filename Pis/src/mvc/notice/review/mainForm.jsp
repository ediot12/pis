<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>


<style>
	.box-button{
    padding-top: 20px;
    padding-bottom: 10px;
	}
	.box-gray{
	background: #555c67;
    padding: 4px 7px;
    border: 0px;
    color: #ffffff !important;
    cursor: pointer;
	}

</style>
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<center>
		<b>후기 글 목록(전체 글:${count})</b>

		<table  width="700" cellpadding="0" cellspacing="0" align="center" class="box-button">
			<tr>
				<td align="ㅣeft">사용후기</td>
				<td align="right">
				<a href="/Pis/review/writeForm.do" class="box-gray">후기 작성</a>
				</td>
				</tr>
		</table>

		
		

			<table border=1 width="700" cellpadding="0" cellspacing="0"
				align="center">
				<tr height="30">
					<td align="center" width="50">번 호</td>
					<td align="center" width="100">평가</td>
					<td align="center" width="250">제 목</td>
					<td align="center" width="100">작성자</td>
					<td align="center" width="100">작성일</td>
				</tr>
				<c:forEach var="article" items="${articleList}" >
					<tr height="30" align="center">
						<td>${article.num }</td>
						<td><c:if test="${article.score == 1 }">
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
						<td><a href="/Pis/review/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
						<td>${article.writer}</td>
						<td>${sd.format(article.regdt)}</td> 	
					</tr>
				</c:forEach>
			</table>
		
		
</body>
</html>