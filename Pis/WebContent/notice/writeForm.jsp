<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<title>게시판 공지사항</title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center>
	<br>
	<div class="title">
		<b>글 쓰기</b>
		<a href="/project/pis/list.do">
		<input type="button" value="글목록" class="button"></a>
	</div>
	<br>  
		<form method="post" name="writeform" action="/Pis/pis/writePro.do"
			onsubmit="return writeSave()">
			<input type="hidden" name="num" value="${num}">
			<table widht="800" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70" class="color">이 름</td>
					<td width="200" align="left"><input type="text" size="30" maxlength="10"
						name="writer"></td>
				</tr>
				<tr>
					<td width="70" class="color">제 목</td>
					<td width="330" align="left"><input type="text" size="30" maxlength="20"
						name="subject"></td>
				</tr>

				<tr>
					<td width="70"  class="color">내 용</td>
					<td width="330" align="left"><textarea name="content" rows="13" cols="40"></textarea>
					</td>
				</tr>
				
			</table>
			<input class="button" type="submit" value="글 등록"> 
			<input class="button" type="reset" value="수정하기"> 
			<input class="button" type="button" value="목록보기" OnClick="window.location='/Pis/pis/list.do'">
		</form>
</body>
</html>