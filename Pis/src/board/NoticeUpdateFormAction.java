package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class NoticeUpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
        NoticeDataBean bdb =  dbPro.updateGetArticle(num);

        //�ش� �信�� ����� �Ӽ�
        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("bdb", bdb);
        
		return "/semi/admin/board/noticeUpdateForm.jsp";
	}

}
