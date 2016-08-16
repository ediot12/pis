<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 수정</title>
</head>
<body bgcolor="${bodyback_c}">
<c:if test="${x==1}">
</c:if> <br>비밀번호가 변경되었습니다.
<a href="/Pis/Join/main.do">창닫기</a>
</body>
</html>