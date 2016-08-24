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
		   String area4 = request.getParameter("area4");//동이름
		   InfoDBBean manager = InfoDBBean.getInstance();  
		   Vector zipcodeList = manager.zipcodeRead(area4);
		   int totalList = zipcodeList.size(); //db에 저장된  총 개수
		   
		   request.setAttribute("check", "n");
		   request.setAttribute("zipcodeList", zipcodeList);
		   request.setAttribute("totalList", totalList);
		
		return "/info/zipcheck.jsp";
	}

}
