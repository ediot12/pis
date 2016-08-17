<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>


<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
	function dongCheck() {
		if (document.zipForm.area4.value == "") {
			alert("동이름을 입력하세요");
			document.zipForm.area4.focus();
			return;
		}
		document.zipForm.submit();
	}

	function sendAddress(zipcode, area1, area2, area3, area4){
		var address = area1+" "+area2+" "+area3+" "+area4;
		opener.document.writeform.zipcode.value = zipcode;
		opener.document.writeform.address.value = address;
		self.close();
	}
</script>
</head>
<body>
	<center>
		<table>
			<b>우편번호 찾기</b>
			<form name="zipForm" method="post" action="/Pis/info/zipcheck.do">
				<tr>
					<td><br> 도로명 주소 입력 : <input name="area4" type="text">
						<input type="button" value="검색" onclick="dongCheck()"></td>
				</tr>
				<input type="hidden" name="check" value="n">
			</form>


			<c:if test="${check=='n'}">
				<c:if test="${totalList==0}">
					<tr>
						<td align="center"><br>검색된 결과가 없습니다.</td>
					</tr>
				</c:if>

				<c:if test="${totalList!=0}">
					<tr>
						<td align="center"><br> ※검색 후, 아래 우편번호를 클릭하면 자동으로 입력됩니다.
						</td>
					</tr>
				</c:if>

				<c:forEach var="area" items="${zipcodeList}" >
				<tr><td>
					<a
						href="javascript:sendAddress(
						'${area.zipcode}','${area.area1}','${area.area2}',
						'${area.area3}','${area.area4}')">
						${area.zipcode}&nbsp;${area.area1}&nbsp;${area.area2}&nbsp;
						${area.area3}&nbsp;${area.area4}</a>
						</td></tr>
				</c:forEach>
				<tr>
					<td align="center"><br> <a
						href="javascript:this.close();">닫기</a>
			</c:if>
		</table>
</body>
</html>