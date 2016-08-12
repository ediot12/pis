package board;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class QuestionContentProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
			request.setCharacterEncoding("utf-8");
		
			CommentDataBean cmt = new CommentDataBean();
			CommentDBBean comt=CommentDBBean.getInstance();
			
			cmt.setComment_num(Integer.parseInt(request.getParameter("comment_num")));
			cmt.setContent_num(Integer.parseInt(request.getParameter("content_num")));
			cmt.setCommentt(request.getParameter("commentt"));
			cmt.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			comt.insertComment(cmt);
			
			String content_num=request.getParameter("content_num");
			String p_num=request.getParameter("p_num");
			
			request.setAttribute("content_num", content_num);
			request.setAttribute("p_num", p_num);
			
		return "/semi/admin/board/questionContentPro.jsp";
	}

}
