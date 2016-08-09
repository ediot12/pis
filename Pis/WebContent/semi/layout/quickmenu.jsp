<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE>
<html>
<style>

	#quick{
		width: 120px;
		height: 30px;
		font-size: 16px;
		font-weight: bold;
		background-color: #31A0B4;
		color: black;
		text-align: center;
		
	}
	
	.menubar1{
		width: 120px;
		height: 90px;
		border-bottom: 2px solid;
		border-color: #31A0B4;
		margin: auto;
		
	}
	
	.menubar2{
		width: 120px;
		height: 90px;		
		margin: auto;
	}
	
	#menu{
		width: 120px;
		height: 300px;
		position: fixed;
		top: 40%;
		left: 90%;
		border: 2px solid;
		
	}
	
	A:link {text-decoration:none;color:#31A0B4; font-style: italic;}
	A:hover{text-decoration:yes;color:#31A0B4; font-style: italic;}
	A:visited {text-decoration:none;color:#31A0B4; font-style: italic;}

</style>

<body>
<div id="menu">
	<div id=quick align="center">퀵메뉴</div>
	<div name="reservation" align="center" class="menubar1">
		<a href="index.jsp"><img src="/semi/image/reservation.png" width="70" height="60"><br>예약하기</a>
	</div>
	<div name="poing" align="center" class="menubar1">
		<a href="index.jsp"><img src="/semi/image/point.png" width="70" height="60"><br>포인트충전</a>
	</div>
	<div name="qna" align="center" class="menubar2">
		<a href="index.jsp"><img src="/semi/image/qna.png" width="70" height="60"><br>1:1 문의</a>
	</div>

</div>

</body>
</html>