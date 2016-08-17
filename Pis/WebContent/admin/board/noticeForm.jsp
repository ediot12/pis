<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<style>
	.table{
		width:1100px;
		text-align: center;
		margin : 20;
		border-color: #31A0B4;
	}
	.title{
		font-size:30px;
		text-align: left;
		height: 40px;
	}
	.title .button{
		float: right;
	}
	.button{
		background:#31A0B4;
		color:#FFFFFF;
		margin : 0 2;
		padding: 5;
	}
	.colorblack{
		color:#000000;
	}
	.color{
		color:#FFFFFF;
		background:#31A0B4;
	}
	a:HOVER {
		text-decoration:underline;
	}
	a{
		text-decoration:none;
		color : #000000;
	}
	.table tr{
		height: 35px;
		font-size:13;
	}
</style>
</head>
<body>
	<center>
	<br>
	<div class="title">
	<b>공지사항</b>
	<a href="/Pis/admin/board/noticewriteForm.do">
		<input type="button" value="글쓰기" class="button">
	</a>
	</div>
	<table class="table" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="50px" class="color">번호</td>
			<td width="700px" class="color">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 목</td>
			<td width="150px" class="color">작성일</td>
			<td width="150px" class="color">조회수</td>
		</tr>
		<c:forEach var="bdb" items="${articleList}">
		<tr>
			<td class="colorblack">
				<c:out value="${number}" />
				<c:set var="number" value="${number - 1}" />
			</td>
			<td class="colorblack"><a href="/Pis/admin/board/noticeContent.do?num=${bdb.num}&pageNum=${currentPage}">${bdb.subject}</a></td>
			<td class="colorblack">${date.format(bdb.regdt)}</td>
			<td class="colorblack">${bdb.readcount}</td>
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
			<a href="/Pis/admin/board/noticeForm.do?pageNum=${startPage - 10 }&searchn=${searchn}&search=${search}"><input type="button" value="<<이전"  class="button"></a>
		</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/Pis/admin/board/noticeForm.do?pageNum=${i}&searchn=${searchn}&search=${search}"><input type="button" value="${i}" class="button"></a>
			</c:forEach>
			
			<c:if test="${endPage < pageCount}">
				<a href="/Pis/admin/board/noticeForm.do?pageNum=${startPage + 10}&searchn=${searchn}&search=${search}"><input type="button" value="다음>>" class="button"></a>
			</c:if>
		</c:if>
	<form>
		<select name="searchn">
		<option value="0">작성자</option>
		<option value="1">제목</option>
		<option value="2">내용</option>
		</select>
			
		<input type="text" name="search" size="15" maxlength="50" /> <input type="submit" value="검색" class="button" />
	</form>
</body>
</html>