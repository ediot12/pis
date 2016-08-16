package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class NoticeUpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		
		NoticeDataBean bdb = new NoticeDataBean();
		bdb.setNum(Integer.parseInt(request.getParameter("num")));
        bdb.setSubject(request.getParameter("subject"));
        bdb.setContent(request.getParameter("content"));
        
        NoticeDBBean dbPro = NoticeDBBean.getInstance();
        dbPro.updateArticle(bdb);

        request.setAttribute("pageNum", new Integer(pageNum));

        
		return "/admin/board/noticeUpdatePro.jsp";
	}

}
