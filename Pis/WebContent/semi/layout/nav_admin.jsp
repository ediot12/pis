<%@ page contentType="text/html; charset=UTF-8"%>
<head>
<style type="text/css">
	#nav{
		border-top: 4px solid #31A0B4;
		border-bottom: 4px solid #31A0B4;
		font-size : 20px;
		color:#31A0B4;
		font-style:italic;
		text-align: center;
	}
	#nav > ul{
		list-style:none;
	}
	#nav > ul > li{
		display:inline;
		text-transform:uppercase;
		padding:0 70px;
		font-size: 25px;
		color:#31A0B4;
		font-style:italic;
	}
	#nav > ul > li > b > a{
		text-decoration:none;
		color:#31A0B4;
	}
	#nav > ul > li > b > a:hover{
		text-decoration:underline;
	}
</style>
</head>
	<nav id="nav">	
	<ul>
		<li><b><a href="login.jsp">서비스</a></b></li>
		<li><b>커뮤니티</b></li>
		<li><b>고객센터</b></li>
		<li><b>마이페이지</b></li>
		<li><b>회원관리</b></li>
		<li><b><a href="../chart/days.jsp">주차장관리</a></b></li>
		<li><b>게시글관리</b></li>
	</ul>
	</nav>