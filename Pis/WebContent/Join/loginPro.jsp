<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${check==1}">
	<meta http-equiv="Refresh" content="0;url=/Pis/Join/main.do"> 
	</c:when>
<c:when test="${check==0}">
<script>
alert("비밀번호가 맞지 않습니다.");
history.go(-1);
</script>
</c:when>
<c:otherwise>

<script>
alert("아이디가 맞지 않습니다.");
history.go(-1);
</script>
</c:otherwise>
</c:choose>
