package mvc.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		
		return "/report/contentForm.jsp";
	}



}
