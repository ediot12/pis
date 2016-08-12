package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class NoticeContentAction implements CommandAction {//글내용 처리
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{

		int num = Integer.parseInt(request.getParameter("num")); //해당 글번호
		String pageNum = request.getParameter("pageNum"); //해당 페이지 번호
		
		NoticeDBBean dbPro = NoticeDBBean.getInstance();//DB처리
		NoticeDataBean bdb = dbPro.getArticle(num);//해당 글번호에 대한 해당 레코드
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("bdb", bdb);
		
		return "/semi/admin/board/noticeContent.jsp";//해당 뷰
	}

}
