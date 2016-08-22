<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
</head>
<body>
	<c:if test="${unpass==0 }">
		<script>	
			alert('30분 미만은 예약 할 수 없습니다.');
			history.go(-1);
		</script>
	</c:if>

	<c:if test="${notpass!=null }">
		<script>
			alert('포인트가 모자랍니다.\n현재포인트 : ${remainPoint}\n결제포인트 : ${pay}');
			location.replace("/Pis/pointcharge/point.do");
		</script>
	</c:if>
</body>
</html>