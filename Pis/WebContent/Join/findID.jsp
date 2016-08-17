<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${id==''}">
<script>
alert("입력하신 정보가 맞지 않습니다.");
history.go(-1);
</script>
</c:if>
<c:if test="${id!=''}">
<center>
<br>
<div class="title">
	<b>아이디 찾기</b>
</div>
<div class="colorblack"> 
요청하신 아이디는 ${id}입니다.
</div>
<br><a href="/Pis/Join/findPWForm.do"><input type="button" value="비밀번호찾기" class="button"></a>
&nbsp;&nbsp;<a href="/Pis/Join/main.do"><input type="button" value="창닫기" class="button"></a>
</c:if>
</body>
</html>
