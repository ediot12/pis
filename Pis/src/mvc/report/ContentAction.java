package mvc.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.report.*;

public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		int num = Integer.parseInt(request.getParameter("num"));//글번호
		String pageNum = request.getParameter("pageNum");//페이지
		
		ReportDBBean dbPro = ReportDBBean.getInstance();
		ReportDataBean article =  dbPro.getArticle(num);
	    
		//뷰에서 사용할 속성
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", new Integer(pageNum));	    
	    request.setAttribute("article", article);
		
		return "/report/content.jsp"; //해당 뷰 이동
	}



}
