<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head><title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
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
       <BODY onload="begin()">
       <center>
       <br>
	<div class="title">
		<b>로그인</b>
	</div>
       <form name="myform" action="/Pis/Join/loginPro.do" method="post" onSubmit="return checkIt()">
       <TABLE cellSpacing=0 cellPadding=0 border=1 align="center" class="shorttable">
         <TR height="30">
         	<td width="30%" class="color"> 아이디 </td>
           	<td width="50%">
              <INPUT type="text" name="id" size="15" maxlength="12">
           	<td rowspan="2" width="20%">
              <INPUT type=submit value="로그인" class="button"></td>
         </TR>
         <TR height="30">
         	<td class="color">비밀번호</td>
           <TD>
             <INPUT type=password name="passwd"  size="15" maxlength="12"></TD></TR>
        </TABLE>
    		<input type="button" value="아이디찾기 " onclick="javascript:window.location='findIDForm.do'" class="button">
    		<input type="button" value="비밀번호찾기" onclick="javascript:window.location='findPWForm.do'" class="button">
          	<input type="button" value="회원가입" onclick="javascript:window.location='inputForm.do'" class="button">
       </form>
	   
       </BODY>
       </HTML>  