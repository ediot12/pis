<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>
<body>
<center>
		<b>불편 신고 작성</b> <br>
		<form method="post" name="writeform"
			action="/project/report/writePro.do" onsubmit="return writeSave()">
			<input type="hidden" name="num" value="${num}">
				
			
			<table width="650" border="1" cellspacing="0" cellpadding="0"
				align="center" style="table-layout:fixed">


				<tr>
					<td width="70" align="center"><b>작성자</b></td>
					<td width="330"><input type="text" size="20" maxlength="10"
						name="writer"></td>
						
					
				</tr>
				
				<tr>
					<td width="70" align="center"><b>제 목</b></td>
					<td width="330"><input type="text" size="20" maxlength="20"
						name="subject"></td>
				</tr>
					
				<tr>
					<td width="70" align="center"><b>내 용</b></td>
					<td width="330"><textarea name="content" rows="14" cols="70"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan=2 align="center">
					<input type="submit" value="글 등록">
					<input type="button" value="목록보기" OnClick="window.location='/project/report/mainForm.do'"></td>
					
				</tr>
				
			</table>
		</form>
</body>
</html>