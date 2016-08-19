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
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = new google.visualization.DataTable();
         data.addColumn('string', '월');
 	 	 data.addColumn('number', '방문자수');

   		<%
   			ChartDBBean cb = ChartDBBean.getInstance();
   		
   			Calendar cal = Calendar.getInstance();
   			
   			String year = Integer.toString(cal.get(Calendar.YEAR));
			String Month = Integer.toString(cal.get(Calendar.MONTH)+1);

  	 	 		for(int i=1; i<=12; i++){
  	 	 			String mon = Integer.toString(i);
  	 	 			
  	 	 			if(mon.length()==2){}
  	 	 			else{mon="0"+i;}
  	 	 			
  	 	 			String c_date = year+"/"+mon;
  	 	 			
	  	 	 	%>
	   		 		data.addRows([             
	         			['<%= i %>', <%=cb.Chart_mon(c_date)%>]           
	         		]);
  	 	 	<% }%>
        var options = {
          chart: {
            title: '월별 방문자수',
            subtitle: '년도 :'+<%=year%>+"년",
          }
        };

        var chart = new google.charts.Bar(document.getElementById('chart_days'));

        chart.draw(data, options);
      }
    </script>
  </head>
  	<br><br>
  	<div id="text">
		<b>월별 방문자수</b>
	</div>
	<br><br>
    <div id="chart_days" style="width: 1150px; height: 500px;"></div>
    
</body>
</html>