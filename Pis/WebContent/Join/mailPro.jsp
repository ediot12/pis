<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>메일인증</title>
</head>
<body bgcolor="${bodyback_c}">
<div align="center">
<c:if test="${check==1}">
	인증이 완료되었습니다. 
	<p>
	나머지 정보를 입력하여 주세요.
	</p>
</c:if>
<c:if test="${check!=1}">
	인증에 실패하셨습니다.
	<p>
	인증번호를 다시 확인하여 주세요.
	</p>
</c:if>
<a href="#" onclick="javascript:self.close();">창 닫기</a>
</div>
</body>
</html>