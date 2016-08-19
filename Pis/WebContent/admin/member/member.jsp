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
		<b>회원관리</b>
	</div>
	<table class="table" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td width="5%" class="color">번호</td>
			<td width="15%" class="color">아이디</td>
			<td width="15%" class="color">이름</td>
			<td width="15%" class="color">회원등급</td>
			<td width="15%" class="color">거주자승인</td>
			<td width="10%" class="color">할인율</td>
			<td width="15%" class="color">포인트잔액</td>
			<td width="10%" class="color">상세내역</td>
		</tr>
		<c:forEach var="mdb" items="${articleList}">
		<tr>
			<td class="colorblack"><c:out value="${number}" />
				<c:set var="number" value="${number - 1}" /></td>
			<td class="colorblack">${mdb.id}</td>
			<td class="colorblack">${mdb.name}</td>
			<td class="colorblack">${mdb.grade}</td>
			<td class="colorblack"><c:if test="${mdb.resident==1}">
					<c:if test="${mdb.checked=='거주자 미 승인'}">
						<form action="/Pis/admin/member/memberupdate.do?id=${mdb.id}" method="post">
							거주자승인요청<input type="submit" value="승인" class="button">
						</form>
					</c:if>
					<c:if test="${mdb.checked=='거주자 승인'}">
						거주자승인완료
					</c:if>
				</c:if>
				<c:if test="${mdb.resident==2}">
				비거주자</c:if>
				</td>
			<td class="colorblack">${mdb.discount}</td>
			<td class="colorblack">${mdb.point}</td>
			<td class="colorblack"><a href="/Pis/admin/member/memberInfo.do?id=${mdb.id}"><input type="button" value="정보확인" class="button"></a></td>
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
				<a href="/Pis/admin/member/member.do?pageNum=${startPage - 10 }&searchn=${searchn}&search=${search}"><input type="button" value="<<이전"  class="button"></a>
			</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/Pis/admin/member/member.do?pageNum=${i}&searchn=${searchn}&search=${search}"><input type="button" value="${i}" class="button"></a>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<a href="/Pis/admin/member/member.do?pageNum=${startPage + 10}&searchn=${searchn}&search=${search}"><input type="button" value="다음>>" class="button"></a>
				</c:if>
			</c:if>
		<form>
			<select name="searchn">
			<option value="0">아이디</option>
			<option value="1">이름</option>
			<option value="2">거주자승인</option>
			</select>
				
			<input type="text" name="search" size="15" maxlength="50" /> <input type="submit" value="검색" class="button" />
		</form>
</body>
</html>