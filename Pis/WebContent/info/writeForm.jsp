<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title></title>
<link href="../style.css" rel="stylesheet" type="text/css">
<script src="script.js">
function zipCheck(){
	
	url="/Pis/info/zipcheck.do?check=y";
	
	window.open(url,"post","toolbar=no ,width=1000 ,height=1000,directories=no,status=yes,scrollbars=yes,menubar=no");
}


</script>
</head>
<body>
	<center>
	<br>
	<div class="title">
		<b>주차장 제보</b>
	</div>
	<br>
	<!-- 전속방식 ,서버에 전송될 이름, 이동할 페이지, 스크립트 , enctype="multipart/form-data" 파일 업로드 할시 필수 요소 -->
		<form method="post" name="writeform"
			action="/Pis/info/writePro.do" onsubmit="return writeSave()" enctype="multipart/form-data">
			<input type="hidden" name="num" value="${num}">
			<table class="table" border="1" cellspacing="0" cellpadding="0"
				align="center" style="table-layout:fixed">
				<tr>
				 <!-- 세션에 저장된 id 값 -->
					<td width="40" class="color"><b>작성자</b></td>
					<td width="170" align="left">${memId}
						</td>
						<!-- 파일 첨부를 위해 타입을 파일로 생성 -->
						<td width="70" class="color"><b>첨부</b></td>
						<td width="330" align="left">
    					<input type="file" name="bfile">
				</tr>
				
				<tr>
					<td width="70" class="color"><b>제 목</b></td>
					<td width="330" colspan="3" align="left" ><input type="text" size="40" maxlength="40"
						name="subject"></td>				
				</tr>
				<tr>
				<!-- 우편번호 검색  -->
					<td width="200" class="color">우편번호</td>
					<td colspan="3" align="left" >
					<input type="text" name="zipcode" size="7" readonly="readonly">
					<input type="button" value="우편번호찾기" onClick="zipCheck()"></td>
				</tr>
				<td class="color">주소</td>
				<td colspan="3" align="left" ><input type="text" name="address" size="70"> </td>
				</tr>



				<tr>
					<td align="center" class="color">내용</td>
      				<td colspan="3"><textarea name="content"  cols="155" rows="20"></textarea></td>
					</td>
				</tr>

			</table>
			
			<input  type="reset" value="취 소" class="button" onclick="document.location.href='mainForm.do'">
			<input  type="submit" value="제보하기" class="button">
		</form>
		
		
		</body>
</html>