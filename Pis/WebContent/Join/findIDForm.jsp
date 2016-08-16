`<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>아이디 찾기 폼</title>
</head>
<body bgcolor="${bodyback_c}">
<form action="findID.do" method="post">
<table border="1" width="220">
<tr bgcolor="${title_c}">
<td colspan="2">아이디 찾기</td>
</tr>
<tr>
<td>이름</td>
<td><input type="text" name="name" size="15" maxlength="10"></td>
</tr>
<tr>
<td>전화번호</td>
<td><input type="text" name="phone" size="15" maxlength="11">
</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" name="confirm" value="찾 기">
<input type="button" value="취소" onclick="document.location.href='/Pis/Join/main.do?pageNum=${pageNum}'"> 
</td>				
</tr>
</table>
</form>