package board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class QuestionContentAction implements CommandAction {//글내용 처리
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		int num = Integer.parseInt(request.getParameter("num")); //해당 글번호
		String pageNum = request.getParameter("pageNum"); //해당 페이지 번호
		
		QuestionDBBean dbPro = QuestionDBBean.getInstance();//DB처리
		QuestionDataBean qdb = dbPro.getArticle(num);//해당 글번호에 대한 해당 레코드
		
		CommentDBBean cdb = CommentDBBean.getInstance();
		ArrayList comments = cdb.getComments(qdb.getNum());
		CommentDataBean dbc = new CommentDataBean();
		try{
		

		int count = cdb.getCommentCount(qdb.getNum());
		
		for(int i=0; i<comments.size(); i++){
			dbc = (CommentDataBean)comments.get(i);
			
			request.setAttribute("comments", comments);
		}
		
		//해당 뷰에서 사용할 속성
		
		
		
		request.setAttribute("dbc", dbc);
		request.setAttribute("count", count);
		request.setAttribute("date", date);
		request.setAttribute("commentcount", comments.size());
		}catch(Exception e){}
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("qdb", qdb);
		
		return "/semi/admin/board/questionContent.jsp";//해당 뷰
	}

}
