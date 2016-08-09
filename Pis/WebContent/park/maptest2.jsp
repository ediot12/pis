<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=421bee34f427ca0e30df2e951e2a3692"></script>
<style>
div#right {
	float: right;
	position: absolute;
	top: 5px;
	right: 0px;
	background-color: skyblue;
	width: 340px;
	top: 5px;
	right: 0px;
}

div#map {
	float: center;
	position: absolute;
	top: 5px;
	right: 350px;
}
</style>
<title></title>
<body>
	<div id="map" style="width: 800px; height: 600px;"></div>
	<div id="right">

		<c:if test="${result!=null }">
			<c:forEach var="search" items="${search }" begin="0" end="${count }">
				<script>
				var check = ${count};
				var lat = ${search.lat};
				var lng = ${search.lng};
				var avlat = ${lat};
				var avlng = ${lng};
				
				
				if(map==null){
					
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						level : 5
					// 지도의 확대 레벨
					};
					var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
					map.setCenter(new daum.maps.LatLng(avlat, avlng));
				
				}
				var marker = new daum.maps.Marker({
					map : map,
					position : new daum.maps.LatLng(parseFloat(lat),
							parseFloat(lng))
				});//마커 찍게 하는거
				
				
				
				
				
				
				</script>
				<ul>
					<li>서울특별시 ${search.addr }</li>
					<li>${search.parking_name }</li>
					<li>${search.tel }</li>
					<input type="button" value="ㄱㄱ"
						onclick="getValue('${search.lat}','${search.lng}','${search.addr }','${search.parking_name }','${search.tel }','${search.capacity2 }','${search.parking_type_nm }')">
				</ul>
				<hr>

			</c:forEach>



		</c:if>



	</div>
	<p id="result"></p>

	
	<script>
	/* 	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다 */

		// 지도에 클릭 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		daum.maps.event.addListener(map, 'click', function(mouseEvent) {

			// 클릭한 위도, 경도 정보를 가져옵니다 
			var latlng = mouseEvent.latLng;

			var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
			message += '경도는 ' + latlng.getLng() + ' 입니다';

			var resultDiv = document.getElementById('result');
			resultDiv.innerHTML = message;

			map.addOverlayMapTypeId(daum.maps.MapTypeId.TRAFFIC);

			/* document.write(message); */

		});

		function getValue() {

			var lat = document.getElementById('lat');
			var lng = document.getElementById('lng');

			map.setCenter(new daum.maps.LatLng(parseFloat(lat.value),
					parseFloat(lng.value)));

			var marker = new daum.maps.Marker({
				map : map,
				position : new daum.maps.LatLng(parseFloat(lat.value),
						parseFloat(lng.value))
			});//마커 찍게 하는거

			map.addOverlayMapTypeId(daum.maps.MapTypeId.TRAFFIC);

		}

		function getValue(lat, lng, addr, parking_name, tel,capacity,parking_type_nm) {

			/* var lat = document.getElementById('lat');
			var lng = document.getElementById('lng'); */

			map.setCenter(new daum.maps.LatLng(parseFloat(lat),
							parseFloat(lng)));

			var seoul = '서울특별시 ';
			map.addOverlayMapTypeId(daum.maps.MapTypeId.TRAFFIC);
			document.getElementById('addr').value = seoul + addr;
			document.getElementById('parking_name').value = parking_name;
			document.getElementById('tel').value = tel;
			document.getElementById('capacity').innerHTML = '남은주차대수 : ' + capacity;
			document.getElementById('parking_type_nm').innerHTML = '주차장종류 : ' + parking_type_nm;

		}

		function gpsChck() {
			if (navigator.geolocation) {

				// GeoLocation을 이용해서 접속 위치를 얻어옵니다
				navigator.geolocation.getCurrentPosition(function(position) {

					var lat = position.coords.latitude, // 위도
					lon = position.coords.longitude; // 경도

					var locPosition = new daum.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
					message = '<div style="padding:5px;">여기있는거 다안다.</div>'; // 인포윈도우에 표시될 내용입니다

					// 마커와 인포윈도우를 표시합니다
					displayMarker(locPosition, message);

				});

			} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

				var locPosition = new daum.maps.LatLng(33.450701, 126.570667), message = 'geolocation을 사용할수 없어요..'

				displayMarker(locPosition, message);
			}

			// 지도에 마커와 인포윈도우를 표시하는 함수입니다
			function displayMarker(locPosition, message) {

				// 마커를 생성합니다
				var marker = new daum.maps.Marker({
					map : map,
					position : locPosition
				});

				var iwContent = message, // 인포윈도우에 표시할 내용
				iwRemoveable = true;

				// 인포윈도우를 생성합니다
				var infowindow = new daum.maps.InfoWindow({
					content : iwContent,
					removable : iwRemoveable
				});

				// 인포윈도우를 마커위에 표시합니다 
				infowindow.open(map, marker);

				// 지도 중심좌표를 접속위치로 변경합니다
				map.setCenter(locPosition);
			}
		}
	</script>

	<div id="left">
		<form name="test" method="post" action="test.do">
			P.I.S <input type="text" name="addr"> <input type="submit"
				value="검색"><br>
		</form>
		<br>
		<form name="reserv" method="post" action="reserv.do">
			주소 : <input type="text" name="addr" id="addr" readonly="readonly"
				size="32"><br> 이름 : <input type="text"
				name="parking_name" id="parking_name" readonly="readonly" size="32"><br>
			전번 : <input type="text" name="tel" id="tel" readonly="readonly"><br>
			<div id=capacity></div>
			<div id=parking_type_nm></div>
			<input type="submit" value="예약하기">
		</form>


		너님의 위치는 ? <input type="button" value="Where?" onclick="gpsChck()">
	</div>
</body>
</html>