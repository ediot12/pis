<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>   
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head> 
<body>   
<center>
<div class="colorblack">
<c:if test="${x==0}">
<script>
alert("비밀번호가 동일하지 않습니다.\n다시 입력해주세요.")
history.go(-1);
</script>
</c:if>
<c:if test="${x==1}">
 <div class="colorblack">
<br>
비밀번호가 변경되었습니다.
<p>오늘도 좋은 하루 되세요!</p>    
</div>
<br>
<a href="/Pis/layout/main.do" class="button">
닫기</a>
</c:if>
</body>  
</html>