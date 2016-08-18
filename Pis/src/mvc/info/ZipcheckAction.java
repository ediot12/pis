package mvc.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.info.*;

import java.util.*;

public class ZipcheckAction implements CommandAction{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse sponse)throws Throwable{
		
		
			request.setCharacterEncoding("utf-8");

		   String check = request.getParameter("check");//n
		   String area4 = request.getParameter("area4");//µø¿Ã∏ß
		   InfoDBBean manager = InfoDBBean.getInstance();  
		   Vector zipcodeList = manager.zipcodeRead(area4);
		   int totalList = zipcodeList.size();
		   
		   request.setAttribute("check", "n");
		   request.setAttribute("zipcodeList", zipcodeList);
		   request.setAttribute("totalList", totalList);
		
		return "/info/zipcheck.jsp";
	}

}
