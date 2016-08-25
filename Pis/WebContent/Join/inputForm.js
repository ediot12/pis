//회원정보 입력란 체크
 

function id_nohan(v) {
	var hangle = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g;

	if (hangle.test(v.id.value)) {
		alert("영문자와 숫자만 사용하세요.");
		v.id.value = "";
		v.id.focus();
		return false;
	}
}

//숫자와 영어 입력 방지
function id_noNumber(v)
{
	var hangle = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g;
	
	if (!hangle.test(v.name.value)) {
		alert("한글만 사용하세요.");
		v.name.value = "";
		v.name.focus();
		return false;
	}
}

function checkId(){
		var userinput = eval("document.userinput");
		
		//form
	
		if(!userinput.id.value){ //값이 있으면 실행 안함
			alert("ID를 입력하세요");
			return false;
		}
	
		if(!userinput.passwd.value){
			alert("비밀번호를 입력하세요");
			return false;
		}
		if(userinput.passwd.value != userinput.passwd2.value){
			alert("비밀번호를 동일하게 입력하세요");
			return false;
		}
		if(!userinput.name.value){
			alert("사용자 이름을 입력하세요");
			return false;
		}
		if(!userinput.phone.value){
			alert("전화번호를 입력하세요");
			return false;
		}
		if(!userinput.zipcode.value){
			alert("우편번호를 검색하세요");
			return false;
		}
		if(!userinput.address.value){
			alert("주소를 전부 입력해주세요");
			return false;
		}
		
		if(!userinput.email.value){
			alert("이메일 입력하여 주세요.");
			return false;
		}
		if(!userinput.resident.value){
			alert("거주자 여부를 선택하여 주세요");
			return false;
		}
		return true;
	}

	//아이디 중복 여부를 판단
	function openConfirmid(userinput){
		
		//아이디를 입력했는지 검사
		if(userinput.id.value ==""){
			alert("아이디를 입력하세요");
			return;
		}
		
		//url과 사용자입력id를 조합합니다.
		url = "/Pis/Join/confirmId.do?id=" + userinput.id.value;
		
		//새로운 윈도우를 엽니다.
		open(url,"confirm","toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
	}
	
	function zipCheck(){
		
		url="Zipcheck.do?check=y";
        window.open(url,"post","left=100,top=100,width=600,height=800,toolbar=no,directories=no,status=yes,scrollbars=yes,menubar=no,fullscreen=no,resizable=no");
	}
	
	function mail(userinput){
		
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; 
		
		if(userinput.email.value ==""){
			alert("이메일을 입력하여 주세요.");
			return;
		}
		
		if (!regExp.test(userinput.email.value)) {
			alert("이메일 형식에 올바르지 않습니다.\n다시 확인해 주세요.");
			userinput.email.value = "";
			userinput.email.focus();
			return false;
		}
	      else{
	          url="/Pis/Join/certifyForm.do?email="+userinput.email.value;
	          window.open(url,"post","left=100,top=100,width=600,height=200,toolbar=no,directories=no,status=yes,scrollbars=yes,menubar=no,fullscreen=no,resizable=no");
	       }
	}
	
	
	

