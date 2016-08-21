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

	var wdopen = ${wdopen};
	var wdclose = ${wdclose};
	var weopen = ${weopen};
	var weclose = ${weclose};
	var today = date.getDay();//오늘
	var close;

	var hour = date.getHours();//이것은 현재시간

	if (today == 0 || today == 6) {//오늘이 일요일이거나 토요일이면
		if (hour < wdopen) {//만약 현재시간보다 오픈시간이 더 크면 보여줄 시간은 오픈시간으로.
			hour = wdopen;
		}
		close = wdclose;
	}
	if (today == 1 || today == 2 || today == 3 || today == 4 || today == 5) {//오늘이 평일이면
		if (hour < weopen) {//만약 현재시간보다 오픈시간이 더 크면 보여줄 시간은 오픈시간으로.
			hour = weopen;
		}
		close = weclose;
	}

	var min = (parseInt((date.getMinutes() / 10)) + 1) * 10;

	if (min > 50) {
		hour = hour + 1;
		min = 0;
	}

	$(function() {

		for (var i = hour; i <= close; i++) {
			$("#inhour").append("<option value='"+i+"'>" + i + "</option>");
		}
		for (var i = min; i < 60; i = i + 10) {
			$("#inmin").append("<option value='"+i+"'>" + i + "</option>");
		}
		for (var i = hour; i <= close; i++) {
			$("#outhour").append("<option value='"+i+"'>" + i + "</option>");
		}
		for (var i = min; i < 60; i = i + 10) {
			$("#outmin").append("<option value='"+i+"'>" + i + "</option>");
		}

	});

	function checkTime(obj) {
		var outhour = document.getElementById('outhour').value;

		if (obj.value != hour) {
			$("#inmin").find("option").remove();
			for (var i = 0; i < 60; i = i + 10) {

				$("#inmin").append("<option value='"+i+"'>" + i + "</option>");
			}
		}
		if (obj.value == date.getHours()) {
			$("#inmin").find("option").remove();
			for (var i = min; i < 60; i = i + 10) {
				$("#inmin").append("<option value='"+i+"'>" + i + "</option>");
			}
		}
		
		if(obj.value == close){
			$("#inmin").find("option").remove();
			$("#inmin").append("<option value='"+0+"'>" + 0 + "</option>");
			$("#outmin").find("option").remove();
			$("#outmin").append("<option value='"+0+"'>" + 0 + "</option>");
		}

		if (obj.value > outhour) {
			document.getElementById('outhour').value = obj.value;
			checkOutTime(document.getElementById('outhour'));
		}

	}

	function checkOutTime(obj) {

		var inhour = document.getElementById('inhour').value;

		if (obj.value != hour) {
			$("#outmin").find("option").remove();
			for (var i = 0; i < 60; i = i + 10) {

				$("#outmin").append("<option value='"+i+"'>" + i + "</option>");
			}
		}
		if (obj.value == date.getHours()) {
			$("#outmin").find("option").remove();
			for (var i = min; i < 60; i = i + 10) {
				$("#outmin").append("<option value='"+i+"'>" + i + "</option>");
			}
		}
		
		if(obj.value == close){
			$("#outmin").find("option").remove();
			$("#outmin").append("<option value='"+0+"'>" + 0 + "</option>");
		}

		if (obj.value < inhour) {
			document.getElementById('inhour').value = obj.value;
			checkTime(document.getElementById('inhour'));
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
	Date date = new Date();
%>
<title></title>
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
							<td><input type="text" id="calendar1" name="calendar1"></td>

						</tr>
						<tr>
							<td>입차 예정 시간</td>
							<td><select name="inhour" id="inhour"
								onchange="checkTime(this)">
							</select>시 <select name="inmin" id="inmin">
							</select>분</td>
						</tr>


						<tr>
							<td>출차 예정 시간</td>
							<td><select name="outhour" id="outhour"
								onchange="checkOutTime(this)">
							</select>시 <select name="outmin" id="outmin">
							</select>분</td>
						</tr>

						<%-- <tr>
							<td>출차 예정 시간</td>
							<td><select name="outhour">
									<%
										for (int i = date.getHours(); i < 24; i++) {
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
						</tr> --%>

						<tr>
							<td>기본료(30분당)</td>
							<td><input type="text" readonly value="${vecList.rates }"
								size="30" name="rates"></td>
						</tr>



						<input type="hidden" name="pay_nm" value="${vecList.pay_nm }">

					</c:forEach>
				</c:if>
			</table>

			<input type="submit" value="확인">

		</form>
</body>
</html>