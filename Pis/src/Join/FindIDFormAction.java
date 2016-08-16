package Join;

import javax.servlet.http.*;
import controller.CommandAction;

public class FindIDFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
	HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		
	return "/Join/findIDForm.jsp";
	
	
	}

}
