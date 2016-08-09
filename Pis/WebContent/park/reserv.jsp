<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
table {
	border-color: #31A0B4;
	color: #31A0B4;
	width: 500px;
	height: 500px;
}
</style>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>


<script>
	$(function() {

		$("#calendar").datepicker();

		//옵션  : 매개변수값 2번째가 옵션의 타입이며 3번째 항목은 옵션에 대한 설정 값
		$("#calendar").datepicker("option", "dateFormat", "yy-mm-dd"); //데이터 포맷으로 날짜의 반환 타입을 지정
		$("#calendar").datepicker("option", "showAnim", "slideDown"); //달력의 표시 형태

	});
</script>
<title>Insert title here</title>
</head>
<body>

	<center>
		<form method="post" name="insert">
			<table border="1">
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
						<td>주차장위치</td>
						<td><input type="text" readonly
							value="${vecList.parking_name }" size="30"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" readonly></td>
					</tr>
					<tr>
						<td>차종</td>
						<td>소형 <input type="radio" value="소형" name="car">중형 <input
							type="radio" value="중형" name="car">대형 <input type="radio"
							value="소형" name="car"></td>
					</tr>
					<tr>
						<td>이용 날짜</td>
						<td><input type="text" id="calendar" name="calendar"></td>
					</tr>



				</c:forEach>

			</table>
			<input type="submit" value="예약하기">

		</form>
</body>
</html>