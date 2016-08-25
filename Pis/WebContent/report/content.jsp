<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title></title>
</head>
<body>
<center>
	<br>
	<div class="title">
		<b>불편신고</b>
	</div> 
	
	<table class="table" border="1" cellspacing="0" cellpadding="0"
			 style="table-layout:fixed">
				<tr>
					<td width="70" align="center" class="color"><b>작성자</b></td>
					<td width="150" align="left"><h3>&nbsp;${memId}</h3></td>
						<td width="70" class="color"><b>불편 종류</b>
						<td width="230" colspan="3" align="left"><h3>${article.type}</h3></td>
				</tr>
				
				<tr>
					<td width="70" class="color"><b>제 목</b></td>
					<td width="330" colspan="5" align="left"><h3>&nbsp;${article.subject }</h3></td>
				</tr>
					
				<tr height="350">
					<td width="70" class="color"><b>내 용</b></td>
					<td width="330" height="40" colspan="5" align="left" style="word-wrap:break-word">
					<pre style="white-space: pre-wrap;"><h3>${article.content }</h3></pre>
					</td>
				</tr>

			</table>
			<input  type="submit" value="글 목록" class="button" OnClick="window.location='/Pis/report/mainForm.do'">
		</form>
</body>
</html>