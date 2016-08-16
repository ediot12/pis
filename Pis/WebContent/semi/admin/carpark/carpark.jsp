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
	.color td{
		background:#31A0B4;
		color:#FFFFFF;
	}
	.table tr{
		height: 35px;
		font-size:13;
	}
	.colorblack{
		color:#000000;
	}
</style>
</head>
<body>
	<center>
	<b>- 전체 주차장 :${count} -</b>
	<div class="title">
	<b>주차장관리</b>
		<a href="/Pis/semi/admin/carpark/carparkInput.do">
			<input type="button" value="주차장등록" class="button">
		</a>
	</div>
	<table class="table" border="1" cellpadding="0" cellspacing="0" >
		<tr class="color">
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
			<td width="250px" class="colorblack">${parkList.parking_name}</td>
			<td width="350px" class="colorblack">${parkList.addr}</td>
			<td width="150px" class="colorblack">${parkList.tel}</td>
			<td width="50px" class="colorblack">${parkList.weekday_begin_time}~${parkList.weekday_end_time}</td>
			<td width="50px" class="colorblack">${parkList.weekend_begin_time}~${parkList.weekend_end_time}</td>
			<td width="130px" class="colorblack">${parkList.operation_rule_nm}</td>
			<td width="20px" class="colorblack">${parkList.capacity2}</td>
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
				<a href="/Pis/semi/admin/carpark/carpark.do?pageNum=${startPage - 10 }&searchn=${searchn}&search=${search}"><input type="button" value="<<이전"  class="button"></a>
			</c:if>
			
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/Pis/semi/admin/carpark/carpark.do?pageNum=${i}&searchn=${searchn}&search=${search}"><input type="button" value="${i}" class="button"></a>
				</c:forEach>
			
			<c:if test="${endPage < pageCount}">
				<a href="/Pis/semi/admin/carpark/carpark.do?pageNum=${startPage + 10}&searchn=${searchn}&search=${search}"><input type="button" value="다음>>" class="button"></a>
			</c:if>
		</c:if>
		<br>
		<br>
		<form>
			<select name="searchn">
			<option value="0">주차장이름</option>
			<option value="1">주차장주소</option>
			</select>
				
			<input type="text" name="search" size="15" maxlength="50" />
			<input type="submit" value="검색" class="button"/>
		</form>
		<br>
</body>
</html>