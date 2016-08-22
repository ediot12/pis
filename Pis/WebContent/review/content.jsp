<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html>

<head>
<style>
	.box-button{
    padding-top: 20px;
    padding-bottom: 10px;
	}
	.box-gray{
	background: #555c67;
    padding: 4px 7px;
    border: 0px;
    color: #ffffff !important;
    cursor: pointer;
	}

</style>
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>

</head>
<body>
	<center>
		<b>글내용 보기</b> <br>
		
		
		<form>
			<table width="500" height="200"border="1" cellpadding="0" cellspacing="0" 
			      align="center" style="table-layout:fixed">

				
				<tr>
					<td align="center" width="70"><b>작성자</b></td>
					<td colspan="3" align="center">${article.writer}</td>
				</tr>

				<tr>
					<td align="center" width="70"><b>제목</b></td>
					<td colspan="3" align="center">${article.subject}</td>
				</tr>

				<tr>
					<td align="center" width="70"><b>내용</b></td>
					<td width="300" colspan="3" style="word-wrap:break-word"><pre style="white-space: pre-wrap;"><img src="../filesave/${article.bfile}" width="250px" height="250px" ><br><b>${article.content}</b></pre>
					</td>
				</tr>
	
				<tr>
					<td colspan="4" align="right">
					<input class="box-gray" type="button" value="글수정" onclick="document.location.href='updateForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="box-gray" type="button" value="글삭제" onclick="document.location.href='deleteForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="box-gray" type="button" value="글목록" onclick="document.location.href='mainForm.do?pageNum=${pageNum}'">
					</td>
				</tr>
			</table>

		</form>
	
</body>
</html>