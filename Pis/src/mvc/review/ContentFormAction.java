package mvc.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.review.*;

public class ContentFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		
		int num = Integer.parseInt(request.getParameter("num"));//글번호
		String pageNum = request.getParameter("pageNum");//페이지 번호
		
		ReviewDBBean rdd = ReviewDBBean.getInstance();
	    ReviewDataBean article =  rdd.getArticle(num);
	    
	     
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", new Integer(pageNum));	    
	    request.setAttribute("article", article);
		
	
	return "/review/content.jsp";
	}
}
