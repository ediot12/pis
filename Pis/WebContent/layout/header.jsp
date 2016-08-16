<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.text.*, chart.*, java.sql.*"  %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
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
			<a href="index.jsp">
	    		<img alt="로고" src="/Pis/image/logo.jpg" width="500px" height="90px">
	    	</a>
	    </div>
	   	
	    <nav>
	    	<div id="one">
	    		<c:choose>
					<c:when test="${memId != null}">
						<c:choose>
							<c:when test="${memId.equals('admin')}">
						    	<ul>
						    		<li><a href="/Pis/Join/logout.do">로그아웃</a></li>
						    		<li>사이트맵</li>
						    	</ul>
							</c:when>
							<c:otherwise>
								<ul>
									<li><a href="/Pis/Join/logout.do">로그아웃</a></li>
						    		<li>사이트맵</li>
						    	</ul>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
					    <ul>
					    	<li><a href="/Pis/Join/loginForm.do">로그인</a></li>
					    	<li><a href="/Pis/Join/inputForm.do">회원가입</a></li>
					    	<li>사이트맵</li>
					    </ul>
			    	</c:otherwise>
			    </c:choose>
		    </div>
		</nav>
		
</body>
</html>
