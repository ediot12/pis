<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head> 
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script>

	$(function(){
		$("ul.menu li").hover(function(){
			$(">ul:not(:animated)",this).slideDown("fast");
		},
		function(){
			$(">ul",this).slideUp("fast");
		});
		
		var dt = new Date();
		var y = dt.getFullYear();
		var m = dt.getMonth() + 1;
		var d = dt.getDate(); 
		var d2 = dt.getDate() + 3;
		var h = dt.getHours();
		mindt = y + "-" + m + "-" + d;
		maxdt = y + "-" + m + "-" + d2;

		$("#calendar1").datepicker({

			minDate : mindt,
			maxDate : maxdt,
			dateFormat : "yy-mm-dd",

		});

		// 옵션 : 매개변수값 2번째가 옵션의 타입이며 3번째 항목은 옵션에 대한 설정 값
		$("#calendar1").datepicker("option", "showAnim", "slideDown"); // 달력의 표시 형태

		$("#calendar2").datepicker({

			minDate : mindt,
			maxDate : maxdt,
			dateFormat : "yy-mm-dd",
			onSelect : function(value) {

				var dt2 = $("#calendar1").datepicker("getDate").getDate();
				var dt3 = value.substr(-2, 2);
				if (dt2 > dt3) {
					alert('넌 과거로 돌아가니?');
					$("#calendar1").datepicker("setDate", "");
					$("#calendar2").datepicker("setDate", "");
				}
			}

		});

		// 옵션 : 매개변수값 2번째가 옵션의 타입이며 3번째 항목은 옵션에 대한 설정 값
		$("#calendar2").datepicker("option", "showAnim", "slideDown"); // 달력의 표시 형태
	
	});
</script>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
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
								<li><b><a href="#">예약정보</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/notice/list.do">커뮤니티</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/notice/list.do">공지사항</a></b></li>
								<li><b><a href="/Pis/review/mainForm.do">사용후기</a></b></li>
								<li><b><a href="/Pis/report/mainForm.do">불편신고</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/findIDForm.do">아이디찾기</a></b></li>
								<li><b><a href="/Pis/Join/findPWForm.do">비밀번호찾기</a></b></li>
								<hr />
								<li><b><a href="#">환불규정</a></b></li>
								<hr />
								<li><b><a href="/Pis/info/mainForm.do">주차장제보</a></b></li>
								<li><b><a href="#">자주묻는질문</a></b></li>
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
								<li><b><a href="#">지역별 사용자빈도</a></b></li>
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
						<li><b><a href="#">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/findIDForm.do">아이디찾기</a></b></li>
								<li><b><a href="/Pis/Join/findPWForm.do">비밀번호찾기</a></b></li>
								<hr />
								<li><b><a href="#">환불규정</a></b></li>
								<hr />
								<li><b><a href="/Pis/info/mainForm.do">주차장제보</a></b></li>
								<li><b><a href="#">자주묻는질문</a></b></li>
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
						<li><b><a href="#">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/Join/findIDForm.do">아이디찾기</a></b></li>
								<li><b><a href="/Pis/Join/findPWForm.do">비밀번호찾기</a></b></li>
								<hr />
								<li><b><a href="#">환불규정</a></b></li>
								<hr />
								<li><b><a href="/Pis/info/mainForm.do">주차장제보</a></b></li>
								<li><b><a href="#">자주묻는질문</a></b></li>
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
	
