<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check==1}">
	인증 됨
</c:if>
<c:if test="${check!=1}">
	안 됨
</c:if>