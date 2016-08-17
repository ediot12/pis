<%@ page  contentType="text/html; charset=UTF-8"%>
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
		<b>불편 신고 </b> <br>
		<form method="post" name="writeform"
			action="/Pis/report/writePro.do" onsubmit="return writeSave()">
			<input type="hidden" name="num" value="${num}">
				
			
			<table width="600" border="1" cellspacing="0" cellpadding="0"
				align="center" style="table-layout:fixed">


				<tr>
					<td width="70" align="center"><b>작성자</b></td>
					<td width="230"><input type="text" size="20" maxlength="10"
						name="writer"></td>
						<td width="70" align="center" colspan="3"><b>불편 종류</b>
						<td width="230">
					<select name="type">
					<option value="">불편종류</option>
					<option value="1">결제</option>
					<option value="2">불 친절</option>
					<option value="3">주차 불편(주변 피해)</option>
					<option value="4">불법 주정차</option>
					<option value="5">기타</option>
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
					<input class="box-gray" type="submit" value="신고하기" OnClick="window.location='/Pis/report/mainForm.do'">
					</td>
					
				</tr>
				
			</table>
		</form>
</body>
</html>