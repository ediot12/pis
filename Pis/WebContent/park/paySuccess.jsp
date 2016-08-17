<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<form action="#" method="post">
		<table>
			<tr>
				<td colspan="2">
					<h2>예약정보</h2>
				</td>
			</tr>

			<tr>
				<td>이름 :</td>
				<td><input type="text" readonly="readonly" name="name" value="${name }" size="40"></td>
			</tr>

			<tr>
				<td>전화번호 :</td>
				<td><input type="text" readonly="readonly" name="phone"
					value="${phoneNum }" size="40"></td>
			</tr>

			<tr>
				<td>주차장위치 : </td>
				<td><input type="text" readonly="readonly" name="parkloca"
					value="${parkloca }" size="40"></td>
			</tr>

			<tr>
				<td>차종 :</td>
				<td><input type="text" readonly="readonly" name="cartype"
					value="${carType }" size="40"></td>
			</tr>

			<tr>
				<td>이용기간 :</td>
				<td><input type="text" readonly="readonly" name="useday"
					value="${beginday }" size="40"></td>
			</tr>

			<tr>
				<td>도착 예정 시간 :</td>
				<td><input type="text" readonly="readonly" name="cometime"
					value="" size="40"></td>
			</tr>

			<tr>
				<td>차감 포인트 :</td>
				<td><input type="text" readonly="readonly" name="point"
					value="" size="40"></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="확인"></td>
		</table>
	</form>

</body>
</html>