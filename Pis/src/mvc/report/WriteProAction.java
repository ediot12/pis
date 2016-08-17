package mvc.report;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.report.ReportDBBean;
import mvc.report.ReportDataBean;

public class WriteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
	
		
		request.setCharacterEncoding("utf-8");
		
		ReportDataBean article = new ReportDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setType(Integer.parseInt(request.getParameter("type")));
		article.setRegdt(new Timestamp(System.currentTimeMillis()));
		
		ReportDBBean rdd = ReportDBBean.getInstance();
		rdd.insertArticle(article);
		
		request.setAttribute("rdd", rdd);
		request.setAttribute("article", article);
		
		
		return "/report/writePro.jsp";
	}

}
