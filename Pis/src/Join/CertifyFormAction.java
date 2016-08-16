package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import email.CreateCode;
import email.SendEmail;
import controller.CommandAction;


public class CertifyFormAction implements CommandAction {


	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		
		SendEmail sendEmail = new SendEmail();
		CreateCode code = new CreateCode();
/*		LogonDataBean member = new LogonDataBean();*/
		
		HttpSession session = request.getSession();
		/*member.setCertify(request.getParameter("certify")); 
		member.setCertifyKey(code.randomCode());*/
		String key=code.randomCode();
		String certify = "n";
/*		String memId = request.getParameter("id");*/
		String email = request.getParameter("email");
		

		session.setAttribute("key", key);
		session.setAttribute("certify", certify);
	/*	session.setAttribute("memId", memId);*/
		sendEmail.send(email,key);
		return "/Join/certifyForm.jsp";
	}
}