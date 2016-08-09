package mvc.pis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class WriteFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		
				int num=0;
				try{
					if(request.getParameter("num")!=null){
						num=Integer.parseInt(request.getParameter("num"));
						
					}
				}catch(Exception e){e.printStackTrace();}
				
				
				request.setAttribute("num", new Integer(num));
			
				
				return "/pis/writeForm.jsp";//ÇØ´ç ºä
	}

}
