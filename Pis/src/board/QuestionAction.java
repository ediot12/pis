package board;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class QuestionAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null){ pageNum = "1";}
		int pageSize = 20; //한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; //한페이지의 시작글 번호
		int endRow = currentPage * pageSize;//한페이지의 마지막 글번호
		int count = 0;
		int number = 0;
		
		//search기능
		String search = request.getParameter("search");
		int searchn = 0;
		if(search==null) search="";
		else searchn = Integer.parseInt(request.getParameter("searchn"));
		////////////////////////////////////////////////////////////////////
		
		QuestionDBBean dbpro = QuestionDBBean.getInstance();//DB연동		
		if(search.equals("") || search == null) count = dbpro.getArticleCount();//전체 글의 수
		else count = dbpro.getArticleCount(searchn, search);
		List articleList = dbpro.getArticles(startRow, endRow);
			
		//search기능
		if(count > 0){
		if(search.equals("") || search == null) articleList = dbpro.getArticles(startRow, endRow);
		else articleList = dbpro.getArticles(startRow, endRow, searchn, search);
		}else{
			articleList = null;
		}
		/////////////////////////////////////////////////////////
		number = count-(currentPage-1)*pageSize;

		request.setAttribute("date", date);
		request.setAttribute("search", search);
		request.setAttribute("searchn", searchn);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
	    request.setAttribute("articleList", articleList);
	   
		return "/admin/board/question.jsp";
	}

}
