<%@ page contentType="text/html; charset=UTF-8"%>
<head>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.0.js" ></script>
<script type="text/javascript">


var IMP = window.IMP;
IMP.init('imp81908602'); //iamport 대신 자신의 "가맹점 식별코드"를 사용하시면 됩니다


IMP.request_pay({
	pay_method : 'trans',
	merchant_uid : 'merchant_' + new Date().getTime(),
	name : '예약에서 넘어오는 값',
	amount : 1000,
	buyer_email : 'iamport@siot.do',
	buyer_name : '넘어오는값',
	buyer_tel : '010-1234-5678',
	buyer_addr : '서울특별시 강남구 삼성동',
	buyer_postcode : '123-456'
}, function(rsp) {
	if ( rsp.success ) {
		var msg = '결제가 완료되었습니다.';
		msg += '고유ID : ' + rsp.imp_uid;
		msg += '상점 거래ID : ' + rsp.merchant_uid;
		msg += '결제 금액 : ' + rsp.paid_amount;
		msg += '카드 승인번호 : ' + rsp.apply_num;
		
		
		
	} else {
		var msg = '결제에 실패하였습니다.';
		msg += '에러내용 : ' + rsp.error_msg;
	}
});


	

</script>
</head>
ㅁㄴㅇㄻㄴㅇㄹㄴ