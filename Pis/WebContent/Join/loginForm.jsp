<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf"%>
<!DOCTYPE html>
<html>
<head><title>로그인</title>
<link href="style.css" rel="stylesheet" type="text/css">

   <script>
       function begin(){
         document.myform.id.focus();
       }
       function checkIt(){
         if(!document.myform.id.value){
           alert("이름을 입력하지 않으셨습니다.");
           document.myform.id.focus();
           return false;
         }
         if(!document.myform.passwd.value){
           alert("비밀번호를 입력하지 않으셨습니다.");
           document.myform.passwd.focus();
           return false;
         }
         
       }
    
       </script>
       </head>
       <BODY onload="begin()" bgcolor="${team}">
       <form name="myform" action="loginPro.do" method="post" onSubmit="return checkIt()">
       <TABLE cellSpacing=1 cellPadding=1 width="280" border=1 align="center" >
         
         <TR height="30">
          <TD colspan="2" align="left" bgcolor="${title_c}"><STRONG>로그인</STRONG></TD></TR>
           <td width="150" bgcolor="${value_c}" align=center>
              <INPUT type="text" name="id" size="15" maxlength="12">
              <td rowspan="2">
              <INPUT type=submit value="로그인"></td>
              </TD></TR>
         <TR height="30">
           <TD width="150" bgcolor="${value_c}" align=center>
             <INPUT type=password name="passwd"  size="15" maxlength="12"></TD></TR>
         <TR height="30">
           <TD colspan="2" align="left" bgcolor="${title_c}" >
    		<input type="button" value="아이디찾기 " onclick="javascript:window.location='findIDForm.do'">
    		<input type="button" value="비밀번호찾기" onclick="javascript:window.location='findPWForm.do'">
          	<input type="button" value="회원가입" onclick="javascript:window.location='inputForm.do'">
   	   </TD></TR>
       </TABLE>
       </form>
	   
       </BODY>
       </HTML>  