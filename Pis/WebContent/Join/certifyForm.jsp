<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>이메일 인증코드 입력</title>
</head>
<body>
<body bgcolor="${bodyback_c}">
<form action="/Pis/Join/mailPro.do" method="post">
<table border="1" width="220">
<tr bgcolor="${title_c}">
<td colspan="2">이메일 인증번호</td>
</tr>
<tr>
<td colspan="2"><input type="text" name="tempkey" size="19" maxlength="6">
<input type="submit" name="" value="전 송"></td>
</tr>
</body>
</html>