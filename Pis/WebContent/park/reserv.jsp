<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<style>
table {
	border-color: #31A0B4;
	color : #31A0B4;
	width : 500px;
	height : 500px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<center>
		<form method="post" name="insert">
			<table border="1" >
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
					<td><input type="text" readonly></td>
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



			</table>
			<input type="submit" value="예약하기">
		</form>
</body>
</html>