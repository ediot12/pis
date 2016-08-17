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
				
				NoticeDataBean bdb = new NoticeDataBean();//单捞磐 贸府后
				bdb.setNum(Integer.parseInt(request.getParameter("num")));
				bdb.setSubject(request.getParameter("subject"));
				bdb.setRegdt(new Timestamp(System.currentTimeMillis()));
				bdb.setContent(request.getParameter("content"));
				
				NoticeDBBean dbpro = NoticeDBBean.getInstance(); //DB贸府
				dbpro.insertArticle(bdb);
				
				System.out.println();
		return "/admin/board/noticewritePro.jsp";
	}

}
