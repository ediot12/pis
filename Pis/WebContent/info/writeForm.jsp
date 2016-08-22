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
	.title{
	float: left;
	}
</style>
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
function zipCheck(){
	
	url="/Pis/info/zipcheck.do?check=y";
	
	window.open(url,"post","toolbar=no ,width=1000 ,height=1000,directories=no,status=yes,scrollbars=yes,menubar=no");
}


</script>
</head>
<body>
	<center>
	<div class="title">
		<b>주차장 제보</b>
	</div>
	<br>
		<form method="post" name="writeform"
			action="/Pis/info/writePro.do" onsubmit="return writeSave()" enctype="multipart/form-data">
			<input type="hidden" name="num" value="${num}">
			<table width="700" border="1" cellspacing="0" cellpadding="0"
				align="center" style="table-layout:fixed">
				<tr>
					<td width="70" align="center"><b>작성자</b></td>
					<td width="230">${memId}
						</td>
						<td width="70" align="center"><b>첨부</b></td>
						<td width="330">
    					<input type="file" name="bfile">
				</tr>
				
				<tr>
					<td width="70" align="center"><b>제 목</b></td>
					<td width="630"><input colspan="4" type="text" size="20" maxlength="20"
						name="subject"></td>				
				</tr>
				<tr>
					<td width="200">우편번호</td>
					<td><input type="text" name="zipcode" size="7" readonly="readonly">
						<input type="button" value="우편번호찾기" onClick="zipCheck()"></td>
				</tr>
				<td>주소</td>
				<td><input type="text" name="address" size="70"> </td>
				</tr>



				<tr>
					<td align="center">내용</td>
      				<td colspan="3"><textarea name="content"  cols="80" rows="20"></textarea></td>
					</td>
				</tr>
				
				<tr>
					<td colspan="4" align="right">
					<input class="box-gray" type="reset" value="취 소" onclick="document.location.href='mainForm.do'">
					<input class="box-gray" type="submit" value="제보하기">
				</tr>
				
			</table>
		</form>
		
		</body>
</html>