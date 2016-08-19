<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="../../style.css" rel="stylesheet" type="text/css">
<script>
	function Checked(){
			url="memberCheck.do?id="+${mdb.id};
			window.open(url,"post","toolbar=no,width=100, heignt=100,directories=no,status=yes,scrollbars=yes,menubar=no");
	}
</script>
</head>
<body>
	<center>
	<br>
	<div class="title">
		<b>"${mdb.name}"</b>님 회원관리
		<a href="/Pis/admin/member/member.do">
			<input type="button" value="목록보기" class="button">
		</a>
	</div>
	<table class="table" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td width="10%" class="color">아이디</td>
			<td width="15%" class="colorblack">${mdb.id}</td>
			<td width="10%" class="color">이름</td>
			<td width="15%" class="colorblack">${mdb.name}</td>
			<td width="10%" class="color">회원등급</td>
			<td width="15%" class="colorblack">${mdb.grade}</td>
			<td width="10%" class="color">비밀번호</td>
			<td width="15%" class="colorblack">${mdb.passwd}</td>
		</tr>
		<tr>
			<td class="color">거주자여부</td>
			<td class="colorblack">
				<c:if test="${mdb.resident==1}">거주자</c:if>
				<c:if test="${mdb.resident==2}">비거주자</c:if>
			</td>
			<td class="color">거주자승인</td>
			<td class="colorblack">
				<c:if test="${mdb.resident==1}">
					<c:if test="${mdb.checked=='거주자 미 승인'}">
						<form action="/Pis/admin/member/memberupdate.do?id=${mdb.id}" method="post">
							거주자승인요청<input type="submit" value="승인" class="button">
						</form>
					</c:if>
					<c:if test="${mdb.checked=='거주자 승인'}">거주자승인완료</c:if>
				</c:if>
				<c:if test="${mdb.resident==2}">비거주자</c:if>
			</td>
			
			<td class="color">거주자승인 자료</td>
			<td class="colorblack" colspan="3"><input type="button" value="정보보기" onClick="Checked()" class="button"></td>
			
		</tr>
		<tr>
			<td class="color">할인율</td>
			<td class="colorblack" colspan="3">${mdb.discount}</td>
			<td class="color">포인트잔액</td>
			<td class="colorblack" colspan="3">${mdb.point}</td>
		</tr>
		<tr>
			<td class="color">우편번호</td>
			<td class="colorblack">${mdb.zipcode}</td>
			<td class="color">주소</td>
			<td class="colorblack" colspan="5">${mdb.address}</td>
		</tr>
		<tr>
			<td class="color">전화번호</td>
			<td class="colorblack" colspan="3">${mdb.phone}</td>
			<td class="color">이메일</td>
			<td class="colorblack" colspan="3">${mdb.email}</td>
		</tr>
	</table>
</body>
</html>