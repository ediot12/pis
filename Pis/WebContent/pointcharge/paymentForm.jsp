<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.0.js" ></script>
<script type="text/javascript">


var IMP = window.IMP;

IMP.init('imp81908602'); //iamport 대신 자신의 "가맹점 식별코드"를 사용하시면 됩니다

// var rsp = {
// 		imp_uid: "Honda",
// 		imp_merchant_uid: "Accord",
// 		imp_paid_amount: "${point}",
// 		apply_num:1,
// 		error_msg: "에러발생!!!!!"
// 		 };

IMP.request_pay({
	pay_method : '${pay_method}',
	merchant_uid : 'ParkingInfoSystem company ' + new Date().getTime(),
	name : 'a',
	amount : '${point}',
	buyer_email : 'b',
	buyer_name : 'c',
	buyer_tel : '1',
	buyer_addr : '',
	buyer_postcode : ''
}, function(rsp) {
	if ( rsp.success ) {
		var msg = '결제가 완료되었습니다.';
		msg += '\n고유ID : ' + rsp.imp_uid;
		msg += '\n상점 거래ID : ' + rsp.merchant_uid;
		msg += '\n결제 금액 : ' + rsp.paid_amount;
		msg += '\n카드 승인번호 : ' + rsp.apply_num;
		
		alert(msg);
		
		
		
	} else {
		var msg = '결제에 실패하였습니다.';
		msg += '에러내용 : ' + rsp.error_msg;
	}
});


</script>

</head>
