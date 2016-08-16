<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
function zipCheck(){
	
	url="Zipcheck.do?check=y";
	window.open(url,"post","toolbar=no,width=100,heignt=100,directories=no,status=yes,scrollbars=yes,menubar=no");
}

function checkIt(){
    var userinput = eval("document.userinput");
          
    if(!userinput.passwd.value ) {
        alert("비밀번호를 입력하세요");
        return false;
    }
    
    if(userinput.passwd.value != userinput.passwd2.value)
    {
        alert("비밀번호를 동일하게 입력하세요");
        return false;
    }
	
    if(!userinput.phone.value){
		alert("전화번호를 입력하세요");
		return false;
	}
	
    if(!userinput.zipcode.value){
		alert("우편번호를 검색하세요");
		return false;
	}
	
    if(!userinput.address.value){
		alert("주소를 전부 입력해주세요");
		return false;
	}
	
    if(!userinput.email.value){
		alert("이메일을 적어주세요");
		return false;
	}
	return true;
}

</script>
</head>
<body bgcolor="${bodyback_c}">
<form method="post" action="/Pis/Join/modifyPro.do" name="userinput" onsubmit="return checkIt()" enctype="multipart/form-data">
<table width="600" border="1" cellspacing="0" cellpadding="3"  align="center">
<tr>
<td colspan="2" height="39" bgcolor="${title_c}" align="center">
<font size="+1" ><b>회원 정보수정</b></font></td>
</tr>
<tr>
<td width="200">아이디</td>
<td width="400">${c.id}</td>
</tr>
<tr>
<td width="200">비밀번호</td> 
<td width="400">
<input type="password" name="passwd" size="10" maxlength="10">
</td>
</tr>
<tr>
<td width="200">비밀번호 확인</td>
<td width="400">
<input type="password" name="passwd2" size="10" maxlength="10">
</td>
</tr> 
<tr>
<td width="200">이름</td>
<td width="400">${c.name}
</td>
</tr>
<tr>
<td width="200">전화번호</td>
<td width="400"><input type="text" name="phone" size="15" maxlength="11" value="${c.phone}">
</td>
</tr>
<tr>
<td width="200">우편번호</td>
<td width="400">
<c:if test="${c.zipcode==null}">
<input type="text" name="zipcode" size="7" readonly>
</c:if>
<c:if test="${c.zipcode!=null}">
<input type="text" name="zipcode"size="7" value="${c.zipcode}">
</c:if>
<input type="button" value="우편번호찾기" onClick="zipCheck()">
</td>
</tr>     
<tr>
<td width="200">주소</td>
<td width="400">
<c:if test="${c.address==null}">
<input type="text" name="address" size="60" maxlength="50" >
</c:if>
<c:if test="${c.address!=null}">
<input type="text" name="address" size="60" maxlength="50" value="${c.address}">
</c:if>
</td>
</tr>
<tr>
<td width="200">E-Mail</td>
<td width="400">
<c:if test="${c.email==null}">
<input type="text" name="email" size="30" maxlength="30">
</c:if>
<c:if test="${c.email!=null}">
<input type="text" name="email" size="30" maxlength="30" value="${c.email}">
</c:if>
</td>
</tr>
<tr>
<td width="200">거주자여부</td>
<td width="400">
<c:if test="${c.resident==1}">
<input type="radio" name="resident" value="1" checked="checked">거주자
<input type="radio" name="resident" value="2">비거주자
</c:if>
<c:if test="${c.resident==2}">
<input type="radio" name="resident" value="1">거주자
<input type="radio" name="resident" value="2" checked="checked">비거주자
</c:if>

</td>
</tr>
<tr>
<td width="200">거주자자료첨부</td>
<td width="400">
<input type="file" name="upload"><br/>
</td>
</tr>
<tr>
<td colspan="2" align="center" bgcolor="${value_c}">
<input type="button" value="취  소" onclick="javascript:window.location='main.jsp'">   
<input type="submit" name="modify" value="등 록" > 
<input type="button" value="탈 퇴" onclick="javascript:window.location='deleteForm.jsp'"> 
</td>
</tr>
</table>
</form>
</body>
</html>