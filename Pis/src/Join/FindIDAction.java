package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.CommandAction;
import logon.LogonDBBean;

public class FindIDAction implements CommandAction{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		LogonDBBean manager = LogonDBBean.getInstance();
		String id = manager.findID(name, phone);
		
		request.setAttribute("id", id);
/*		if(id.equals("")){
		
			} else{
			 request.getAttribute("id");
		}*/
		
		return "/Join/findID.jsp";
	}
}