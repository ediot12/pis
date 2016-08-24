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
		<b>후기 글 작성</b>
	</div> 
	<br>
		<form method="post" name="writeform"
			action="/Pis/review/writePro.do" onsubmit="return writeSave()" enctype="multipart/form-data">
			<input type="hidden" name="num" value="${num}">
			<table class="table" border="1" cellspacing="0" cellpadding="0"
				align="center" style="table-layout:fixed">
				<tr>
					<td width="70" class="color"><b>작성자</b></td>
					<!-- 로그인한 아이디 -->
					<td width="230" align="left" >${memId }</td>
						<td width="70" class="color"><b>평가하기</b>
						<td width="230" align="left">
					<!-- 별점 평가... 별 선택에 맞는 벨류값 전송 -->
					<select name="score">
					<option value="">선택</option>
					<option value="1">★</option>
					<option value="2">★★</option>
					<option value="3">★★★</option>
					<option value="4">★★★★</option>
					<option value="5">★★★★★</option>
					</select></td>
						
					
				</tr>
				
				<tr>
					<td width="70" class="color"><b>제 목</b></td>
					<td width="330" align="left"><input type="text" size="40" maxlength="20"
						name="subject"></td>
						<!-- 파일 첨부위해 타입을 파일 -->
						<td width="70" class="color"><b>첨부</b></td>
						<td width="330" align="left">
    					<input type="file" name="bfile">
				</tr>
				
				<tr>
					<td class="color">내용</td>
      				<td colspan="3"><textarea name="content"  cols="149" rows="20"></textarea></td>
					</td>
				</tr>
			</table>
			<input class="button" type="reset" value="취 소" onclick="document.location.href='mainForm.do'">
			<input class="button" type="submit" value="작 성">
		</form>
		
		</body>
</html>