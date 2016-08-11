<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<html>
<head>
<style>


table {
	border-color: #31A0B4;
	color: #31A0B4;
	width: 600px;
	height: 500px;
}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>


<script>
	 $(function() {

		var dt = new Date();
		var y = dt.getFullYear();
		var m = dt.getMonth()+1;
		var d = dt.getDate();
		var d2 = dt.getDate()+3;
		var h = dt.getHours();
		mindt = y+"-"+m+"-"+d;
		maxdt = y+"-"+m+"-"+d2;

		$("#calendar1").datepicker({

			

			minDate : mindt,
			maxDate : maxdt,
			dateFormat : "yy-mm-dd",
				
		});

		//옵션  : 매개변수값 2번째가 옵션의 타입이며 3번째 항목은 옵션에 대한 설정 값
		$("#calendar1").datepicker("option", "showAnim", "slideDown"); //달력의 표시 형태 
		

		$("#calendar2").datepicker({

			

			minDate : mindt,
			maxDate : maxdt,
			dateFormat : "yy-mm-dd",
			onSelect : function(value){
				
				var dt2 = $("#calendar1").datepicker("getDate").getDate();
				var dt3 = value.substr(-2,2);
				if(dt2>dt3){
					alert('넌 과거로 돌아가니?');
					$("#calendar1").datepicker("setDate","");
					$("#calendar2").datepicker("setDate","");
				}
			}
				
		});	

		//옵션  : 매개변수값 2번째가 옵션의 타입이며 3번째 항목은 옵션에 대한 설정 값
		$("#calendar2").datepicker("option", "showAnim", "slideDown"); //달력의 표시 형태 

	}); 
	 
	 function checkPay(){
		 if(confirm('확인을 누르시면 포인트가 차감되며 예약됩니다. 진행하시겠습니까?')){
			 
		 }
		 else{
			 return false;
		 }
	 }
	
</script>
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