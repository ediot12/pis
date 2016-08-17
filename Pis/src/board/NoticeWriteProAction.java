package board;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class NoticeWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
				request.setCharacterEncoding("utf-8");
				
				NoticeDataBean bdb = new NoticeDataBean();//������ ó����
				bdb.setNum(Integer.parseInt(request.getParameter("num")));
				bdb.setSubject(request.getParameter("subject"));
				bdb.setRegdt(new Timestamp(System.currentTimeMillis()));
				bdb.setContent(request.getParameter("content"));
				
				NoticeDBBean dbpro = NoticeDBBean.getInstance(); //DBó��
				dbpro.insertArticle(bdb);
				
				System.out.println();
		return "/admin/board/noticewritePro.jsp";
	}

}
