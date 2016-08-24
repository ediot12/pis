package mvc.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.report.*;

public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
		int num = Integer.parseInt(request.getParameter("num"));//�۹�ȣ
		String pageNum = request.getParameter("pageNum");//������
		
		ReportDBBean dbPro = ReportDBBean.getInstance();
		ReportDataBean article =  dbPro.getArticle(num);
	    
		//�信�� ����� �Ӽ�
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", new Integer(pageNum));	    
	    request.setAttribute("article", article);
		
		return "/report/content.jsp"; //�ش� �� �̵�
	}



}
