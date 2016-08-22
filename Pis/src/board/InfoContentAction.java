package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import mvc.info.*;

public class InfoContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int num = Integer.parseInt(request.getParameter("num")); //�ش� �۹�ȣ
		String pageNum = request.getParameter("pageNum"); //�ش� ������ ��ȣ
		
		InfoDBBean dbPro = InfoDBBean.getInstance();//DBó��
		InfoDataBean idb = dbPro.getArticle(num);//�ش� �۹�ȣ�� ���� �ش� ���ڵ�
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("idb", idb);
		
		return "/admin/board/infoContent.jsp";
	}

}
