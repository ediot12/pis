package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.CommandAction;
import logon.LogonDBBean;
import logon.LogonDataBean;

public class ModifyFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		
		HttpSession session=request.getSession();
		
	    String id = (String)session.getAttribute("memId");
	    LogonDBBean manager = LogonDBBean.getInstance();
	    LogonDataBean c = manager.getMember(id);
	    
	    request.setAttribute("c", c);
	    
	    return "/Join/modifyForm.jsp";
		/*String id = (String)request.getSession().getAttribute("memId");
		LogonDBBean manager = LogonDBBean.getInstance();
		LogonDataBean c = manager.getMember(id);
		request.setAttribute("memId",id);
		request.setAttribute("c",c);
		return "/Join/modifyForm.jsp";
*/
	}
}
