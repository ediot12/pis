<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head> 
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
	$(function(){
		$("ul.menu li").hover(function(){
			$(">ul:not(:animated)",this).slideDown("fast");
		},
		function(){
			$(">ul",this).slideUp("fast");
		});
	});
</script>
<style type="text/css">
	#container{
		border-top: 4px solid #31A0B4; 
		border-bottom: 4px solid #31A0B4;
		height:50px;
	}
	#textline{
		display: table; margin-left: auto; margin-right: auto; 
	}
	*{
		margin:0;
		padding:0;
		list-style-type:none;
	}
	ul.menu li{
		float:left;
		width:179px;
		height:48px;
		position:relative;
		
	}
	ul.menu li a{
		display:block;
		width:100%;
		height:100%;
		line-height:48px;
		text-indent:30px;
		font-weight:bold;
		color:#31A0B4;
		text-decoration:none;
		position:relative;
	}
	ul.sub{
		display:none;
		background: #FFFFFF;
		border: 2px solid #31A0B4;
	}
	ul.sub li{
		float:none;
	}
	ul.sub li ul.sub{
		position:absolute;
		left:179px;
		top:0;
	}
	ul.menu{
		zoom:1;
	}
	ul.menu:after {
		height:0;
		visibility:hidden;
		content:".";
		display:block;
		clear:both;
	}
</style>
</head>
<div id="container">
	<div id="textline">
	<c:choose>
		<c:when test="${memId != null}">
			<c:choose>
				<c:when test="${memId.equals('admin')}">	
					<ul class="menu">
						<li><b><a href="#">서비스</a></b>
							<ul class="sub">
								<li><b><a href="#">예약</a></b></li>
								<li><b><a href="#">예약정보</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">커뮤니티</a></b>
							<ul class="sub">
								<li><b><a href="#">공지사항</a></b></li>
								<li><b><a href="#">사용후기</a></b></li>
								<li><b><a href="#">불편신고</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="#">아이디찾기</a></b></li>
								<li><b><a href="#">비밀번호찾기</a></b></li>
								<li><b><a href="#">환불규정</a></b></li>
								<li><b><a href="#">주차장제보</a></b></li>
								<li><b><a href="#">자주묻는질문</a></b></li>
								<li><b><a href="#">1:1문의</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">마이페이지</a></b>
							<ul class="sub">
								<li><b><a href="#">회원정보수정</a></b></li>
								<hr />
								<li><b><a href="#">포인트충전</a></b></li>
								<li><b><a href="#">포인트사용내역</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">회원관리</a></b></li>
						<li><b><a href="/Pis/semi/admin/carpark/carpark.do">주차장관리</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/semi/admin/carpark/carpark.do">주차장 관리</a></b></li>
								<li><b><a href="#">지역별 사용자빈도</a></b></li>
								<li><b><a href="/Pis/semi/admin/chart/days.do">일별 사용자빈도</a></b></li>
								<li><b><a href="/Pis/semi/admin/chart/month.do">월별 사용자빈도</a></b></li>
							</ul>
						</li>
						<li><b><a href="/Pis/semi/admin/board/noticeForm.do">게시글관리</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/semi/admin/board/noticeForm.do">공지사항</a></b></li>
								<li><b><a href="#">자주묻는질문관리</a></b></li>
								<li><b><a href="/Pis/semi/admin/board/question.do">1:1문의관리</a></b></li>
								<li><b><a href="#">주차장제보관리</a></b></li>
								<li><b><a href="#">불편신고관리</a></b></li>
							</ul>
						</li>
					</ul>
				</c:when>
				<c:otherwise>
			   	  	<ul class="menu">
						<li><b><a href="#">서비스</a></b>
							<ul class="sub">
								<li><b><a href="#">예약</a></b></li>
								<li><b><a href="#">예약정보</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">커뮤니티</a></b>
							<ul class="sub">
								<li><b><a href="#">공지사항</a></b></li>
								<li><b><a href="#">사용후기</a></b></li>
								<li><b><a href="#">불편신고</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="#">아이디찾기</a></b></li>
								<li><b><a href="#">비밀번호찾기</a></b></li>
								<hr />
								<li><b><a href="#">환불규정</a></b></li>
								<hr />
								<li><b><a href="#">주차장제보</a></b></li>
								<li><b><a href="#">자주묻는질문</a></b></li>
								<li><b><a href="#">1:1문의</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">마이페이지</a></b>
							<ul class="sub">
								<li><b><a href="#">회원정보수정</a></b></li>
								<hr />
								<li><b><a href="#">포인트충전</a></b></li>
								<li><b><a href="#">포인트사용내역</a></b></li>
							</ul>
						</li>
					</ul>
			</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<ul class="menu">
				<li><b><a href="#">서비스</a></b>
						<ul class="sub">
							<li><b><a href="#">예약</a></b></li>
							<li><b><a href="#">예약정보</a></b></li>
						</ul>
					</li>
					<li><b><a href="#">커뮤니티</a></b>
							<ul class="sub">
								<li><b><a href="#">공지사항</a></b></li>
								<li><b><a href="#">사용후기</a></b></li>
								<li><b><a href="#">불편신고</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">고객센터</a></b>
							<ul class="sub">
								<li><b><a href="#">아이디찾기</a></b></li>
								<li><b><a href="#">비밀번호찾기</a></b></li>
								<hr />
								<li><b><a href="#">환불규정</a></b></li>
								<hr />
								<li><b><a href="#">주차장제보</a></b></li>
								<li><b><a href="#">자주묻는질문</a></b></li>
								<li><b><a href="#">1:1문의</a></b></li>
							</ul>
						</li>
						<li><b><a href="#">마이페이지</a></b>
							<ul class="sub">
								<li><b><a href="/Pis/semi/logon/loginForm.do">로그인</a></b></li>
								<li><b><a href="#">회원정보수정</a></b></li>
								<hr />
								<li><b><a href="#">포인트충전</a></b></li>
								<li><b><a href="#">포인트사용내역</a></b></li>
							</ul>
						</li>
					</ul>
		</c:otherwise>
	</c:choose>
	</div>
</div>
	
