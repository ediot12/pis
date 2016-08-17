package board;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class FAQwriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
				request.setCharacterEncoding("utf-8");
				
				FAQDataBean fdb = new FAQDataBean();//������ ó����
				fdb.setNum(Integer.parseInt(request.getParameter("num")));
				fdb.setKind(request.getParameter("kind"));
				fdb.setSubject(request.getParameter("subject"));
				fdb.setContent(request.getParameter("content"));
				
				FAQDBBean dbpro = FAQDBBean.getInstance(); //DBó��
				dbpro.insertArticle(fdb);
				
				System.out.println();
		return "/admin/board/FAQwritePro.jsp";
	}

}
