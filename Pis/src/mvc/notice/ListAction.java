package mvc.notice;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.*;

import controller.CommandAction;
import mvc.notice.PisDBBean;
 
public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
	
		// 등록 날짜 년 월 일~
		SimpleDateFormat sd =
		        new SimpleDateFormat("yyyy-MM-dd");
		
		//페이지 번호
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		//search기능
		String search = request.getParameter("search");
		int searchn = 0;
		if(search==null){
			search = "";
		}else{
			searchn = Integer.parseInt(request.getParameter("searchn"));
		}
		
		
		int pageSize = 10; //한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);//페이지 번호
		int startRow = (currentPage -1)* pageSize+1; // 한페이지 시작 글번호
		int endRow = currentPage * pageSize; // 한페이지 마지막 글번호
		int count = 0;
		int number = 0;

		List articleList = null;
		PisDBBean pdd = PisDBBean.getInstance();
		if(search.equals("")||search==null){
			count = pdd.getArticleCount();//전체 글의 수
		}else{
			count = pdd.getArticleCount(searchn, search);
		}
		//search기능
		if(count > 0){
			if(search.equals("") || search ==null){
				articleList = pdd.getArticles(startRow, endRow);
			}else{
				articleList = pdd.getArticles(startRow, endRow, searchn, search);
			}
		}
		
		// 해당 글 번호~
	    number=count-(currentPage-1)*pageSize;
	    
	    request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("sd", sd);
	  
		return "/notice/list.jsp";
	}
	
}
