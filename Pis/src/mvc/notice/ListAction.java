package mvc.notice;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.*;

import controller.CommandAction;
import mvc.notice.PisDBBean;
 
public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
	
		// ��� ��¥ �� �� ��~
		SimpleDateFormat sd =
		        new SimpleDateFormat("yyyy-MM-dd");
		
		//������ ��ȣ
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		//search���
		String search = request.getParameter("search");
		int searchn = 0;
		if(search==null){
			search = "";
		}else{
			searchn = Integer.parseInt(request.getParameter("searchn"));
		}
		
		
		int pageSize = 10; //���������� ���� ����
		int currentPage = Integer.parseInt(pageNum);//������ ��ȣ
		int startRow = (currentPage -1)* pageSize+1; // �������� ���� �۹�ȣ
		int endRow = currentPage * pageSize; // �������� ������ �۹�ȣ
		int count = 0;
		int number = 0;

		List articleList = null;
		PisDBBean pdd = PisDBBean.getInstance();
		if(search.equals("")||search==null){
			count = pdd.getArticleCount();//��ü ���� ��
		}else{
			count = pdd.getArticleCount(searchn, search);
		}
		//search���
		if(count > 0){
			if(search.equals("") || search ==null){
				articleList = pdd.getArticles(startRow, endRow);
			}else{
				articleList = pdd.getArticles(startRow, endRow, searchn, search);
			}
		}
		
		// �ش� �� ��ȣ~
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
