<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String id = request.getParameter("id");

	session.setAttribute("memId", id);
	response.sendRedirect("main.jsp");
%>
<meta http-equiv="Refresh" content="0;url=main.jsp">