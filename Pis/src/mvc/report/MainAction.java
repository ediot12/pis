package mvc.report;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.report.ReportDBBean;

public class MainAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{

		
		request.setCharacterEncoding("utf-8");
		
		SimpleDateFormat sd =
		        new SimpleDateFormat("yyyy-MM-dd");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage * pageSize)- pageSize+1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		ReportDBBean rdd = ReportDBBean.getInstance();
		count = rdd.getArticleCount();
		
		if(count > 0){
			articleList = rdd.getArticles(startRow, endRow);
		}
		
		number=count-(currentPage-1)*pageSize;
		
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("sd", sd);
		
		
		return "/report/mainForm.jsp";
	}

}
