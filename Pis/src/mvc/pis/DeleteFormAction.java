package mvc.pis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class DeleteFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		PisDBBean pdd = PisDBBean.getInstance();
		int check = pdd.deleteArticle(num);
		  
		request.setAttribute("check", new Integer(check));
		request.setAttribute("pageNum", new Integer(pageNum));
		
		return "/pis/deleteForm.jsp";
	}

}
