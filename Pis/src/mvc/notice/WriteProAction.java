package mvc.notice;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.notice.PisDBBean;
import mvc.notice.PisDataBean;


public class WriteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		
		request.setCharacterEncoding("utf-8"); //한글 인코딩
		
		PisDataBean article = new PisDataBean();//데이터 처리 빈
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setRegdt(new Timestamp(System.currentTimeMillis()));
		

		PisDBBean pdd = PisDBBean.getInstance();
		pdd.insertArticle(article);
		
		request.setAttribute("pdd", pdd);
		request.setAttribute("article", article);

		return "/notice/writePro.jsp";
	}

}
