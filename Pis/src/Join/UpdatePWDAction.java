package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.CommandAction;
import logon.LogonDBBean;

public class UpdatePWDAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable{

		String passwd = request.getParameter("passwd");
		String id = request.getParameter("id");
	    
		LogonDBBean manager = LogonDBBean.getInstance();
		int x = manager.updatePwd(passwd,id);
		request.setAttribute("passwd", x);
	
		return "/Join/updatePWD.jsp";

	}
}
