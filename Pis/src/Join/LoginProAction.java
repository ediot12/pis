package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.CommandAction;
import logon.LogonDBBean;
import logon.LogonDataBean;

public class LoginProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Throwable{
		
		request.setCharacterEncoding("utf-8");
		
		LogonDBBean dbPro = LogonDBBean.getInstance();
		// ��ü�� ����
		HttpSession session = request.getSession();
		// HttpSession session�� = request.getSession() ���� ���� �޾ƿö� �̰ž�
	
		String id = request.getParameter("id");
		// jsp���� ���� id���� �����ͼ� id���������� ����
		String passwd = request.getParameter("passwd");
	
		int check = dbPro.userCheck(id, passwd);
		// dbpro.����üũ�� id, passwd�� ������ ���� SQL�� ������ �� ������ x���� check�� ����
		if(check==1){
			session.setAttribute("memId",id);
			//response.sendRedirect("main.jsp");
		 } //üũ�� 1�϶� ���ǿ� "memId ��� ������ id�� ����
		LogonDataBean member = new LogonDataBean();
		member.setGrade(request.getParameter("grade"));
	
		request.setAttribute("check", new Integer(check));
	
		
		return "/Join/loginPro.jsp";
		
	}

}
