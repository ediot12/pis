function checkPay() {


	
	if(document.insert.calendar1.value==""){
		alert("날짜를 입력해주세요.");
		return false;
	}

	if (confirm('확인을 누르시면 포인트가 차감되며 예약됩니다. 진행하시겠습니까?')) {

	} else {
		return false;
	}
}
