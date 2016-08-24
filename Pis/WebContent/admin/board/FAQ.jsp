<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
<link href="../../style.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>  
    $(document).ready(function(){

        $("#report tr:odd").addClass("odd");
        $("#report tr:not(.odd)").hide(); 
        $("#report tr:first-child").show(); //열머리글 보여주기

        $("#report tr.odd").click(function(){
            $(this).next("tr").toggle();
            $(this).find(".arrow").toggleClass("up");
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
    <table id="report">
        <tr>
            <th width="10%" align="center">번호</th>
            <th width="20%" align="center">문의유형</th>
            <th width="50%" align="center">자주묻는질문</th>
            <th width="10%" align="center" colspan="2">수정 / 삭제</th> 
        </tr>
        <c:forEach var="fdb" items="${articleList}">
        <tr>
            <td><c:out value="${number}" />
				<c:set var="number" value="${number - 1}" /></td>
			<td>${fdb.kind}</td>
            <td>${fdb.subject}</td>
            <td>
            	<a href="/Pis/admin/board/FAQUpdateForm.do?num=${fdb.num}&pageNum=${pageNum}">
					<input type="button" value="수정" class="button">
				</a>
			</td>
			<td>
				<a href="/Pis/admin/board/FAQDeletePro.do?num=${fdb.num}&pageNum=${pageNum}" >
					<input type="button" value="삭제" class="button">
				</a>
			</td> 
        </tr>
        <tr>
        	<td></td>
            <td colspan="3">             
				<pre>${fdb.content}</pre>
            </td>
            <td></td>
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