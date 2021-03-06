<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
		<div id="logo">
			<a href="/Pis/layout/main.do">
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
						    		<li>${memId}</li>
						    		<li><a href="/Pis/Join/logout.do">로그아웃</a></li>
						    		<li><a href="/Pis/layout/siteMapAdmin.do">사이트맵</a></li>
						    	</ul>
							</c:when>
							<c:otherwise>
								<ul>
								    <li>${memId} [회원등급 : ${grade}/ 현재포인트 : ${point}]</li>
									<li><a href="/Pis/Join/logout.do">로그아웃</a></li>
						    		<li><a href="/Pis/layout/siteMapUser.do">사이트맵</a></li>
						    	</ul>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
					    <ul>
					    	<li><a href="/Pis/Join/loginForm.do">로그인</a></li>
					    	<li><a href="/Pis/Join/inputForm.do">회원가입</a></li>
					    	<li><a href="/Pis/layout/siteMap.do">사이트맵</a></li>
					    </ul>
			    	</c:otherwise>
			    </c:choose>
		    </div>
		</nav>
		
</body>
</html>
