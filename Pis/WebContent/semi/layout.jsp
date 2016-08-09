<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html lang="ko">
<head>
	<title>PIS(주차장안내시스템)<decorator:title /></title>
	<style type="text/css">
	</style>
	<decorator:head />
	<style>
		td{
			color:#31A0B4;
		}
	</style>
</head>
<body>
	<table width="1900px">
<%
    String memId = (String) session.getAttribute("memId");
	if(memId!=null){
	if(memId.equals("admin")){
%> 
		<tr>
			<td colspan="3" width="1900px" height="120px">
				<jsp:include page="layout/header_admin.jsp" flush="true" />
			</td>
		</tr>
		<tr>
			<td colspan="3" width="1900px" height="30px">
				<jsp:include page="layout/nav_admin.jsp" flush="true" />
			</td>
		</tr>
<% } else {%>
		<tr>
			<td colspan="3" width="1900px" height="120px">
				<jsp:include page="layout/header_login.jsp" flush="true" />
			</td>
		</tr>
		<tr>
			<td colspan="3" width="1900px" height="30px">
				<jsp:include page="layout/nav.jsp" flush="true" />
			</td>
		</tr>
<%}}else{%>
		<tr>
			<td colspan="3" width="1900px" height="120px">
				<jsp:include page="layout/header.jsp" flush="true" />
			</td>
		</tr>
		<tr>
			<td colspan="3" width="1900px" height="30px">
				<jsp:include page="layout/nav.jsp" flush="true" />
			</td>
		</tr>
<%} %>
		<tr>
			<td valign="top" width="350px" height="700px"></td>
			<td valign="top" width="1100px" height="700px"><decorator:body /></td>
			<td valign="top" width="350px" height="700px"></td>
		</tr>
		<tr>
			<td colspan="3" width="1900px" height="40px" align="center">
				<jsp:include page="layout/footer.jsp" flush="true" />
			</td>
		</tr>
	</table>

</body>
</html>