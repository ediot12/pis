package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class NoticeContentAction implements CommandAction {//�۳��� ó��
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{

		int num = Integer.parseInt(request.getParameter("num")); //�ش� �۹�ȣ
		String pageNum = request.getParameter("pageNum"); //�ش� ������ ��ȣ
		
		NoticeDBBean dbPro = NoticeDBBean.getInstance();//DBó��
		NoticeDataBean bdb = dbPro.getArticle(num);//�ش� �۹�ȣ�� ���� �ش� ���ڵ�
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("bdb", bdb);
		
		return "/semi/admin/board/noticeContent.jsp";//�ش� ��
	}

}
