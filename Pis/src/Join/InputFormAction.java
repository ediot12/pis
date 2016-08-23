package Join;

import javax.servlet.http.*;
import controller.CommandAction;


public class InputFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.getSession().getAttribute("certify");
		
		return "/Join/inputForm.jsp";

	}
}
