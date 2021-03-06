<%@page import="logon.LogonDBBean"%>
<%@page import="Pay.PayDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="park.css">
<script type="text/javascript"
		src="//apis.daum.net/maps/maps3.js?apikey=421bee34f427ca0e30df2e951e2a3692&libraries=services"></script>
		<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script>
	$(function(){
		$("ul.menu li").hover(function(){
			$(">ul:not(:animated)",this).slideDown("fast");
		},
		function(){
			$(">ul",this).slideUp("fast");
		});
	});
	
	function selectCheck(){
		if(document.reserv.addr.value==''){
			alert('선택을 하지 않았습니다.');
			return false;
		}
	}
</script>
<%
String memId = (String) request.getSession().getAttribute("memId");
PayDBBean paydb = PayDBBean.getInstance();
int p1 = paydb.getPoint(memId);

LogonDBBean logdb = LogonDBBean.getInstance();
String grade = logdb.getGrade(memId);

%>

<title>PIS(주차장안내시스템)</title>
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
								    <li>${memId} [회원등급 : <%=grade %>/ 현재포인트 : <%=p1 %>]</li>
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
<div id="container">
	<div id="textline">
	<c:choose>
		<c:when test="${memId != null}">
			<c:choose>
				<c:when test="${memId.equals('admin')}">	
					<ul class="menu">
						<li><b><a href="/Pis/park/parkmap.do">서비스</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/park/parkmap.do">예약</a></b></li>
								<li><b><a href="/Pis/park/myReserv.do">예약정보</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/notice/list.do">커뮤니티</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/notice/list.do">공지사항</a></b></li>
								<li><b><a href="/Pis/review/mainForm.do">사용후기</a></b></li>
								<li><b><a href="/Pis/report/mainForm.do">불편신고</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/faq/faqForm.do">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/refundForm.do">환불규정</a></b></li>
								<hr />
								<li><b><a href="/Pis/info/mainForm.do">주차장제보</a></b></li>
								<li><b><a href="/Pis/faq/faqForm.do">자주묻는질문</a></b></li>
								<li><b><a href="/Pis/question/mainForm.do">1:1문의</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/Join/modifyForm.do">마이페이지</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/modifyForm.do">회원정보수정</a></b></li>
								<hr />
								<li><b><a href="/Pis/pointcharge/point.do">포인트충전</a></b></li>
								<li><b><a href="/Pis/pointcharge/pointlist.do">포인트사용내역</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/admin/member/member.do">회원관리</a></b></li>
						<li><b><a href="/Pis/admin/carpark/carpark.do">주차장관리</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/admin/carpark/carpark.do">주차장 관리</a></b></li>
								<li><b><a href="/Pis/admin/chart/area.do">지역별 사용자빈도</a></b></li>
								<li><b><a href="/Pis/admin/chart/days.do">일별 사용자빈도</a></b></li>
								<li><b><a href="/Pis/admin/chart/month.do">월별 사용자빈도</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/admin/board/noticeForm.do">게시글관리</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/admin/board/noticeForm.do">공지사항</a></b></li>
								<li><b><a href="/Pis/admin/board/FAQ.do">자주묻는질문관리</a></b></li>
								<li><b><a href="/Pis/admin/board/question.do">1:1문의관리</a></b></li>
								<li><b><a href="/Pis/admin/board/info.do">주차장제보관리</a></b></li>
								<li><b><a href="/Pis/admin/board/report.do">불편신고관리</a></b></li>
							</ul>
						</li>
					</ul>
				</c:when>
				<c:otherwise>
			   	  	<ul class="menu">
						<li><b><a href="/Pis/park/parkmap.do">서비스</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/park/parkmap.do">예약</a></b></li>
								<li><b><a href="/Pis/park/myReserv.do">예약정보</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/notice/list.do">커뮤니티</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/notice/list.do">공지사항</a></b></li>
								<li><b><a href="/Pis/review/mainForm.do">사용후기</a></b></li>
								<li><b><a href="/Pis/report/mainForm.do">불편신고</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/faq/faqForm.do">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/refundForm.do">환불규정</a></b></li>
								<hr />
								<li><b><a href="/Pis/info/mainForm.do">주차장제보</a></b></li>
								<li><b><a href="/Pis/faq/faqForm.do">자주묻는질문</a></b></li>
								<li><b><a href="/Pis/question/mainForm.do">1:1문의</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/Join/modifyForm.do">마이페이지</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/modifyForm.do">회원정보수정</a></b></li>
								<hr />
								<li><b><a href="/Pis/pointcharge/point.do">포인트충전</a></b></li>
								<li><b><a href="/Pis/pointcharge/pointlist.do">포인트사용내역</a></b></li>
							</ul>
						</li>
					</ul>
			</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<ul class="menu">
				<li><b><a href="../park/parkmap.do">서비스</a></b>
						<ul class="sub">
							<li><b><a href="../park/parkmap.do">예약</a></b></li>
							<li><b><a href="/Pis/park/myReserv.do">예약정보</a></b></li>
						</ul>
					</li>
					<li><b><a href="/Pis/notice/list.do">커뮤니티</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/notice/list.do">공지사항</a></b></li>
								<li><b><a href="/Pis/review/mainForm.do">사용후기</a></b></li>
								<li><b><a href="/Pis/report/mainForm.do">불편신고</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/faq/faqForm.do">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/findIDForm.do">아이디찾기</a></b></li>
								<li><b><a href="/Pis/Join/findPWForm.do">비밀번호찾기</a></b></li>
								<hr />
								<li><b><a href="/Pis/Join/refundForm.do">환불규정</a></b></li>
								<hr />
								<li><b><a href="/Pis/info/mainForm.do">주차장제보</a></b></li>
								<li><b><a href="/Pis/faq/faqForm.do">자주묻는질문</a></b></li>
								<li><b><a href="/Pis/question/mainForm.do">1:1문의</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/Join/loginForm.do">마이페이지</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/loginForm.do">로그인</a></b></li>
								<hr/>
								<li><b><a href="/Pis/pointcharge/point.do">포인트충전</a></b></li>
								<li><b><a href="/Pis/pointcharge/pointlist.do">포인트사용내역</a></b></li>
							</ul>
						</li>
					</ul>
		</c:otherwise> 
	</c:choose>
	</div>
