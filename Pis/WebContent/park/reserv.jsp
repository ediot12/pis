<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<html>
<head>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<script src="reserv.js"></script>

<%
	Date date = new Date();
	int hour = date.getHours();
	int min = date.getMinutes();


%>
<title>Insert title here</title>
</head>
<body>

	<center>
		<form method="post" name="insert" action="../pointcharge/Point.do" onsubmit="return checkPay()">
			<table border="1" id="ok">
				<c:forEach var="vecList" items="${reserv }" begin="0">
					<tr>
						<td>이름</td>
						<td><input type="text" readonly></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" readonly></td>
					</tr>
					<tr>
						<td>주차장이름</td>
						<td><input type="text" readonly
							value="${vecList.parking_name }" size="30"></td>
					</tr>
					<tr>
						<td>주차장위치</td>
						<td><input type="text" readonly
							value="서울특별시 ${vecList.addr }" size="30"></td>
					</tr>
					<tr>
						<td>차종</td>
						<td>소형 <input type="radio" value="소형" name="car">중형 <input
							type="radio" value="중형" name="car">대형 <input type="radio"
							value="소형" name="car"></td>
					</tr>
					<tr>
						<td>이용 날짜</td>
						<td><input type="text" id="calendar1" name="calendar1">부터
						<input type="text" id="calendar2" name="calendar2">까지
						</td>
					</tr>
					<tr>
					<td>입차 예정 시간</td>
					<td><select>
					<%for(int i=hour;i<24;i++){ %>
					<option><%=i %></option>
					<%} %>
					</select>시
					<select>
					<%for(int i=min;i<60;i++){ %>
					<option><%=i %></option>
					<%} %>
					</select>분
				
					</td>
					</tr>
					
					<tr>
					<td>입차 예정 시간</td>
					<td><select>
					<%for(int i=0;i<24;i++){ %>
					<option><%=i %></option>
					<%} %>
					</select>시
					<select>
					<%for(int i=0;i<60;i++){ %>
					<option><%=i %></option>
					<%} %>
					</select>분
				
					</td>
					</tr>
					



				</c:forEach>

			</table>
			<input type="submit" value="예약하기">

		</form>
</body>
</html>