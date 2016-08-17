<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head> 
	<title>PIS(주차장안내시스템)<decorator:title /></title>
	<style type="text/css">
	</style>
	<decorator:head />
	<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%">
		<tr>
			<td colspan="3" width="100%" height="120px">
				<jsp:include page="layout/header.jsp" flush="true" />
			</td>
		</tr>
		<tr> 
			<td colspan="3" width="100%" height="30px">
				<jsp:include page="layout/nav.jsp" flush="true" />
			</td>
		</tr>
		<tr>
			<td valign="top" width="21%" height="700px"></td>
			<td valign="top" width="57%" height="700px" id="main"><decorator:body /></td>
			<td valign="top" width="22%" height="700px"></td>
		</tr>
		<tr>
			<td colspan="3" width="100%" height="40px" align="center">
				<jsp:include page="layout/footer.jsp" flush="true" />
			</td>
		</tr>
	</table>

</body>
</html>