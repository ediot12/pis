<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="reserv.js"></script>
<script>
	var date = new Date();
	var hour = date.getHours();
	var min = (parseInt((date.getMinutes()/10))+1)*10;
	if(min>50){
		hour = hour + 1;
		min = 0;
	}
	$(function() {
		
		for (var i = hour; i < 24; i++) {
			$("#test").append("<option value='"+i+"'>" + i + "</option>");
		}
		for (var i = min; i < 60; i=i+10) {
			$("#test2").append("<option value='"+i+"'>" + i + "</option>");
		}

	});
	
	function checkTime(obj){
		if(obj.value!=hour){
			$("#test2").find("option").remove();
			for (var i = 0; i < 60; i=i+10) {
				
				$("#test2").append("<option value='"+i+"'>" + i + "</option>");
			}
		}
		if(obj.value==date.getHours()){
			$("#test2").find("option").remove();
			for (var i = min; i < 60; i=i+10) {
				$("#test2").append("<option value='"+i+"'>" + i + "</option>");
			}
		}
		
		
	}
</script>

<%
	if (session.getAttribute("memId") == null) {
%>
<script>
	alert('로그인이 필요합니다.');
	location.replace("/Pis/Join/loginForm.do");
</script>

<%
	}
%>
<title>Insert title here</title>
</head>
<body>

	<center>
		<form method="post" name="insert" action="/Pis/park/paySuccess.do"
			onsubmit="return checkPay()">
			<table border="1" id="ok">
				<c:if test="${reserv!=null }">
					<c:forEach var="vecList" items="${reserv }" begin="0">
						<tr>
							<td>이름</td>
							<td><input type="text" readonly name="name" value="${name }"></td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td><input type="text" readonly name="phone"
								value="${phone }"></td>
						</tr>
						<tr>
							<td>주차장이름</td>
							<td><input type="text" readonly
								value="${vecList.parking_name }" size="30" name="parkname"></td>
						</tr>
						<tr>
							<td>주차장위치</td>
							<td><input type="text" readonly
								value="서울특별시 ${vecList.addr }" size="30" name="parkloca"></td>
						</tr>
						<tr>
							<td>차종</td>
							<td>소형 <input type="radio" value="소형" name="car">중형
								<input type="radio" value="중형" name="car">대형 <input
								type="radio" value="소형" name="car"></td>
						</tr>
						<tr>
							<td>이용 날짜</td>
							<td><input type="text" id="calendar1" name="calendar1">부터
								<input type="text" id="calendar2" name="calendar2">까지</td>
						</tr>
						<tr>
							<td>입차 예정 시간</td>
							<td>
							<select id="test" onchange="checkTime(this)">
							</select>시
							<select id="test2">
							</select>분</td>
						</tr>

						<tr>
							<td>출차 예정 시간</td>
							<td><select name="outhour">
									<%
										for (int i = 0; i < 24; i++) {
									%>
									<option value=<%=i%>><%=i%></option>
									<%
										}
									%>
							</select>시 <select name="outmin">
									<%
										for (int i = 0; i < 60; i = i + 10) {
									%>
									<option value=<%=i%>><%=i%></option>
									<%
										}
									%>
							</select>분</td>
						</tr>

						<tr>
							<td>기본료(30분당)</td>
							<td><input type="text" readonly value=" ${vecList.rates }원"
								size="30" name="rates"></td>
						</tr>



						<input type="hidden" name="pay_nm" value="${vecList.pay_nm }">

					</c:forEach>
				</c:if>
			</table>

			<input type="submit" value="예약하기">

		</form>
</body>
</html>