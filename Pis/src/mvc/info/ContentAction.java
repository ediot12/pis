package mvc.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.info.*;

public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 
		int num = Integer.parseInt(request.getParameter("num")); //db����� �۹�ȣ
		String pageNum = request.getParameter("pageNum"); // �ش� ������ ��ȣ
		
		InfoDBBean dbPro = InfoDBBean.getInstance(); //db����
	    InfoDataBean article =  dbPro.getArticle(num);
	    
	    //�ش� ��� ������ �Ӽ� ����
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", new Integer(pageNum));	    
	    request.setAttribute("article", article);
	    
		return "/info/content.jsp";//�ش� �� �̵�
		
		
	}

}
