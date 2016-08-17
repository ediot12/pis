<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>예약확인 누르면 포인트 차감</title>
</head>
<body>
<form name="payment" action="reserv_pointment.do">
<table width="420" border="1">
	<tr>
		<td width="220">예약번호</td>
		<td>넘어오는값</td>
	</tr>
	<tr>
		<td width="220">아이디</td>
		<td>id</td>
	</tr>
	<tr>
		<td width="220">포인트잔액</td>
		<td>currentpoint</td>
	</tr>
	<tr>
		<td width="220">사용포인트</td>
		<td>use point</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="yellow"></td>
	</tr>	
	<tr>
		<td width="220">사용 후 잔여 포인트</td>
		<td>point</td>
	</tr>
	
</table>
	<input type="submit" value="결제진행">
</form>
</body>
</html>