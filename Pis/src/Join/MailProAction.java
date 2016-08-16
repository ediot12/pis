package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.CommandAction;
import email.CreateCode;
import email.SendEmail;
import logon.LogonDBBean;
import logon.LogonDataBean;

public class MailProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");

		HttpSession session= request.getSession();
		
		int check=0;
		String tempKey= request.getParameter("tempkey");
		String key =(String)session.getAttribute("key");
		String certify =(String) session.getAttribute("certify");
		if(key.equals(tempKey)){
			certify="y";
			check=1;
		}else
		{
			certify="n";
		}
		session.setAttribute("certify", certify);
		request.setAttribute("check", new Integer(check));
		return "/Join/mailPro.jsp";
	}

}
