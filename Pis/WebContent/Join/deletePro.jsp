<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원탈퇴</title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>

<c:if test="${check==1}">
<body>
<form method="post" action="/Pis/layout/main.do" name="userinput" >
<table border="0" cellspacing="0" cellpadding="5" class="shorttable">
  <tr>
  <td>
  <font size="+1" ><b>회원정보가 삭제되었습니다.</b></font>
      <meta http-equiv="Refresh" content="5;url=/Pis/layout/main.do" >
    </td> 
  </tr>
</table>
<input type="submit" value="확인" class="button">
</form>
</c:if>
<c:if test="${check!=1}">
<script>
  alert("비밀번호가 맞지 않습니다.");
      history.go(-1);
</script>
</c:if>

</body>
</html>