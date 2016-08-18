<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html> 
<head>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center>
<div class="colorblack">
<c:if test="${check==1}">
	<br>
	인증이 완료되었습니다. 
	<p>
	나머지 정보를 입력하여 주세요.
	</p>
</c:if>
<c:if test="${check!=1}">
	<br>
	인증에 실패하셨습니다.
	<p>
	인증번호를 다시 확인하여 주세요.
	</p>
	<br>
</c:if>
<a href="#" onclick="javascript:self.close();" class="button" >닫기</a>

</div>
</body>
</html>