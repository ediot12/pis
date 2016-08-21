<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
div#myReserv {
	float: left;
	display: inline-block;
	background-color: lightgrey;
	width: 300px;
	height: 100px;
	border: 15px solid green;
	padding: 15px;
	margin: 15px;
	border: 15px solid green;
	color: black;
	font-size: 9pt;
	background-color: lightgrey;
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
		<%=id%>님의 예약 현황
		<c:if test="${myreservList!=null }">
			<c:forEach var="myreservList" items="${myreservList }" begin="0">

				<div id="myReserv">
					주차장이름 : ${myreservList.parking_name }<br> 주차장위치 :
					${myreservList.parking_loca }<br> 입차예정시간 :
					${myreservList.beginTime }<br> <font color="red">출차예정시간
						: ${myreservList.outTime }</font><br> 결제금액 : ${myreservList.cost }
					포인트
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>