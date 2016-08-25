<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
<link href="../../style.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>  
  $(function(){
	  	var article=(".report .show");
	  	
		$(".report .faqmenu td").on('click',function(){
			
			var art = $(this).parents().next("tr");
			
		if($(art).hasClass('hide')){
			$(article).removeClass('show').addClass('hide');
			$(art).removeClass('hide').addClass('show');
		}else{
			$(art).addClass('hide').removeClass('show');
		}
		});
	});

</script>
</head>
<body>
    <center>
	<br>
	<div class="title">
	<b>자주묻는 질문</b>
	<a href="/Pis/admin/board/FAQwriteForm.do">
		<input type="button" value="글쓰기" class="button">
	</a>
	</div>
	<br>
    <table class="report">
        <tr class="">
            <th width="10%" align="center">번호</th>
            <th width="20%" align="center">문의유형</th>
            <th width="70%" align="center">자주묻는질문</th> 
        </tr>
        <c:forEach var="fdb" items="${articleList}">
        <tr class="faqmenu">
            <td><c:out value="${number}" />
				<c:set var="number" value="${number - 1}" /></td>
			<td>${fdb.kind}</td>
            <td>${fdb.subject}</td>
        </tr>
        <tr class="hide">
        	<td></td>
            <td colspan="2" class="colorblack"><br>          
				<pre>${fdb.content}</pre>
				<br>
            </td>
        </tr>
        </c:forEach>
    </table>
    
    <br/>
    <c:if test="${count > 0}">
		<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
		<c:set var="pageBlock" value="${10}"/>
		<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
		<c:set var="startPage" value="${result * 10 + 1}" />
		<c:set var="endPage" value="${startPage + pageBlock-1}"/>
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}"/>
		</c:if>
			         
		<c:if test="${startPage > 10}">
			<a href="/Pis/admin/board/FAQ.do?pageNum=${startPage - 10 }&searchn=${searchn}&search=${search}"><input type="button" value="<<이전"  class="button"></a>
		</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/Pis/admin/board/FAQ.do?pageNum=${i}&searchn=${searchn}&search=${search}"><input type="button" value="${i}" class="button"></a>
			</c:forEach>
			
			<c:if test="${endPage < pageCount}">
				<a href="/Pis/admin/board/FAQ.do?pageNum=${startPage + 10}&searchn=${searchn}&search=${search}"><input type="button" value="다음>>" class="button"></a>
			</c:if>
		</c:if>
	<form>
		<select name="searchn">
		<option value="0">회원정보</option>
		<option value="1">예약</option>
		<option value="2">결제</option>
		<option value="3">환불</option>
		<option value="4">기타</option>
		</select>
			
		<input type="text" name="search" size="15" maxlength="50" /> <input type="submit" value="검색" class="button" />
	</form>
</body>
</html>