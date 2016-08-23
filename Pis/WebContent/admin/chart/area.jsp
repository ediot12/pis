<%@page import="chart.ChartDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <link href="../../style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
    	var data = new google.visualization.DataTable(); <%-- 데이터 방식은 기본적으로 Array형식, DataTable 객체 생성해서 data에 저장 --%>
		data.addColumn('string', '서울특별시'); <%-- data에 컬럼 추가 (string형식, '서울특별시'라는 타이틀)--%>
		data.addColumn('number', '예약자 수');
		
		<% ChartDBBean cb = ChartDBBean.getInstance(); // ChartDBBean의 getInstance()를 가져와 cb에 저장
		
			String[] area_name = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구",
					"마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};
			
			for(int i=0; i<area_name.length; i++){
		%>
		data.addRows([
			['<%=area_name[i]%>', <%= cb.getArea(area_name[i])%>]
		]);
		<%} %>
        var options = {
           chart: {
            title: '서울특별시 예약자수'
           }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
     <center>
  	<br><br>
  	<div class="title">
		<b>지역별 예약자수</b>
	</div>
	<br><br>
    <div id="columnchart_material"></div>
  </body>
</html>