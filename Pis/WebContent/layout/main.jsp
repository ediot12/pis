<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.sql.*, java.util.*, java.util.Date, java.text.*" %>
<!DOCTYPE html>
<html> 
<head>
<style type="text/css">
	#maintext{
		text-align: center
	}
	.maintable{
		font-size : 20px;
		color:#31A0B4;
		text-align: center; 
	}
	#mainimg{
		position: relative;
		z-index: -1;
	}
	.maintext{ 
		background: #31A0B4;
		color : #FFFFFF;
		font-size: 11px;
	}
	.maincolor{
		color : #000000;
		font-size : 11px;
	}
	a:HOVER {
		text-decoration:underline;
	}
	a{
		text-decoration:none;
		color : #000000;
	}
</style>
</head>
<body>
	<center>
	<div id="maintext">
		오늘 방문자 : ${todayCount} 
		총 방문자 : ${totalCount}
	</div>
	<br><br><img src="../image/main.jpg" alt="메인이미지" width="1100px" height="330px" id="mainimg"><br><br><br><br><br>
	<table>
	<tr><td>
	<table class= "maintable">
		<tr>
			<td width="500px" colspan="3"><b>공지사항</b></td>
		</tr>
		<tr height="10"></tr>
		<tr height="10">
			<td width="50px" class="maintext" >번호</td>
			<td width="350px" class="maintext" >제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</td>
			<td width="150px" class="maintext" >작성일</td>
		</tr>
		<c:forEach var="bdb" items="${articleList}">
		<tr height="15">
			<td class="maincolor">
				<c:out value="${number}" />
				<c:set var="number" value="${number - 1}" />
			</td>
			<td class="maincolor"><a href="/Pis/admin/board/noticeContent.do?num=${bdb.num}&pageNum=${currentPage}">${bdb.subject}</a></td>
			<td class="maincolor">${date.format(bdb.regdt)}</td>
		</tr>
		</c:forEach>
	</table>
	</td>
	<td>
	<%--자주묻는 질문 --%>
	<table class="maintable">
		<tr>
			<td width="500px" colspan="3"><b>자주묻는질문</b></td>
		</tr>
		<tr height="10"></tr>
		<tr height="10">
			<td width="50px" class="maintext" >번호</td>
			<td width="150px" class="maintext">문의유형</td>
			<td width="350px" class="maintext" >제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</td>
		</tr>
		<c:forEach var="fdb" items="${articleList2}">
        	<tr>
	            <td class="maincolor"><c:out value="${num}" />
					<c:set var="num" value="${num - 1}" /></td>
				<td class="maincolor">${fdb.kind}</td>
	            <td class="maincolor"><a href="/Pis/admin/board/FAQ.do">${fdb.subject}</a></td>
           </tr>
       	</c:forEach>
    </td>
    </tr>
	</table>
	</table>
</body>
</html>
	