<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.text.*, chart.*, java.sql.*"  %>
<!DOCTYPE html>
<html>
<head> 
<title></title>
<link href="../../style.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['line']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = new google.visualization.DataTable(); 
         data.addColumn('string', '월');
 	 	 data.addColumn('number', '방문자수');
 	 	 data.addColumn('number', '예약자수');

   		<%
   			ChartDBBean cb = ChartDBBean.getInstance();
   		
   			String year = request.getParameter("year");

   			Calendar cal = Calendar.getInstance();
   			if(year==null){
   				year = Integer.toString(cal.get(Calendar.YEAR));
   			}
			String Month = Integer.toString(cal.get(Calendar.MONTH)+1);

  	 	 		for(int i=1; i<=12; i++){
  	 	 			String mon = Integer.toString(i);
  	 	 			
  	 	 			if(mon.length()==2){}
  	 	 			else{mon="0"+i;}
  	 	 			
  	 	 			String c_date = year+"/"+mon;
  	 	 			String date = year+"-"+mon;
  	 	 			
	  	 	 	%>
	   		 		data.addRows([             
	         			['<%= i %>', <%=cb.Chart_mon(c_date)%>, <%=cb.getYear(date)%>]           
	         		]);
  	 	 	<% }%>
        var options = {
        	chart:{
            title: '월별 사용자빈도',
            subtitle: '년도 :'+<%=year%>+"년"
        	}
        };

        var chart = new google.charts.Line(document.getElementById('linechart_material'));

        chart.draw(data, options);
      }
    </script>
  </head>
  	<center>
  	<br><br>
  	<div class="title">
		<b>월별 사용자빈도</b>
	</div>
	<a href="/Pis/admin/chart/month.do?year=<%=Integer.parseInt(year)-1%>">
		<input type="button" value="◀" class="button"></a>
		<%=year%>
	<a href="/Pis/admin/chart/month.do?year=<%=Integer.parseInt(year)+1%>">
		<input type="button" value="▶" class="button">
	</a>
	<br><br>
    <div id="linechart_material"></div>
</body>
</html>