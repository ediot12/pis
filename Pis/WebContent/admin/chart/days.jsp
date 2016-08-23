<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.text.*, chart.*, java.sql.*"  %>
<!DOCTYPE html>
<html> 
<head>
<title></title>
<link href="../../style.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script><%-- chart관련 스크립트 받아옴. --%>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']}); <%-- 구글 차트에서 bar형태의 패키지를 연결함 --%>
      google.charts.setOnLoadCallback(drawChart);<%-- drawChart에 값을 다시 보내줌 --%>
      function drawChart() {
        var data = new google.visualization.DataTable(); <%-- 데이터 방식은 기본적으로 Array형식, DataTable 객체 생성해서 data에 저장 --%>
         data.addColumn('string', '일'); <%-- data에 컬럼 추가 (string형식, '일'이라는 타이틀)--%>
 	 	 data.addColumn('number', '방문자수');
        
 	 	 
   		<%
   			ChartDBBean cb = ChartDBBean.getInstance(); // ChartDBBean의 getInstance()를 가져와 cb에 저장
   			
   			String Month = request.getParameter("Month");
   			
   			Calendar cal = Calendar.getInstance(); // 캘린더를 가져와 cal에 저장
   			
   			String year = Integer.toString(cal.get(Calendar.YEAR)); //현재 년도를 받아와 String타입으로 형변환
   			
   			if(Month==null){//받은 Month값이 null일 경우
   				Month = Integer.toString(cal.get(Calendar.MONTH)+1); //현재 달을 받아와 String타입으로 형변화
   			}
   			if(Month.equals("13")||Month.equals("0")){//받은 Month값이 13또는 0일 경우
   				Month = "01";
   			}
			
   			if(Month.length()==1){Month = "0"+Month;} //만약 현재 달의 자릿수가 1일 경우 0을 앞에 추가해줌!! DB의 저장된 값은 01,02...형식이고 캘린더는 1,2...로 받기 때문에 수정해줘야함.
   			
  	 		 if(Month.equals("01")||Month.equals("03")||Month.equals("05")||Month.equals("07")||Month.equals("08")||Month.equals("10")||Month.equals("12")){//현재 달이 01,03,05,07,08,10,12일 경우 아래 for문을 실행
				//위에 달은 31일까지 있으므로 31번 for문을 돌림
  	 	 		for(int i=1; i<=31; i++){
  	 	 			String day = Integer.toString(i); //만약 i가 1일때 String형변환 시켜서 day에 저장
  	 	 			if(day.length()==1){day= "0"+i;}// 일단위도 01,02,..형식이므로 자릿수가 1일 경우는 0을 앞에 붙여줌
  	 	 			String c_date = year+"/"+Month+"/"+day; //현재년도/현재달/현재일을 c_date에 저장
	  	 	 	%>
	   		 		data.addRows([<%-- data에 행을 추가함. 총 31번 --%>  
	         			['<%=i %>', <%=cb.Chart_days(c_date)%>]<%-- i는 1~31일, cb.Chart_days(c_date)는 c_date값을 가지고 Chart_days로 감. --%>
	         		]);
  	 	 	<% }} else if(Month.equals("04")||Month.equals("06")||Month.equals("09")||Month.equals("11")){
  	 			for(int i=1; i<=30; i++){
  	 				String day = Integer.toString(i);
  	 				if(day.length()==1){day= "0"+i;}
  	 				String c_date = year+"/"+Month+"/"+day;
	  	 	 	%>
	   		 		data.addRows([             
	         			['<%=i %>', <%=cb.Chart_days(c_date)%>]
	         		]);
	   		<%}} else {
  	 			for(int i=1; i<=28; i++){
  	 				String day = Integer.toString(i);
  	 				if(day.length()==1){day= "0"+i;}
  	 				String c_date = year+"/"+Month+"/"+day;
	  	 	 	%>
	   		 		data.addRows([             
	         			['<%=i %>', <%=cb.Chart_days(c_date)%>]           
	         		]);
	   		<% } } %>
        var options = { 
          chart: {
            title: '일별 방문자수',
            subtitle: <%=year%>+'년 '+<%=Month%>+'월'
          }
        };<%-- chart에 title값과 subtitle값을 options에 저장함.(수많은 옵션이 존재하는 데 x,y축 항목도 정의 가능하다.) --%>

        var chart = new google.charts.Bar(document.getElementById('columnchart_material')); <%-- div element를 parameter로 전달함. columnchart_material은 자료로써 마우스를 차트자료에 올렸을 때 자료가 출력됨. --%>

        chart.draw(data, options); <%-- data와 option정보가 필요함. --%>
      }
    </script>
  </head>
  <body>
  <center>
  	<br><br>
  	<div class="title">
		<b>일별 방문자수</b>
	</div>
	<a href="/Pis/admin/chart/days.do?Month=<%=Integer.parseInt(Month)-1%>">
		<input type="button" value="◀" class="button"></a>
		<%=Month%>
	<a href="/Pis/admin/chart/days.do?Month=<%=Integer.parseInt(Month)+1%>">
		<input type="button" value="▶" class="button">
	</a>
	<br><br>
    <div id="columnchart_material" style="width: 1150px; height: 500px;"></div>
    
</body>
</html>