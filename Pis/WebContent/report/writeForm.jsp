<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html>
<head>

<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>
<body>
<center>
	<br> 
	<div class="title">
		<b>불편신고</b>
	</div>
		<form method="post" name="writeform"
			action="/Pis/report/writePro.do" onsubmit="return writeSave()">
			<input type="hidden" name="num" value="${num}">
				
			
			<table class="table" border="1" cellspacing="0" cellpadding="0"
			 style="table-layout:fixed">
				<tr>
					<td width="70" align="center" class="color"><b>작성자</b></td>
					<!-- 로그인한 id  -->
					<td width="150">${memId}</td>
						<td width="70" align="center" class="color"><b>불편 종류</b>
						<td width="230" colspan="3">
						<!-- 불편 종류 클릭시 서버로 전송될 값 -->
					<select name="type">
					<option value="">불편종류</option>
					<option value="결제">결제</option>
					<option value="불 친절">불 친절</option>
					<option value="주차 불편(주변 피해)">주차 불편(주변 피해)</option>
					<option value="불법 주정차">불법 주정차</option>
					<option value="기타">기타</option>
					</select></td>
						
					
				</tr>
				
				<tr>
					<td width="70" class="color"><b>제 목</b></td>
					<td width="330" colspan="5" align="left"><input type="text" size="44" maxlength="20"
						name="subject"></td>
				</tr>
					
				<tr>
					<td width="70" class="color"><b>내 용</b></td>
					<td width="330" colspan="5"><textarea name="content" rows="14" cols="146"></textarea>
					</td>
				</tr>

			</table>
			<input  type="submit" value="신고하기" class="button" OnClick="window.location='/Pis/report/mainForm.do'">
		</form>
</body>
</html>