package mvc.question;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.QuestionDBBean;
import board.QuestionDataBean;
import controller.CommandAction;

public class WriteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		QuestionDataBean article = new QuestionDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter((String)session.getAttribute("memId"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setKind(request.getParameter("kind"));
		article.setChecked(request.getParameter("checked"));
		article.setRegdt(new Timestamp(System.currentTimeMillis()));
		
		QuestionDBBean qdbean = QuestionDBBean.getInstance();
		qdbean.insertArticle(article);
		
		return "/question/writePro.jsp";
	}

}
