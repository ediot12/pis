package Join;

import javax.servlet.http.*;
import controller.CommandAction;

public class LoginFormAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/Join/loginForm.jsp";
	
	
	}
}
