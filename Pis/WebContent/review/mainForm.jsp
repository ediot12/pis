<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>



<!DOCTYPE html >
<html>
<head>
<style>
	.box-button{
	text-align: right;
    padding-top: 20px;
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
		<b>사용후기 목록(전체글:${count})</b><br>

		<c:if test="${count==0}">

			<table width="700" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">사용자 후기 글이 없습니다.</td>
				</tr>
			</table>
		</c:if>

		<table width="650" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left"><b>사용자 후기</b></td>
			</tr>
		</table>

		<c:if test="${count != 0}">
			<table>
				<c:forEach var="article" items="${articleList}">
					<table>
						<tr height="30">
							<td width="110">No:${article.num}</td>
							<td width="250">제 목:
							<a href="/project/review/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>

							<td width="180">작성자:${article.writer}</td>
							<td width="100">조회수:${article.readcount}</td>
						</tr>
					</table>

					<table>
						<tr>
							<td width="650" align="left">평 가 : 
							<c:if test="${article.score == 1 }">
									<img src="/project/review/img/rating02.png">
								</c:if> <c:if test="${article.score == 2 }">
									<img src="/project/review/img/rating04.png">
								</c:if> <c:if test="${article.score == 3 }">
									<img src="/project/review/img/rating06.png">
								</c:if> <c:if test="${article.score == 4 }">
									<img src="/project/review/img/rating08.png">
								</c:if> <c:if test="${article.score == 5 }">
									<img src="/project/review/img/rating10.png">
								</c:if>
							</td>
						</tr>
					</table>

					<tr>
						<td><textarea rows="8" cols="80" readonly="readonly">${article.content}</textarea></td>
					</tr>

					<table >
						<tr>
							<td width="650" align="left">등록일:${sd.format(article.regdt)}</td>
						</tr>

					</table>
				</c:forEach>
			</table>
		</c:if>




		<table width="600" class="box-button">
			<tr>
				<td align="right"><a href="/project/review/writeForm.do" class="box-gray">후기
						글 작성</a></td>
			</tr>
		</table>
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
				<a href="/project/review/mainForm.do?pageNum=${startPage-10 }">[이전]</a>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/project/review/mainForm.do?pageNum=${i}">[${i}]</a>
			</c:forEach>

			<c:if test="${endPage<pageCount }">
				<a href="/project/review/mainForm.do?pageNum=${startPage+10 }">[다음]</a>
			</c:if>
		</c:if>
</body>
</html>