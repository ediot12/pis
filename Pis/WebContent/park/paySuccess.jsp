<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<center>
<br>

	<form action="/Pis/park/payAfter.do" method="post">
	<input type="hidden" value="${beginday }" name="beginday">
	<input type="hidden" value="${capacity }" name="capacity">
	<input type="hidden" value="${parking_code }" name="parking_code">
		
			<br>
		<div class="title"> 
			<b>예약하기 </b>
		</div> 
		<table id="ok" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td class="color">이름 </td>
				<td class="colorblack">${name }</td>
			</tr>

			<tr>
				<td class="color">전화번호 </td>
				<td class="colorblack">${phoneNum }</td>
			</tr>

			<tr>
				<td class="color">주차장이름</td>
				<td class="colorblack">${parkname }</td>
			</tr>
			<tr>
				<td class="color">주차장위치</td>
				<td class="colorblack">${parkloca }</td>
			</tr>

			<tr>
				<td class="color">차종</td>
				<td class="colorblack">${cartype }</td>
			</tr>

			<tr>
				<td class="color">이용기간</td>
				<td class="colorblack">${begintime } ~ ${outtime}</td>
			</tr>

			<tr>
				<td class="color">입차 예정 시간</td>
				<td class="colorblack">${begintime }</td>
			</tr>
			
			<tr>
				<td class="color">출차 예정 시간</td>
				<td class="colorblack">${outtime }</td>
			</tr>

			<tr>
				<td class="color">차감 포인트</td>
				<td class="colorblack">${payment }</td>
			</tr>
		</table>
		<br>
		<input class="button" type="submit" value="예약하기">
	</form>

</body>
</html>