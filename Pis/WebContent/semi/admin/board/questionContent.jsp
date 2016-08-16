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
	.titlesmall{
		text-align: left;
		height: 10px;
		color:#000000;
	}
	.title .button{
		float: right;
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
	<b>${qdb.writer}님의 1:1문의사항</b>
       <input type="button" value="글목록" class="button"
       onclick="document.location.href='/Pis/semi/admin/board/question.do?pageNum=${pageNum}'">
	</div>
<br>
<form>
<table border="1" cellspacing="0" cellpadding="0" align="center" class="table"> 
  <tr height="30">
    <td align="center" width="10%" class="color">작성자</td>
    <td align="center" width="20%" align="center" class="colorblack">${qdb.writer}</td>
     <td align="center" width="10%" class="color">문의종류</td>
    <td align="center" width="25%" align="center" class="colorblack">${qdb.kind}</td>
    <td align="center" width="10%" class="color">작성일</td>
    <td align="center" width="25%" align="center" class="colorblack">${qdb.regdt}</td>
  </tr>
  <tr height="30">
     <td align="center" class="color">제 목</td>
    <td align="center" align="center" class="colorblack" colspan="5">${qdb.subject}</td>
  </tr>
  <tr>
    <td align="center" height=200" class="color">글내용</td>
    <td align="left" colspan="5" class="colorblack" style="word-wrap:break-word"><pre style="white-space: pre-wrap;">${qdb.content}</pre></td>
  </tr>
  </table>
</form>
<form method=post action=/Pis/semi/admin/board/questionContentPro.do>
	<div class="titlesmall">
		<b>답변작성</b>
	</div>
	<table border="1" cellspacing="0" cellpadding="0" class="table">
			<tr height="50">
				
					<td colspan=6>
						<textarea name=commentt rows="10" cols="120"></textarea>
						<input type=hidden name=content_num value=${qdb.num}>
						<input type=hidden name=p_num value=${pageNum}>
						<input type=hidden name=comment_num value=${count+1}>
						<input type=submit value=답변달기 class="button">
					</td>
				</tr>
			</table>
		</form>
		
		<c:if test="${count>0}">
		<p>
		<table width=100% border=0 cellspacing=0 cellpadding=0 align=center>
		<c:forEach var="dbc" items="${comments}">
			<tr>
				<td align=left size=250 class="colorblack">&nbsp;<b>관리자</b>
				(답변등록일 : ${date.format(dbc.reg_date)})</td>
				<td align = right size=250 class="colorblack">
				<a href="/Pis/semi/admin/board/questionDelComPro.do?content_num=${dbc.content_num}&comment_num=${dbc.comment_num}&pageNum=${pageNum}">
					<input type="button" value="삭제" class="button"></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="colorblack">${dbc.commentt}</td>
		</c:forEach>
			</tr>
		</table>
		</c:if>
</body>
</html>    