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
	.colorblack{
		color:#000000;
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
<table border="1" cellspacing="0" cellpadding="0" align="center" class="table"> 
  <tr height="30">
    <td align="center" width="15%" class="color">작성자</td>
    <td align="center" width="35%" align="center" class="colorblack">${rdb.subject}</td>
    <td align="center" width="15%" class="color">불편종류</td>
    <td align="center" width="35%" align="center" class="colorblack">${rdb.kind}</td>
  </tr>
  <tr height="30">
    <td align="center" class="color">제목</td>
    <td align="center" align="center" class="colorblack" colspan="3">${rdb.kind}</td>
  </tr>
  <tr>
    <td align="center" height="450" class="color">글내용</td>
    <td align="left" colspan="3" width="950" class="colorblack" style="word-wrap:break-word"><pre style="white-space: pre-wrap;">${rdb.content}</pre></td>
  </tr>
</table>
<br>
       <input type="button" value="글목록" class="button"
       onclick="document.location.href='/Pis/semi/admin/board/report.do?pageNum=${pageNum}'">
</form>     
</body>
</html>    