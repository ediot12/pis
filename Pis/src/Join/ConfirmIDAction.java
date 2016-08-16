package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logon.LogonDBBean;
import controller.CommandAction;

public class ConfirmIDAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
		HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		LogonDBBean manager = LogonDBBean.getInstance();
		int check= manager.confirmId(id);
		request.setAttribute("check",check);   
		request.setAttribute("id", id);
		return "/Join/confirmId.jsp";
	
	}	
}
