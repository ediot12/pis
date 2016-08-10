package mvc.pis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.pis.PisDataBean;
import mvc.pis.PisDBBean;

public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		PisDBBean dbPro = PisDBBean.getInstance();
	    PisDataBean article =  dbPro.getArticle(num);
	    
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", new Integer(pageNum));	    
	    request.setAttribute("article", article);
	    
		return "/pis/content.jsp";
	}

}
