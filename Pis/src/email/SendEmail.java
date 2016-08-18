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
	        // ���� ���� ����
		    String host = "smtp.gmail.com"; /*�̸��� ����*/
	        String user = "welcome.pis5@gmail.com"; /*�̸��� ����*/
	        String password = "123456789!@"; /*�̸��� ���� ���*/
	         
	        // ���� ����
	        String recipient = address;
	        String subject = "������ �����մϴ�."; /*�̸����� ����*/
	      //  String code = "http://localhost:8088/Certify/certify/setKey.do?key=" + key; /*Ű���� Ű �Ķ���Ϳ� �Է�*/
	        String content = "������ȣ�� "+key+"�� �Է��Ͻø�  ȸ�������� �Ϸ�˴ϴ�."; /*������ �޼���*/
	         
	        //properties ����
	        Properties props = new Properties();
	        props.put("mail.smtps.auth", "true");
	        
	        // ���� ����
	        Session session = Session.getDefaultInstance(props);
	        MimeMessage msg = new MimeMessage(session); /*������ ������ ���� MimeMessageŸ�� ���� ����*/
	 
	        // ���� ����
	        msg.setSubject(subject); /*�̸��� ���� ����*/
	        msg.setText(content); /*�̸��� ���� ���� :: Ű���� ���Ե� ���� �ּҿ� �޼��� ����*/
	        msg.setFrom(new InternetAddress(user)); /*������� �̸��� �ּ� ����*/
	        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); /*�޴»�� �ּҸ� �߰�*/
	 
	        // �߼� ó��
	        Transport transport = session.getTransport("smtps");
	        transport.connect(host, user, password);
	        transport.sendMessage(msg, msg.getAllRecipients());
	        transport.close();     
	 }
}
