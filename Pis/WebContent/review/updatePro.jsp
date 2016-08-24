<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="article" scope="page" class="mvc.review.ReviewDataBean">
   <jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<c:if test="${check==1}">
<meta http-equiv="Refresh" content="0;url=/Pis/review/mainForm.do?pageNum=${pageNum}" >
</c:if>

 
<!DOCTYPE html >
<html>
<head>
<title></title>
</head>
<body>

</body>
</html>