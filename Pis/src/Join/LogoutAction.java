package Join;

import javax.servlet.http.*;
import controller.CommandAction;

public class LogoutAction implements CommandAction{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable{
		
		request.getSession().invalidate();
		
		return "/layout/main.do";
	
	}
}
