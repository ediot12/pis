package mvc.pis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.pis.*;

public class UpdateProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		
		String pageNum = request.getParameter("pageNum");
		PisDataBean article = new PisDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		
		PisDBBean dbPro = PisDBBean.getInstance();
		int check = dbPro.updateArticle(article);

		request.setAttribute("check", check);
		request.setAttribute("pageNum", new Integer(pageNum));
			
			
		
		
		return "/pis/updatePro.jsp";
	}

}
