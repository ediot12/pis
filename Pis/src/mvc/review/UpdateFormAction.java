package mvc.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;

public class UpdateFormAction implements CommandAction{
	
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
		 	
			throws Throwable{
		int num = Integer.parseInt(request.getParameter("num"));//글번호
		String pageNum = request.getParameter("pageNum");// 페이지
		
		ReviewDBBean rdd = ReviewDBBean.getInstance();
		ReviewDataBean article = rdd.updateGetArticle(num);
		
		HttpSession session = request.getSession();
		article.getWriter();
		
		String id = (String) session.getAttribute("memId");
		String writer = article.getWriter();
		if(!id.equals(writer)){
			return "/review/mainForm.do";
		}else{
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		}
		
	
	return "/review/updateForm.jsp";
	}
}
