<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<script language="JavaScript" src="script.js"></script>
<style>
	.table{
		width:800px;
		text-align: left;
		margin : 20;
		border-color: #31A0B4;
	}
	.title{
		font-size:30px;
		text-align: left;
		height: 40px;
	}
	.button{
		background:#31A0B4;
		color:#FFFFFF;
		margin : 0 2;
		padding: 5;
	}
	.table td{
		height: 40;
	}
	.titletd{
		background: #31A0B4;
		color: #FFFFFF;
	}
</style>
</head>
<body> 
<center>
<div class="title">
	<b>주차장 추가하기</b>
</div>
<br>
<form method="post" name="writeform" action="/Pis/semi/admin/carpark/carparkInputPro.do" onsubmit="return writeSave()">

<table class="table" border="1" cellspacing="0" cellpadding="0"  align="center">
   <tr>
    <td align="right" colspan="4">
    <a href="/Pis/semi/admin/carpark/carpark.do"><input type="button" value="주차장 목록" class="button"></a>
   </td>
   </tr>
   <tr>
    <td class="titletd">주차장 코드</td>
    <td>
       <input type="text" size="10" maxlength="10" name="parking_code"></td>
    <td class="titletd">주차장 이름</td>
    <td>
       <input type="text" size="20" name="parking_name"></td>
  </tr>
  <tr>
    <td class="titletd">주차장 주소</td>
    <td colspan="3">
       <input type="text" size="50" name="addr">
	</td>
  </tr>
  <tr>
    <td class="titletd">주차장 종류</td>
    <td>
       <input type="radio" name="parking_type_nm" class="carparkRadio1" value="노상"> 노상
       <input type="radio" name="parking_type_nm" class="carparkRadio1" value="노외"> 노외
    </td>
    <td class="titletd">운영구분</td>
    <td><select name="operation_rule_nm">
     	<option value="시간제">시간제</option>
     	<option value="거주자">거주자</option>
     	<option value="이륜차 전용">이륜차 전용</option>
     	<option value="버스전용">버스전용</option>
     	<option value="시간제 + 거주자">시간제 + 거주자</option>
     	<option value="시간제 + 이륜차 전용">시간제 + 이륜차 전용</option>
     	<option value="시간제 + 버스전용">시간제 + 버스전용</option>
     </select></td>
  </tr>
  <tr>
    <td class="titletd">전화번호</td>
    <td colspan="3">
     <input type="text" size="20" maxlength="11" name="tel">
	</td>
  </tr>
  <tr>
    <td class="titletd">주차가능대수</td>
    <td>
     <input type="text" size="8" name="capacity2">
	</td>
    <td class="titletd">유/무료 구분</td>
    <td>
     	<input type="radio" name="pay_nm" class="carparkRadio2" value="유료"> 유료
      	<input type="radio" name="pay_nm" class="carparkRadio2" value="무료"> 무료
	</td>
  </tr>
  <tr>
    <td class="titletd">평일 운영 시간</td>
    <td><select name="weekday_begin_time">
     	<option value="0">0</option> <option value="1">1</option> <option value="2">2</option>
     	<option value="3">3</option> <option value="4">4</option> <option value="5">5</option>
     	<option value="6">6</option> <option value="7">7</option> <option value="8">8</option>
     	<option value="9">9</option> <option value="10">10</option> <option value="11">11</option>
     	<option value="12">12</option> <option value="13">13</option> <option value="14">14</option>
     	<option value="15">15</option> <option value="16">16</option> <option value="17">17</option>
     	<option value="18">18</option> <option value="19">19</option> <option value="20">20</option>
     	<option value="21">21</option> <option value="22">22</option> <option value="23">23</option>
     	<option value="24">24</option>
     </select> ~ 
     <select name="weekday_end_time">
     	<option value="0">0</option> <option value="1">1</option> <option value="2">2</option>
     	<option value="3">3</option> <option value="4">4</option> <option value="5">5</option>
     	<option value="6">6</option> <option value="7">7</option> <option value="8">8</option>
     	<option value="9">9</option> <option value="10">10</option> <option value="11">11</option>
     	<option value="12">12</option> <option value="13">13</option> <option value="14">14</option>
     	<option value="15">15</option> <option value="16">16</option> <option value="17">17</option>
     	<option value="18">18</option> <option value="19">19</option> <option value="20">20</option>
     	<option value="21">21</option> <option value="22">22</option> <option value="23">23</option>
     	<option value="24">24</option>
     </select></td>
    <td class="titletd">주말 운영 시간</td>
    <td><select name="weekend_begin_time">
     	<option value="0">0</option> <option value="1">1</option> <option value="2">2</option>
     	<option value="3">3</option> <option value="4">4</option> <option value="5">5</option>
     	<option value="6">6</option> <option value="7">7</option> <option value="8">8</option>
     	<option value="9">9</option> <option value="10">10</option> <option value="11">11</option>
     	<option value="12">12</option> <option value="13">13</option> <option value="14">14</option>
     	<option value="15">15</option> <option value="16">16</option> <option value="17">17</option>
     	<option value="18">18</option> <option value="19">19</option> <option value="20">20</option>
     	<option value="21">21</option> <option value="22">22</option> <option value="23">23</option>
     	<option value="24">24</option>
     </select> ~ 
     <select name="weekend_end_time">
     	<option value="0">0</option> <option value="1">1</option> <option value="2">2</option>
     	<option value="3">3</option> <option value="4">4</option> <option value="5">5</option>
     	<option value="6">6</option> <option value="7">7</option> <option value="8">8</option>
     	<option value="9">9</option> <option value="10">10</option> <option value="11">11</option>
     	<option value="12">12</option> <option value="13">13</option> <option value="14">14</option>
     	<option value="15">15</option> <option value="16">16</option> <option value="17">17</option>
     	<option value="18">18</option> <option value="19">19</option> <option value="20">20</option>
     	<option value="21">21</option> <option value="22">22</option> <option value="23">23</option>
     	<option value="24">24</option>
     </select></td>
  </tr>
  <tr>
    <td class="titletd">주말 유/무료 구분</td>
    <td>
     	<input type="radio" name="saturday_pay_nnm" class="carparkRadio3" value="유료"> 유료
      	<input type="radio" name="saturday_pay_nnm" class="carparkRadio3" value="무료"> 무료
	</td>
    <td class="titletd">공휴일 유/무료 구분</td>
    <td>
     	<input type="radio" name="holiday_pay_nm" class="carparkRadio4" value="유료"> 유료
      	<input type="radio" name="holiday_pay_nm" class="carparkRadio4" value="무료"> 무료
	</td>
  </tr>
  <tr>
    <td class="titletd">월 정기권 금액</td>
    <td colspan="3">
     <input type="text" size="8" name="fulltime_monthly">
	</td>
  </tr>
  <tr>
    <td class="titletd">기본료</td>
    <td>
     <input type="text" size="8" name="rates">
	</td>
    <td class="titletd">기본부과시간</td>
    <td>
     <input type="text" size="8" name="time_rate">
	</td>
  </tr>
  <tr>
    <td class="titletd">추가부과요금</td>
    <td>
     <input type="text" size="8" name="add_rates">
	</td>
    <td class="titletd">추가부과시간</td>
    <td>
     <input type="text" size="8" name="add_time_rate">
	</td>
  </tr>
  <tr>
    <td class="titletd">일 최대 요금</td>
    <td colspan="3">
     <input type="text" size="8" name="day_maximum">
	</td>
  </tr>
<tr>     
<td colspan=5 align="center">
  <input type="submit" value="글쓰기" class="button"> 
  <input type="reset" value="다시작성" class="button">
  <input type="button" value="목록보기" OnClick="window.location='/Pis/semi/admin/carpark/carpark.do'" class="button">
</td></tr></table>   
</form>     
</body>
</html> 