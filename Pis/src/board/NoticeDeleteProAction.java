package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class NoticeDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		dbPro.deleteArticle(num);
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("pageNum", new Integer(pageNum));
        	
		return "/semi/admin/board/FAQDeletePro.jsp";
	}

}
