<%@ page contentType="text/html; charset=UTF-8"%>
<head>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.0.js" ></script>
<script type="text/javascript">


var IMP = window.IMP;
IMP.init('imp81908602'); //iamport 대신 자신의 "가맹점 식별코드"를 사용하시면 됩니다



IMP.request_pay({
	pay_method : '${pay_method}',
	merchant_uid : 'Parking Infomation System ' + new Date().getTime(),
	name : '포인트충전',
	amount : '${point}',
	buyer_email : '${member.email}',
	buyer_name : '${member.name}',
	buyer_tel : '123',
	buyer_addr : '${member.address}',
	buyer_postcode : '${member.zipcode}'
}, function(rsp) {
	if ( rsp.success ) {
		var msg = '결제가 완료되었습니다.';
		msg += '\n고유ID : ' + rsp.imp_uid;
		msg += '\n상점 거래ID : ' + rsp.merchant_uid;
		msg += '\n결제 금액 : ' + rsp.paid_amount;
		msg += '\n카드 승인번호 : ' + rsp.apply_num;
		
		alert(msg);
		location.href="/Pis/pointcharge/pointcharge.do";
		
	} else {
		var msg = '결제에 실패하였습니다.';
		msg += '에러내용 : ' + rsp.error_msg;
	}
});


	

</script>
</head>
