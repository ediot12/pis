package email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	 public void send(String address, String key) throws MessagingException{
	        // 메일 관련 정보
		    String host = "smtp.gmail.com"; /*이메일 서버*/
	        String user = "welcome.pis5@gmail.com"; /*이메일 계정*/
	        String password = "123456789!@"; /*이메일 계정 비번*/
	         
	        // 메일 내용
	        String recipient = address;
	        String subject = "가입을 죽하합니다."; /*이메일의 제목*/
	      //  String code = "http://localhost:8088/Certify/certify/setKey.do?key=" + key; /*키값을 키 파라메터에 입력*/
	        String content = "인증번호는 "+key+"를 입력하시면  회원가입이 완료됩니다."; /*전송할 메세지*/
	         
	        //properties 설정
	        Properties props = new Properties();
	        props.put("mail.smtps.auth", "true");
	        
	        // 메일 세션
	        Session session = Session.getDefaultInstance(props);
	        MimeMessage msg = new MimeMessage(session); /*전송할 내용을 담을 MimeMessage타입 변수 생성*/
	 
	        // 메일 관련
	        msg.setSubject(subject); /*이메일 제목 세팅*/
	        msg.setText(content); /*이메일 내용 세팅 :: 키값이 포함된 인증 주소와 메세지 새팅*/
	        msg.setFrom(new InternetAddress(user)); /*보낸사람 이메일 주소 세팅*/
	        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); /*받는사람 주소를 추가*/
	 
	        // 발송 처리
	        Transport transport = session.getTransport("smtps");
	        transport.connect(host, user, password);
	        transport.sendMessage(msg, msg.getAllRecipients());
	        transport.close();     
	 }
}
