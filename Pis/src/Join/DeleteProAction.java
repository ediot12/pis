package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.CommandAction;
import logon.LogonDBBean;

public class DeleteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request,
		HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		
	    HttpSession session=request.getSession();
	    String id = (String)session.getAttribute("memId");
	    String passwd  = request.getParameter("passwd");
	    LogonDBBean manager = LogonDBBean.getInstance();
	        int check = manager.deleteMember(id,passwd);
	       if(check==1){
	        request.setAttribute("check", check);
	        session.invalidate();
	       }
		return "/Join/deletePro.jsp";

	}
}