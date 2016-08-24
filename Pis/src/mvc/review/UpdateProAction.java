package mvc.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.review.*;

public class UpdateProAction implements CommandAction{
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
	
		// 실제 데이터를 수정
		String pageNum = request.getParameter("pageNum");
		ReviewDataBean article = new ReviewDataBean();
		article.setNum(Integer.parseInt((String)request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setScore(Integer.parseInt(request.getParameter("score")));
		article.setBfile(request.getParameter("bfile"));
		
		ReviewDBBean rdd = ReviewDBBean.getInstance();
		int check = rdd.updateArticle(article);

		request.setAttribute("check", check);
		request.setAttribute("pageNum", new Integer(pageNum));
		
	
	return "/review/updatePro.jsp";
	}
}
