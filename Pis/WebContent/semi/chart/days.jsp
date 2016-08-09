<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.text.*, chart.*, java.sql.*"  %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<style>
	#chart_days{
		position: absolute;
		left : 400px;
	}
	#text{
		font-size: 25px;
		color: #31A0B4;
		text-align: left;
	}
</style>
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = new google.visualization.DataTable();
         data.addColumn('string', '일');
 	 	 data.addColumn('number', '방문자수');
        
   		<%
   			ChartDBBean cb = ChartDBBean.getInstance();
   		
   			Calendar cal = Calendar.getInstance();
   			
   			String year = Integer.toString(cal.get(Calendar.YEAR));
			String Month = Integer.toString(cal.get(Calendar.MONTH)+1);
			
   			if(Month.length()==1){Month = "0"+Month;}
   			
  	 		 if(Month.equals("01")||Month.equals("03")||Month.equals("05")||Month.equals("07")||Month.equals("08")||Month.equals("10")||Month.equals("12")){

  	 	 		for(int i=1; i<=31; i++){
  	 	 			String day = Integer.toString(i);
  	 	 			if(day.length()==2){}
  	 	 			else{day= "0"+i;}
  	 	 			String c_date = year+"/"+Month+"/"+day;
	  	 	 	%>
	   		 		data.addRows([             
	         			['<%=i %>', <%=cb.Chart_days(c_date)%>]           
	         		]);
  	 	 	<% }} else if(Month.equals("04")||Month.equals("06")||Month.equals("09")||Month.equals("11")){
  	 			for(int i=1; i<=30; i++){
  	 				String day = Integer.toString(i);
  	 				if(day.length()==2){}
  	 	 			else{day= "0"+i;}
  	 				String c_date = year+"/"+Month+"/"+i;
	  	 	 	%>
	   		 		data.addRows([             
	         			[<%=i %>, <%=cb.Chart_days(c_date)%>]
	         		]);
	   		<%}} else {
  	 			for(int i=1; i<=28; i++){
  	 				String day = Integer.toString(i);
  	 				if(day.length()==2){}
  	 	 			else{day= "0"+i;}
  	 				String c_date = year+"/"+Month+"/"+i;
	  	 	 	%>
	   		 		data.addRows([             
	         			[<%=i %>, <%=cb.Chart_days(c_date)%>]           
	         		]);
	   		<% } } %>
        var options = {
          chart: {
            title: '일별 방문자수',
            subtitle: '월 :'+<%=Month%>+'월'
          }
        };

        var chart = new google.charts.Bar(document.getElementById('chart_days'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
  
  	<br><br>
  	<div id="text">
		<b>일별 방문자수</b>
	</div>
	<br><br>
    <div id="chart_days" style="width: 1150px; height: 500px;"></div>
    
</body>
</html>