package mvc.review;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class MainFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		// 년월일 날짜
		SimpleDateFormat sd =
		        new SimpleDateFormat("yyyy-MM-dd");
		//페이지 번호
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		} 
		
		int pageSize = 10; // 한페이지 총 개수
		int currentPage = Integer.parseInt(pageNum); //1
		int startRow = (currentPage -1)* pageSize+1; // 한페이지 시작글
		int endRow = currentPage * pageSize;// 한페이지 마지막 글
		int count = 0;
		int number = 0;
		//세션에 저장된 id
		String writer=(String)request.getSession().getAttribute("memId");
		
		List articleList = null;
		ReviewDBBean rdd = ReviewDBBean.getInstance();
		count = rdd.getArticleCount();
		
		if(count > 0){
			articleList = rdd.getArticles(startRow, endRow);
		}
		//해당 글 번호
		number=count-(currentPage-1)*pageSize;
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("sd", sd);
		
	
	return "/review/mainForm.jsp"; // 해당 뷰 이동
	}
}
