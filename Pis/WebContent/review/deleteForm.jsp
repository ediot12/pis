<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1}">
<meta http-equiv="Refresh" content="0;url=/project/review/mainForm.do?pageNum=${pageNum}" >
<!DOCTYPE html >
</c:if>
<html>
<head>
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center><b>글삭제</b>
<br>
		<table>

			<c:choose>
				<c:when test="${check==1}">
					<script>
						alert("게시물이 삭제되었습니다.")
					</script>
				</c:when>


				<c:otherwise>
				</c:otherwise>
			</c:choose>
	</table>
</body>
</html>