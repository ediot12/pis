<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test = "${exist==1}">
<script>
alert
("회원가입이 완료되었습니다.");
</script>
<meta http-equiv="Refresh" content="0;url=/Pis/Join/loginForm.do">
</c:if>

<c:if test = "${exist==0}">
<script>
alert
("아이디가 중복됩니다. \n다른 아이디를 사용하여 주세요.");
history.go(-1);
</script>
</c:if>

<c:if test = "${exist==-1}">
<script>
alert
("이메일 인증을 해주세요");
history.go(-1);
</script>
</c:if>