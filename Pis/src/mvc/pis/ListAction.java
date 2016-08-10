package mvc.pis;
import java.util.List;

import javax.servlet.http.*;

import controller.CommandAction;
import mvc.pis.PisDBBean;

public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		
		String search = request.getParameter("search");
		int searchn = 0;
		if(search==null){
			search = "";
		}else{
			searchn = Integer.parseInt(request.getParameter("searchn"));
		}
		
		
		int pageSize = 10; //한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage * pageSize )-pageSize+1;//한 페이지의 시작글
		int endRow = currentPage * pageSize;//한페이지의 마지막 글번호
		int count = 0;
		int number = 0;

		List articleList = null;
		PisDBBean pdd = PisDBBean.getInstance();
		if(search.equals("")||search==null){
			count = pdd.getArticleCount();//전체 글의 수
		}else{
			count = pdd.getArticleCount(searchn, search);
		}
		
		if(count > 0){
			if(search.equals("") || search ==null){
				articleList = pdd.getArticles(startRow, endRow);
			}else{
				articleList = pdd.getArticles(startRow, endRow, searchn, search);
			}
		}

	    number=count-(currentPage-1)*pageSize;
	    
	    request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
	  
		return "/pis/list.jsp";
	}
	
}
