package Join;

import java.util.Vector;
import controller.CommandAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logon.LogonDBBean;
import logon.ZipcodeBean;

public class Zipcheck implements CommandAction{
	private ZipcodeBean ZipcodeBean;
	
	public String requestPro(HttpServletRequest request,
			HttpServletResponse sponse)throws Throwable{
		
		request.setCharacterEncoding("utf-8");

		   String check = request.getParameter("check");//n
		   String area4 = request.getParameter("area4");//µø¿Ã∏ß
		   LogonDBBean manager = LogonDBBean.getInstance();  
		   Vector zipcodeList = manager.zipcodeRead(area4);
		   int totalList = zipcodeList.size();
		   
		   request.setAttribute("check", "n");
		   request.setAttribute("zipcodeList", zipcodeList);
		   request.setAttribute("totalList", totalList);
		
		return "/Join/Zipcheck.jsp";
	}

}
