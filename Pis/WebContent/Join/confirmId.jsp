<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>ID 중복확인</title>
<link href="../style.css" rel="stylesheet" type="text/css">
<script>
  function setid()   
    {
    opener.document.userinput.id.value='${id}';//opener:새로운 창을 연다.
    self.close();
}
</script>
</head> 
<body>

<c:if test="${check==1}">
<table width="300" border="0" cellspacing="0" cellpadding="5">
  <tr>
   <td height="50" class="colorblack" align="center">${id}는 사용중인 아이디입니다.</td>
  </tr>
</table>
<form name="checkForm" method="post" action="/Pis/Join/confirmId.do">
<table width="300" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td align="center" class="colorblack">
       <p>다른 아이디를 입력하세요.</p>
       <input type="text" size="10" maxlength="12" name="id">
       <input type="submit" value="ID중복확인"  class="button">
    </td>
  </tr>
</table>
</form>
</c:if>
<c:if test="${check !=1}" >
<table width="300" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td align="center" class="colorblack">
      <p>입력하신 ${id}는 사용하실 수 있는 
      <br>ID 입니다.</p>
      <input type="button" value="닫기" onclick="setid();" class="button">
    </td>
  </tr>
</table>
</c:if>
</body>
</html>
