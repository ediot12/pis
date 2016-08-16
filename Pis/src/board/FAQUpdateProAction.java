package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class FAQUpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		
		FAQDataBean fdb = new FAQDataBean();
		fdb.setNum(Integer.parseInt(request.getParameter("num")));
		fdb.setKind(request.getParameter("kind"));
        fdb.setSubject(request.getParameter("subject"));
        fdb.setContent(request.getParameter("content"));
        
        FAQDBBean dbPro = FAQDBBean.getInstance();
        dbPro.updateArticle(fdb);

        request.setAttribute("pageNum", new Integer(pageNum));

        
		return "/semi/admin/board/FAQUpdatePro.jsp";
	}

}
