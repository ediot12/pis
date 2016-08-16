<%@ page contentType="text/html; charset=UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<title>아이디찾기</title>
</head>
<body>
<c:if test="${id==''}">
<script>
alert("입력하신 정보가 맞지 않습니다.");
history.go(-1);
</script>
</c:if>
<c:if test="${id!=''}">
요청하신 아이디는 ${id}입니다.
<br><a href="/Pis/Join/findPWForm.do">비밀번호찾기</a>
<br><a href="/Pis/Join/main.do">창닫기</a>
</c:if>
</body>
</html>
