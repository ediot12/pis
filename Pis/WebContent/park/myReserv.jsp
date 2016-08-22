<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
div#myReserv {
	float: left;
	display: inline-block;
	background-color: #EAEAEA;
	width: 300px;
	height: 100px;
	border: 5px solid #31A0B4;
	padding: 15px;
	margin: 15px;
	border: 5px solid #31A0B4;
	color: black;
	font-size: 10pt;
	background-color: #EAEAEA;
}
</style>
<%
	String id = (String) session.getAttribute("memId");
%>
<title></title>
</head>
<body>
	<div>
		<br>
		<div align="center">
		<h2><%=id%>님의 예약 현황</h2>
		</div>
		<c:if test="${myreservList!=null }">
			<c:forEach var="myreservList" items="${myreservList }" begin="0">

				<div id="myReserv" align="center"> 
					<form name="myReserv" onsubmit="checkoutTime()">
					<table >				
					<tr align="left">주차장이름 : ${myreservList.parking_name }</tr><br>
					<tr align="left">주차장위치 : ${myreservList.parking_loca }</tr><br>
					<tr align="left">입차예정시간 : ${myreservList.beginTime }</tr><br>
					<tr align="left" name="outtime"><font color="red">출차예정시간 : ${myreservList.outTime }</font></tr><br> 
					<tr align="left">결제금액 : ${myreservList.cost }포인트</tr><br>
					<tr align="right"><input type="submit" value="예약취소"></tr>					
					</table>
					</form>
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>