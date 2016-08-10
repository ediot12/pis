<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<style>
	.table{
		width:1100px;
		text-align: center;
		margin : 20;
		border-color: #31A0B4;
	}
	.title{
		font-size:30px;
		text-align: left;
		height: 40px;
	}
	.button{
		background:#31A0B4;
		color:#FFFFFF;
		margin : 0 2;
		padding: 5;
	}
	.color{
		background:#31A0B4;
		color:#FFFFFF;
	}
</style>
</head>

<body> 
<center>
	<br>
	<div class="title">
	<b>공지사항</b>
	</div>
<br>
<form>
<table border="1" cellspacing="0" cellpadding="0" align="center"> 
  <tr height="30">
    <td align="center" width="200" class="color">글제목</td>
    <td align="center" width="650" align="center">${bdb.subject}</td>
    <td align="center" width="200" class="color">조회수</td>
    <td align="center" width="100" align="center">${bdb.readcount}</td>
  </tr>
  <tr>
    <td align="center" width="200" height="450" class="color">글내용</td>
    <td align="left" colspan="3" width="950"><pre>${bdb.content}</pre></td>
  </tr>
</table>
<br>
	<input type="button" value="글수정" class="button"
       onclick="document.location.href='/Pis/semi/admin/board/noticeUpdateForm.do?num=${bdb.num}&pageNum=${pageNum}'">
   &nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="글삭제" class="button"
       onclick="document.location.href='/Pis/semi/admin/board/noticeDeletePro.do?num=${bdb.num}&pageNum=${pageNum}'">
   &nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="글목록" class="button"
       onclick="document.location.href='/Pis/semi/admin/board/noticeForm.do?pageNum=${pageNum}'">
</form>     
</body>
</html>    