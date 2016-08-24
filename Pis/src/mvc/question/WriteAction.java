package mvc.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class WriteAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		 
		int num=0;
		try{
			if(request.getParameter("num")!=null){
				num=Integer.parseInt(request.getParameter("num"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("num", new Integer(num));
		
		return "/question/writeForm.jsp";
	}

}
