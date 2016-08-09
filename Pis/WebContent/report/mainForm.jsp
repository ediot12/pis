<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.util.Date" %>

<!DOCTYPE html >
<html>
<head>

<title>불편 신고 게시판</title>

<style>
	.target{border-bottom: 1px solid #a5a5a5}
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



<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<center>
		<b>불편신고 목록(전체글:${count})</b><br>


		<table width="700" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left"><b>불편 신고</b></td>
			</tr>
		</table>

		
			<table boder=1 width="700" cellpadding="0" cellspacing="0"
			style="border-top: 2px solid #3d9013">
				<tr height="30">
					<td class="target" align="center" width="50">No</td>
					<td class="target" align="center" width="300">제 목</td>
					<td class="target" align="center" width="100">작성자</td>
					<td class="target" align="center" width="100">답변</td>
					<td class="target" align="center" width="100">작성일</td>
				</tr>
				<c:if test="${count != 0 }">
				 <c:forEach var="article" items="${articleList}">
					<tr height="30" align="center">
						<td>${article.num }</td>
						<td><a href="/project/report/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>


						<td>${article.writer }</td>
						<td>${article.reply }</td>
						<td>${sd.format(article.regdt)}</td>
				</c:forEach> 
			</table>
		</c:if>
		<br>
		<table width="700" class="box-button">
			<tr>
				<td align="right"><a href="/project/report/writeForm.do" class="box-gray">글 작성</a></td>
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