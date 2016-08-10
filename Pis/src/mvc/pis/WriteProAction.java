package mvc.pis;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.pis.PisDataBean;
import mvc.pis.PisDBBean;

public class WriteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		request.setCharacterEncoding("utf-8"); //�ѱ� ���ڵ�
		
		PisDataBean article = new PisDataBean();//������ ó�� ��
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setRegdt(new Timestamp(System.currentTimeMillis()));
		

		PisDBBean pdd = PisDBBean.getInstance();
		pdd.insertArticle(article);
		
		request.setAttribute("pdd", pdd);
		request.setAttribute("article", article);

		return "/pis/writePro.jsp";
	}

}
