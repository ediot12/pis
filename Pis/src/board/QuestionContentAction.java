package board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class QuestionContentAction implements CommandAction {//�۳��� ó��
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		int num = Integer.parseInt(request.getParameter("num")); //�ش� �۹�ȣ
		String pageNum = request.getParameter("pageNum"); //�ش� ������ ��ȣ
		
		QuestionDBBean dbPro = QuestionDBBean.getInstance();//DBó��
		QuestionDataBean qdb = dbPro.getArticle(num);//�ش� �۹�ȣ�� ���� �ش� ���ڵ�
		
		CommentDBBean cdb = CommentDBBean.getInstance();
		ArrayList comments = cdb.getComments(qdb.getNum());
		CommentDataBean dbc = new CommentDataBean();
		try{
		

		int count = cdb.getCommentCount(qdb.getNum());
		
		for(int i=0; i<comments.size(); i++){
			dbc = (CommentDataBean)comments.get(i);
			
			request.setAttribute("comments", comments);
		}
		
		//�ش� �信�� ����� �Ӽ�
		
		
		
		request.setAttribute("dbc", dbc);
		request.setAttribute("count", count);
		request.setAttribute("date", date);
		request.setAttribute("commentcount", comments.size());
		}catch(Exception e){}
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("qdb", qdb);
		
		return "/semi/admin/board/questionContent.jsp";//�ش� ��
	}

}
