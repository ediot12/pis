<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
div#myReserv {
	float: left;
	display: inline-block;
	background-color: #EAEAEA;
	width: 350px;
	height: 120px;
	border: 5px solid #31A0B4;
	padding: 15px;
	margin: 15px;
	border: 5px solid #31A0B4;
	color: black;
	font-size: 10pt;
	text-align: left;
	background-color: #EAEAEA;
}
</style>
<title></title>
<link rel="stylesheet" href="style.css">

<%
	String id = (String) session.getAttribute("memId");
	System.out.println(id);
	if (id == null) {
%>

<script>
	alert("로그인 후 이용해주세요");
	location.href = "/Pis/Join/loginForm.do"; // 로그인페이지로 이동
</script>
<%
	}
%>
</head>
<body>
	<div>
		<br>
		<div align="center">
			<h2><%=id%>님의 예약 현황
			</h2>
		</div>
		<c:if test="${myreservList!=null }">
			<c:forEach var="myreservList" items="${myreservList }" begin="0">
				<div id="myReserv" align="center">
					<form name="myReserv" method="post" action="deletereserv.do">
						<table>
							<tr>주차장이름 : ${myreservList.parking_name }
							</tr>
							<br>
							<tr>주차장위치 : ${myreservList.parking_loca }
							</tr>
							<br>
							<tr>입차예정시간 : ${myreservList.beginTime }
							</tr>
							<br>
							<tr name="outtime">
								<font color="red">출차예정시간 : ${myreservList.outTime }</font>
							</tr>
							<br>
							<tr>결제금액 : ${myreservList.cost }포인트
							</tr>
							<br>

							<c:if test="${myreservList.check==true }">
								<tr>
									<input class="button" type="submit" value="예약취소">
								</tr>

							</c:if>
						</table>
						<input type="hidden" name="beginTime"
							value="${myreservList.beginTime }"> <input type="hidden"
							name="parkname" value="${myreservList.parking_name }">
					</form>
				</div>
			</c:forEach>
		</c:if>
		
	</div>
</body>
</html>