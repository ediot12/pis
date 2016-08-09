<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<style type="text/css">
	#logo{
		padding : 0;
		margin : 0 auto;
		position: absolute;
		top: 20px;
		left: 20px;
	}
	#one{
		padding : 0;
		margin : 0 auto;
		position: absolute;
		right: 20px;
		top: 1px;
	}
	#one > ul{
		list-style:none;
		padding:10px 0;
	}
	#one > ul > li{
		display:inline;
		text-transform:uppercase;
		padding:0 10px;
		color:#31A0B4;
	}
	#one > ul > li > a{
		text-decoration:none;
		color:#31A0B4;
	}
	#one > ul > li > a:hover{
		text-decoration:underline;
	}
</style>
</head>
<body>
	<div id="logo">
			<a href="main.jsp">
	    		<img alt="로고" src="../image/logo.jpg" width="500px" height="90px">
	    	</a>
	   </div>

		<nav>
	    		<div id="one">
			    	<ul>
			    		<li>사이트맵</li>
			    	</ul>
		    	</div>
		    </nav>
</body>
</html>