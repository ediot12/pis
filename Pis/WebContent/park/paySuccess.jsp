<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<center>
<br>

   <form action="/Pis/park/payAfter.do" method="post">
   <input type="hidden" value="${beginday }" name="beginday">
   <input type="hidden" value="${capacity }" name="capacity">
   <input type="hidden" value="${parking_code }" name="parking_code">
      
         <br>
      <!-- <div class="title"> 
         <b>공지사항 </b>
      </div> -->
      <table id="ok" border="1" cellpadding="0" cellspacing="0">
         <tr>
            <td class="color">이름 :</td>
            <td><input type="text" readonly="readonly" name="name" value="${name }" size="40"></td>
         </tr>

         <tr>
            <td class="color">전화번호 :</td>
            <td><input type="text" readonly="readonly" name="phone"
               value="${phoneNum }" size="40"></td>
         </tr>

         <tr>
            <td class="color">주차장이름 : </td>
            <td><input type="text" readonly="readonly" name="parkname"
               value="${parkname }" size="40"></td>
         </tr>
         <tr>
            <td class="color">주차장위치 : </td>
            <td><input type="text" readonly="readonly" name="parkloca"
               value="${parkloca }" size="40"></td>
         </tr>

         <tr>
            <td class="color">차종 :</td>
            <td><input type="text" readonly="readonly" name="cartype"
               value="${cartype }" size="40"></td>
         </tr>

         <tr>
            <td class="color">이용기간 :</td>
            <td><input type="text" readonly="readonly" name="useday"
               value="${begintime } ~ ${outtime}" size="40"></td>
         </tr>

         <tr>
            <td class="color">입차 예정 시간 :</td>
            <td><input type="text" readonly="readonly" name="begintime"
               value="${begintime }" size="40"></td>
         </tr>
         
         <tr>
            <td class="color">출차 예정 시간 :</td>
            <td><input type="text" readonly="readonly" name="outtime"
               value="${outtime }" size="40"></td>
         </tr>

         <tr>
            <td class="color">차감 포인트 :</td>
            <td><input type="text" readonly="readonly" name="point"
               value="${payment }" size="40"></td>
         </tr>
      </table>
      <input class="button" type="submit" value="예약하기">
   </form>

</body>
</html>