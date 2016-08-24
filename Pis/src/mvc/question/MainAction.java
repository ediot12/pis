package mvc.question;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.QuestionDBBean;
import controller.CommandAction;

public class MainAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		request.setCharacterEncoding("utf-8");
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		 
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null){ pageNum = "1";}
		int pageSize = 20; //한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage -1)* pageSize+1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		//세션에 저장된 아이디 
		String writer=(String)request.getSession().getAttribute("memId");
		
		
		QuestionDBBean dbpro = QuestionDBBean.getInstance();//DB연동		
		count = dbpro.getArticleCount();//전체 글의 수
		List articleList = null;
		if (count > 0) {
			articleList = dbpro.getArticles(startRow, endRow, writer);
		}
		
		number = count-(currentPage-1)*pageSize;

		request.setAttribute("date", date);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
	    request.setAttribute("articleList", articleList);
		
		return "/question/mainForm.jsp";
	}

}
