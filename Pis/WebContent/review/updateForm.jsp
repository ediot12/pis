<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center>
	<br>
	<div class="title">
		<b>사용후기</b>
	</div>
	<br>
	<!-- 글수정시 전송방식, 전송될 이름, 이동할 페이지에 페이지 번호 -->
		<form method="post" name="writeform"
			action="/Pis/review/updatePro.do?pageNum=${pageNum}"
			onsubmit="return writeSave()" >
			<table width="800" border="1" cellspacing="0" cellpadding="0"
			 align="center" style="table-layout:fixed">
				<tr>
					<td width="70" height="40" class="color">작성자</td>
					<td align="left" width="230">
					<input type="text" size="20" maxlength="10" name="writer" value="${article.writer}">
					<input type="hidden" name="num" value="${article.num}"></td>
					<td width="70" height="40" class="color">평가하기</td>
					<!-- 별점 평가... 별 선택에 맞는 벨류값 전송 -->
					<td width="230">
					<select name="score">
					<option value="">선택</option>
					<option value="1">★</option>
					<option value="2">★★</option>
					<option value="3">★★★</option>
					<option value="4">★★★★</option>
					<option value="5">★★★★★</option>
					</select>평가 : ${article.score}</td>
				</tr>
				<tr>
					<td width="70" height="40" class="color">제 목</td>
					<td align="left" width="230" colspan="3"><input type="text" size="20"
						maxlength="50" name="subject" value="${article.subject}"></td>
						
				</tr>
				
				<tr>
					<td width="70" height="40" class="color">내 용</td>
					<td align="left" width="330" colspan="3"><textarea name="content"
							rows="12" cols="99">${article.content}</textarea><br><img src="../filesave/${article.bfile}" width="230px" height="150px" ></td>
				</tr>

			</table>
			<br>
						<input class="button" type="submit" value="글수정">
						 <input class="button" type="reset" value="다시작성">
						 <input class="button" type="button" value="목록보기" onclick="document.location.href='mainForm.do?pageNum=${pageNum}'">
		</form>
</body>
</html>