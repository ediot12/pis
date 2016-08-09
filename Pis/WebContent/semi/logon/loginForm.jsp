<%@ page contentType="text/html; charset=UTF-8"%>
<body>
<form name="myform" action="/Pis/semi/logon/loginPro.do" method="post" onSubmit="return checkIt()">
<table cellSpacing=1 cellpadding=1 width="260" border=1 align="center">
	<tr height="30"> 
		<td width="110" align="center">아이디</td>
		<td width="150" align="center">
			<input type="text" name="id" size="15" maxlength="12"></td></tr>
  <tr height="30">
  		<td colspan="2" align="middle">
  			<input type=submit value="로그인"></td></tr> 
  </table>
</body>