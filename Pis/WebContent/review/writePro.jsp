<%@ page  contentType="text/html; charset=UTF-8"%>

<jsp:useBean id="article" scope="page" class="mvc.review.ReviewDataBean">
   <jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<!DOCTYPE html >
<html>
<head>
<title></title>
</head>
<body>
<%response.sendRedirect("mainForm.do");%>
</body>
</html>