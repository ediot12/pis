package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logon.LogonDBBean;
import controller.CommandAction;

public class FindPWAction implements CommandAction{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
	

		LogonDBBean manager = LogonDBBean.getInstance();
		String pwd = manager.findPWD(id, name, phone);

		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		
		return "/Join/findPW.jsp";
		
	}
}
