<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<style>
	.target{border-bottom: 1px solid #a5a5a5}
	.box-button{
	text-align: right;
    padding-top:5px;
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
<b>1:1문의 하기 </b> <br>
		<form method="post" name="writeform"
			action="/Pis/question/writePro.do" onsubmit="return writeSave()">
			<input type="hidden" name="num" value="${num}">
			<table width="600" border="1" cellspacing="0" cellpadding="0"
				align="center" style="table-layout:fixed">
				<tr>
					<td width="70" align="center"><b>작성자</b></td>
					<td width="230">${memId }</td>
						<td width="70" align="center" colspan="3"><b>문의 종류</b></td>
						<td width="230">
					<select name="kind">
						<option value="회원정보">회원정보</option>
						<option value="예약">예약</option>
						<option value="취소">취소</option>
						<option value="환불">환불</option>
						<option value="기타">기타</option>
					</select></td>
					</tr>
				
				<tr>
					<td width="70" align="center"><b>제 목</b></td>
					<td width="330" colspan="6"><input type="text" size="20" maxlength="20"
						name="subject"></td>
				</tr>
					
				<tr>
					<td width="70" align="center"><b>내 용</b></td>
					<td width="330"><textarea name="content" rows="14" cols="70"></textarea>
					</td>
				</tr>
				<tr>
					<td class="box-button" colspan="6" align="right" >
					<input type="button" value="취소하기" OnClick="window.location='/Pis/question/mainForm.do'">
					<input type="submit" value="문의하기"> </td>
					</tr>
			</table>
		</form>
</body>
</html>