<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.sql.*, java.util.*, java.util.Date, java.text.*" %>
<!DOCTYPE html>
<html> 
<head>
<style type="text/css">
	#maintext{
		text-align: center
	}
	#maintable{
		font-size : 20px;
		color:#31A0B4;
		text-align: center; 
	}
	#mainimg{
		position: relative;
		z-index: -1;
	}
</style>
</head>
<body>
	<div id="maintext">
		오늘 방문자 : ${todayCount} 
		총 방문자 : ${totalCount}
	</div>
	<br><br><img src="../image/main.jpg" alt="메인이미지" width="1100px" height="330px" id="mainimg"><br><br><br><br><br>
	<table id="maintable">
		<tr>
			<td width="500px"><b>공지사항</b></td>
			<td width="500px"><b>자주묻는질문</b></td>
		</tr>
		<tr>
			<td></td>
			<td></td> 
		</tr>
	</table>
</body>
</html>