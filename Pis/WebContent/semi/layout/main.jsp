<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="chart.*, java.sql.*, java.util.*, java.util.Date, java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	
	#maintable{
		font-size : 20px;
		color:#31A0B4;
		text-align: center;
	}
	#mainimg{
		position: relative;
	}
</style>
</head>
<body>
<%    
	ChartDataBean article = new ChartDataBean();
	article.setC_date(new Timestamp(System.currentTimeMillis()));
	article.setC_ip(request.getRemoteAddr());

	ChartDBBean cb = ChartDBBean.getInstance(); 
	int check=cb.TodayIpCheck(request.getRemoteAddr());

	if(check==-1||check==0){
			cb.setVisitTotalCount(article);
	}
	
	int todayCount= cb.getVisitTodayCount();
	int totalCount = cb.getVisitTotalCount();
	
	request.getSession().setAttribute("totalCount", totalCount); // 전체 방문자 수
	request.getSession().setAttribute("todayCount", todayCount); // 오늘 방문자 수
%>
	오늘 방문자 : ${todayCount} 
	총 방문자 : ${totalCount}
	
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