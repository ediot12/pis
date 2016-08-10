<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<style>
	.parktable{
		width:1100px;
		text-align: center;
		margin : 20;
		border-color: #31A0B4;
	}
	.parktitle{
		font-size:30px;
		text-align: left;
		height: 40px;
	}
	.parktitle .parkbutton{
		float: right;
	}
	.parkbutton{
		background:#31A0B4;
		color:#FFFFFF;
		margin : 0 2;
		padding: 5;
	}
	.carparkcolor td{
		background:#31A0B4;
		color:#FFFFFF;
	}
	.parktable tr{
		height: 35px;
		font-size:13;
	}
</style>
</head>
<body>
	<center>
	<b>- 전체 주차장 :${count} -</b>
	<div class="parktitle">
		<form>
		<b>주차장관리</b>
			<select name="searchn">
			<option value="0">주차장이름</option>
			<option value="1">주차장주소</option>
			</select>
				
			<input type="text" name="search" size="15" maxlength="50" />
			<input type="submit" value="검색" />
		<a href="/Pis/semi/admin/carpark/carparkInput.do">
			<input type="button" value="주차장등록" class="parkbutton">
		</a>
		</form>
	</div>
	<table class="parktable" border="1" cellpadding="0" cellspacing="0" >
		<tr class="carparkcolor">
			<td width="250px" height="30px">주차장명</td>
			<td width="350px" height="30px">주차장 주소</td>
			<td width="150px" height="30px">전화번호</td>
			<td width="50px" height="30px">주중</td>
			<td width="50px" height="30px">주말</td>
			<td width="130px" height="30px">운영구분</td>
			<td width="100px" height="30px">이용대수</td>
		</tr>
		<c:forEach var="parkList" items="${parkList}">
		<tr>
			<td width="250px">${parkList.parking_name}</td>
			<td width="350px">${parkList.addr}</td>
			<td width="150px">${parkList.tel}</td>
			<td width="50px">${parkList.weekday_begin_time}~${parkList.weekday_end_time}</td>
			<td width="50px">${parkList.weekend_begin_time}~${parkList.weekend_end_time}</td>
			<td width="130px">${parkList.operation_rule_nm}</td>
			<td width="20px">${parkList.capacity2}</td>
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
				<a href="/Pis/semi/admin/carpark/carpark.do?pageNum=${startPage - 10 }&searchn=${searchn}&search=${search}"><input type="button" value="<<이전"  class="parkbutton"></a>
			</c:if>
			
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/Pis/semi/admin/carpark/carpark.do?pageNum=${i}&searchn=${searchn}&search=${search}"><input type="button" value="${i}" class="parkbutton"></a>
				</c:forEach>
			
			<c:if test="${endPage < pageCount}">
				<a href="/Pis/semi/admin/carpark/carpark.do?pageNum=${startPage + 10}&searchn=${searchn}&search=${search}"><input type="button" value="다음>>" class="parkbutton"></a>
			</c:if>
		</c:if>
</body>
</html>