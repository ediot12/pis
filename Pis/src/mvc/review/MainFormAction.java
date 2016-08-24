package mvc.review;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class MainFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		// ����� ��¥
		SimpleDateFormat sd =
		        new SimpleDateFormat("yyyy-MM-dd");
		//������ ��ȣ
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		} 
		
		int pageSize = 10; // �������� �� ����
		int currentPage = Integer.parseInt(pageNum); //1
		int startRow = (currentPage -1)* pageSize+1; // �������� ���۱�
		int endRow = currentPage * pageSize;// �������� ������ ��
		int count = 0;
		int number = 0;
		//���ǿ� ����� id
		String writer=(String)request.getSession().getAttribute("memId");
		
		List articleList = null;
		ReviewDBBean rdd = ReviewDBBean.getInstance();
		count = rdd.getArticleCount();
		
		if(count > 0){
			articleList = rdd.getArticles(startRow, endRow);
		}
		//�ش� �� ��ȣ
		number=count-(currentPage-1)*pageSize;
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("sd", sd);
		
	
	return "/review/mainForm.jsp"; // �ش� �� �̵�
	}
}
