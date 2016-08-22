package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.info.*;

public class InfoContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int num = Integer.parseInt(request.getParameter("num")); //해당 글번호
		String pageNum = request.getParameter("pageNum"); //해당 페이지 번호
		
		InfoDBBean dbPro = InfoDBBean.getInstance();//DB처리
		InfoDataBean idb = dbPro.getArticle(num);//해당 글번호에 대한 해당 레코드
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("idb", idb);
		
		return "/admin/board/infoContent.jsp";
	}

}
