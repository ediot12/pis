package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.CommandAction;

public class DeleteFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable{
	
		
		return "/Join/deleteForm.jsp";
	}
}
