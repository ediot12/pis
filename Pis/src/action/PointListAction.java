package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Pay.PayDBBean;
import Pay.PointListDBBean;
import controller.CommandAction;
import mvc.notice.PisDBBean;

public class PointListAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String id = (String)request.getSession().getAttribute("memId");
				
		PointListDBBean paydb = PointListDBBean.getInstance();
		paydb.InsertList(id);
				     
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null){ pageNum = "1";}
		int pageSize = 15; //���������� ���� ����
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; //���������� ���۱� ��ȣ
		int endRow = currentPage * pageSize;//���������� ������ �۹�ȣ
		int count = 0;
		int number = 0;
		
		
		List articleList = null;
		// DB����
		
		count = paydb.getArticleCount();
		
		if(count > 0){
			
				articleList = paydb.getArticles(startRow, endRow);
			
		}

	    number=count-(currentPage-1)*pageSize;
	    
	    request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		
		return "/pointcharge/PointList.jsp";
		
	}

}