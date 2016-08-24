package mvc.notice;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.notice.PisDBBean;
import mvc.notice.PisDataBean;

public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		 
		int num = Integer.parseInt(request.getParameter("num")); // 글번호
		String pageNum = request.getParameter("pageNum"); // 페이지 번호
		
		PisDBBean dbPro = PisDBBean.getInstance(); //db연동
	    PisDataBean article =  dbPro.getArticle(num);
	    
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", new Integer(pageNum));	    
	    request.setAttribute("article", article);
	    
		return "/notice/content.jsp";
	}

}
