package mvc.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.info.*;

public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 
		int num = Integer.parseInt(request.getParameter("num")); //db저장된 글번호
		String pageNum = request.getParameter("pageNum"); // 해당 페이지 번호
		
		InfoDBBean dbPro = InfoDBBean.getInstance(); //db연동
	    InfoDataBean article =  dbPro.getArticle(num);
	    
	    //해당 뷰로 가져갈 속성 저장
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", new Integer(pageNum));	    
	    request.setAttribute("article", article);
	    
		return "/info/content.jsp";//해당 뷰 이동
		
		
	}

}