</div>
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
		<div id="left" style="overflow-y: auto; overflow-x:hidden">
		<form name="test" method="post" action="parkmap.do">
			P.I.S <input type="text" name="addr" value="${result }"> <!-- <input type="submit"
				value="검색"><br> --><input type="image" src="icon/parksearch.png" width="30px"><br>
				
		</form>
		<br>
		
		
	<div id="res">
	<table class="search">
	
		<tr>
		<td id="main">검색 결과</td>
		</tr>
		
		<c:if test="${result!=null }">
		<c:if test="${count==0 }">
		<script>
		
		document.getElementById('map').innerHTML = null;
		document.getElementById('map').innerHTML = '<img src="icon/DataNotFound.jpg" alt="이미지" width="60%" height="80%">';
	
		</script>
		</c:if>
			<c:forEach var="search" items="${search }" begin="0" end="${count }" varStatus="abc">
			
		<tr>
		<td class="colorblack">
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
					var points = new Array();
									
					
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						level : 5
					// 지도의 확대 레벨
					};
					var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
					map.setCenter(new daum.maps.LatLng(avlat, avlng));
					
					var mapTypeControl = new daum.maps.MapTypeControl();
					// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
					var bounds = new daum.maps.LatLngBounds();   
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
				points[fcount-1] = new daum.maps.LatLng(parseFloat(lat),parseFloat(lng));
				bounds.extend(points[fcount-1]);
				var abc = new Array();
				
				var marker = new daum.maps.Marker({
					map : map,
					position : new daum.maps.LatLng(parseFloat(lat),
							parseFloat(lng))
				});//마커 찍게 하는거
				abc[fcount-1] = marker;
				
				var fInfo = new daum.maps.InfoWindow({
                    content: '<div id="markin">주소 : 서읕륵별시 ' + faddress +
                    '<br>전화번호 : '+ftel+
                    '<br>평일운영시간 : '+fweekd_bt+'시~'+fweekd_et+'시'+
                    '<br>주말운영시간 : '+fweeke_bt+'시~'+fweeke_et+'시'+
                    '<br>기본요금 : 30분/'+frates+'원'+
                    '</div>',
                    removable : true
                });
				
				daum.maps.event.addListener(marker, 'click', (function(marker, fInfo,fcount,faddress,ftel,fcapa,fweekd_bt,fweekd_et,fweeke_bt,fweeke_et,frates,lat,lng) {
					return function() {
						getValue('${search.lat}',
								'${search.lng}','${search.addr }','${search.parking_name }',
								'${search.tel }','${search.capacity2 }','${search.parking_type_nm }',
								'${search.rates }','${search.weekday_begin_time}','${search.weekday_end_time}',
								'${search.weekend_begin_time}','${search.weekend_end_time}','${search.parking_code }',
								'${search.capacity3}','${search.capacity4}','${search.capacity5}','${search.firstdate }'
								,'${search.seconddate }','${search.thirddate }','${search.fourthdate }');
						map.setLevel(3);
						map.setCenter(new daum.maps.LatLng(parseFloat(lat),parseFloat(lng)));
                        var infowindow = fInfo;
                        infowindow.open(map, marker);
                    }
                    
                })(marker, fInfo,fcount,faddress,ftel,fcapa,fweekd_bt,fweekd_et,fweeke_bt,fweeke_et,frates,lat,lng));
				
				daum.maps.event.addListener(map,'click',(function(fInfo){
					return function(){
					var infowindow = fInfo
					infowindow.close();
				}
				})(fInfo));
				
				
				
				if(check==fcount){
					map.setBounds(bounds);
				}
				
				</script>
				<ul id="list">
					<li>서울특별시 ${search.addr}</li>
					<li>${search.parking_name}</li>
					<c:if test="${search.tel!=null }">
					<li>${search.tel }</li>
					</c:if>
				<input class="button" type="button" value="선택"
						onclick="getValue('${search.lat}',
						'${search.lng}','${search.addr }','${search.parking_name }',
						'${search.tel }','${search.capacity2 }','${search.parking_type_nm }',
						'${search.rates }','${search.weekday_begin_time}','${search.weekday_end_time}',
						'${search.weekend_begin_time}','${search.weekend_end_time}','${search.parking_code }',
						'${search.capacity3}','${search.capacity4}','${search.capacity5}','${search.firstdate }'
						,'${search.seconddate }','${search.thirddate }','${search.fourthdate }')">
					
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
		
		<form name="reserv" method="post" action="reserv.do" onsubmit="return selectCheck()">
			<input type="hidden" value="abc" name="time1" id="time1">
			<input type="hidden" value="abc" name="time2" id="time2">
			<input type="hidden" value="abc" name="time5" id="time5">
			<input type="hidden" value="abc" name="time6" id="time6">
			<input type="hidden" value="abc" name="capacity2" id="capacity2">
			<input type="hidden" value="abc" name="capacity3" id="capacity3">
			<input type="hidden" value="abc" name="capacity4" id="capacity4">
			<input type="hidden" value="abc" name="capacity5" id="capacity5">
			<input type="hidden" value="abc" name="parking_code" id="parking_code">
			주소 : <input type="text" name="addr" id="addr" readonly="readonly"><br> 이름 : <input type="text"
				name="parking_name" id="parking_name" readonly="readonly"><br>
			전번 : <input type="text" name="tel" id="tel" readonly="readonly" ><br>
			<div id=capacity></div>
			<div id="capacity_2"></div>
			<div id="capacity_3"></div>
			<div id="capacity_4"></div>
			<div id=parking_type_nm></div>
			<div id=rates></div>
			<div id=time3></div>
			<div id=time4></div>
			<input class="button" type="submit" value="예약하기">
		</form>
		<br>
		<br>
		<br>

		</div>
		
		<script>
		function viewSelect(lat,lng,parking_name){
			
			map.setCenter(new daum.maps.LatLng(parseFloat(lat),parseFloat(lng)));
			var infowindow = new daum.maps.InfoWindow({
				map : map,
				/* position : new daum.maps.LatLng(parseFloat(lat)+0.001,parseFloat(lng)), */
				content : parking_name,
				zindex : 1
			}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
		}
		//최소한 지도가 뜬 이후에나
		function getValue(lat, lng, addr, parking_name, tel,capacity,parking_type_nm,rates,fweekbt,fweeket,fweekebt,fweekeet,parking_code,capacity2,capacity3,capacity4,first,second,third,fourth) {

			map.setCenter(new daum.maps.LatLng(parseFloat(lat),
							parseFloat(lng)));
			map.setLevel(1);
			var seoul = '서울특별시 ';
			/* map.addOverlayMapTypeId(daum.maps.MapTypeId.TRAFFIC); */
			document.getElementById('addr').value = seoul + addr;
			document.getElementById('parking_name').value = parking_name;
			document.getElementById('tel').value = tel;
			document.getElementById('capacity').innerHTML = first + ' 남은주차대수 : ' + capacity;
			document.getElementById('capacity_2').innerHTML = second + ' 남은주차대수 : ' + capacity2;
			document.getElementById('capacity_3').innerHTML = third + ' 남은주차대수 : ' + capacity3;
			document.getElementById('capacity_4').innerHTML = fourth + ' 남은주차대수 : ' + capacity4;
			document.getElementById('parking_type_nm').innerHTML = '주차장종류 : ' + parking_type_nm;
			document.getElementById('rates').innerHTML = '기본요금 : 30분 /'+ rates+'원';
			document.getElementById('time3').innerHTML = '평일운영시간 : ' +fweekbt+'시 ~ '+fweeket+'시';
			document.getElementById('time4').innerHTML = '주말운영시간 : ' +fweekebt+'시 ~ '+fweekeet+'시';

			document.getElementById('parking_code').value = parking_code;
			document.getElementById('capacity2').value = capacity;
			document.getElementById('capacity3').value = capacity2;
			document.getElementById('capacity4').value = capacity3;
			document.getElementById('capacity5').value = capacity4;
			document.getElementById('time1').value = fweekbt;//평일 오픈 시간
			document.getElementById('time2').value = fweekebt;//주말 오픈 시간
			document.getElementById('time5').value = fweeket;//평일 클로징 시간
			document.getElementById('time6').value = fweekeet;//주말 클로징 시간

			

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
					function searchDetailAddrFromCoords(coords, callback) {
						// 좌표로 법정동 상세 주소 정보를 요청합니다
						geocoder.coord2detailaddr(coords, callback);
					}
				});
		}
		</script>
		
		<div id="footer">
<b>PIS(Parking Information System)</b><br>
김창배 박송이 박흥철 장찬규 주다연
</div>
		
	
</body>
</html>