package mvc.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.review.*;

public class DeleteFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
	
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		ReviewDBBean pdd = ReviewDBBean.getInstance();
		int check = pdd.deleteArticle(num);
		  
		request.setAttribute("check", new Integer(check));
		request.setAttribute("pageNum", new Integer(pageNum));
		
	
	return "/review/deleteForm.jsp";
	}
}
