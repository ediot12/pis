<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=695ba71a42e2ca4d5170303619a2f56a&libraries=services"></script>
<style>
div#right {
	position : fixed;
	float: right;
	top: 5px;
	right: 0px;
	width: 20%;
	color : #31A0B4;
	font-size: 9pt;
	text-align: center;
	
}

#res{
	overflow-y: scroll;
}

div#left{
	
	float : left;
	top: 5px;
	left : 0px;
	width : 20%;
	color : #31A0B4;
	font-size: 9pt;
	text-align: center;
}

div#map {
	position : fixed;
	top: 5px;
	left : 20%;
	width : 60%;
	height : 100%;
}

table{
	font-size: 9pt;
}

table.search td#main{
	
	text-align: center;
}

.title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}

ul{
	color : #31A0B4;
}

</style>
<title>Parking Information Service</title>
<body>
<div id="map">
		<c:if test="${result==null }">
		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new daum.maps.LatLng(37.56629305031, 126.97988661), // 지도의 중심좌표
	        level: 7 // 지도의 확대 레벨
	    };  
	
	// 지도를 생성합니다    
	var map = new daum.maps.Map(mapContainer, mapOption); 
	
	var mapTypeControl = new daum.maps.MapTypeControl();
	
	//지도에 컨트롤을 추가해야 지도위에 표시됩니다
	//daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
	
	//지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new daum.maps.ZoomControl();
	map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new daum.maps.services.Geocoder();
	
	var marker = new daum.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
	    infowindow = new daum.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
	
	
	// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
	daum.maps.event.addListener(map, 'click', function(mouseEvent) {
	    searchDetailAddrFromCoords(mouseEvent.latLng, function(status, result) {
	        if (status === daum.maps.services.Status.OK) {
	            var detailAddr = !!result[0].roadAddress.name ? '<div>도로명주소 : ' + result[0].roadAddress.name + '</div>' : '';
	            detailAddr += '<div>지번 주소 : ' + result[0].jibunAddress.name + '</div>';
	            
	            var content = '<div class="bAddr">' +
	                            '<span class="title">법정동 주소정보</span>' + 
	                            detailAddr + 
	                        '</div>';
	
	            // 마커를 클릭한 위치에 표시합니다 
	            marker.setPosition(mouseEvent.latLng);
	            marker.setMap(map);
	
	            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
	            infowindow.setContent(content);
	            infowindow.open(map, marker);
	        }   
	    });
	});
	
	// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
	daum.maps.event.addListener(map, 'idle', function() {
	    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
	});
	
	function searchAddrFromCoords(coords, callback) {
	    // 좌표로 행정동 주소 정보를 요청합니다
	    geocoder.coord2addr(coords, callback);         
	}
	
	function searchDetailAddrFromCoords(coords, callback) {
	    // 좌표로 법정동 상세 주소 정보를 요청합니다
	    geocoder.coord2detailaddr(coords, callback);
	}
	
	// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
	function displayCenterInfo(status, result) {
	    if (status === daum.maps.services.Status.OK) {
	        var infoDiv = document.getElementById('centerAddr');
	        infoDiv.innerHTML = result[0].fullName;
	    }    
	}
	
	
		</script>
		</c:if>
		</div>
		<div id="left">
		<form name="test" method="post" action="test.do">
			P.I.S <input type="text" name="addr" value="${result }"> <!-- <input type="submit"
				value="검색"><br> --><input type="image" src="icon/parksearch.png" width="30px"><br>
				무료<input type="radio" value="free" name="paycheck" onclick="location.href='/Pis/park/test.do?paycheck=free&result=${result}'">
				유료<input type="radio" value="charge" name="paycheck" onclick="location.href='/Pis/park/test.do?paycheck=charge&result=${result}'">
				
		</form>
		<br>
		
		
	<div id="res">
	<table class="search">
	
		<tr>
		<td id="main">검색 결과</td>
		</tr>
		<c:if test="${result!=null }">
			<c:forEach var="search" items="${search }" begin="0" end="${count }" varStatus="abc">
			
		<tr>
		<td>
				<script>
				var check = ${count};
				var lat = ${search.lat};
				var lng = ${search.lng};
				var avlat = ${lat};
				var avlng = ${lng};
				var faddress = new String('${search.addr}');
				var ftel = new String('${search.tel}');
				var fcount = ${abc.count};
				var fcapa = ${search.capacity2};
				var fweekd_bt = new String('${search.weekday_begin_time}');
				var fweekd_et = new String('${search.weekday_end_time}');
				var fweeke_bt = new String('${search.weekend_begin_time}');
				var fweeke_et = new String('${search.weekend_end_time}');
				var frates = ${search.rates};
				
				
				if(map==null){
					
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						level : 5
					// 지도의 확대 레벨
					};
					var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
					map.setCenter(new daum.maps.LatLng(avlat, avlng));
					
					var mapTypeControl = new daum.maps.MapTypeControl();

					// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
					// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
					map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

					// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
					var zoomControl = new daum.maps.ZoomControl();
					map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
				
				
				
				
				var circle = new daum.maps.Circle({
				    center : new daum.maps.LatLng(avlat, avlng),  // 원의 중심좌표 입니다 
				    radius: 800, // 미터 단위의 원의 반지름입니다 
				    strokeWeight: 5, // 선의 두께입니다 
				    strokeColor: '#75B8FA', // 선의 색깔입니다
				    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
				    strokeStyle: 'dashed', // 선의 스타일 입니다
				    fillColor: '#CFE7FF', // 채우기 색깔입니다
				    fillOpacity: 0.5  // 채우기 불투명도 입니다   
				}); 

				// 지도에 원을 표시합니다 
				circle.setMap(map); 
				
				
				
				}
				
				
				var marker = new daum.maps.Marker({
					map : map,
					position : new daum.maps.LatLng(parseFloat(lat),
							parseFloat(lng))
				});//마커 찍게 하는거
				
				
				daum.maps.event.addListener(marker, 'click', (function(marker, fcount,faddress,ftel,fcapa,fweekd_bt,fweekd_et,fweeke_bt,fweeke_et,frates) {
                    return function() {
                        var infowindow = new daum.maps.InfoWindow({
                            content: '<div style="padding:5px; width:220px ;height:150px;font-size:9pt">주소 : 서읕륵별시 ' + faddress +
                            '<br>전화번호 : '+ftel+
                            '<br>남은대수 : '+fcapa+
                            '<br>평일운영시간 :'+fweekd_bt+'~'+fweekd_et+
                            '<br>주말운영시간 :'+fweeke_bt+'~'+fweeke_et+
                            '<br>기본요금 : 10분/'+frates+'원'+
                            '</div>',
                            removable : true
                        });
                      infowindow.open(map, marker);
                    }
                })(marker, fcount,faddress,ftel,fcapa,fweekd_bt,fweekd_et,fweeke_bt,fweeke_et,frates));
				
				
				
				
				
				</script>
				<ul>
					<li>서울특별시 ${search.addr }</li>
					<li>${search.parking_name }</li>
					<c:if test="${search.tel!=null }">
					<li>${search.tel }</li>
					</c:if>
					<input type="button" value="ㄱㄱ"
						onclick="getValue('${search.lat}',
						'${search.lng}','${search.addr }','${search.parking_name }',
						'${search.tel }','${search.capacity2 }','${search.parking_type_nm }','${search.rates }')">
				</ul>
				
				<hr>
				</td>
				</tr>

			</c:forEach>

			</c:if>
			
		</table>
		</div>
		</div>
		<div id="right">
		
		<form name="reserv" method="post" action="reserv.do">
			주소 : <input type="text" name="addr" id="addr" readonly="readonly"><br> 이름 : <input type="text"
				name="parking_name" id="parking_name" readonly="readonly"><br>
			전번 : <input type="text" name="tel" id="tel" readonly="readonly"><br>
			<div id=capacity></div>
			<div id=parking_type_nm></div>
			<div id=rates></div>
			<input type="submit" value="예약하기">
		</form>

	
		너님의 위치는 ? <input type="button" value="Where?" onclick="checkLocation()"><br>
		<input type="text" id="loc" value="이것이 너의 위치" size="30" readonly="readonly">



		</div>
		
		<script>
	
		

		function getValue(lat, lng, addr, parking_name, tel,capacity,parking_type_nm,rates) {

			/* var lat = document.getElementById('lat');
			var lng = document.getElementById('lng'); */
			
			map.setCenter(new daum.maps.LatLng(parseFloat(lat),
							parseFloat(lng)));
			
			var seoul = '서울특별시 ';
			/* map.addOverlayMapTypeId(daum.maps.MapTypeId.TRAFFIC); */
			document.getElementById('addr').value = seoul + addr;
			document.getElementById('parking_name').value = parking_name;
			document.getElementById('tel').value = tel;
			document.getElementById('capacity').innerHTML = '남은주차대수 : ' + capacity;
			document.getElementById('parking_type_nm').innerHTML = '주차장종류 : ' + parking_type_nm;
			document.getElementById('rates').innerHTML = '기본요금 : 10분 /'+ rates+'원';

		}

		function gpsCheck() {
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
		
		function checkLocation(){
			
			navigator.geolocation.getCurrentPosition(function(position) {

				var lat = position.coords.latitude, // 위도
				lon = position.coords.longitude; // 경도
				

				// 지도를 생성합니다 
			
					var locPosition = new daum.maps.LatLng(lat, lon);
					map.setCenter(new daum.maps.LatLng(lat, lon));
					/* var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new daum.maps.LatLng(lat, lon), // 지도의 중심좌표
						level : 3
					// 지도의 확대 레벨
					};
					var map = new daum.maps.Map(mapContainer, mapOption); */
					var geocoder = new daum.maps.services.Geocoder();
					var marker = new daum.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
					infowindow = new daum.maps.InfoWindow({
						zindex : 1
					}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
					
					searchDetailAddrFromCoords(locPosition,function(status, result) {
						if (status === daum.maps.services.Status.OK) {
								var detailAddr = result[0].jibunAddress.name;
								var content = detailAddr;
								// 마커를 클릭한 위치에 표시합니다 
								marker.setPosition(locPosition);
								marker.setMap(map);
								// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
						
								infowindow.setContent(content);
								infowindow.open(map, marker);
								document.getElementById('loc').value = result[0].jibunAddress.name;
							}
						});

					// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
				

					

					function searchDetailAddrFromCoords(coords, callback) {
						// 좌표로 법정동 상세 주소 정보를 요청합니다
						geocoder.coord2detailaddr(coords, callback);
					}

				
					
				// 주소-좌표 변환 객체를 생성합니다
			/* 	else{
					document.getElementById('map').innerHTML = "";
					var locPosition = new daum.maps.LatLng(lat, lon);
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new daum.maps.LatLng(lat, lon), // 지도의 중심좌표
						level : 3
					// 지도의 확대 레벨
					};
					var map = new daum.maps.Map(mapContainer, mapOption);
					var geocoder = new daum.maps.services.Geocoder();
					var marker = new daum.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
					infowindow = new daum.maps.InfoWindow({
						zindex : 1
					}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
					
					searchDetailAddrFromCoords(locPosition,function(status, result) {
						if (status === daum.maps.services.Status.OK) {
								var detailAddr = result[0].jibunAddress.name;
								var content = detailAddr;
								// 마커를 클릭한 위치에 표시합니다 
								marker.setPosition(locPosition);
								marker.setMap(map);
								// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
						
								infowindow.setContent(content);
								infowindow.open(map, marker);
								document.getElementById('loc').value = result[0].jibunAddress.name;
							}
						});

					// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
				

					

					function searchDetailAddrFromCoords(coords, callback) {
						// 좌표로 법정동 상세 주소 정보를 요청합니다
						geocoder.coord2detailaddr(coords, callback);
					}
					
				} */
				
				
				// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
				/* var locPosition = new daum.maps.LatLng(lat, lon); */
			

			
				});
			
			
		}
		</script>
		
	
</body>
</html>