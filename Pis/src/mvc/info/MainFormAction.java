package mvc.info;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.info.*;

public class MainFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");//한글 처리 인코딩
		
		//날짜 년 월 일 
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
		 
		//페이지 번호 
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 10; // 한페이지 글의 개수
		int currentPage = Integer.parseInt(pageNum); // 페이지번호 1
		int startRow = (currentPage -1)* pageSize+1; //한페이지 시작 글번호
		int endRow = currentPage * pageSize; // 한페이지의 마지막 글번호
		int count = 0;
		int number = 0;
		//세션에 저장된 id 값 
		String writer=(String)request.getSession().getAttribute("memId");

		List articleList = null;
		InfoDBBean rdd = InfoDBBean.getInstance();//db연동
		count = rdd.getArticleCount(writer); // 메서드 이동
		
		if (count > 0) {
			//해당 아이디로 작성한 글만 보기
			articleList = rdd.getArticles(startRow, endRow, writer);
		}
		//해당 글번호
		number = count - (currentPage - 1) * pageSize;
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("sd", sd);
		
		return "/info/mainForm.jsp";// 해당 뷰 이동
	}

}
