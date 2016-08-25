<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link href="../style.css" rel="stylesheet" type="text/css">
<body> 

<c:if test="${!empty id }">
<form name=reservation  method=post action="/Pis/pointcharge/paymentForm.do">

<br>
<center> 
			<p><h1>결제시스템 </h1></p>
			<br>
		
 	<table width="600" border="1" cellspacing="0" cellpadding="0" >
    <tr height="40">
        <td class="color" width="110">결제수단</td> <!--신용카드/가상계좌/계좌이체/월드패스카드/포인트/휴대폰결제/상품권-->
        <td width="290">
      	<!-- <input type="hidden" name="sndPayMethod" value="1000000000"> 신용카드인 경우 -->
			<select name="pay_method" id="pay_method" >
				<option value="card">신용카드</option>
				<option value="trans">실시간계좌이체</option>
				<option value="vbank">가상계좌</option>
				<option value="phone">휴대폰소액결제</option>
			</select>
		 </td>
	</tr>
	
	<tr height="40">
		<td class="color" width="110">아이디</td> 
		<!--주문번호는 50Byte(한글 25자) 입니다. ' " ` 는 사용하실수 없습니다. 따옴표,쌍따옴표,백쿼테이션 -->
		<td class="colorblack"  width="290">${memId }</td>
	</tr>
	
	<tr height="40">
		<td class="color" width="110">이름</td> 
		<!--  상품명 50Byte(한글 25자) 입니다. ' " ` 는 사용하실수 없습니다. 따옴표,쌍따옴표,백쿼테이션-->
		<td class="colorblack"  width="290">${name }</td>
	</tr>
	
	<tr height="40">
		<td class="color" width="110">충전금액</td> 
		<!--금액은 ,없이 입력 -->
 		<td class="colorblack"  width="290"> ${point }</td>
	</tr>

</table>
<br>
<input class="button" type="submit" value="결 제">
</form>
</c:if>
<c:if test="${empty id }">
	<script>
	alert("로그인 후 이용해주세요");
	location.href="/Pis/Join/loginForm.do";	// 로그인페이지로 이동
	
	</script>
</c:if>
</body>
</html>