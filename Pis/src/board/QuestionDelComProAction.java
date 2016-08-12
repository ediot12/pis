package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class QuestionDelComProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int content_num=Integer.parseInt(request.getParameter("content_num"));
		int comment_num=Integer.parseInt(request.getParameter("comment_num"));
		String pageNum=request.getParameter("pageNum");
		
		CommentDBBean cmtPro=CommentDBBean.getInstance();
		
		cmtPro.deleteComment(content_num, comment_num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("content_num", content_num);
		
		return "/semi/admin/board/questionDelComPro.jsp";
	}

}
