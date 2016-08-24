package mvc.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.review.*;

public class UpdateFormAction implements CommandAction{
	
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
		 	throws Throwable{
		
		int num = Integer.parseInt(request.getParameter("num"));//글번호
		String pageNum = request.getParameter("pageNum");// 페이지
		 
		ReviewDBBean rdd = ReviewDBBean.getInstance();
		ReviewDataBean article = rdd.updateGetArticle(num);
		
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
	
	return "/review/updateForm.jsp";
	}
}
