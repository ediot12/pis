<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<title>포인트 충전</title>
<link href="../style.css" rel="stylesheet" type="text/css">

<script>

 
// 	radio버튼에서 선택된 값 체크하는 함수 
	function pointcheck(){
		if(!document.pointform.point.value){	// 선택된 값이 없을때
			alert("선택된 값이 없습니다.");
			return false;	
		}
		
		if(document.pointform.point.value){		// 선택된 값이 있을때
	      	var check = confirm("결제를 진행하시겠습니까?");	// 확인, 취소 버튼있는 alert창 
	      	if(check){	// 확인버튼을 눌렀을때 포인트 결제하는 페이지로 이동
	      		document.pointform.submit();
	      	}
	    
		}
	
	}	
</script>

</head>
<body>

<center>
<br>
<p><h1>포인트충전</h1></p><br>
<form name="pointform"  action="pointment.do" onsubmit="pointcheck()">
<!-- radio 버튼으로 클릭된 값이 on 으로 넘어감 -->
<table id="ok" border=1  cellpadding="0" cellspacing="0">
	<tr class="hr" height="20">
	<td height="10" class="color" width="150">충전캐시</td>
	<td class="colorblack" width="300">적립혜택</td>
	</tr>
	<tr height="20">
	<td class="color" width="200"><input type="radio" name="point" value="1000">1,000원</td>
	<td class="colorblack" width="300">1,000P</td>
	</tr>
	<tr height="20">
	<td class="color" width="200"><input type="radio" name="point" value="5000">5,000원</td>
	<td class="colorblack" align="left" width="300">5,000P + 1,000P 추가적립</td>
	</tr>
	<tr height="20">
	<td class="color" width="200"><input type="radio" name="point" value="10000">10,000원</td>
	<td class="colorblack" align="left" width="300">10,000P + 2,000P 추가적립</td>
	</tr>
	<tr height="20">
	<td class="color" width="200"><input type="radio" name="point" value="50000">50,000원</td>
	<td class="colorblack" align="left" width="300">50,000P + 6,000P 추가적립</td>
	</tr>
	<tr height="20">
	<td class="color" width="200"><input type="radio" name="point" value="100000">100,000원</td>
	<td class="colorblack" align="left" width="300">100,000P + 20,000P 추가적립</td>
	</tr>
</table>
<br>
<input class="button" type="submit" value="결제하기">

</form>

</body>
</html>