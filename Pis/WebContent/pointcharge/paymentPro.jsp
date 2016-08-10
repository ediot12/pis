<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>결제완료내역</title>
</head>
<body>
<table width="560" border="0" cellspacing="0" cellpadding="0" >
 <tr>
 	<td align="center"><table width="400" border="0" cellspacing="0" cellpadding="0">
  <tr height="70">
    <td><h1>결제완료</h1></td>
  </tr>
  	</table>
  	</td>
  </tr>
  <tr>
    <td height="530" valign="top">	
	<table width="560" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="25">&nbsp;</td>
        <td width="505" align="center">
<table border='0' cellpadding='0' cellspacing='0' width='500' align='center'>


    <tr>
      <td align="center"><table width="400" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="110">결제수단</td> 
          <td width="290"></td>
        </tr>
  	    <tr> 
          <td height="1" colspan="2"></td>
        </tr>
        <tr bgcolor="#E3E3E3">
          <td height="1" colspan="2"></td>
       	</tr>
        <tr>
          <td width="110">예약번호</td> 
		  <!--주문번호는 50Byte(한글 25자) 입니다. ' " ` 는 사용하실수 없습니다. 따옴표,쌍따옴표,백쿼테이션 -->
          <td width="290"></td>
        </tr>
        <tr>
          <td height="1" colspan="2"></td>
        </tr>
		<tr bgcolor="#E3E3E3">
          <td height="1" colspan="2"></td>
       	</tr>
    	<tr>
          <td width="110">주차장명</td> 
		 <!--  상품명 50Byte(한글 25자) 입니다. ' " ` 는 사용하실수 없습니다. 따옴표,쌍따옴표,백쿼테이션-->
          <td width="290"></td>
        </tr>
		<tr bgcolor="#E3E3E3">
          <td height="1" colspan="2"></td>
       	</tr>
        <tr>
          <td width="110">결제금액</td> 
		  <!--금액은 ,없이 입력 -->
          <td width="290"></td>
        </tr>
        <tr bgcolor="#E3E3E3">
          <td height="1" colspan="2"></td>
        </tr>
        <tr>
          <td width="110">예약자명</td> 
          <td width="290"></td>
        </tr>
        <tr bgcolor="#E3E3E3">
          <td height="1" colspan="2"></td>
        </tr>
        <tr>
          <td width="110">전자우편</td> 
		  <!--KSPAY에서 결제정보를 메일로 보내줍니다.(신용카드거래에만 해당)-->
          <td width="290"></td>
        </tr>
        <tr bgcolor="#E3E3E3">
          <td height="1" colspan="2"></td>
        </tr>
        <tr>
          <td width="110">이동전화</td> 
		  <!--전화번호 value 값에 숫자만 넣게 해주시길 바랍니다. : '-' 가 들어가면 안됩니다.-->
          <td width="290"></td>
        </tr>
        <tr bgcolor="#E3E3E3">
          <td height="1" colspan="2"></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td height="40" align="center"><input type="button" value="결 제"></td>
    </tr>

</table>

</body>
</html>