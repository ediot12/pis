<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="../../style.css" rel="stylesheet" type="text/css"> 
</head>
<body>
<center>
	<br>
	<div class="title">
	<b>불편신고</b>
	</div>
	<br>
	<table class="table" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td class="color" width="5%">번호</td>
			<td class="color" width="20%">불편유형</td>
			<td class="color" width="50%">제 목</td>
			<td class="color" width="15%">작성자</td>
			<td class="color" width="10%">작성일</td>
		</tr>
		<c:forEach var="rdb" items="${articleList}">
			<tr>
				<td class="colorblack">
					<c:out value="${number}" />
					<c:set var="number" value="${number - 1}" />
				</td>
				<td class="colorblack">${rdb.kind}</td>
				<td class="colorblack"><a href="/Pis/admin/board/reportContent.do?num=${rdb.num}&pageNum=${currentPage}">${rdb.subject}</a></td>
				<td class="colorblack">${rdb.writer}</td>
				<td class="colorblack">${date.format(rdb.regdt)}</td>
			</tr>
		</c:forEach>
	</table>
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
				<a href="/Pis/semi/admin/board/report.do?pageNum=${startPage - 10 }&searchn=${searchn}&search=${search}"><input type="button" value="<<이전"  class="button"></a>
			</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/Pis/semi/admin/board/report.do?pageNum=${i}&searchn=${searchn}&search=${search}"><input type="button" value="${i}" class="button"></a>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<a href="/Pis/semi/admin/board/report.do?pageNum=${startPage + 10}&searchn=${searchn}&search=${search}"><input type="button" value="다음>>" class="button"></a>
				</c:if>
			</c:if>
		<form>
			<select name="searchn">
			<option value="결제">결제</option>
			<option value="불친절">불친절</option>
			<option value="주차 불편">주차 불편</option>
			<option value="불법주정차">불법주정차</option>
			<option value="기타">기타</option>
			</select>
				
			<input type="text" name="search" size="15" maxlength="50" /> <input type="submit" value="검색" class="button" />
		</form>
</body>
</html>