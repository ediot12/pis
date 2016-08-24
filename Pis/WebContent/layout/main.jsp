<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.sql.*, java.util.*, java.util.Date, java.text.*" %>
<!DOCTYPE html>
<html>  
<head>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<center>
	<br>
	<div class="main">
		오늘 방문자 : ${todayCount} 
		총 방문자 : ${totalCount}
	</div>
	<br><br><img src="../image/main.jpg" alt="메인이미지" width="1100px" height="330px" id="mainimg"><br><br><br><br><br>
	<table>
	<tr><td>
	<table class="maintable"> 
		<tr>
			<td colspan="3"><b>공지사항</b></td>
			<td class="main"><a href="/Pis/notice/list.do"><b>+더보기</b></a></td>
		</tr>
		<tr height="10"></tr>
		<tr height="10">
			<td class="maintext" >번호</td>
			<td class="maintext" colspan="2">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</td>
			<td class="maintext" >작성일</td>
		</tr>
		<c:forEach var="bdb" items="${articleList}">
		<tr height="15">
			<td class="maincolor">
				<c:out value="${number}" />
				<c:set var="number" value="${number - 1}" />
			</td>
			<td class="maincolor" colspan="2"><a href="/Pis/notice/list.do?num=${bdb.num}&pageNum=${currentPage}">${bdb.subject}</a></td>
			<td class="maincolor">${date.format(bdb.regdt)}</td>
		</tr>
		</c:forEach>
		 <tr>
			<td width="10%"></td>
			<td width="15%"></td>
	        <td width="60%"></td>
	        <td width="15%"></td>
		</tr>
	</table>
	</td>
	<td>
	<%--자주묻는 질문 --%>
	<table class="maintable">
		<tr>
			<td colspan="3"><b>자주묻는질문</b></td>
			<td class="main"><a href="/Pis/faq/faqForm.do"><b>+더보기</b></a></td>
		</tr>
		<tr height="10"></tr>
		<tr height="10">
			<td class="maintext" >번호</td>
			<td class="maintext">문의유형</td>
			<td class="maintext" colspan="2">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</td>
		</tr>
		<c:forEach var="fdb" items="${articleList2}">
        	<tr height="10">
	            <td class="maincolor"><c:out value="${num}" />
					<c:set var="num" value="${num - 1}" /></td>
				<td class="maincolor">${fdb.kind}</td>
	            <td class="maincolor" colspan="2"><a href="/Pis/faq/faqForm.do">${fdb.subject}</a></td>
           </tr>
       	</c:forEach>
       		<tr>
	            <td width="10%"></td>
				<td width="15%"></td>
	            <td width="60%"></td>
	            <td width="15%"></td>
           </tr>
    	</td>
    </tr>
	</table>
	</table>

</body>
</html>
	