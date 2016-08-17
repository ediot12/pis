package mvc.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.info.*;

public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		InfoDBBean dbPro = InfoDBBean.getInstance();
		InfoDataBean article =  dbPro.getArticle(num);
	    
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", new Integer(pageNum));	    
	    request.setAttribute("article", article);
		
		return "/info/content.jsp";
	}

}
